package com.stormpz.radial.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.stormpz.radial.components.WheelMotion;
import com.stormpz.radial.components.physics.Joint;
import com.stormpz.radial.components.physics.Joints;
import com.stormpz.radial.components.physics.WheelJointConfig;

import java.util.Collection;

/**
 * Moves around entities with {@link WheelMotion} and {@link Joints}.
 */
public class WheelMotionSystem extends EntityProcessingSystem {

  @Wire
  private ComponentMapper<WheelMotion> wm;

  @Wire
  private ComponentMapper<Joints> jm;

  public WheelMotionSystem() {
    super(Aspect.getAspectForAll(WheelMotion.class, Joints.class));
  }

  protected void process(Entity e) {
    WheelMotion motion = wm.get(e);
    Joints joints = jm.get(e);

    if (joints != null) {
      Collection<Joint> wheelJoints = joints.getJoints(WheelJointConfig.class);
      if (wheelJoints != null) {
        for (Joint joint : wheelJoints) {
          WheelJoint wheelJoint = (WheelJoint) joint.getJoint();
          if (wheelJoint != null) {
            wheelJoint.setMaxMotorTorque(motion.getCurrentTorque());
            wheelJoint.setMotorSpeed(motion.getCurrentVelocity());
          }
        }
      }
    }
  }
}