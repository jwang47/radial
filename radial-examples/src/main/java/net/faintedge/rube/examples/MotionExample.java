package net.faintedge.rube.examples;

import com.artemis.Entity;
import com.artemis.World;
import net.faintedge.rube.Context;
import net.faintedge.rube.components.Render;
import net.faintedge.rube.components.Transform;
import net.faintedge.rube.components.TransformMotion;

/**
 * Uses the {@link TransformMotion} to control the motion of the {@link Transform} for an entity.
 */
public class MotionExample extends BaseExample {

  private Transform transform;
  private TransformMotion transformMotion;

  @Override
  public void show() {
    super.show();

    Context context = super.getContext();
    World world = context.getWorld();

    final Entity e = world.createEntity();
    e.addComponent(transform = new Transform(0, 0));
    e.addComponent(transformMotion = new TransformMotion());
    e.addComponent(new Render(testTextureSpec, 25, 25));
    e.addToWorld();

    transformMotion.getMotion().set(15, 0);
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    if (Math.abs(transform.getTranslation().x) > 5) {
      transformMotion.getMotion().scl(-1, 1);
    }
  }

  public static void main(String[] args) {
    run(new MotionExample());
  }

}
