package com.mycompany.a2;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a2.commands.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.lang.Object;


public class Game extends Form implements Runnable	 {
	
	private GameWorld gWorld;
	private MapView mView;
	private PointsView pView;
	private Object displayDeadScreen;
	//private GameWorldProxy proxy;
	 
	
	public Game() {
		
		//Set up Observable/Observer
		gWorld = new GameWorld();
		//gWorld.init();
		mView = new MapView();
		pView = new PointsView();
		//proxy = new GameWorldProxy(gWorld);
		//Add center container for game map
		/*this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, mView);*/
		
		//Add north container for points view stuff
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, pView);
		
		//this.setLayout(new BorderLayout());
		//this.add(BorderLayout.CENTER, mView);
		/*Container centerContainer=new Container();
		centerContainer.getAllStyles().setBgColor(ColorUtil.WHITE);
		centerContainer.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.BLACK));
		add(BorderLayout.CENTER,centerContainer);*/
		
		this.add(BorderLayout.CENTER, mView);
		//centerContainer.add(mView);
		gWorld.addObserver(mView);
		gWorld.addObserver(pView);
		
		//Toolbar
		Toolbar toolbar = new Toolbar();
		setToolbar(toolbar);
		toolbar.setTitle("Asteroids Game");
		
		//Declare all the needed Commands in the West Container
		//this.setLayout(new BorderLayout());
		Container westContainer = new Container();
		westContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		Label commandLabel = new Label("Commands");
		westContainer.add(commandLabel);
		this.add(BorderLayout.WEST, westContainer);
		
		QuitCommand myQuitCommand = new QuitCommand(gWorld);
		AboutCommand myAboutCommand = new AboutCommand(gWorld);
		CheckBox soundCheckbox = new CheckBox();
		SoundCommand mySoundCommand = new SoundCommand(gWorld);
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
		this.addKeyListener('Q', myQuitCommand);
		
		AddAsteroidCommand myAsteroidCommand = new AddAsteroidCommand(gWorld);
		GameButton addAsteroid = new GameButton(myAsteroidCommand);
		westContainer.add(addAsteroid);
		
		AddNPSCommand myNPSCommand = new AddNPSCommand(gWorld);
		GameButton addNPS = new GameButton(myNPSCommand);
		westContainer.add(addNPS);
		
		AddBlinkingSpaceStationCommand myBSSCommand = new AddBlinkingSpaceStationCommand(gWorld);
		GameButton addBSS = new GameButton(myBSSCommand);
		westContainer.add(addBSS);
		
		AddPlayerShipCommand myPSCommand = new AddPlayerShipCommand(gWorld);
		GameButton addPS = new GameButton(myPSCommand);
		westContainer.add(addPS);
		
		FireMissileCommand myFirePSMissileCommand = new FireMissileCommand(gWorld); //fires the PS missile
		GameButton addFM = new GameButton(myFirePSMissileCommand);
		westContainer.add(addFM);
		this.addKeyListener(-90, myFirePSMissileCommand);
		
		JumpThroughHyperspaceCommand myJumpHyperspaceCommand = new JumpThroughHyperspaceCommand(gWorld);
		GameButton addJump = new GameButton(myJumpHyperspaceCommand);
		westContainer.add(addJump);
		this.addKeyListener('j', myJumpHyperspaceCommand);
		
		EliminateCommand myEliminateCommand = new EliminateCommand(gWorld); //PS missile hit NPS
		//GameButton eliminateNPS = new GameButton(myEliminateCommand);
		//westContainer.add(eliminateNPS);
		this.addKeyListener('e', myEliminateCommand);
		
		FireNonPlayerMissileCommand myFireNPSCommand = new FireNonPlayerMissileCommand(gWorld);
		//GameButton addFNPM = new GameButton(myFireNPSCommand);
		//westContainer.add(addFNPM);
		this.addKeyListener('L', myFireNPSCommand);
		
		/*MapCommand myMapCommand = new MapCommand(gWorld);
		GameButton addMap = new GameButton(myMapCommand);
		westContainer.add(addMap);*/
		
		/*PrintCommand myPrintCommand = new PrintCommand(gWorld);
		GameButton addPrint = new GameButton(myPrintCommand);
		westContainer.add(addPrint);*/
		
		TurnPlayerMissileLauncherLeftCommand myTurnPlayerMLLCommand = new TurnPlayerMissileLauncherLeftCommand(gWorld);
		//GameButton addTPMLL = new GameButton(myTurnPlayerMLLCommand);
		//westContainer.add(addTPMLL);
		this.addKeyListener('<', myTurnPlayerMLLCommand);
		
		TurnPlayerMissileLauncherRightCommand myTurnPlayerMLRCommand = new TurnPlayerMissileLauncherRightCommand(gWorld);
		//GameButton addTPMLR = new GameButton(myTurnPlayerMLRCommand);
		//westContainer.add(addTPMLR);
		this.addKeyListener('>', myTurnPlayerMLRCommand);
		
