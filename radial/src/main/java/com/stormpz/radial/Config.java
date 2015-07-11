package com.stormpz.radial;

import com.google.common.base.Preconditions;

/**
 * Configuration for {@link com.stormpz.radial.screens.GameScreen}.
 */
public class Config {

  /**
   * Meters per world unit.
   */
  private final float metersPerUnit;

  /**
   * World units per meter.
   */
  private final float unitsPerMeter;

  /**
   * Width of the world bounds. Used to remove entities that have gone outside the worldBounds.
   */
  private final float worldBoundsWidth;

  /**
   * Height of the world bounds. Used to remove entities that have gone outside the worldBounds.
   */
  private final float worldBoundsHeight;

  public Config(float unitsPerMeter, float worldBoundsWidth, float worldBoundsHeight) {
    Preconditions.checkArgument(unitsPerMeter > 0);
    this.unitsPerMeter = unitsPerMeter;
    this.metersPerUnit = 1 / unitsPerMeter;
    this.worldBoundsWidth = worldBoundsWidth;
    this.worldBoundsHeight = worldBoundsHeight;
  }

  public Config() {
    this(2f, 1000.0f, 1000.0f);
  }

  public float getWorldBoundsWidth() {
    return worldBoundsWidth;
  }

  public float getWorldBoundsHeight() {
    return worldBoundsHeight;
  }

  public float getUnitsPerMeter() {
    return unitsPerMeter;
  }
  public float getMetersPerUnit() {
    return metersPerUnit;
  }
}
