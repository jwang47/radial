package net.faintedge.rube.joint;

import net.faintedge.rube.RUBEVector;

/**
 *
 */
public class RUBEWheelJoint extends RUBEJoint {

  private final boolean enableMotor;
  private final RUBEVector localAxisA;
  private final float maxMotorTorque;
  private final float motorSpeed;
  private final String name;
  private final float springDampingRatio;
  private final float springFrequency;

  public RUBEWheelJoint(RUBEVector anchorA, RUBEVector anchorB, int bodyA, int bodyB,
                        boolean enableMotor, RUBEVector localAxisA, float maxMotorTorque,
                        float motorSpeed, String name, float springDampingRatio, float springFrequency) {
    super(anchorA, anchorB, bodyA, bodyB);
    this.enableMotor = enableMotor;
    this.localAxisA = localAxisA;
    this.maxMotorTorque = maxMotorTorque;
    this.motorSpeed = motorSpeed;
    this.name = name;
    this.springDampingRatio = springDampingRatio;
    this.springFrequency = springFrequency;
  }

  public boolean isEnableMotor() {
    return enableMotor;
  }

  public RUBEVector getLocalAxisA() {
    return localAxisA;
  }

  public float getMaxMotorTorque() {
    return maxMotorTorque;
  }

  public float getMotorSpeed() {
    return motorSpeed;
  }

  public String getName() {
    return name;
  }

  public float getSpringDampingRatio() {
    return springDampingRatio;
  }

  public float getSpringFrequency() {
    return springFrequency;
  }
}
