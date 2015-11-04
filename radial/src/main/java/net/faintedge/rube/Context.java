package net.faintedge.rube;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.faintedge.rube.components.Transform;
import net.faintedge.rube.components.TransformMotion;
import net.faintedge.rube.screens.GameScreen;
import net.faintedge.rube.systems.*;

/**
 * Context for {@link GameScreen}.
 *
 * TODO: Getting a bit too big..
 */
public class Context {

  // TODO: multiple cameras
  private Entity cameraEntity;
  private Transform cameraTransform;
  private final Camera currentCamera;
  private final Viewport currentViewport;

  private SpriteBatch batch;
  private ShapeRenderer shapeRenderer;
  private AssetManager assetManager;

  private Config config;

  private World world;
  private PhysicsSystem physicsSystem;
  private RenderSystem renderSystem;

  // TODO: remove once TransformMotionSystem.setTpf() is removed
  private TransformMotionSystem transformMotionSystem;

  private TransformMotion cameraMotion;
  private InputMultiplexer inputMultiplexer;

  public Context(Viewport viewport) {
    this.currentCamera = viewport.getCamera();
    this.currentViewport = viewport;
  }

  public void create() {
    config = new Config();
    inputMultiplexer = new InputMultiplexer();
    assetManager = new AssetManager();
    currentCamera.update();
    batch = new SpriteBatch();
    shapeRenderer = new ShapeRenderer();

    world = new World();

    renderSystem = world.setSystem(new RenderSystem(batch, assetManager), true);
    physicsSystem = world.setSystem(new PhysicsSystem(config), true);
    transformMotionSystem = world.setSystem(new TransformMotionSystem());

    world.setSystem(new CameraSystem(batch));
    world.setSystem(new PhysicsMotionSystem());
    world.setSystem(new WheelMotionSystem());

    world.initialize();

    cameraEntity = world.createEntity();
    cameraEntity.addComponent(cameraTransform = new Transform());
    cameraEntity.addComponent(cameraMotion = new TransformMotion());
    // TODO: no cast
    cameraEntity.addComponent(new net.faintedge.rube.components.Camera(
      (com.badlogic.gdx.graphics.OrthographicCamera) currentCamera));
    world.addEntity(cameraEntity);
  }

  public void activate() {
    Gdx.input.setInputProcessor(inputMultiplexer);
  }

  public void dispose() {
    batch.dispose();
    assetManager.dispose();
  }

  // TODO: multiple cameras
//  public void setCurrentCamera(OrthographicCamera currentCamera) {
//    this.currentCamera = currentCamera;
//    this.cameraEntity.removeComponent(Camera.class);
//    this.cameraEntity.addComponent(new Camera(currentCamera));
//  }

  public InputMultiplexer getInputMultiplexer() {
    return inputMultiplexer;
  }

  public TransformMotion getCameraMotion() {
    return cameraMotion;
  }

  public Viewport getCurrentViewport() {
    return currentViewport;
  }

  public Transform getCameraTransform() {
    return cameraTransform;
  }

  public Camera getCurrentCamera() {
    return currentCamera;
  }

  public PhysicsSystem getPhysicsSystem() {
    return physicsSystem;
  }

  public SpriteBatch getBatch() {
    return batch;
  }

  public World getWorld() {
    return world;
  }

  public TransformMotionSystem getTransformMotionSystem() {
    return transformMotionSystem;
  }

  public AssetManager getAssetManager() {
    return assetManager;
  }

  public RenderSystem getRenderSystem() {
    return renderSystem;
  }

  public ShapeRenderer getShapeRenderer() {
    return shapeRenderer;
  }

  public Config getConfig() {
    return config;
  }
}
