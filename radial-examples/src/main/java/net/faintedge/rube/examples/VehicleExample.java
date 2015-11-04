package net.faintedge.rube.examples;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.google.common.base.Throwables;
import net.faintedge.rube.Context;
import net.faintedge.rube.WASDInputProcessor;
import net.faintedge.rube.components.Physics;
import net.faintedge.rube.components.Transform;
import net.faintedge.rube.components.WheelMotion;
import net.faintedge.rube.components.physics.*;
import net.faintedge.rube.util.RUBELoader;

import java.io.IOException;

/**
 * Uses {@link Physics}, {@link Joints}, and {@link WheelMotion} components to create vehicle physics behavior.
 *
 * <p>
 *   Controls:<br/>
 *   A/D to move left/right
 * </p>
 */
public class VehicleExample extends BaseExample {

  private WheelMotion wheelMotion;
  private Transform vehicleTransform;
  private WASDInputProcessor wasd;

  @Override
  public void show() {
    super.show();

    Context context = super.getContext();
    context.getInputMultiplexer().addProcessor(wasd = new WASDInputProcessor());
    World world = context.getWorld();
    populate(world);

    debugMode = true;
  }

  public void populate(World world) {
    RUBELoader rubeLoader = new RUBELoader();
    try {
      rubeLoader.load(world, Gdx.files.internal("tank.json"), 4f);
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }

    Material rubberMaterial = new Material(1.0f, 0.2f, 0.5f);

    float vehicleX = 0;
    float vehicleY = 0;
    float vehicleScale = 30.0f;

    // vehicle right wheel
    Entity rightWheel = world.createEntity();
    rightWheel.addComponent(new Transform(vehicleX + (0.1f - 0.02f) * vehicleScale, vehicleY));
    rightWheel.addComponent(new Physics());
    rightWheel.addComponent(new Colliders(new CircleCollider(0.06f * vehicleScale, rubberMaterial)));
    rightWheel.addToWorld();

    // vehicle left wheel
    Entity leftWheel = world.createEntity();
    leftWheel.addComponent(new Transform(vehicleX - (0.1f + 0.02f) * vehicleScale, vehicleY));
    leftWheel.addComponent(new Physics());
    leftWheel.addComponent(new Colliders(new CircleCollider(0.06f * vehicleScale, rubberMaterial)));
    leftWheel.addToWorld();

    // vehicle chassis
    Entity chassis = world.createEntity();
    chassis.addComponent(wheelMotion = new WheelMotion(1.5f * vehicleScale, 0.1f, 1.5f * vehicleScale));
    chassis.addComponent(vehicleTransform = new Transform(vehicleX, vehicleY));
    chassis.addComponent(new Physics());
    chassis.addComponent(new Colliders(new RectangleCollider(0.2f * vehicleScale, 0.025f * vehicleScale,
                                                             new Material(1.0f, 0.4f, 7.5f))));
    chassis.addComponent(new Joints(
      new Joint(WheelJointConfig.builder()
        .setAxisOfMovement(new Vector2(0, 1))
        .setSuspensionFrequencyHz(2 * vehicleScale)
        .build(), rightWheel),
      new Joint(WheelJointConfig.builder()
        .setAxisOfMovement(new Vector2(0, 1))
        .setSuspensionFrequencyHz(2 * vehicleScale)
        .build(), leftWheel)));
    chassis.addToWorld();
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    wheelMotion.setDirection(-wasd.getX());
    getContext().getCameraTransform().getTranslation().set(vehicleTransform.getTranslation()).scl(-1);
  }

  public static void main(String[] args) {
    run(new VehicleExample());
  }

}
