package com.github.keton.dropOnTargetGame.core;

import static playn.core.PlayN.*;

import java.util.HashMap;
import java.util.Map;

import playn.core.AssetWatcher;
import playn.core.AssetWatcher.Listener;
import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.ResourceCallback;

public class DropOnTarget implements Game {
	
	public static GroupLayer frontLayer;
	public static GroupLayer backLayer;
	
	public static boolean isDragging=false;
	public static float scale=1;
	
  @SuppressWarnings({ "rawtypes", "unchecked" })
@Override
  public void init() {
    // create and add background image layer
	  
	  frontLayer=graphics().createGroupLayer();
	  backLayer=graphics().createGroupLayer();
	  
	final Image bgImage = assets().getImage("images/beach.png");
   
    bgImage.addCallback(new ResourceCallback(){

		@Override
		public void done(Object resource) {
			// TODO Auto-generated method stub
			if (graphics().screenWidth()>=bgImage.width()||graphics().screenHeight()>=bgImage.height())
			{
			graphics().setSize((int)bgImage.width(), (int)bgImage.height());
			log().debug("Big screen platform");
			}
			else 
			{
				log().debug("Scaled screen platform");
				
				int screenWidth=graphics().screenWidth();
				int screenHeight=graphics().screenHeight();
				
				//int screenWidth=600;
				//int screenHeight=400;
				
				graphics().setSize(
						  screenWidth,
						  screenHeight
						);
				// Keep the same aspect ratio.
				float sx = (float)screenWidth / bgImage.width();
				float sy = (float)screenHeight / bgImage.height();

				// Fit to the available screen without stretching.
				graphics().rootLayer().setScale(Math.min(sx, sy));
				DropOnTarget.scale=Math.min(sx, sy);
				log().debug("Scale is: "+scale);
			}
			
			ImageLayer bgLayer = graphics().createImageLayer(bgImage);
			
			
			    graphics().rootLayer().add(bgLayer);
			    
			    graphics().rootLayer().add(backLayer);
			    graphics().rootLayer().add(frontLayer);
		}

		@Override
		public void error(Throwable err) {
			// TODO Auto-generated method stub
			
		}});
    
   
    
    final String entities[]={"snake","lion","giraffe","monkey"};
    final Map<String, Image[]> imageMap=new HashMap<String, Image[]>();
    
    AssetWatcher aw=new AssetWatcher(new Listener() {
    	  public void done() {
    		    log().debug("image loading done");
    		    int i=0;
    		    for (String s:entities){
    		    	Image arry[]=imageMap.get(s);
    		    	new Entity(arry[0],arry[1],arry[2],i*100,0,i*150,300);
    		    	i++;
    		    }
    		  }

		@Override
		public void error(Throwable e) {
			// TODO Auto-generated method stub
			 log().debug("image loading error");
		}
    		});
    
    
    
    for (String s:entities)
    {
    //new Entity("images/"+s+".png","images/"+s+"-glow.png","images/"+s+"-black.png",0,0,100,100);
    	Image eImage=assets().getImage("images/"+s+".png");
    	Image eGlowImage=assets().getImage("images/"+s+"-glow.png");
    	Image eDropImage=assets().getImage("images/"+s+"-black.png");
    	aw.add(eDropImage);
    	aw.add(eGlowImage);
    	aw.add(eImage);
    	Image arry[]={eImage,eGlowImage,eDropImage};
    	imageMap.put(s, arry);
    }
    log().debug("starting image load");
    aw.start();
  }

  @Override
  public void paint(float alpha) {
    // the background automatically paints itself, so no need to do anything here!
	  
  }

  @Override
  public void update(float delta) {
  }

  @Override
  public int updateRate() {
    return 25;
  }
}
