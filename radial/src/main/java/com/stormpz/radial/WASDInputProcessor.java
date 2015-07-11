package com.stormpz.radial;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class WASDInputProcessor extends InputAdapter {

  private int x;
  private int y;
  
  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Input.Keys.W) {
      y += 1;
    } else if (keycode == Input.Keys.S) {
      y += -1;
    } else if (keycode == Input.Keys.A) {
      x += -1;
    } else if (keycode == Input.Keys.D) {
      x += 1;
    }
    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Input.Keys.W) {
      y -= 1;
    } else if (keycode == Input.Keys.S) {
      y -= -1;
    } else if (keycode == Input.Keys.A) {
      x -= -1;
    } else if (keycode == Input.Keys.D) {
      x -= 1;
    }
    return true;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
