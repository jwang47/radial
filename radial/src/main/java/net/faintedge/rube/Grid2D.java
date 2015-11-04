package net.faintedge.rube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Grid2D {

  private Context context;

  public Grid2D(Context context) {
    this.context = context;
  }

  public void render() {
    OrthographicCamera camera = (OrthographicCamera) context.getCurrentCamera();
    ShapeRenderer shapeRenderer = context.getShapeRenderer();

    float centerX = camera.position.x;
    float centerY = camera.position.y;

    float xMin = centerX - camera.viewportWidth * 2;
    float xMax = centerX + camera.viewportWidth * 2;

    float yMin = centerY - camera.viewportHeight * 2;
    float yMax = centerY + camera.viewportHeight * 2;

    Gdx.gl.glEnable(GL30.GL_BLEND);
    Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);

    shapeRenderer.setProjectionMatrix(camera.combined);
    shapeRenderer.begin(ShapeType.Line);
    shapeRenderer.setColor(1, 1, 1, 0.2f);

    float cellSize = 10.0f;
    float offsetX = -centerX % cellSize;
    float offsetY = -centerY % cellSize;

    float leftScreenX = offsetX + offsetX + xMin;
    float rightScreenX = offsetX + xMax;
    float topScreenY = offsetY + yMin;
    float bottomScreenY = offsetY + yMax;

    // vertical lines

    for (float x = centerX; x <= xMax; x += cellSize) {
      shapeRenderer.line(
        offsetX + x, topScreenY,
        offsetX + x, bottomScreenY);
    }

    for (float x = centerX - cellSize; x >= xMin; x -= cellSize) {
      shapeRenderer.line(
        offsetX + x, topScreenY,
        offsetX + x, bottomScreenY);
    }

    // horizontal lines

    for (float y = centerY; y <= yMax; y += cellSize) {
      shapeRenderer.line(
        leftScreenX, offsetY + y,
        rightScreenX, offsetY + y);
    }

    for (float y = centerY - cellSize; y >= yMin; y -= cellSize) {
      shapeRenderer.line(
        leftScreenX, offsetY + y,
        rightScreenX, offsetY + y);
    }

    shapeRenderer.end();

    Gdx.gl.glDisable(GL30.GL_BLEND);
  }

}
