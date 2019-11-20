package com.mycompany.a2;

abstract class ObjectFixed extends GameObject {
	//from GameObject
	//float xPoint, float yPoint, int color
	private int id;
	private int helper;
	
	public ObjectFixed(){
		
	}
	
	public void setLocation(double xPoint, double yPoint) {
		
	}
	
	//get next id implies id ++? right?
	int getNextID() {
		return id++;
	}
}
