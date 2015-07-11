package com.stormpz.rainbow.examples;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.stormpz.radial.Context;
import com.stormpz.radial.components.Physics;
import com.stormpz.radial.components.Render;
import com.stormpz.radial.components.Transform;
import com.stormpz.radial.components.physics.Colliders;
import com.stormpz.radial.components.physics.RectangleCollider;

/**
 * Uses {@link Transform}, {@link Render} and {@link com.stormpz.radial.components.Physics} components
 * to create physics behavior with graphical representations of the entities.
 *
 * <p>
 *   Controls:<br/>
 *   D to toggle on/off physics debug rendering
 * </p>
 */
public class PhysicsRenderExample extends BaseExample {

  @Override
  public void show() {
    super.show();

    Context context = super.getContext();
    context.getInputMultiplexer().addProcessor(new InputAdapter() {
      @Override
      public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.D) {
          debugMode = !debugMode;
        }
        return true;
      }
    });
    World world = context.getWorld();
    populate(world);
  }

  public void populate(World world) {
    Entity e;

    int cols = 5;
    float width = 1;
    float height = 1;
    float padding = width * 5;
    float x = -(cols * width) / 2 + width/2;
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < 5; j++) {
        e = world.createEntity();
        e.addComponent(new Transform(x + i * width, j * (width + padding)));
        e.addComponent(new Physics());
        e.addComponent(new Colliders(new RectangleCollider(width, height)));
        e.addComponent(new Render(crateTextureSpec, width, height));
        e.addToWorld();
      }
    }

    e = world.createEntity();
    e.addComponent(new Transform(0, -5));
    e.addComponent(new Physics(Physics.Type.STATIC));
    e.addComponent(new Colliders(new RectangleCollider(40, 1)));
    e.addComponent(new Render(crateTextureSpec, 40, 1));
    e.addToWorld();
  }

  public static void main(String[] args) {
    run(new PhysicsRenderExample());
  }

}
