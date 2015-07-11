package com.stormpz.rube;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 *
 */
public class RUBEVectorDeserializer implements JsonDeserializer<RUBEVector> {

  @Override
  public RUBEVector deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext context) throws JsonParseException {
    if (jsonElement.isJsonObject()) {
      float x = jsonElement.getAsJsonObject().get("x").getAsFloat();
      float y = jsonElement.getAsJsonObject().get("y").getAsFloat();
      return new RUBEVector(x, y);
    } else if (jsonElement.isJsonPrimitive()) {
      if (jsonElement.getAsFloat() == 0) {
        return new RUBEVector(0, 0);
      }
    }

    throw new IllegalArgumentException("Unrecognized RUBEVector type");
  }
}
