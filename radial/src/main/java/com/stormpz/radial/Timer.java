package com.stormpz.radial;

public class Timer {

  private long lastCalled = 0;

  public long tick() {
    long now = System.currentTimeMillis();
    long diff = now - lastCalled;
    lastCalled = now;
    return diff;
  }

  public float tickSeconds() {
    return tick() / 1000.0f;
  }

}
