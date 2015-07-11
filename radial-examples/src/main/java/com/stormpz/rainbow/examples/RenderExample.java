package com.stormpz.rainbow.examples;

import com.artemis.Entity;
import com.artemis.World;
import com.stormpz.radial.Context;
import com.stormpz.radial.components.Render;
import com.stormpz.radial.components.Transform;

/**
 * Uses {@link Render} and {@link Transform} components to render entities.
 */
public class RenderExample extends BaseExample {

  @Override
  public void show() {
    super.show();

    Context context = super.getContext();
    World world = context.getWorld();

    Entity e = world.createEntity();
    e.addComponent(new Transform(0, 0));
    e.addComponent(new Render(testTextureSpec, 25, 25));
    e.addToWorld();
  }

  public static void main(String[] args) {
    run(new RenderExample());
  }

}
