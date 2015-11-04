package net.faintedge.rube;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RUBESceneLoaderTest {

  private final Gson GSON = new Gson();

  @Test
  public void testLoad() throws IOException {
    RUBESceneLoader sceneLoader = new RUBESceneLoader();

    InputStream truckJsonInput = getClass().getClassLoader().getResourceAsStream("scene.json");
    Readable readable = new InputStreamReader(truckJsonInput, Charsets.UTF_8);
    RUBEScene scene = sceneLoader.load(CharStreams.toString(readable));
    System.out.println(GSON.toJson(scene));
  }

}