package com.github.keton.dropOnTargetGame.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.github.keton.dropOnTargetGame.core.DropOnTarget;

public class DropOnTargetJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("com/github/keton/dropOnTargetGame/resources");
    PlayN.run(new DropOnTarget());
  }
}
