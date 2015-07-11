package com.stormpz.radial.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stormpz.radial.components.Render;
import com.stormpz.radial.components.TextureRegionSpec;
import com.stormpz.radial.components.Transform;

import java.util.HashMap;
import java.util.Map;

/**
 * Renders entities using the {@link Render} component,
 * with position and rotation from the corresponding {@link Transform}.
 */
public class RenderSystem extends EntityProcessingSystem {

  @Wire
  private ComponentMapper<Transform> pm;

  @Wire
  private ComponentMapper<Render> rm;

  private final AssetManager assetManager;
  private final RenderingContext context;

  private final Map<Integer, Sprite> sprites;

  public RenderSystem(SpriteBatch spriteBatch, AssetManager assetManager) {
    super(Aspect.getAspectForAll(Transform.class, Render.class));
    this.context = new RenderingContext(spriteBatch);
    this.assetManager = assetManager;
    this.sprites = new HashMap<Integer, Sprite>();
  }

  @Override
  protected void inserted(Entity e) {
    Render render = rm.get(e);
    sprites.put(e.getId(), generateSprite(render));
  }

  @Override
  protected void removed(Entity e) {
    sprites.remove(e.getId());
  }

  protected void process(Entity e) {
    Transform transform = pm.get(e);

    Sprite sprite = sprites.get(e.getId());
    sprite.setRotation(transform.getRotation());
    sprite.setPosition(transform.getTranslation().x, transform.getTranslation().y);
    sprite.translate(-sprite.getOriginX(), -sprite.getOriginY());
    sprite.draw(context.getBatch());
  }

  public RenderingContext getContext() {
    return context;
  }

  private Sprite generateSprite(Render render) {
    Texture texture;
    if (render.getTexture() != null) {
      AssetDescriptor<Texture> textureAD = new AssetDescriptor<Texture>(
        Gdx.files.internal(render.getTexture().getPath()), Texture.class);
      // TODO: load asset before usage (e.g. loading screen)
      assetManager.load(textureAD);
      assetManager.finishLoading();
      texture = assetManager.get(textureAD);
    } else {
      // TOOD: use default texture
      texture = new Texture(1, 1, null);
    }

    TextureRegion region;
    if (render.getTexture().getRegion() != null) {
      TextureRegionSpec regionSpec = render.getTexture().getRegion();
      region = new TextureRegion(texture,
        regionSpec.getX(), regionSpec.getY(),
        regionSpec.getWidth(), regionSpec.getHeight());
    } else {
      region = new TextureRegion(texture);
    }

    Sprite sprite = new Sprite(region);
    sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    sprite.setScale(render.getWidth() / sprite.getWidth(), render.getHeight() / sprite.getHeight());
    //sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
    return sprite;
  }
}