package net.faintedge.rube.examples;

import com.artemis.Entity;
import com.artemis.World;
import net.faintedge.rube.Context;
import net.faintedge.rube.WASDInputProcessor;
import net.faintedge.rube.components.Render;
import net.faintedge.rube.components.Transform;
import net.faintedge.rube.components.TransformMotion;

/**
 * Uses the {@link WASDInputProcessor} to control the {@link TransformMotion} of an entity,
 * which in turn controls the {@link Transform} of an entity. The layer of indirection is
 * nice because then an AI player can simply control the {@link TransformMotion}, and the state of the
 * {@link TransformMotion} can be synchronized over the network.
 *
 * <p>
 *   Controls:<br/>
 *   WASD to move the entity
 * </p>
 */
public class InputExample extends BaseExample {

  private TransformMotion transformMotion;

  private final float movespeed = 15f;
  private WASDInputProcessor wasd;

  @Override
  public void show() {
    super.show();

    Context context = super.getContext();
    context.getInputMultiplexer().addProcessor(wasd = new WASDInputProcessor());
    World world = context.getWorld();

    final Entity e = world.createEntity();
    e.addComponent(new Transform(0, 0));
    e.addComponent(transformMotion = new TransformMotion());
    e.addComponent(new Render(testTextureSpec, 25f, 25f));
    e.addToWorld();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    transformMotion.getMotion().set(wasd.getX(), wasd.getY()).scl(movespeed);
  }

  public static void main(String[] args) {
    run(new InputExample());
  }

}
