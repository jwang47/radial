package net.faintedge.rube.screens;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.World;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.faintedge.rube.codec.ColliderCodec;
import net.faintedge.rube.codec.ComponentCodec;
import net.faintedge.rube.codec.JointConfigCodec;
import net.faintedge.rube.components.physics.Collider;
import net.faintedge.rube.components.physics.JointConfig;

import java.io.Reader;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class Scene {

  private static final Gson GSON = new GsonBuilder()
    .registerTypeAdapter(Component.class, new ComponentCodec())
    .registerTypeAdapter(Collider.class, new ColliderCodec())
    .registerTypeAdapter(JointConfig.class, new JointConfigCodec())
    .create();

  private static final Gson GSON_PRETTY = new GsonBuilder()
    .registerTypeAdapter(Component.class, new ComponentCodec())
    .registerTypeAdapter(Collider.class, new ColliderCodec())
    .registerTypeAdapter(JointConfig.class, new JointConfigCodec())
    .setPrettyPrinting()
    .create();

  private final List<Set<? extends Component>> entities;

  public Scene(List<Set<? extends Component>> entities) {
    this.entities = ImmutableList.copyOf(entities);
  }

  public static Scene fromJson(String json) {
    return GSON.fromJson(json, Scene.class);
  }

  public static Scene fromJson(Reader json) {
    return GSON.fromJson(json, Scene.class);
  }

  public String toJson() {
    return GSON.toJson(this);
  }

  public String toPrettyJson() {
    return GSON_PRETTY.toJson(this);
  }

  public List<Set<? extends Component>> getEntities() {
    return entities;
  }

  public void load(World world) {
    for (Set<? extends Component> components : entities) {
      Entity e = world.createEntity();
      for (Component component : components) {
        e.addComponent(component);
      }
      e.addToWorld();
    }
  }

  public static class Builder {
    private final List<Set<? extends Component>> entities = Lists.newArrayList();

    public Builder addEntity(Component... components) {
      entities.add(ImmutableSet.copyOf(components));
      return this;
    }

    public Builder addEntity(Set<? extends Component> components) {
      entities.add(ImmutableSet.copyOf(components));
      return this;
    }

    public Scene build() {
      return new Scene(entities);
    }
  }
}
