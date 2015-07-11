package com.stormpz.radial.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Context required for rendering in libgdx.
 */
public class RenderingContext {

  private final SpriteBatch batch;

  public RenderingContext(SpriteBatch batch) {
    this.batch = batch;
  }

  public SpriteBatch getBatch() {
    return batch;
  }
}
