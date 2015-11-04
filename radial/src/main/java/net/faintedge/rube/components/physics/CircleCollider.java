package net.faintedge.rube.components.physics;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 *
 */
public class CircleCollider extends Collider {

  private final float radius;

  public CircleCollider(float radius, Material material, boolean isSensor) {
    super(isSensor, material);
    this.radius = radius;
  }

  public CircleCollider(float radius, Material material) {
    this(radius, material, false);
  }

  public CircleCollider(float radius) {
    this(radius, Material.DEFAULT, false);
  }

  public float getRadius() {
    return radius;
  }

  @Override
  public FixtureDef createFixtureDef(float metersPerUnit) {
    CircleShape shape = new CircleShape();
    shape.setRadius(radius * metersPerUnit);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = material.getDensity();
    fixtureDef.friction = material.getFriction();
    fixtureDef.restitution = material.getRestitution();

    return fixtureDef;
  }
}
