package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Missile extends ObjectMoveable {
	//from GameObject
	//float xPoint, float yPoint, int color
	//from objectMoveable
	//int speed, int direction
	
	private int fuelLevel;
	//private Ship owner;
	
	//don't fully understand this code yet
	//given by professor
	public Missile() {

		this.setFuelLevel(10); 
		this.setColor(ColorUtil.red(256));
		
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
}
