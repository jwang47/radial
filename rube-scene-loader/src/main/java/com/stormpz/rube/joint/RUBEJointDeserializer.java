package com.stormpz.rube.joint;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class RUBEJointDeserializer implements JsonDeserializer<RUBEJoint> {

  private Map<String, Class<? extends RUBEJoint>> types = new HashMap<String, Class<? extends RUBEJoint>>() {{
    put("wheel", RUBEWheelJoint.class);
    put("revolute", RUBERevoluteJoint.class);
    put("distance", RUBEDistanceJoint.class);
  }};

  @Override
  public RUBEJoint deserialize(JsonElement json, Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException {
    String type = json.getAsJsonObject().get("type").getAsString();
    if (!types.containsKey(type)) {
      throw new IllegalArgumentException("Invalid type: " + type);
    }

    Class<? extends RUBEJoint> typeClass = types.get(type);
    return context.deserialize(json, typeClass);
  }
}
