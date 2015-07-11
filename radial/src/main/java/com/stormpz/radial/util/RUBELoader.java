package com.stormpz.radial.util;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.stormpz.radial.components.Physics;
import com.stormpz.radial.components.Transform;
import com.stormpz.radial.components.physics.CircleCollider;
import com.stormpz.radial.components.physics.Collider;
import com.stormpz.radial.components.physics.Colliders;
import com.stormpz.radial.components.physics.PolygonCollider;
import com.stormpz.rube.RUBEBody;
import com.stormpz.rube.RUBECircle;
import com.stormpz.rube.RUBEFixture;
import com.stormpz.rube.RUBEScene;
import com.stormpz.rube.RUBESceneLoader;
import com.stormpz.rube.RUBEVector;
import com.stormpz.rube.RUBEVertices;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *
 */
public class RUBELoader {

  public void load(World world, FileHandle rubeFile, float scale) throws IOException {
    InputStream rubeJson = rubeFile.read();
    try {
      RUBESceneLoader sceneLoader = new RUBESceneLoader();
      RUBEScene scene = sceneLoader.load(rubeJson);
      List<RUBEBody> bodies = scene.getBody();
      for (RUBEBody body : bodies) {
        Entity e = world.createEntity();
        RUBEVector center = body.getMassDataCenter();
        if (center == null) {
          center = new RUBEVector(0, 0);
        }
        Transform transform = new Transform(body.getPosition().getX() * scale,
                                            body.getPosition().getY() * scale,
                                            body.getAngle() * MathUtils.radiansToDegrees);
        e.addComponent(transform);

        Physics.Type bodyType;
        if (body.getType() == 0) {
          bodyType = Physics.Type.STATIC;
        } else if (body.getType() == 1) {
          bodyType = Physics.Type.KINEMATIC;
        } else if (body.getType() == 2) {
          bodyType = Physics.Type.DYNAMIC;
        } else {
          throw new IllegalArgumentException("Unrecognized body type '" + body.getType() +
                                               "' for body '" + body.getName() + "'");
        }
        e.addComponent(new Physics(bodyType));
        e.addComponent(getColliders(body.getFixture(), scale));
        e.addToWorld();
      }
    } finally {
      rubeJson.close();
    }
  }

  private Colliders getColliders(List<RUBEFixture> fixtures, float scale) {
    Colliders.Builder result = Colliders.builder();
    for (RUBEFixture fixture : fixtures) {
      result.add(getCollider(fixture, scale));
    }
    return result.build();
  }

  private Collider getCollider(RUBEFixture fixture, float scale) {
    if (fixture.getPolygon() != null) {
      RUBEVertices fixtureVertices = fixture.getPolygon().getVertices();
      int numVertices = fixtureVertices.getX().length;
      Vector2[] vertices = new Vector2[numVertices];
      for (int i = 0; i < numVertices; i++) {
        float x = fixtureVertices.getX()[i];
        float y = fixtureVertices.getY()[i];
        vertices[i] = new Vector2(x * scale, y * scale);
      }
      return new PolygonCollider(vertices);
    } else if (fixture.getCircle() != null) {
      RUBECircle circle = fixture.getCircle();
      // TODO: handle circle center
      return new CircleCollider(circle.getRadius() * scale);
    } else {
      throw new IllegalArgumentException("RUBE scene is missing shape for fixture '" + fixture.getName() + "'");
    }
  }

}
