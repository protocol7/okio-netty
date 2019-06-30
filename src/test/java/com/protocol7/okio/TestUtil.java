package com.protocol7.okio;

import java.util.Random;

public class TestUtil {

  public static byte[] rnd(final int size) {
    byte[] data = new byte[size];
    new Random().nextBytes(data);
    return data;
  }
}
