package com.stormpz.rainbow.examples;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.stormpz.radial.Context;
import com.stormpz.radial.components.Physics;
import com.stormpz.radial.components.Render;
import com.stormpz.radial.components.Transform;
import com.stormpz.radial.components.physics.Colliders;
import com.stormpz.radial.components.physics.RectangleCollider;
import com.stormpz.radial.screens.Scene;

/**
 * Uses {@link Scene} to load a map from JSON.
 */
public class JsonExample extends BaseExample {

  @Override
  public void show() {
    super.show();

    Context context = super.getContext();
    World world = context.getWorld();
    populate(world);
  }

  public void populate(World world) {
    Scene.Builder builder = new Scene.Builder();

    int cols = 5;
    float width = 1;
    float height = 1;
    float padding = width * 5;
    float x = -(cols * width) / 2 + width/2;
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < 5; j++) {
        builder.addEntity(
          new Render(crateTextureSpec, width, height),
          new Transform(x + i * width, j * (width + padding)),
          new Physics(),
          new Colliders(new RectangleCollider(width, height))
        );
      }
    }

    builder.addEntity(
      new Render(crateTextureSpec, 40, 1),
      new Transform(0, -5),
      new Physics(Physics.Type.STATIC),
      new Colliders(new RectangleCollider(40, 1))
    );

    Scene map = builder.build();
    String jsonString = map.toPrettyJson();
    Gdx.app.log("JsonExample", "Loading map from JSON:\n" + jsonString);
    Scene parsedMap = Scene.fromJson(jsonString);

    parsedMap.load(world);
  }

  public static void main(String[] args) {
    run(new JsonExample());
  }

}
