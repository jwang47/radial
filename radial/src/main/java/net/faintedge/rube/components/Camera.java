package net.faintedge.rube.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends Component {

  private final OrthographicCamera camera;

  public Camera(OrthographicCamera camera) {
    this.camera = camera;
  }

  public OrthographicCamera getCamera() {
    return camera;
  }

}
