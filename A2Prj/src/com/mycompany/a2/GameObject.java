package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

abstract class GameObject implements IDrawable, ICollider{
	
	//keeping these for when calling getLocation
	private double xPoint;
	private double yPoint;
	private int globalX;
	private int globalY;
	private int centerX;
	private int centerY;
	private int radii;
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
	
	public void setCenter(int x, int y) {
		centerX=x;
		centerY=y;
	}
	
	public int getCenterX() {
		return centerX;
	}
	
	public int getCenterY() {
		return centerY;
	}
	
	public void setRadii(int r) {
		radii=r;
	}
	
	public int getRadii() {
		return radii;
	}
	
	public void setLocation(double x, double y) {
		xPoint= x;
		yPoint= y;
	}
	
	public int getGlobalX() {
		return globalX;
	}
	
	public int getGlobalY() {
		return globalY;
	}
	
	public void setGlobalX(int x) {
		globalX=x;
	}
	public void setGlobalY(int x) {
		globalY=x;
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

	public void draw(Graphics g, Point pCmpRelPrnt) {
		
	}
	public boolean collidesWith(ICollider otherObject) {
		return false;
	}
	public void handleCollision(ICollider otherObject) {
		
	}

}
