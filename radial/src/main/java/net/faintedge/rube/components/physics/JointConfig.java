package net.faintedge.rube.components.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;

/**
 *
 */
public abstract class JointConfig {
  public abstract JointDef createDef(Body sourceBody, Body targetBody, Vector2 targetOffset);
}
