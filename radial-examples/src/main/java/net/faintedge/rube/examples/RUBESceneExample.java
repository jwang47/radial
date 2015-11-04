package net.faintedge.rube.examples;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.google.common.base.Throwables;
import net.faintedge.rube.Context;
import net.faintedge.rube.WASDInputProcessor;
import net.faintedge.rube.util.RUBELoader;

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
