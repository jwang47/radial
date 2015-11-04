package net.faintedge.rube.components.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 *
 */
public class PolygonCollider extends Collider {

  private final Vector2[] vertices;

  public PolygonCollider(Vector2[] vertices, Material material, boolean isSensor) {
    super(isSensor, material);
    this.vertices = vertices;
  }

  public PolygonCollider(Vector2[] vertices, Material material) {
    this(vertices, material, false);
  }

  public PolygonCollider(Vector2[] vertices) {
    this(vertices, Material.DEFAULT, false);
  }

  public Vector2[] getVertices() {
    return vertices;
  }

  @Override
  public FixtureDef createFixtureDef(float metersPerUnit) {
    PolygonShape shape = new PolygonShape();

    // TODO: gc?
    Vector2[] scaledVertices = new Vector2[vertices.length];
    for (int i = 0; i < vertices.length; i++) {
      scaledVertices[i] = vertices[i].scl(metersPerUnit);
    }
    shape.set(scaledVertices);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = material.getDensity();
    fixtureDef.friction = material.getFriction();
    fixtureDef.restitution = material.getRestitution();

    return fixtureDef;
  }
}
