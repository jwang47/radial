package net.faintedge.rube.codec;

import com.artemis.Component;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.faintedge.rube.components.physics.Colliders;
import net.faintedge.rube.components.physics.Joints;
import net.faintedge.rube.components.*;

import java.lang.reflect.Type;
import java.util.Map;

/**
 *
 */
public class ComponentCodec implements JsonSerializer<Component>, JsonDeserializer<Component> {

  private final static BiMap<String, Class<? extends Component>> types = createTypesMap();

  private static BiMap<String, Class<? extends Component>> createTypesMap() {
    HashBiMap<String, Class<? extends Component>> result = HashBiMap.create();
    result.put("render", Render.class);
    result.put("anchors", Anchors.class);
    result.put("camera", Camera.class);
    result.put("colliders", Colliders.class);
    result.put("joints", Joints.class);
    result.put("name", Name.class);
    result.put("physics", Physics.class);
    result.put("physics-motion", PhysicsMotion.class);
    result.put("transform", Transform.class);
    result.put("transform-motion", TransformMotion.class);
    result.put("wheel-motion", WheelMotion.class);
    return result;
  }

  @Override
  public Component deserialize(JsonElement jsonElement, Type type,
                               JsonDeserializationContext ctx) throws JsonParseException {
    JsonObject obj = jsonElement.getAsJsonObject();
    Preconditions.checkArgument(obj.has("type"));
    String typeString = obj.get("type").getAsString();
    return ctx.deserialize(jsonElement, getType(typeString));
  }

  @Override
  public JsonElement serialize(Component object, Type type, JsonSerializationContext ctx) {
    JsonObject result = new JsonObject();
    result.addProperty("type", getTypeString(object.getClass()));

    JsonObject base = ctx.serialize(object).getAsJsonObject();
    Preconditions.checkArgument(!base.has("type"));
    for (Map.Entry<String, JsonElement> entry : base.entrySet()) {
      result.add(entry.getKey(), entry.getValue());
    }
    return result;
  }

  private Class<? extends Component> getType(String typeString) {
    Preconditions.checkArgument(types.containsKey(typeString), "Unknown component type: " + typeString);
    return types.get(typeString);
  }

  private String getTypeString(Class<? extends Component> componentType) {
    Preconditions.checkArgument(types.containsValue(componentType), "Unknown component type class: " + componentType);
    return types.inverse().get(componentType);
  }
}
