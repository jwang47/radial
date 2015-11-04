package net.faintedge.rube.components;

import com.artemis.Component;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Transformation of an entity in 2D space.
 */
public class Transform extends Component {

  /**
   * translation, in world units.
   */
  private Vector2 translation;

  /**
   * rotation, in degrees.
   */
  private float rotation;

  public Transform(float x, float y, float rotation) {
    this.translation = new Vector2(x, y);
    this.rotation = rotation;
  }

  public Transform(float x, float y) {
    this(x, y, 0);
  }

  public Transform() {
    this(0, 0, 0);
  }

  public float getRotation() {
    return rotation;
  }

  public float getRotationRadians() {
    return rotation * MathUtils.degreesToRadians;
  }

  public void setRotation(float rotation) {
    this.rotation = rotation;
  }

  public Vector2 getTranslation() {
    return translation;
  }
}
