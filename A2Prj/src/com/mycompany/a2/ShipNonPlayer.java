package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class ShipNonPlayer extends Ship {
	//from GameObject
	//float xPoint, float yPoint, int color
	
	//from objectMoveable
	//int speed, int direction
	
	//from ship
	//int missileCount
	
	private int size;
	private MissileLauncher NPSLauncher;
	
	public ShipNonPlayer() {
		Random r = new Random();
		//couldn't figure out how to do 15 or 25
		//so if random greater than 5, do 15, else do 25
		if (r.nextInt(10)>5) {
			size = 15;
		}else
		{
			size =25;
		}
		this.setLocation((r.nextInt(1024)+r.nextFloat()),(r.nextInt(768)+r.nextFloat()));
		//set color of NPS to yellow
		this.setColor(ColorUtil.YELLOW);
		//set speed of NPS between 0 and 14
		this.setSpeed(r.nextInt(15));
		//set direction of NPS to random
		this.setDirection(r.nextInt(360));	
		//set max missile count of NPS to 4
		this.setMissileCount(4);
	}
	
	public String toString() {
		//String parentDesc=super.toString();
		String myDesc="NPS: location = ("+this.getLocationX()+
				", "+this.getLocationY()+") Color = ["+ColorUtil.red(this.getColor())+", "+
				ColorUtil.green(this.getColor())+", "+ColorUtil.blue(this.getColor())+"] Speed = "
				+this.getSpeed()+" dir = "+this.getDirection()+" size = "+size+" missiles = "+
				this.getMissileCount()+" Missile Launcher dir = "+this.getDirection();
		return myDesc;
	}
	
	public int getSize() {
		return this.size;
	}
}
