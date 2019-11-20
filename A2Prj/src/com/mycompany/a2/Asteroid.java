package com.mycompany.a2;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Asteroid extends ObjectMoveable {
	//from GameObject
	//float xPoint, float yPoint, int color
	//from objectMoveable
	//int speed, int direction
	
	
	protected int size;
	
	
	Random r = new Random();
	
	public Asteroid(){
		//setting asteroid size to between 6 and 30
		this.size = (r.nextInt(24+1)+6);
		//setting asteroid location
		this.setLocation((r.nextInt(1024)+r.nextFloat()),(r.nextInt(768)+r.nextFloat()));
		//setting asteroid color to green
		this.setColor(ColorUtil.WHITE);
		//setting asteroid speed to 0 to 15
		this.setSpeed(r.nextInt(15));
		//setting asteroid direction to a random direction
		this.setDirection(r.nextInt(360));		
	}
	
	public String toString() {
		//String parentDesc=super.toString();
		String myDesc="Asteroid: location = ("+this.getLocationX()+
				", "+this.getLocationY()+") Color = ["+ColorUtil.red(this.getColor())+", "+
				ColorUtil.green(this.getColor())+", "+ColorUtil.blue(this.getColor())+"] Speed = "
				+this.getSpeed();
		return myDesc;
	}
	
	public int getSize() {
		return this.size;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)(pCmpRelPrnt.getX()+ this.getLocationX());
		int y = (int)(pCmpRelPrnt.getY()+ this.getLocationY());
		int startAngle=360, arcAngle=360;
		g.setColor(this.getColor()); 
	    g.drawArc(x, y, 50, 50, startAngle, arcAngle);
		g.fillArc(x, y, 50, 50, startAngle, arcAngle);
	}
}
