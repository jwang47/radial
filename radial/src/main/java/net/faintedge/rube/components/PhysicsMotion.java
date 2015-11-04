package net.faintedge.rube.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Allows {@link Physics} to be moved.
 */
public class PhysicsMotion extends Component {

  /**
   * How much impulse to apply every physics step.
   */
  private final Vector2 impulse;

  public PhysicsMotion(Vector2 impulse) {
    this.impulse = impulse;
  }

  public PhysicsMotion() {
    this(new Vector2());
  }

  public Vector2 getImpulse() {
    return impulse;
  }
}
