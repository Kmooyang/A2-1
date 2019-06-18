package com.mycompany.a2;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.lang.Object;


public class Game extends Form {
	
	private GameWorld gWorld;
	private MapView mView;
	private PointsView pView;
	
	public Game() {
		gWorld = new GameWorld();
		mView=new MapView();
		pView=new PointsView();
		/*gWorld.addObserver(mView);
		gWorld.addObserver(pView);*/
		gWorld.init();
		play();
	}
	private void quit() {
		
	}
	private void play() {
		Label myLabel = new Label ("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				switch(sCommand.charAt(0)) {
				case 'e':
					gWorld.eliminate();
					break;
				case 'a':
					gWorld.addNewAsteroid();
					break;
				case 'y':
					gWorld.addShipNonPlayer();
					break;
				case 'b':
					gWorld.addBlinkingSpaceStation();
					break;
				case 's':
					gWorld.addShipPlayer();
					break;
				case 'f':
					gWorld.fireMissile();
					break;
				case 'L':
					gWorld.fireNonPlayerMissile();
					break;
				case 'm':
					gWorld.map();
					break;
				case 'p':
					gWorld.print();
					break;
				case '>':
					gWorld.turnPlayerMissileLauncher();
					break;
				case 'n':
					gWorld.reloadMissiles();
					break;
				case 'k':
					gWorld.PSKillAsteroid();
					break;
				case 'E':
					gWorld.NPSMissileKillPS();
					break;
				case 'c':
					gWorld.PSCollideAsteroid();
					break;
				case 'h':
					gWorld.PSCollideNPS();
					break;
				case 'x':
					gWorld.AsteroidCollision();
					break;
				case 'I':
					gWorld.NPSCollideAsteroid();
					break;
				case 'l':
					gWorld.turnPlayerShipLeft();
					break;
				case 'd':
					gWorld.decreasePSSpeed();
					break;
				case 'r':
					gWorld.turnPlayerShipRight();
					break;
				case 'j':
					gWorld.jumpThroughHyperspace();
					break;
				case 't':
					gWorld.tick();
				}
			}
		});
	}
}
