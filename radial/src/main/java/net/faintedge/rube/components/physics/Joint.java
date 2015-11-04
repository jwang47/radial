package net.faintedge.rube.components.physics;

import com.artemis.Component;
import com.artemis.Entity;
import net.faintedge.rube.systems.PhysicsSystem;

/**
 *
 */
public class Joint extends Component {
  /**
   * Set by {@link PhysicsSystem}, once the joint has been materialized.
   */
  private com.badlogic.gdx.physics.box2d.Joint joint;

  private final Entity targetEntity;

  /**
   * Name of the anchor to attach to, on the target. Null means attach to center of target.
   */
  private final String targetAnchorName;

  private final JointConfig config;

  public Joint(JointConfig config, Entity targetEntity, String targetAnchorName) {
    this.config = config;
    this.targetEntity = targetEntity;
    this.targetAnchorName = targetAnchorName;
  }

  public Joint(JointConfig config, Entity targetEntity) {
    this(config, targetEntity, null);
  }

  public JointConfig getConfig() {
    return config;
  }

  public com.badlogic.gdx.physics.box2d.Joint getJoint() {
    return joint;
  }

  public void setB2dJoint(com.badlogic.gdx.physics.box2d.Joint joint) {
    this.joint = joint;
  }

  public String getTargetAnchorName() {
    return targetAnchorName;
  }

  public Entity getTargetEntity() {
    return targetEntity;
  }

  public com.badlogic.gdx.physics.box2d.Joint consumeJoint() {
    com.badlogic.gdx.physics.box2d.Joint result = joint;
    joint = null;
    return result;
  }
}
