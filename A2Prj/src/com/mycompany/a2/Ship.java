package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

abstract class Ship extends ObjectMoveable {
	//from GameObject
	//float xPoint, float yPoint, int color
	//from objectMoveable
	//int speed, int direction
	
	private int missileCount;
	
	public Ship() {
			
	}
	
	public void setMissileCount(int miss) {
		this.missileCount=miss;
	}
	
	public int getMissileCount() {
		return this.missileCount;
	}
	
	public void decrementMissileCount() {
		missileCount-=1;
	}
	
}