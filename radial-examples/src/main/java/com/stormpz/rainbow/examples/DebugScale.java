package com.stormpz.rainbow.examples;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.stormpz.radial.Context;
import com.stormpz.radial.components.Physics;
import com.stormpz.radial.components.Transform;
import com.stormpz.radial.components.physics.Colliders;
import com.stormpz.radial.components.physics.PolygonCollider;
import com.stormpz.radial.components.physics.RectangleCollider;

/**
 * Uses {@link com.stormpz.radial.components.Transform} and {@link com.stormpz.radial.components.Physics} components to create physics behavior.
 */
public class DebugScale extends BaseExample {

  private Vector2 inputVector = new Vector2();
  private final float movespeed = 15.5f;

  @Override
  public void render(float delta) {
    super.render(delta);

    getContext().getCameraMotion().getMotion().set(inputVector).scl(-movespeed);
  }

  @Override
  public void show() {
    super.show();

    Context context = super.getContext();
    context.getInputMultiplexer().addProcessor(new InputAdapter() {
      @Override
      public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
          inputVector.y += (movespeed);
        } else if (keycode == Input.Keys.S) {
          inputVector.y += (-movespeed);
        } else if (keycode == Input.Keys.A) {
          inputVector.x += (-movespeed);
        } else if (keycode == Input.Keys.D) {
          inputVector.x += (movespeed);
        }
        return true;
      }

      @Override
      public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) {
          inputVector.y -= (movespeed);
        } else if (keycode == Input.Keys.S) {
          inputVector.y -= (-movespeed);
        } else if (keycode == Input.Keys.A) {
          inputVector.x -= (-movespeed);
        } else if (keycode == Input.Keys.D) {
          inputVector.x -= (movespeed);
        }
        return true;
      }
    });
    World world = context.getWorld();
    populate(world);

    debugMode = true;
  }

  public void populate(World world) {
    Entity e;

    e = world.createEntity();
    e.addComponent(new Transform(10.0f, 10.0f));
    e.addComponent(new Physics(Physics.Type.STATIC));
    e.addComponent(new Colliders(new PolygonCollider(new Vector2[] {
      new Vector2(0.0f, 0.0f),
      new Vector2(0.0f, -10.0f),
      new Vector2(-10.0f, -10.0f),
    })));
    e.addToWorld();

    e = world.createEntity();
    e.addComponent(new Transform(0, -2.5f));
    e.addComponent(new Physics(Physics.Type.STATIC));
    e.addComponent(new Colliders(new RectangleCollider(10f, 0.25f)));
    e.addToWorld();
  }

  public static void main(String[] args) {
    run(new DebugScale());
  }

}
