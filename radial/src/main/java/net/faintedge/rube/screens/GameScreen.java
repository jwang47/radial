package net.faintedge.rube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.faintedge.rube.Context;
import net.faintedge.rube.Grid2D;
import net.faintedge.rube.Timer;

/**
 * Main entry point. Provides a way for the developer to load assets and set up the {@link com.artemis.World}.
 */
public class GameScreen extends ScreenAdapter {

  protected boolean debugMode = false;

  private Grid2D grid;
  private FPSLogger fpsLogger;
  private Context context;
  private Timer timer;

  @Override
  public void show() {
    fpsLogger = new FPSLogger();
    timer = new Timer();

    float maxAspectRatio = 16.0f / 9.0f;
    float worldEdgeLength = 100.0f;
    Viewport viewport = new ExtendViewport(worldEdgeLength, worldEdgeLength,
                                           worldEdgeLength * maxAspectRatio, worldEdgeLength);

    context = new Context(viewport);
    context.create();
    context.activate();

    // debug grid
    grid = new Grid2D(context);
  }

  @Override
  public void hide() {
    context.getAssetManager().dispose();
  }

  @Override
  public void dispose() {
    context.dispose();
  }

  @Override
  public void pause() {
    // TODO Auto-generated method stub

  }

  @Override
  public void render(float delta) {
    float tpf = Math.min(0.25f, timer.tickSeconds());

    // TODO: need better way to set TPF
    context.getTransformMotionSystem().setTpf(tpf);
    context.getWorld().process();

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

    SpriteBatch batch = context.getBatch();
    Camera camera = context.getCurrentCamera();
    camera.update();

    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    // render
    context.getRenderSystem().process();
    batch.end();

    if (debugMode) {
      grid.render();
      context.getPhysicsSystem().debugRender(camera.combined);
    }

    // physics world step
    context.getPhysicsSystem().setTPF(tpf);
    context.getPhysicsSystem().step();
    context.getPhysicsSystem().process();

//    fpsLogger.log();
  }

  @Override
  public void resize(int width, int height) {
    context.getCurrentViewport().update(width, height);
  }

  public Context getContext() {
    return context;
  }
}
