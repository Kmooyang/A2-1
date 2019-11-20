package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class ShipPlayer extends Ship implements Steerable{
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
		//int width = 15;
		//int height = 15;
		this.setLocation(100,100);
		//set initial speed to 0
		this.setSpeed(0);
		//set initial direction to north
		this.setDirection(360);
		//set missile amount at 10
		this.setMissileCount(10);
		//set color to green for the good side
		this.setColor(ColorUtil.GREEN);
		
		this.launcherSteerable= new MissileLauncherSteerable(this.getDirection(),
				this.getLocationX(), this.getLocationY(), this.getSpeed());
	}
	
	private MissileLauncherSteerable launcherSteerable;

	
	public void increaseSpeed() {
		this.setSpeed(getSpeed()+5);
		System.out.println("Player speed slowed down");
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
	
	public MissileLauncher getMissileLauncher() {
		return launcherSteerable;
	}
	
	public void turnLauncherRight() {
		this.launcherSteerable.turnRight();
	}
	
	public void turnLauncherLeft() {
		this.launcherSteerable.turnLeft();

	}
	
	public void move(double minHeight, double minWidth, double maxHeight, double maxWidth) {
		this.setLocation((this.getLocationX()+(Math.cos(90-this.getDirection())*this.getSpeed())), 
				((this.getLocationY()+(Math.sin(90-this.getDirection())*this.getSpeed()))));
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		int x = (int)(pCmpRelPrnt.getX()+ this.getLocationX());
		int y = (int)(pCmpRelPrnt.getY()+ this.getLocationY());
		
		//int startAngle=360, arcAngle=360;
		g.setColor(this.getColor()); 
	    g.drawRect(x, y, 50, 50);
		g.fillRect(x, y, 50, 50);
		g.drawRect(x, y+50, 10, 10);
		g.fillRect(x, y+50, 10, 10);
		g.drawRect(x+40, y+50, 10, 10);
		g.fillRect(x+40, y+50, 10, 10);
	}
}
