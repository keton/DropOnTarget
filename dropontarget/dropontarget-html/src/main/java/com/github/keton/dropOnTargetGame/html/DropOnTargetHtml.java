package com.github.keton.dropOnTargetGame.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import com.github.keton.dropOnTargetGame.core.DropOnTarget;

public class DropOnTargetHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("dropontarget/");
    PlayN.run(new DropOnTarget());
  }
}
