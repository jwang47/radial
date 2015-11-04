package net.faintedge.rube.components;

public class TextureSpec {
  private String path;
  private TextureRegionSpec region;

  public TextureSpec(String path, TextureRegionSpec region) {
    this.path = path;
    this.region = region;
  }

  public TextureSpec(String path) {
    this(path, null);
  }

  public String getPath() {
    return path;
  }

  public TextureRegionSpec getRegion() {
    return region;
  }
}
