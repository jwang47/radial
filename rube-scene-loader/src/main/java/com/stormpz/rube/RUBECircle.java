package com.stormpz.rube;

/**
 *
 */
public class RUBECircle {
  private final RUBEVector center;
  private final float radius;

  public RUBECircle(RUBEVector center, float radius) {
    this.center = center;
    this.radius = radius;
  }

  public RUBEVector getCenter() {
    return center;
  }

  public float getRadius() {
    return radius;
  }
}
