package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class PointsView extends Container implements Observer {

	public PointsView() {
		
	}
	
	private Label pointsValueLabel;
	private Label MissileCountValueLabel;	
	private Label elapsedTimeValueLabel;
	private Label soundValueLabel;
	
	public void PointsView() {
		
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
}
