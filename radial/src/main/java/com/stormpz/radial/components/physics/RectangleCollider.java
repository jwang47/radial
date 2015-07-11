package com.stormpz.radial.components.physics;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 *
 */
public class RectangleCollider extends Collider {

  private final float width;
  private final float height;

  public RectangleCollider(float width, float height, Material material, boolean isSensor) {
    super(isSensor, material);
    this.width = width;
    this.height = height;
  }

  public RectangleCollider(float width, float height, Material material) {
    this(width, height, material, false);
  }

  public RectangleCollider(float width, float height) {
    this(width, height, Material.DEFAULT, false);
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  @Override
  public FixtureDef createFixtureDef(float metersPerUnit) {
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(width * 0.5f * metersPerUnit, height * 0.5f * metersPerUnit);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = material.getDensity();
    fixtureDef.friction = material.getFriction();
    fixtureDef.restitution = material.getRestitution();

    return fixtureDef;
  }
}
