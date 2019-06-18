package com.mycompany.a2;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;

import Commands.*;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.lang.Object;


public class Game extends Form {
	
	private GameWorld gWorld;
	private MapView mView;
	private PointsView pView;
	 
	
	public Game() {
		
		//Set up Observable/Observer
		gWorld = new GameWorld();
		mView = new MapView();
		pView = new PointsView();
		gWorld.addObserver(mView);
		gWorld.addObserver(pView);
		
		//Add center container for game map
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, mView);
		
		//Add north container for points view stuff
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, pView);
		
		
		//Toolbar
		Toolbar toolbar = new Toolbar();
		setToolbar(toolbar);
		toolbar.setTitle("Asteroids");
		
		//Declare all the needed Commands in the West Container
		this.setLayout(new BorderLayout());
		Container westContainer = new Container();
		westContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		Label commandLabel = new Label("Commands");
		westContainer.add(commandLabel);
		this.add(BorderLayout.WEST, westContainer);
		
		QuitCommand myQuitCommand = new QuitCommand(gWorld);
		AboutCommand myAboutCommand = new AboutCommand(gWorld);
		CheckBox soundCheckbox = new CheckBox();
		SoundCommand mySoundCommand = new SoundCommand(soundCheckbox, gWorld);
    	soundCheckbox.setCommand(mySoundCommand);
    	UndoCommand myUndoCommand = new UndoCommand(gWorld);
    	SaveCommand mySaveCommand = new SaveCommand(gWorld);
    	NewCommand myNewCommand = new NewCommand(gWorld);
    	
		//Buttons that invoke commands & their key bindings
		
		Button buttonOne = new Button("New");
		buttonOne.setCommand(myNewCommand);
		toolbar.addCommandToSideMenu(myNewCommand);
		
		Button buttonTwo = new Button("Save");
		buttonTwo.setCommand(mySaveCommand);
		toolbar.addCommandToSideMenu(mySaveCommand);
		
		Button buttonThree = new Button("Undo");
		buttonThree.setCommand(myUndoCommand);
		toolbar.addCommandToSideMenu(myUndoCommand);
		
		Button buttonFour = new Button("Sound");
		buttonFour.setCommand(mySoundCommand);
		toolbar.addCommandToSideMenu(mySoundCommand);
		
		Button buttonFive = new Button("About");
		buttonFive.setCommand(myAboutCommand);
		toolbar.addCommandToSideMenu(myAboutCommand);
		
		Button buttonSix = new Button("Quit");
		buttonSix.setCommand(myQuitCommand);
		toolbar.addCommandToSideMenu(myQuitCommand);
		this.addKeyListener('q', myQuitCommand);
		
		AddAsteroidCommand myAsteroidCommand = new AddAsteroidCommand(gWorld);
		GameButton addAsteroid = new GameButton(myAsteroidCommand);
		westContainer.add(addAsteroid);
		
		EliminateCommand myEliminateCommand = new EliminateCommand(gWorld);
		GameButton eliminateNPS = new GameButton(myEliminateCommand);
		westContainer.add(eliminateNPS);
		
		AddNPSCommand myNPSCommand = new AddNPSCommand(gWorld);
		GameButton addNPS = new GameButton(myNPSCommand);
		westContainer.add(addNPS);
		
		AddBlinkingSpaceStationCommand myBSSCommand = new AddBlinkingSpaceStationCommand(gWorld);
		GameButton addBSS = new GameButton(myBSSCommand);
		westContainer.add(addBSS);
		
		AddPlayerShipCommand myPSCommand = new AddPlayerShipCommand(gWorld);
		GameButton addPS = new GameButton(myPSCommand);
		westContainer.add(addPS);
		
		FireMissileCommand myFireMissileCommand = new FireMissileCommand(gWorld);
		GameButton addFM = new GameButton(myFireMissileCommand);
		westContainer.add(addFM);
		
		FireNonPlayerMissileCommand myFireNPSCommand = new FireNonPlayerMissileCommand(gWorld);
		GameButton addFNPM = new GameButton(myFireNPSCommand);
		westContainer.add(addFNPM);
		
		MapCommand myMapCommand = new MapCommand(gWorld);
		GameButton addMap = new GameButton(myMapCommand);
		westContainer.add(addMap);
		
		PrintCommand myPrintCommand = new PrintCommand(gWorld);
		GameButton addPrint = new GameButton(myPrintCommand);
		westContainer.add(addPrint);
		
		TurnPlayerMissileLauncherCommand myTurnPlayerMLCommand = new TurnPlayerMissileLauncherCommand(gWorld);
		GameButton addTPML = new GameButton(myTurnPlayerMLCommand);
		westContainer.add(addTPML);
		
		ReloadMissileCommand myReloadCommand = new ReloadMissileCommand(gWorld);
		GameButton addReload = new GameButton(myReloadCommand);
		westContainer.add(addReload);
		
		PSKillAsteroidCommand myKillAsteroidCommand = new PSKillAsteroidCommand(gWorld);
		GameButton addKillAst = new GameButton(myKillAsteroidCommand);
		westContainer.add(addKillAst);
		
		NPSMissileKillPSCommand myNPSMissileKillPSCommand = new NPSMissileKillPSCommand(gWorld);
		GameButton addNPSMK = new GameButton(myNPSMissileKillPSCommand);
		westContainer.add(addNPSMK);
		
		PSCollideAsteroidCommand myPSCollideCommand = new PSCollideAsteroidCommand(gWorld);
		GameButton addPSC = new GameButton(myPSCollideCommand);
		westContainer.add(addPSC);
		
		PSCollideNPSCommand myPSCollideNPS = new PSCollideNPSCommand(gWorld);
		GameButton addPSCNPS = new GameButton(myPSCollideNPS);
		westContainer.add(addPSCNPS);
		
		AsteroidCollisionCommand myAsteroidCollision = new AsteroidCollisionCommand(gWorld);
		GameButton addAC = new GameButton(myAsteroidCollision);
		westContainer.add(addAC);
		
		NPSCollideAsteroidCommand myNPSCollideCommand = new NPSCollideAsteroidCommand(gWorld);
		GameButton addNPSC = new GameButton(myNPSCollideCommand);
		westContainer.add(addNPSC);
		
		TurnPSLeftCommand myTurnPSLeftCommand = new TurnPSLeftCommand(gWorld);
		GameButton addTPSL = new GameButton(myTurnPSLeftCommand);
		westContainer.add(addTPSL);
		
		DecreasePSSpeedCommand myDecreasePSSpeedCommand = new DecreasePSSpeedCommand(gWorld);
		GameButton addDecreasePS = new GameButton(myDecreasePSSpeedCommand);
		westContainer.add(addDecreasePS);
		
		TurnPSRightCommand myTurnPSRightCommand = new TurnPSRightCommand(gWorld);
		GameButton addTPSR = new GameButton(myTurnPSRightCommand);
		westContainer.add(addTPSR);
		
		JumpThroughHyperspaceCommand myJumpHyperspaceCommand = new JumpThroughHyperspaceCommand(gWorld);
		GameButton addJump = new GameButton(myJumpHyperspaceCommand);
		westContainer.add(addJump);
		
		TickCommand myTickCommand = new TickCommand(gWorld);
		GameButton addTick = new GameButton(myTickCommand);
		westContainer.add(addTick);
		
		this.show();
	
	}
	
}
