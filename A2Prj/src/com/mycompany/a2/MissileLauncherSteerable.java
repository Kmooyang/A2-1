package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class MissileLauncherSteerable extends MissileLauncher implements Steerable {
	//from GameObject
	//int color
	//from objectMoveable
	//int speed, int direction
	
	public MissileLauncherSteerable(int direction, double x,
			double y, int speed) {
		super(direction, x, y, speed);
		this.setColor(ColorUtil.CYAN);
		this.setDirection(direction);
		this.setLocation(x, y);
		this.setSpeed(speed);
	}
	public void turnLeft() {
		this.setDirection(this.getDirection()-5);
	}
	public void turnRight() {
		this.setDirection(this.getDirection()+5);
	}
}
