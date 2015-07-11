package com.stormpz.radial.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.stormpz.radial.components.Physics;
import com.stormpz.radial.components.PhysicsMotion;

/**
 * Moves around entities with {@link PhysicsMotion} and {@link Physics}.
 */
public class PhysicsMotionSystem extends EntityProcessingSystem {

  @Wire
  private ComponentMapper<Physics> pm;

  @Wire
  private ComponentMapper<PhysicsMotion> pmm;

  public PhysicsMotionSystem() {
    super(Aspect.getAspectForAll(Physics.class, PhysicsMotion.class));
  }

  protected void process(Entity e) {
    PhysicsMotion motion = pmm.get(e);
    Vector2 vector = motion.getImpulse();

    if (!vector.isZero()) {
      Physics physics = pm.get(e);
      Body body = physics.getBody();
      body.applyLinearImpulse(vector, body.getWorldCenter(), true);
    }
  }
}