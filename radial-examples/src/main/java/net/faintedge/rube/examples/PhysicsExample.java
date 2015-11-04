package net.faintedge.rube.examples;

import com.artemis.Entity;
import com.artemis.World;
import net.faintedge.rube.Context;
import net.faintedge.rube.components.Physics;
import net.faintedge.rube.components.Transform;
import net.faintedge.rube.components.physics.Colliders;
import net.faintedge.rube.components.physics.RectangleCollider;

/**
 * Uses {@link Transform} and {@link Physics} components to create physics behavior.
 */
public class PhysicsExample extends BaseExample {

  @Override
  public void show() {
    super.show();

    Context context = super.getContext();
    World world = context.getWorld();
    populate(world);

    debugMode = true;
  }

  public void populate(World world) {
    Entity e;

    int cols = 5;
    float width = 1f;
    float height = 1f;
    float padding = width * 5f;
    float x = -(cols * width) / 2 + width/2;
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < 5; j++) {
        e = world.createEntity();
        e.addComponent(new Transform(x + i * width, j * (width + padding)));
        e.addComponent(new Physics());
        e.addComponent(new Colliders(new RectangleCollider(width, height)));
        e.addToWorld();
      }
    }

    e = world.createEntity();
    e.addComponent(new Transform(0, -5f));
    e.addComponent(new Physics(Physics.Type.STATIC));
    e.addComponent(new Colliders(new RectangleCollider(40f, 1f)));
    e.addToWorld();
  }

  public static void main(String[] args) {
    run(new PhysicsExample());
  }

}
