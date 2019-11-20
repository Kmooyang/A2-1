package com.mycompany.a2;

//import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
//import com.mycompany.a2.GameObjectCollection.Iterator;

public class MapView extends Container implements Observer {
	
	private GameWorld gWorld;
	private IGameWorld gw;

	Form form2;

	public MapView() {
		//this.getAllStyles().setBgTransparency(250);
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.BLACK));
		//this.gWorld=gWorld;
	}
	
	//@Override
	public void update(Observable observable, Object data) {

		this.gw=(IGameWorld) data;
		this.repaint();

		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(!(this.gw==null)) 	{
			IIterator iter = this.gw.getIterator();
			Point pCmpRelPrnt=new Point(getX(), getY());
			while(iter.hasNext()) {
				GameObject go = (GameObject) iter.getNext();
				if(go instanceof IDrawable) {
					((IDrawable)go).draw(g, pCmpRelPrnt);
				}
			}
		}
	}
}