		ReloadMissileCommand myReloadCommand = new ReloadMissileCommand(gWorld);
		//GameButton addReload = new GameButton(myReloadCommand);
		//westContainer.add(addReload);
		this.addKeyListener('n', myReloadCommand);
		
		PSKillAsteroidCommand myKillAsteroidCommand = new PSKillAsteroidCommand(gWorld);
		//GameButton addKillAst = new GameButton(myKillAsteroidCommand);
		//westContainer.add(addKillAst);
		this.addKeyListener('k', myKillAsteroidCommand);
		
		NPSMissileKillPSCommand myNPSMissileKillPSCommand = new NPSMissileKillPSCommand(gWorld);
		//GameButton addNPSMK = new GameButton(myNPSMissileKillPSCommand);
		//westContainer.add(addNPSMK);
		this.addKeyListener('E', myNPSMissileKillPSCommand);
		
		PSCollideAsteroidCommand myPSCollideCommand = new PSCollideAsteroidCommand(gWorld);
		//GameButton addPSC = new GameButton(myPSCollideCommand);
		//westContainer.add(addPSC);
		this.addKeyListener('c', myPSCollideCommand);
		
		PSCollideNPSCommand myPSCollideNPS = new PSCollideNPSCommand(gWorld);
		//GameButton addPSCNPS = new GameButton(myPSCollideNPS);
		//westContainer.add(addPSCNPS);
		this.addKeyListener('n', myPSCollideNPS);
		
		AsteroidCollisionCommand myAsteroidCollision = new AsteroidCollisionCommand(gWorld);
		//GameButton addAC = new GameButton(myAsteroidCollision);
		//westContainer.add(addAC);
		this.addKeyListener('x', myAsteroidCollision);
		
		NPSCollideAsteroidCommand myNPSCollideCommand = new NPSCollideAsteroidCommand(gWorld);
		//GameButton addNPSC = new GameButton(myNPSCollideCommand);
		//westContainer.add(addNPSC);
		this.addKeyListener('I', myNPSCollideCommand);
		
		TurnPSLeftCommand myTurnPSLeftCommand = new TurnPSLeftCommand(gWorld);
		//GameButton addTPSL = new GameButton(myTurnPSLeftCommand);
		//westContainer.add(addTPSL);
		this.addKeyListener('l', myTurnPSLeftCommand);
		this.addKeyListener(-93, myTurnPSLeftCommand);
		
		IncreasePSSpeedCommand myIncreasePSSpeedCommand = new IncreasePSSpeedCommand(gWorld);
	    //GameButton addIncreasePS = new GameButton(myIncreasePSSpeedCommand);
		//westContainer.add(addIncreasePS);
		this.addKeyListener('i', myIncreasePSSpeedCommand);
		this.addKeyListener(-91, myIncreasePSSpeedCommand);
		
		DecreasePSSpeedCommand myDecreasePSSpeedCommand = new DecreasePSSpeedCommand(gWorld);
		//GameButton addDecreasePS = new GameButton(myDecreasePSSpeedCommand);
		//westContainer.add(addDecreasePS);
		this.addKeyListener('d', myDecreasePSSpeedCommand);
		this.addKeyListener(-92, myIncreasePSSpeedCommand);
		
		TurnPSRightCommand myTurnPSRightCommand = new TurnPSRightCommand(gWorld);
		//GameButton addTPSR = new GameButton(myTurnPSRightCommand);
		//westContainer.add(addTPSR);
		this.addKeyListener('r', myTurnPSRightCommand);
		this.addKeyListener(-94, myTurnPSLeftCommand);
		
		TickCommand myTickCommand = new TickCommand(gWorld);
		//GameButton addTick = new GameButton(myTickCommand);
		//westContainer.add(addTick);
		this.addKeyListener('t', myTickCommand);
		
		UITimer timer = new UITimer(this);
		timer.schedule(25,true,this);
		
		/*CheckBox soundCheckbox=new CheckBox("Sound");
		SoundCommand mySoundCommand=new SoundCommand(gWorld);*/
		mySoundCommand.putClientProperty("Sound",  soundCheckbox);
		soundCheckbox.getAllStyles().setBgTransparency(200);
		soundCheckbox.setSelected(true);
		soundCheckbox.setCommand(mySoundCommand);
		toolbar.addComponentToSideMenu(soundCheckbox);
		
		this.show();
		
		/*mView.setWidth(centerContainer.getWidth());
		mView.setHeight(centerContainer.getHeight());
		gWorld.setDimension((int)centerContainer.getWidth(),(int)centerContainer.getHeight());
		centerContainer.add(mView);
		
		this.show();*/
	}


	@Override
	public void run() {
	/*if (gWorld.getNumLives==0) {
		gWorld.newGame();
		//System.out.println("You have 0 lives left! Game over!");
		final String youDiedMsg = ("You have no lives left! ");
		if (Dialog.show("Game over!", youDiedMsg, "Okay, cool", null));
		}*/
	
		gWorld.tick();
	}

	
}
