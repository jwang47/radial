package net.faintedge.rube.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import net.faintedge.rube.components.Transform;
import net.faintedge.rube.components.TransformMotion;

/**
 * Moves around entities with {@link TransformMotion} and {@link Transform}.
 */
public class TransformMotionSystem extends EntityProcessingSystem {

  @Wire
  private ComponentMapper<TransformMotion> mm;

  @Wire
  private ComponentMapper<Transform> tm;

  /**
   * Time per frame, in seconds.
   */
  private float tpf;

  public TransformMotionSystem() {
    super(Aspect.getAspectForAll(Transform.class, TransformMotion.class));
  }

  protected void process(Entity e) {
    TransformMotion transformMotion = mm.get(e);
    Vector2 vector = transformMotion.getMotion();

    if (!vector.isZero()) {
      Transform transform = tm.get(e);
      transform.getTranslation().add(vector.x * tpf, vector.y * tpf);
    }
  }

  public void setTpf(float tpf) {
    this.tpf = tpf;
  }
}