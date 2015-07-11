package com.stormpz.radial.components.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;

/**
 *
 */
public class WheelJointConfig extends JointConfig {

  private final Vector2 axisOfMovement;
  /** Suspension frequency, zero indicates no suspension */
  public final float suspensionFrequencyHz;
  /** Suspension damping ratio, one indicates critical damping */
  public final float suspensionDampingRatio;

  public WheelJointConfig(Vector2 axisOfMovement, float suspensionFrequencyHz, float suspensionDampingRatio) {
    this.axisOfMovement = axisOfMovement;
    this.suspensionFrequencyHz = suspensionFrequencyHz;
    this.suspensionDampingRatio = suspensionDampingRatio;
  }

  @Override
  public JointDef createDef(Body sourceBody, Body targetBody, final Vector2 targetOffset) {
    WheelJointDef jointDef = new WheelJointDef() {{
      enableMotor = true;
      frequencyHz = suspensionFrequencyHz;
      dampingRatio = suspensionDampingRatio;
    }};

    Vector2 worldTarget = new Vector2(targetBody.getWorldCenter()).add(targetOffset);
    jointDef.initialize(sourceBody, targetBody, worldTarget, axisOfMovement);
    return jointDef;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Vector2 axisOfMovement;
    /** Suspension frequency, zero indicates no suspension */
    public float suspensionFrequencyHz = 2;
    /** Suspension damping ratio, one indicates critical damping */
    public float suspensionDampingRatio = 0.7f;

    public Builder setSuspensionFrequencyHz(float suspensionFrequencyHz) {
      this.suspensionFrequencyHz = suspensionFrequencyHz;
      return this;
    }

    public Builder setSuspensionDampingRatio(float suspensionDampingRatio) {
      this.suspensionDampingRatio = suspensionDampingRatio;
      return this;
    }

    public Builder setAxisOfMovement(Vector2 axisOfMovement) {
      this.axisOfMovement = axisOfMovement;
      return this;
    }

    public WheelJointConfig build() {
      return new WheelJointConfig(axisOfMovement, suspensionFrequencyHz, suspensionDampingRatio);
    }
  }
}
