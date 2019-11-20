package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class PointsView extends Container implements Observer {
	
		
	private Label pointsValueLabel;
	private Label MissileCountValueLabel;
	private Label elapsedTimeValueLabel;
	private Label soundValueLabel;
	private Label getLivesRemaining;
	
	public PointsView() {
		
		this.setLayout(new FlowLayout(CENTER));
		this.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		this.getAllStyles().setBgTransparency(0);
		
		//labels
		
		
		pointsValueLabel = new Label ("Points: 0");
		MissileCountValueLabel = new Label ("Missile Count: 10");
		elapsedTimeValueLabel = new Label ("Time: 0");
		soundValueLabel = new Label("Sound: OFF");
		getLivesRemaining = new Label("Lives: 3   ");
		
		
		this.add(pointsValueLabel);
		this.add(MissileCountValueLabel);
		this.add(elapsedTimeValueLabel);
		this.add(soundValueLabel);
		this.add(getLivesRemaining);
	}

	
		public void update(Observable observable, Object data) {
			Integer missilesRemaining = ((IGameWorld) data).getMissileCount();
			Integer remainingTime = (int)((IGameWorld) data).getElapsedTime();
			Integer livesRemaining = ((IGameWorld) data).getLivesRemaining();
			Integer whatIsPlayerScore = ((IGameWorld) data).getPlayerScore();
			this.MissileCountValueLabel.setText("Missile Count: " + Integer.toString(missilesRemaining));
			elapsedTimeValueLabel.setText("Time: " + Integer.toString(remainingTime));
			getLivesRemaining.setText("Lives: " + Integer.toString(livesRemaining));
			pointsValueLabel.setText("Points: " + Integer.toString(whatIsPlayerScore));
			if(((IGameWorld) data).isSoundEnabled() == true) {
				soundValueLabel.setText("Sound: ON");
			}
			else {
				soundValueLabel.setText("Sound: OFF");
			}
			this.repaint();
		}
}
