package net.faintedge.rube.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Allows {@link Transform} to be moved.
 */
public class TransformMotion extends Component {

  /**
   * Motion, in <code>world units / sec</code>.
   */
  private final Vector2 motion;

  public TransformMotion(Vector2 motion) {
    this.motion = motion;
  }

  public TransformMotion() {
    this(new Vector2());
  }

  public Vector2 getMotion() {
    return motion;
  }
}
