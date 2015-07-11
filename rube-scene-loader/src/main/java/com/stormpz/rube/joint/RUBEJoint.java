package com.stormpz.rube.joint;

import com.stormpz.rube.RUBEVector;

/**
 *
 */
public class RUBEJoint {

  private final RUBEVector anchorA;
  private final RUBEVector anchorB;
  private final int bodyA;
  private final int bodyB;

  public RUBEJoint(RUBEVector anchorA, RUBEVector anchorB, int bodyA, int bodyB) {
    this.anchorA = anchorA;
    this.anchorB = anchorB;
    this.bodyA = bodyA;
    this.bodyB = bodyB;
  }

  public RUBEVector getAnchorA() {
    return anchorA;
  }

  public RUBEVector getAnchorB() {
    return anchorB;
  }

  public int getBodyA() {
    return bodyA;
  }

  public int getBodyB() {
    return bodyB;
  }
}
