package net.faintedge.rube.components;

import com.artemis.Component;
import com.google.common.base.Preconditions;

/**
 * Allows {@link Transform} to be moved.
 */
public class WheelMotion extends Component {

  private final float maxTorque;
  private final float idleTorque;
  private final float speed;

  private int direction;

  public WheelMotion(float maxTorque, float idleTorque, float speed) {
    this.maxTorque = maxTorque;
    this.idleTorque = idleTorque;
    this.speed = speed;
  }

  public void setDirection(int direction) {
    Preconditions.checkArgument(direction >= -1 && direction <= 1);
    this.direction = direction;
  }

  public float getMaxTorque() {
    return maxTorque;
  }

  public float getIdleTorque() {
    return idleTorque;
  }

  public float getSpeed() {
    return speed;
  }

  public float getCurrentVelocity() {
    return speed * direction;
  }

  public float getCurrentTorque() {
    if (direction == 0) {
      return idleTorque;
    } else {
      return maxTorque;
    }
  }
}
