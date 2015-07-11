package com.stormpz.rube.joint;

import com.stormpz.rube.RUBEVector;

/**
 *
 */
public class RUBERevoluteJoint extends RUBEJoint {

  private final boolean enableLimit;
  private final boolean enableMotor;
  private final float jointSpeed;
  private final float lowerLimit;
  private final float upperLimit;
  private final float maxMotorTorque;
  private final float motorSpeed;
  private final String name;
  private final float refAngle;

  public RUBERevoluteJoint(RUBEVector anchorA, RUBEVector anchorB, int bodyA, int bodyB,
                           boolean enableLimit, boolean enableMotor, float jointSpeed, float lowerLimit,
                           float upperLimit, float maxMotorTorque, float motorSpeed, String name, float refAngle) {
    super(anchorA, anchorB, bodyA, bodyB);
    this.enableLimit = enableLimit;
    this.enableMotor = enableMotor;
    this.jointSpeed = jointSpeed;
    this.lowerLimit = lowerLimit;
    this.upperLimit = upperLimit;
    this.maxMotorTorque = maxMotorTorque;
    this.motorSpeed = motorSpeed;
    this.name = name;
    this.refAngle = refAngle;
  }

  public boolean isEnableLimit() {
    return enableLimit;
  }

  public boolean isEnableMotor() {
    return enableMotor;
  }

  public float getJointSpeed() {
    return jointSpeed;
  }

  public float getLowerLimit() {
    return lowerLimit;
  }

  public float getUpperLimit() {
    return upperLimit;
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

  public float getRefAngle() {
    return refAngle;
  }
}
