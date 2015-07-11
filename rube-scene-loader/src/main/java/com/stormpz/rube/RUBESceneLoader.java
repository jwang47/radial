package com.stormpz.rube;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stormpz.rube.joint.RUBEJoint;
import com.stormpz.rube.joint.RUBEJointDeserializer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 */
public class RUBESceneLoader {

  private final Gson GSON = new GsonBuilder()
    .registerTypeAdapter(RUBEJoint.class, new RUBEJointDeserializer())
    .registerTypeAdapter(RUBEVector.class, new RUBEVectorDeserializer())
    .create();

  public RUBEScene load(String json) {
    return GSON.fromJson(json, RUBEScene.class);
  }

  public RUBEScene load(InputStream json) {
    return GSON.fromJson(new InputStreamReader(json), RUBEScene.class);
  }

}
