package com.stormpz.radial.codec;

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
import com.stormpz.radial.components.physics.CircleCollider;
import com.stormpz.radial.components.physics.Collider;
import com.stormpz.radial.components.physics.PolygonCollider;
import com.stormpz.radial.components.physics.RectangleCollider;

import java.lang.reflect.Type;
import java.util.Map;

/**
 *
 */
public class ColliderCodec implements JsonSerializer<Collider>, JsonDeserializer<Collider> {

  private final static BiMap<String, Class<? extends Collider>> types = createTypesMap();

  private static BiMap<String, Class<? extends Collider>> createTypesMap() {
    HashBiMap<String, Class<? extends Collider>> result = HashBiMap.create();
    result.put("circle", CircleCollider.class);
    result.put("polgyon", PolygonCollider.class);
    result.put("rectangle", RectangleCollider.class);
    return result;
  }

  @Override
  public Collider deserialize(JsonElement jsonElement, Type type,
                               JsonDeserializationContext ctx) throws JsonParseException {
    JsonObject obj = jsonElement.getAsJsonObject();
    Preconditions.checkArgument(obj.has("type"));
    String typeString = obj.get("type").getAsString();
    return ctx.deserialize(jsonElement, getType(typeString));
  }

  @Override
  public JsonElement serialize(Collider object, Type type, JsonSerializationContext ctx) {
    JsonObject result = new JsonObject();
    result.addProperty("type", getTypeString(object.getClass()));

    JsonObject base = ctx.serialize(object).getAsJsonObject();
    Preconditions.checkArgument(!base.has("type"));
    for (Map.Entry<String, JsonElement> entry : base.entrySet()) {
      result.add(entry.getKey(), entry.getValue());
    }
    return result;
  }

  private Class<? extends Collider> getType(String typeString) {
    Preconditions.checkArgument(types.containsKey(typeString), "Unknown collider type: " + typeString);
    return types.get(typeString);
  }

  private String getTypeString(Class<? extends Collider> componentType) {
    Preconditions.checkArgument(types.containsValue(componentType), "Unknown collider type class: " + componentType);
    return types.inverse().get(componentType);
  }
}
