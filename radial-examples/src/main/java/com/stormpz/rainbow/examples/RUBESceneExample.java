package com.stormpz.rainbow.examples;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.google.common.base.Throwables;
import com.stormpz.radial.Context;
import com.stormpz.radial.WASDInputProcessor;
import com.stormpz.radial.util.RUBELoader;

import java.io.IOException;

/**
 * Loads a RUBE scene from a file.
 */
public class RUBESceneExample extends BaseExample {

  private final float movespeed = 35.5f;
  private WASDInputProcessor wasd;

  @Override
  public void render(float delta) {
    super.render(delta);

    // TODO: shouldn't need negative movespeed
    getContext().getCameraMotion().getMotion().set(wasd.getX(), wasd.getY()).scl(-movespeed);
  }

  @Override
  public void show() {
    super.show();

    Context context = super.getContext();
    context.getInputMultiplexer().addProcessor(wasd = new WASDInputProcessor());
    World world = context.getWorld();
    populate(world);

    debugMode = true;
  }

  public void populate(World world) {
    RUBELoader rubeLoader = new RUBELoader();
    try {
      rubeLoader.load(world, Gdx.files.internal("gettingStarted.json"), 2f);
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }
  }

  public static void main(String[] args) {
    run(new RUBESceneExample());
  }

}
