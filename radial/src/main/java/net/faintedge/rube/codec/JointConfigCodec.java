package net.faintedge.rube.codec;

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
import net.faintedge.rube.components.physics.JointConfig;
import net.faintedge.rube.components.physics.PrismaticJointConfig;
import net.faintedge.rube.components.physics.RevoluteJointConfig;
import net.faintedge.rube.components.physics.WheelJointConfig;

import java.lang.reflect.Type;
import java.util.Map;

/**
 *
 */
public class JointConfigCodec implements JsonSerializer<JointConfig>, JsonDeserializer<JointConfig> {

  private final static BiMap<String, Class<? extends JointConfig>> types = createTypesMap();

  private static BiMap<String, Class<? extends JointConfig>> createTypesMap() {
    HashBiMap<String, Class<? extends JointConfig>> result = HashBiMap.create();
    result.put("prismatic", PrismaticJointConfig.class);
    result.put("revolute", RevoluteJointConfig.class);
    result.put("wheel", WheelJointConfig.class);
    return result;
  }

  @Override
  public JointConfig deserialize(JsonElement jsonElement, Type type,
                               JsonDeserializationContext ctx) throws JsonParseException {
    JsonObject obj = jsonElement.getAsJsonObject();
    Preconditions.checkArgument(obj.has("type"));
    String typeString = obj.get("type").getAsString();
    return ctx.deserialize(jsonElement, getType(typeString));
  }

  @Override
  public JsonElement serialize(JointConfig object, Type type, JsonSerializationContext ctx) {
    JsonObject result = new JsonObject();
    result.addProperty("type", getTypeString(object.getClass()));

    JsonObject base = ctx.serialize(object).getAsJsonObject();
    Preconditions.checkArgument(!base.has("type"));
    for (Map.Entry<String, JsonElement> entry : base.entrySet()) {
      result.add(entry.getKey(), entry.getValue());
    }
    return result;
  }

  private Class<? extends JointConfig> getType(String typeString) {
    Preconditions.checkArgument(types.containsKey(typeString), "Unknown joint type: " + typeString);
    return types.get(typeString);
  }

  private String getTypeString(Class<? extends JointConfig> componentType) {
    Preconditions.checkArgument(types.containsValue(componentType), "Unknown joint type class: " + componentType);
    return types.inverse().get(componentType);
  }
}
