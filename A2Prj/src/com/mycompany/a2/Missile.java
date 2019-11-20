package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Missile extends ObjectMoveable {
	//from GameObject
	//float xPoint, float yPoint, int color
	//from objectMoveable
	//int speed, int direction
	
	private int fuelLevel;
	private Ship owner;
	
	//don't fully understand this code yet
	//given by professor
	public Missile(int speed, int direction, double x, double y, MissileLauncher launcher, Ship ship) {

		this.setFuelLevel(400); 
		this.owner=ship;
		if(this.owner instanceof ShipPlayer) {
			this.setColor(ColorUtil.LTGRAY);
		}
		if(this.owner instanceof ShipNonPlayer) {
			this.setColor(ColorUtil.red(256));
		}
		//this.setSpeed(speed);
		this.setSpeed(10);
		this.setDirection(direction);
		this.setDirection(launcher.getDirection());
		this.setLocation(x, y);
		
	}

	public void setFuelLevel(int i) {
		this.fuelLevel=i;
	}
	
	public String toString() {
		//String parentDesc=super.toString();
		String myDesc="Missile: location = ("+this.getLocationX()+
				", "+this.getLocationY()+") Color = ["+ColorUtil.red(this.getColor())+", "+
				ColorUtil.green(this.getColor())+", "+ColorUtil.blue(this.getColor())+"] Speed = "
				+this.getSpeed()+" Fuel = "+this.fuelLevel+" Direction = "+this.getDirection();
		return myDesc;
	}
	
	public int getFuelLevel() {
		return this.fuelLevel;
	}
	
	public void decrementFuel() {
		
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)(pCmpRelPrnt.getX()+ this.getLocationX());
		int y = (int)(pCmpRelPrnt.getY()+ this.getLocationY());
		int startAngle=360, arcAngle=360;
		g.setColor(this.getColor()); 
	    g.drawArc(x, y, 25, 25, startAngle, arcAngle);
		g.fillArc(x, y, 25, 25, startAngle, arcAngle);
		setGlobalX(x);
		setGlobalY(y);
		setCenter(x+25/2,y+25/2);
		setRadii(25/2);
	}
	
	public boolean collidesWith(ICollider obj) {
		boolean result=false;
		int thisCenterX=this.getCenterX();
		int thisCenterY=this.getCenterY();
		int otherCenterX=((GameObject) obj).getCenterX();
		int otherCenterY=((GameObject) obj).getCenterY();
		
		int dx=thisCenterX-otherCenterX;
		int dy=thisCenterY-otherCenterY;
		int distBetweenCentersSqr=((dx*dx)+(dy*dy));
		
		int thisRadius=this.getRadii();
		int otherRadius=((GameObject)obj).getRadii();
		
		int radiiSqr=(thisRadius*thisRadius+
				2*thisRadius*otherRadius
				+ otherRadius*otherRadius);
		
		if(distBetweenCentersSqr<=radiiSqr) {
			result=true;
		}
		return result;	
	}
	
	public void handleCollision(ICollider otherObject) {
		
	}
}
