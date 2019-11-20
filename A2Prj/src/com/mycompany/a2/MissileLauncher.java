package com.mycompany.a2;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MissileLauncher extends ObjectMoveable {
	//from objectMoveable
	//int speed, int direction
	
	public MissileLauncher(int direction, double x, double y, int speed) {
		
		this.setDirection(direction);
		this.setLocation(x, y);
		this.setSpeed(speed);
		
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}
}
