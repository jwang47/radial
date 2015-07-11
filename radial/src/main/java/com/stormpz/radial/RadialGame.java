package com.stormpz.radial;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Main entry point. Provides a way for the developer to configure {@link Screen}s.
 */
public class RadialGame extends Game {

  private final Screen initialScreen;

  public RadialGame(Screen initialScreen) {
    this.initialScreen = initialScreen;
  }

  @Override
  public void create() {
    setScreen(initialScreen);
  }
}
