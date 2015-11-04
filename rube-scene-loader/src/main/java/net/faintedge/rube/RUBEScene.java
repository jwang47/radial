package net.faintedge.rube;

import com.google.gson.annotations.SerializedName;
import net.faintedge.rube.joint.RUBEJoint;

import java.util.List;

/**
 *
 */
public class RUBEScene {

  private final int positionIterations;
  private final int velocityIterations;

  private final List<RUBEBody> body;
  private final List<RUBEJoint> joint;
  private final RUBEVector gravity;

  @SerializedName("collisionbitplanes")
  private final RUBECollisionBitPlanes collisionBitPlanes;

  private final boolean allowSleep;
  private final boolean autoClearForces;
  private final float stepsPerSecond;
  private final boolean subStepping;
  private final boolean continuousPhysics;
  private final boolean warmStarting;

  private RUBEScene(int positionIterations, int velocityIterations, List<RUBEBody> body, List<RUBEJoint> joint,
                    RUBEVector gravity, RUBECollisionBitPlanes collisionBitPlanes, boolean allowSleep,
                    boolean autoClearForces, float stepsPerSecond, boolean subStepping,
                    boolean continuousPhysics, boolean warmStarting) {
    this.positionIterations = positionIterations;
    this.velocityIterations = velocityIterations;
    this.body = body;
    this.joint = joint;
    this.gravity = gravity;
    this.collisionBitPlanes = collisionBitPlanes;
    this.allowSleep = allowSleep;
    this.autoClearForces = autoClearForces;
    this.stepsPerSecond = stepsPerSecond;
    this.subStepping = subStepping;
    this.continuousPhysics = continuousPhysics;
    this.warmStarting = warmStarting;
  }

  public int getPositionIterations() {
    return positionIterations;
  }

  public int getVelocityIterations() {
    return velocityIterations;
  }

  public List<RUBEBody> getBody() {
    return body;
  }

  public List<RUBEJoint> getJoint() {
    return joint;
  }

  public RUBEVector getGravity() {
    return gravity;
  }

  public RUBECollisionBitPlanes getCollisionBitPlanes() {
    return collisionBitPlanes;
  }

  public boolean isAllowSleep() {
    return allowSleep;
  }

  public boolean isAutoClearForces() {
    return autoClearForces;
  }

  public float getStepsPerSecond() {
    return stepsPerSecond;
  }

  public boolean isSubStepping() {
    return subStepping;
  }

  public boolean isContinuousPhysics() {
    return continuousPhysics;
  }

  public boolean isWarmStarting() {
    return warmStarting;
  }
}
