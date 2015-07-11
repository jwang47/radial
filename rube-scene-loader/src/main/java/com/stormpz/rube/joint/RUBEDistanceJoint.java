package com.stormpz.rube.joint;

import com.stormpz.rube.RUBEVector;

/**
 *
 */
public class RUBEDistanceJoint extends RUBEJoint {

  private final float dampingRatio;
  private final float frequency;
  private final float length;

  public RUBEDistanceJoint(RUBEVector anchorA, RUBEVector anchorB, int bodyA,
                           int bodyB, float dampingRatio, float frequency, float length) {
    super(anchorA, anchorB, bodyA, bodyB);
    this.dampingRatio = dampingRatio;
    this.frequency = frequency;
    this.length = length;
  }

  public float getDampingRatio() {
    return dampingRatio;
  }

  public float getFrequency() {
    return frequency;
  }

  public float getLength() {
    return length;
  }
}
