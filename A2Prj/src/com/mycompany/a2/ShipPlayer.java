package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class ShipPlayer extends Ship implements Steerable {
	//from GameObject
	//float xPoint, float yPoint, int color
	
	//from objectMoveable
	//int speed, int direction
	
	//from ship
	//int missileCount
	
	public ShipPlayer() {
		//set location of PS to middle of map
		/*this.xPoint=512;
		this.yPoint=384;*/
		
		this.setLocation(512,384);
		//set initial speed to 0
		this.setSpeed(0);
		//set initial direction to north
		this.setDirection(0);
		//set missile amount at 10
		this.setMissileCount(10);
		//set color to green for the good side
		this.setColor(ColorUtil.GREEN);
		
		this.launcherSteerable= new MissileLauncherSteerable();
		launcherSteerable.setDirection(this.getDirection());
		launcherSteerable.setLocation(this.getLocationX(), this.getLocationY());
		launcherSteerable.setSpeed(this.getSpeed());
	}
	
	private MissileLauncherSteerable launcherSteerable;
	
	/*+shipPlayer(double, double, int, int, int, int)
	+increaseSpeed(): void
	+decreaseSpeed(): void
	+turnLeft(): void
	+turnRight(): void
	+reloadMissiles(): void
	+getDirection(): int
	+revolve(): void*/
	
	public void increaseSpeed() {
		
	}
	
	public void decreaseSpeed() {
		this.setSpeed(getSpeed()-5);
		System.out.println("Player speed slowed down");
	}
	
	public void turnLeft() {
		this.setDirection(this.getDirection()-5);
		System.out.println("Player turned slightly left");
	}
	
	public void turnRight() {
		this.setDirection(this.getDirection()+5);
		System.out.println("Player turned slightly right");
	}
	
	public void reloadMissiles() {
		
	}
	
	public void revolveML() {
		launcherSteerable.setDirection(launcherSteerable.getDirection()+5);
	}
	
	public String toString() {
		//TODO Colors check module 3
		//String parentDesc=super.toString();
		String myDesc="PS: location = ("+this.getLocationX()+
				", "+this.getLocationY()+") Color = ["+ColorUtil.red(this.getColor())+", "+
				ColorUtil.green(this.getColor())+", "+ColorUtil.blue(this.getColor())+"] Speed = "
				+this.getSpeed()+" dir = "+this.getDirection()+" missiles ="+
				this.getMissileCount()+" Missile Launcher dir "+this.getDirection();
		return myDesc;
	}

	public void jumpThroughHyperspace() {
		this.setLocation(512, 384);
	}
	
}
