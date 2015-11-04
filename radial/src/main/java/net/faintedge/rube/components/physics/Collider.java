package net.faintedge.rube.components.physics;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 *
 */
public abstract class Collider {

  /** A sensor collects contact information but never generates a collision response. */
  private final boolean isSensor;
  protected final Material material;

  // TODO: Maybe should be moved outside this, since it's not strictly config..
  private Fixture fixture;

  public Collider(boolean isSensor, Material material) {
    this.isSensor = isSensor;
    this.material = material;
  }

  public abstract FixtureDef createFixtureDef(float metersPerUnit);

  public Material getMaterial() {
    return material;
  }

  public boolean isSensor() {
    return isSensor;
  }

  public Fixture getFixture() {
    return fixture;
  }

  public void setFixture(Fixture fixture) {
    this.fixture = fixture;
  }
}
