package com.github.keton.dropOnTargetGame.core;

import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Layer;
import playn.core.Mouse.ButtonEvent;
import playn.core.Touch.Event;

import playn.core.Mouse.MotionEvent;
import playn.core.Mouse.WheelEvent;
import pythagoras.f.Point;
import pythagoras.f.Rectangle;

public class Entity {

	private class MouseEventProcessor implements playn.core.Mouse.LayerListener{


		@Override
		public void onMouseDown(ButtonEvent event) {
			// TODO Auto-generated method stub
			mouseDownPoint.set(event.localX(), event.localY());
			DropOnTarget.isDragging=true;
		}

		@Override
		public void onMouseUp(ButtonEvent event) {
			// TODO Auto-generated method stub
			Point p=Layer.Util.layerToScreen(entityLayer, event.localX(), event.localY());
	 		p.x-=mouseDownPoint.x();
	 		p.y-=mouseDownPoint.y();
	 		p.x/=DropOnTarget.scale;
			p.y/=DropOnTarget.scale;
			
	 		Rectangle checkRect=new Rectangle(p.x(),p.y(),entityRect.width(),entityRect.height());
	 		if (dropRect.intersects(checkRect))
	 		{
	 			entityLayer.setTranslation(dropRect.x(), dropRect.y());
	 			entityLayer.setInteractive(false);
	 		}
	 		else entityLayer.setTranslation(p.x(), p.y());
			DropOnTarget.isDragging=false;
		}

		@Override
		public void onMouseDrag(MotionEvent event) {
			// TODO Auto-generated method stub
			Point p=Layer.Util.layerToScreen(entityLayer, event.localX(), event.localY());
	 		p.x-=mouseDownPoint.x();
	 		p.y-=mouseDownPoint.y();
			p.x/=DropOnTarget.scale;
			p.y/=DropOnTarget.scale;
	 		//log().debug("MM lX:"+event.localX()+" px: "+p.x());
	 		Rectangle checkRect=new Rectangle(p.x(),p.y(),entityRect.width(),entityRect.height());
	 		if (dropRect.intersects(checkRect))
	 		{
	 			entityLayer.setTranslation(dropRect.x(), dropRect.y());
	 			//entityLayer.setInteractive(false);
	 		}
	 		else entityLayer.setTranslation(p.x(), p.y());
		}

		@Override
		public void onMouseOver(MotionEvent event) {
			// TODO Auto-generated method stub
			if(!DropOnTarget.isDragging)
			{
			entityLayer.setImage(entityGlow);
			entityLayer.setDepth(200);
			}
		}

		@Override
		public void onMouseOut(MotionEvent event) {
			// TODO Auto-generated method stub
			entityLayer.setImage(entityImage);
			entityLayer.setDepth(100);
			
		
		}

		@Override
		public void onMouseWheelScroll(WheelEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class TouchEventProcessor implements playn.core.Touch.LayerListener{

		@Override
		public void onTouchStart(Event touch) {
			// TODO Auto-generated method stub
			mouseDownPoint.set(touch.localX(), touch.localY());
			DropOnTarget.isDragging=true;
			entityLayer.setImage(entityGlow);
			entityLayer.setDepth(200);
		}

		@Override
		public void onTouchMove(Event touch) {
			// TODO Auto-generated method stub
			Point p=Layer.Util.layerToScreen(entityLayer, touch.localX(), touch.localY());
	 		p.x-=mouseDownPoint.x();
	 		p.y-=mouseDownPoint.y();
	 		p.x/=DropOnTarget.scale;
			p.y/=DropOnTarget.scale;
			
	 		Rectangle checkRect=new Rectangle(p.x(),p.y(),entityRect.width(),entityRect.height());
	 		if (dropRect.intersects(checkRect))
	 		{
	 			entityLayer.setTranslation(dropRect.x(), dropRect.y());
	 			//entityLayer.setInteractive(false);
	 		}
	 		else entityLayer.setTranslation(p.x(), p.y());
		}

		@Override
		public void onTouchEnd(Event touch) {
			// TODO Auto-generated method stub
			Point p=Layer.Util.layerToScreen(entityLayer, touch.localX(), touch.localY());
	 		p.x-=mouseDownPoint.x();
	 		p.y-=mouseDownPoint.y();
	 		p.x/=DropOnTarget.scale;
			p.y/=DropOnTarget.scale;
	 		
	 		Rectangle checkRect=new Rectangle(p.x(),p.y(),entityRect.width(),entityRect.height());
	 		if (dropRect.intersects(checkRect))
	 		{
	 			entityLayer.setTranslation(dropRect.x(), dropRect.y());
	 			entityLayer.setInteractive(false);
	 		}
	 		else entityLayer.setTranslation(p.x(), p.y());
			DropOnTarget.isDragging=false;
			entityLayer.setImage(entityImage);
			entityLayer.setDepth(100);
			DropOnTarget.isDragging=false;
		}
		
	}
	
	private Image entityImage;
	private Image entityGlow;
	private Image entityDrop;
	ImageLayer entityLayer;
	ImageLayer dropLayer;
	private Point mouseDownPoint=new Point(0,0);
	private Rectangle entityRect;
	private Rectangle dropRect;
	
	Entity(Image eImage, Image eGlow, Image eDrop, float eX, float eY, float dX, float dY)
	{
		entityImage=eImage;
		entityGlow=eGlow;
		entityDrop=eDrop;
		entityLayer=graphics().createImageLayer(entityImage);
		
		if (mouse().hasMouse()) entityLayer.addListener(new MouseEventProcessor());
		else if(touch().hasTouch()) entityLayer.addListener(new TouchEventProcessor());
		else log().debug("no pointing device found");
		
		
		entityLayer.setTranslation(eX, eY);
		entityRect=new Rectangle(eX,eY,entityImage.width(),entityImage.height());
		
		dropLayer=graphics().createImageLayer(entityDrop);
		dropLayer.setTranslation(dX, dY);
		dropRect=new Rectangle(dX,dY,entityDrop.width(),entityDrop.height());
		entityLayer.setDepth(100);
		DropOnTarget.backLayer.add(dropLayer);
		DropOnTarget.frontLayer.add(entityLayer);
		
		
	}

	

	
	
}
