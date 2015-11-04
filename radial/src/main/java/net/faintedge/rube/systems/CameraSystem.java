package net.faintedge.rube.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import net.faintedge.rube.components.Camera;
import net.faintedge.rube.components.Transform;

/**
 * Updates the position of {@link Camera}s to be the position of the {@link Transform}.
 */
public class CameraSystem extends EntityProcessingSystem {
  @Wire
  private ComponentMapper<Transform> pm;
  @Wire
  private ComponentMapper<Camera> cm;

  private final RenderingContext context;
  private Vector3 dest = new Vector3();

  public CameraSystem(SpriteBatch spriteBatch) {
    super(Aspect.getAspectForAll(Transform.class, Camera.class));
    this.context = new RenderingContext(spriteBatch);
  }

  protected void process(Entity e) {
    Transform transform = pm.get(e);
    Camera camera = cm.get(e);

    dest.set(transform.getTranslation(), 0).scl(-1);
    camera.getCamera().position.lerp(dest, 0.95f);
  }

  public RenderingContext getContext() {
    return context;
  }
}