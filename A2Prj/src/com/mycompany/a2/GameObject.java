package com.mycompany.a2;
import java.util.Random;

import com.codename1.ui.geom.Point2D;

abstract class GameObject {
	
	//keeping these for when calling getLocation
	private double xPoint;
	private double yPoint;
	//
	Point2D location = new Point2D(xPoint, yPoint);

	//eclipse recommended making this static, so here it is
	private int color;

	public GameObject() {

	}

	//these methods are kind of cheating but I haven't figured out how to 
	//return the x and y coords without storing them in local variables
	public double getLocationX() {
		return xPoint;
	}

	public double getLocationY() {
		return yPoint;
	}
	
	public Point2D getLocation() {
		return location;
	}
	
	public void setLocation(double x, double y) {
		xPoint= x;
		yPoint= y;
	}
	
	/*@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}*/
	
	public int getColor() {
		return this.color;
	}
	
	public void setColor(int color) {
		this.color=color;
	}
}
