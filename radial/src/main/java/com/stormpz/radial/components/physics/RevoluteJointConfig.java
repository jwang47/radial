package com.stormpz.radial.components.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.stormpz.radial.components.Anchor;

/**
 *
 */
public class RevoluteJointConfig extends JointConfig {

  // TODO: add fields corresponding to RevoluteJointDef...

  public RevoluteJointConfig() {
  }

  @Override
  public JointDef createDef(Body sourceBody, Body targetBody, final Vector2 targetOffset) {


    RevoluteJointDef jointDef = new RevoluteJointDef() {{
      enableLimit = false;
    }};

    Vector2 worldTarget = new Vector2(targetBody.getWorldCenter()).add(targetOffset);
    jointDef.initialize(sourceBody, targetBody, worldTarget);
    return jointDef;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    public RevoluteJointConfig build() {
      return new RevoluteJointConfig();
    }
  }
}
