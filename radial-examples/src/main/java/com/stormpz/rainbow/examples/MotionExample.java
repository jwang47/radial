package com.stormpz.rainbow.examples;

import com.artemis.Entity;
import com.artemis.World;
import com.stormpz.radial.Context;
import com.stormpz.radial.components.Render;
import com.stormpz.radial.components.Transform;
import com.stormpz.radial.components.TransformMotion;

/**
 * Uses the {@link com.stormpz.radial.components.TransformMotion} to control the motion of the {@link Transform} for an entity.
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
