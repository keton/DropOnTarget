package com.github.keton.dropOnTargetGame.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.github.keton.dropOnTargetGame.core.DropOnTarget;

public class DropOnTargetActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("com/github/keton/dropOnTargetGame/resources");
    PlayN.run(new DropOnTarget());
  }
}
