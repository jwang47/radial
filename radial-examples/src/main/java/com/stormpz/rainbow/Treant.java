package com.stormpz.rainbow;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.stormpz.radial.Context;
import com.stormpz.radial.Grid2D;
import com.stormpz.radial.RadialGame;
import com.stormpz.radial.components.TransformMotion;
import com.stormpz.radial.screens.GameScreen;
import com.stormpz.radial.screens.Scene;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Uses {@link com.stormpz.radial.components.Transform} and {@link com.stormpz.radial.components.Physics} components to create physics behavior.
 *
 * <p>
 *   Controls:<br/>
 *   R to reset the scene
 * </p>
 */
public class Treant extends GameScreen {

  private Grid2D grid;
  private ConcurrentLinkedQueue<Entity> entities = new ConcurrentLinkedQueue<Entity>();

  private Vector2 inputVector = new Vector2();
  private final float movespeed = 100.5f;
  private TransformMotion playerTransformMotion;

  @Override
  public void show() {
    super.show();

    Context context = super.getContext();
    World world = context.getWorld();
    populate(world);

    grid = new Grid2D(context);
  }

  public void populate(World world) {
    FileHandle sceneFile = Gdx.files.internal("default_scene.json");
    Scene scene = Scene.fromJson(sceneFile.reader());
    scene.load(world);
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    grid.render();
//    playerTransformMotion.getMotion().set(inputVector);
  }

  public static void main(String[] args) {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = "Treant";
    cfg.width = 800;
    cfg.height = 600;

    new LwjglApplication(new RadialGame(new Treant()), cfg);
  }

}
