package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class SpaceStation extends ObjectFixed {
	//from GameObject
	//float xPoint, float yPoint, int color
	//from objectFixed
	//int id, int helper
	
	Random r = new Random();
	
	private int blinkRate;
	private boolean lightOn;
	
	public SpaceStation() {
		this.setLocation((r.nextInt(1024)+r.nextFloat()), (r.nextInt(768)+r.nextFloat()));
		//set color of station to blue for health
		this.setColor(ColorUtil.BLUE);
		//set blinkrate to between 0 and 6 seconds?
		this.blinkRate=r.nextInt(6+1);
		//set light to on initially
		this.lightOn=true;
	}
	
	public int getBlinkRate() {
		return this.blinkRate;
	}
	
	public void toggleLight() {
		if (lightOn==true)
		{
			lightOn=false;
			System.out.println("The Space Station light has been turned off");
		}
		else
		if (lightOn==false)
		{
			lightOn=true;
			System.out.println("The Space Station light has been turned on");
		}
	}
}
