package net.faintedge.rube.components;

import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public class Anchor {

  private final String name;
  private final Vector2 offset;

  public Anchor(String name, Vector2 offset) {
    this.name = name;
    this.offset = offset;
  }

  public String getName() {
    return name;
  }

  public Vector2 getOffset() {
    return offset;
  }
}
