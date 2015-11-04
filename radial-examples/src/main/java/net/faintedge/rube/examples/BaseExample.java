package net.faintedge.rube.examples;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.faintedge.rube.RadialGame;
import net.faintedge.rube.components.TextureSpec;
import net.faintedge.rube.screens.GameScreen;

/**
 * Base class for examples. Provides some textures to test with.
 */
public class BaseExample extends GameScreen {

  protected TextureSpec testTextureSpec = new TextureSpec("libgdx.png");
  protected TextureSpec crateTextureSpec = new TextureSpec("crate.png");

  protected static void run(BaseExample example) {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.width = 1024;
    cfg.height = 768;

    new LwjglApplication(new RadialGame(example), cfg);
  }
}
