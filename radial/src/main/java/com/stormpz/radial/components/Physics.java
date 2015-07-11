package com.stormpz.radial.components;

import com.artemis.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.google.common.base.Preconditions;

/**
 * Represents a Box2D {@link Body} with a {@link Fixture}.
 *
 * TODO: Rename to Rigidbody
 */
public class Physics extends Component {

  public enum Type {
    STATIC(BodyDef.BodyType.StaticBody),
    KINEMATIC(BodyDef.BodyType.KinematicBody),
    DYNAMIC(BodyDef.BodyType.DynamicBody);

    private final BodyDef.BodyType bodyType;

    Type(BodyDef.BodyType bodyType) {
      this.bodyType = bodyType;
    }

    public BodyDef.BodyType getBodyType() {
      return bodyType;
    }
  }

  private final Type bodyType;

  /**
   * Linear damping is use to reduce the linear velocity. The damping parameter can be larger than 1.0f but the damping effect
   * becomes sensitive to the time step when the damping parameter is large.
   * **/
  private final float linearDamping;

  /**
   * Angular damping is use to reduce the angular velocity.
   * The damping parameter can be larger than 1.0f but the damping effect
   * becomes sensitive to the time step when the damping parameter is large.
   **/
  private final float angularDamping;

  /** Should this body be prevented from rotating? Useful for characters. **/
  public final boolean fixedRotation;

  // TODO: Maybe should be moved outside this, since it's not strictly config..
  private Body body;

  public Physics(Type bodyType, float linearDamping, float angularDamping, boolean fixedRotation) {
    this.bodyType = bodyType;
    this.linearDamping = linearDamping;
    this.angularDamping = angularDamping;
    this.fixedRotation = fixedRotation;
  }

  public Physics(Type bodyType) {
    this(bodyType, 0, 0, false);
  }

  public Physics() {
    this(Type.DYNAMIC, 0, 0, false);
  }

  public Body getBody() {
    if (body == null) {
      throw new IllegalStateException();
    }
    return body;
  }

  public Body consumeBody() {
    Preconditions.checkState(body != null);
    Body result = body;
    this.body = null;
    return result;
  }

  public void setBody(Body body) {
    Preconditions.checkState(this.body == null);
    this.body = body;
  }

  public Type getBodyType() {
    return bodyType;
  }

  public float getLinearDamping() {
    return linearDamping;
  }

  public float getAngularDamping() {
    return angularDamping;
  }

  public BodyDef createBodyDef(Transform initialTransform, float metersPerUnit) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.angle = initialTransform.getRotationRadians();
    bodyDef.position.set(initialTransform.getTranslation()).scl(metersPerUnit);
    bodyDef.type = bodyType.getBodyType();
    bodyDef.linearDamping = linearDamping;
    bodyDef.angularDamping = angularDamping;
    bodyDef.fixedRotation = fixedRotation;
    return bodyDef;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Physics.Type type = Type.DYNAMIC;
    private float linearDamping;
    private float angularDamping;
    private boolean fixedRotation;

    public Builder setType(Physics.Type type) {
      this.type = type;
      return this;
    }

    public Builder setLinearDamping(float linearDamping) {
      this.linearDamping = linearDamping;
      return this;
    }

    public Builder setAngularDamping(float angularDamping) {
      this.angularDamping = angularDamping;
      return this;
    }

    public Builder setFixedRotation(boolean fixedRotation) {
      this.fixedRotation = fixedRotation;
      return this;
    }

    public Physics build() {
      return new Physics(type, linearDamping, angularDamping, fixedRotation);
    }
  }
}
