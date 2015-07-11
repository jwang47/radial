package com.stormpz.radial.components.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;

/**
 *
 */
public class PrismaticJointConfig extends JointConfig {

  private final Vector2 axisOfMovement;
  private final float lowerTranslation;
  private final float upperTranslation;

  public PrismaticJointConfig(Vector2 axisOfMovement, float lowerTranslation, float upperTranslation) {
    this.axisOfMovement = axisOfMovement;
    this.lowerTranslation = lowerTranslation;
    this.upperTranslation = upperTranslation;
  }

  @Override
  public JointDef createDef(Body sourceBody, Body targetBody, final Vector2 targetOffset) {
    PrismaticJointDef jointDef = new PrismaticJointDef() {{
      enableLimit = true;
      enableMotor = true;
      lowerTranslation = PrismaticJointConfig.this.lowerTranslation;
      upperTranslation = PrismaticJointConfig.this.upperTranslation;
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
    private float lowerTranslation;
    private float upperTranslation;

    public Builder setAxisOfMovement(Vector2 axisOfMovement) {
      this.axisOfMovement = axisOfMovement;
      return this;
    }

    public Builder setTranslationLimits(float lower, float upper) {
      this.lowerTranslation = lower;
      this.upperTranslation = upper;
      return this;
    }

    public PrismaticJointConfig build() {
      return new PrismaticJointConfig(axisOfMovement, lowerTranslation, upperTranslation);
    }
  }
}
