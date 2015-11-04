package net.faintedge.rube.components;

import com.artemis.Component;

/**
 * Graphical representation of an image-based entity.
 */
public class Render extends Component {

  private TextureSpec texture;
  private float width;
  private float height;

  public Render(TextureSpec texture, float width, float height) {
    this.texture = texture;
    this.width = width;
    this.height = height;
  }

  public TextureSpec getTexture() {
    return texture;
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private TextureSpec texture;

    private float width;
    private float height;

    public Builder setTexture(TextureSpec texture) {
      this.texture = texture;
      return this;
    }

    public Builder setWidth(float width) {
      this.width = width;
      return this;
    }

    public Builder setHeight(float height) {
      this.height = height;
      return this;
    }

    public Render build() {
      return new Render(texture, width, height);
    }
  }
}
