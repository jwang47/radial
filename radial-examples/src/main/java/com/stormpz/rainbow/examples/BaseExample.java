package com.stormpz.rainbow.examples;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stormpz.radial.RadialGame;
import com.stormpz.radial.components.TextureSpec;
import com.stormpz.radial.screens.GameScreen;

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
