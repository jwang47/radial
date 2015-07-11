package com.stormpz.radial.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.stormpz.radial.Config;
import com.stormpz.radial.components.Anchor;
import com.stormpz.radial.components.Anchors;
import com.stormpz.radial.components.Physics;
import com.stormpz.radial.components.Transform;
import com.stormpz.radial.components.physics.Collider;
import com.stormpz.radial.components.physics.Colliders;
import com.stormpz.radial.components.physics.Joint;
import com.stormpz.radial.components.physics.Joints;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Manages the lifecycle of {@link com.stormpz.radial.components.Physics}s, updates the {@link #physicsWorld},
 * and binds the position of the {@link com.stormpz.radial.components.Physics}s to their corresponding {@link Transform}s.
 */
public class PhysicsSystem extends EntityProcessingSystem {

  @Wire
  private ComponentMapper<Transform> tm;

  @Wire
  private ComponentMapper<Physics> pm;

  @Wire
  private ComponentMapper<Joints> jm;

  @Wire
  private ComponentMapper<Anchors> am;

  @Wire
  private ComponentMapper<Colliders> cm;

  private final World physicsWorld;

  private Box2DDebugRenderer debugRenderer;

  private final ConcurrentLinkedDeque<Entity> toMaterialize = new ConcurrentLinkedDeque<Entity>();
  private final ConcurrentLinkedDeque<Entity> toDematerialize = new ConcurrentLinkedDeque<Entity>();
  private final ReentrantLock materializeLock = new ReentrantLock(true);

  private final float unitsPerMeter;
  private final float metersPerUnit;

  private Matrix4 debugRenderMatrix = new Matrix4();

  /**
   * Time per frame, in seconds.
   */
  private float tpf;
  private float accumulator;

  private final float timeScale = 1.0f;
  private final float timeStep = 1 / (60f * timeScale);

  public PhysicsSystem(Config config) {
    super(Aspect.getAspectForAll(Physics.class, Transform.class));
    this.unitsPerMeter = config.getUnitsPerMeter();
    this.metersPerUnit = config.getMetersPerUnit();
    this.physicsWorld = new World(new Vector2(0, -10), true);
    this.debugRenderer = new Box2DDebugRenderer();
  }

  @Override
  protected void dispose() {
    physicsWorld.dispose();
  }

  @Override
  protected void inserted(Entity e) {
    addBody(e);
  }

  @Override
  protected void removed(Entity e) {
    removeBody(e);
  }

  @Override
  protected void process(Entity e) {
    Transform transform = tm.get(e);
    Physics physics = pm.get(e);

    if (physics != null) {
      Body body = physics.getBody();
      Vector2 bodyPosition = body.getPosition();

      if (transform != null) {
        transform.getTranslation().set(bodyPosition).scl(unitsPerMeter);
        transform.setRotation(MathUtils.radiansToDegrees * body.getAngle());
      }
    }
  }

  public void step() {
    // fixed time step
    // max frame time to avoid spiral of death (on slow devices)
    float frameTime = Math.min(tpf, 0.25f);
    accumulator += frameTime;
    while (accumulator >= timeStep) {
      physicsWorld.step(timeStep, 6, 2);
      accumulator -= timeStep;
    }

    Entity e;
    while ((e = toMaterialize.pollFirst()) != null) {
      materialize(e);
    }
    while ((e = toDematerialize.pollFirst()) != null) {
      dematerialize(e);
    }
  }

  public void debugRender(Matrix4 camera) {
    debugRenderMatrix.set(camera);
    debugRenderMatrix.scale(unitsPerMeter, unitsPerMeter, 0);
    debugRenderer.render(physicsWorld, debugRenderMatrix);
  }

  /**
   * @return pixels per meters
   */
  public float getPPM() {
    return unitsPerMeter;
  }

  private void materialize(Entity entity) {
    materializeLock.lock();
    try {
      Physics physics = pm.get(entity);
      Transform transform = tm.get(entity);
      Colliders collider = cm.getSafe(entity);

      // create body
      BodyDef bodyDef = physics.createBodyDef(transform, metersPerUnit);
      Body body = physicsWorld.createBody(bodyDef);
      physics.setBody(body);

      // create fixtures
      if (collider != null) {
        for (Collider c : collider.getColliders()) {
          FixtureDef fixtureDef = c.createFixtureDef(metersPerUnit);
          Fixture fixture = body.createFixture(fixtureDef);
          fixtureDef.shape.dispose();
          c.setFixture(fixture);
        }
      }

      // create joints
      Joints joint = jm.get(entity);
      if (joint != null) {
        for (Joint j : joint.getJoints()) {
          Physics targetPhysics = pm.getSafe(j.getTargetEntity());
          Anchors targetAnchors = am.getSafe(j.getTargetEntity());
          if (targetPhysics == null || (targetAnchors == null && j.getTargetAnchorName() != null)) {
            throw new IllegalStateException();
          }
          Anchor targetAnchor = j.getTargetAnchorName() == null ?
            null : targetAnchors.getAnchor(j.getTargetAnchorName());
          materializeJoint(body, j, targetPhysics.getBody(), targetAnchor);
        }
      }
    } finally {
      materializeLock.unlock();
    }
  }

  private void materializeJoint(Body sourceBody, Joint joint, Body targetBody, Anchor targetAnchor) {
    Vector2 targetOffset = targetAnchor == null ? Vector2.Zero : targetAnchor.getOffset();
    com.badlogic.gdx.physics.box2d.Joint b2dJoint = physicsWorld.createJoint(
      joint.getConfig().createDef(sourceBody, targetBody, targetOffset));
    joint.setB2dJoint(b2dJoint);
  }

  private void dematerialize(Entity entity) {
    materializeLock.lock();
    try {
      Physics body = pm.get(entity);
      if (body != null) {
        // TODO: don't need to destroy joints?
        // destroy joints
//        Joints entityJoints = joints.get(entityId);
//        if (entityJoints != null) {
//          for (Joint joint : entityJoints.getJoints()) {
//            if (joint.getJoint() != null) {
//              physicsWorld.destroyJoint(joint.consumeJoint());
//            }
//          }
//        }
        entity.removeComponent(Physics.class);

        // destroy body
        physicsWorld.destroyBody(body.consumeBody());
      }
    } finally {
      materializeLock.unlock();
    }
  }

  private void addBody(Entity entity) {
    materializeLock.lock();
    try {
      toMaterialize.add(entity);
    } finally {
      materializeLock.unlock();
    }
  }

  private void removeBody(Entity entity) {
    materializeLock.lock();
    try {
      toDematerialize.add(entity);
    } finally {
      materializeLock.unlock();
    }
  }

  public void setTPF(float tpf) {
    this.tpf = tpf;
  }
}
