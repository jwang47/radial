package com.stormpz.rube;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */
public class RUBEBody {
  private final float angle;
  private final float angularVelocity;
  private final boolean awake;
  private final List<RUBEFixture> fixture;
  private final RUBEVector linearVelocity;

  @SerializedName("massData-I")
  private final float massDataI;

  @SerializedName("massData-center")
  private final RUBEVector massDataCenter;

  @SerializedName("massData-mass")
  private final float massDataMass;

  private final String name;
  private final RUBEVector position;

  private final int type;

  private RUBEBody(float angle, float angularVelocity, boolean awake, List<RUBEFixture> fixture,
                   RUBEVector linearVelocity, float massDataI, RUBEVector massDataCenter,
                   float massDataMass, String name, RUBEVector position, int type) {
    this.angle = angle;
    this.angularVelocity = angularVelocity;
    this.awake = awake;
    this.fixture = fixture;
    this.linearVelocity = linearVelocity;
    this.massDataI = massDataI;
    this.massDataCenter = massDataCenter;
    this.massDataMass = massDataMass;
    this.name = name;
    this.position = position;
    this.type = type;
  }

  public float getAngle() {
    return angle;
  }

  public float getAngularVelocity() {
    return angularVelocity;
  }

  public boolean isAwake() {
    return awake;
  }

  public List<RUBEFixture> getFixture() {
    return fixture;
  }

  public RUBEVector getLinearVelocity() {
    return linearVelocity;
  }

  public float getMassDataI() {
    return massDataI;
  }

  public RUBEVector getMassDataCenter() {
    return massDataCenter;
  }

  public float getMassDataMass() {
    return massDataMass;
  }

  public String getName() {
    return name;
  }

  public RUBEVector getPosition() {
    return position;
  }

  public int getType() {
    return type;
  }
}
