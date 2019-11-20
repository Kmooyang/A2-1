package com.mycompany.a2;
import java.util.Observable;
//import java.util.Iterator;
import java.util.Random;
//import java.util.Vector;
//import java.util.Observerable;
//import java.util.Vector.clone();

import com.codename1.ui.geom.Point;
import com.mycompany.a2.GameObjectCollection.Iterator;
import com.mycompany.a2.sounds.BackgroundMusic;
import com.mycompany.a2.sounds.Sound;

//import com.codename1.charts.util.ColorUtil;
//import com.mycompany.a2.GameObjectCollection.Iterator;

//import com.codename1.charts.util.ColorUtil;

public class GameWorld extends Observable implements IGameWorld {
	
	//random will be used for randomized integers and floats etc
	Random random = new Random();
	//this will be the storage for the objects in the game
	//limitless, atleast until RAM runs out
	private GameObjectCollection store = new GameObjectCollection();
	
	BackgroundMusic gameBackgroundMusic = new BackgroundMusic("Alien.mp3");
	Sound shipFiresMissile = new Sound("rocket2.wav");
	Sound PSMissileLauncherRotates = new Sound("compidle.wav");
	Sound gameOverNoLives = new Sound("zdeblo00.wav");
	
	int numLives =3;
	float gameTime=0;
	int playerScore=0;
	int numPlayerMissiles=10;
	int numNonPlayerMissiles=4;
	double windowWidth;
	double windowLength;
	boolean hasPlayerShip;
	boolean soundToggle=true;
	double windowHeight;
	double minWidth;
	double minHeight;
	public int getNumLives;
	
	public GameWorld() {
		init();
	}
	
	public void init() {
		//this.addShipPlayer();
		gameBackgroundMusic.play();
	}
	
	public void proxy() {
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	public void addNewAsteroid() {
		
		Asteroid asteroid1 = new Asteroid();
		
		//Adds an asteroid to the vector
		store.add(asteroid1);
		System.out.println("An asteroid has been created");
		
		proxy();
	}
	
	public void addShipNonPlayer() {
		//Creates a non player ship object
		ShipNonPlayer NPS = new ShipNonPlayer();
		//Adds an asteroid to the vector
		store.add(NPS);
		
		System.out.println("An NPS has been added");
		
		proxy();
	}
	
	public void addBlinkingSpaceStation() {
		
		SpaceStation station = new SpaceStation();
		
		//Adds a station to the vector
		store.add(station);
		System.out.println("A space station has been added");
		proxy();
	}
	
	public void addShipPlayer() {
		if(hasPlayerShip==false) {
			ShipPlayer player= new ShipPlayer();
			store.add(player);
			System.out.println("A player ship has been added");
			numLives=3;
			//once a player ship is added, change boolean to show that
			//a player ship exists
			hasPlayerShip=true;
		}else
		{
			System.out.println("Player ship already exists");
		}
		proxy();
	}
	
	public void fireMissile() {
		boolean ps=false;
		Iterator bank = this.getIterator();
		while(bank.hasNext()) {
			GameObject shipclone = (GameObject) bank.getNext();
			if(shipclone instanceof ShipPlayer) {
				ps=true;
				//once ShipPlayer found, make clone of player ship
				ShipPlayer clone= new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				//use clone to call methods
				if (clone.getMissileCount()>0) {
					Missile PSmissile = new Missile(clone.getSpeed(), clone.getDirection(),
							clone.getLocationX(), clone.getLocationY(), clone.getMissileLauncher(), clone);
					clone.decrementMissileCount();
					store.add(PSmissile);
					numPlayerMissiles-=1;
					proxy();
					System.out.println("Player has fired a missile");
					shipFiresMissile.play();
					//clone.setMissileCount(clone.getMissileCount()-1);
				}else {
					System.out.println("You have run out of missiles!");
				}
				break;
			}
		}
		if(ps==false) {
			System.out.println("A PS is not in play");
		}
	} 
	
	public void fireNonPlayerMissile() {
		boolean nps=false;
		Iterator bank=this.getIterator();
		//looking through vector for object of type NPS
		while (bank.hasNext()) {
			GameObject shipclone = (GameObject) bank.getNext();
			if(shipclone instanceof ShipNonPlayer) {
				nps=true;
				//when found, create clone to call object methods
				ShipNonPlayer clone= new ShipNonPlayer();
				clone = (ShipNonPlayer) shipclone;
				//use clone to call methods
				if (clone.getMissileCount()>0) {
					Missile NPSmissile = new Missile(clone.getSpeed(), clone.getDirection(),
							clone.getLocationX(), clone.getLocationY(), clone.getMissileLauncher(), clone);
					clone.decrementMissileCount();
					store.add(NPSmissile);
					proxy();
					System.out.println("NPS has fired a missile");
					//clone.setMissileCount(clone.getMissileCount()-1);
				}else {
					proxy();
					System.out.println("NPS out of missiles");
				}
				break;
			}
		}
		if(nps==false) {
			System.out.println("An NPS is not in play");
		}
	}

	public void eliminate() {
		boolean nps=false;
		boolean missile=false;
		Iterator bank=getIterator();
		//look through vector for object of NPS
		while (bank.hasNext())
		{
			GameObject shipclone = (GameObject) bank.getNext();
			 if(shipclone instanceof ShipNonPlayer) {
				 //when found, make sure there is a missile in there too
				 nps=true;
				 Iterator bank2 = getIterator();
					while (bank2.hasNext())
					{
						GameObject missileclone=(GameObject) bank2.getNext();
						//when found, remove the one with a higher index
						//that way, object doesn't change index and unwanted removal doesn't occur
						 if(missileclone instanceof Missile) {
							 missile=true;
							 bank2.remove(missileclone);
							 bank.remove(shipclone);
							 System.out.println("PS missile has destroyed an NPS");
							 System.out.println("+20 points");
							 playerScore+=20;
							 proxy();
							 break;
						 }
					}
					break;
			}
		}
		if((nps==false)&&(missile==false)) {
			System.out.println("No NPS's or missiles are in play");
		}else if(nps==false) {
			System.out.println("No NPS's are in play");
		}else if(missile==false) {
			System.out.println("No missiles are in play");
		}
	}


	public void map() {
		
		//find PS in vector, call toString method to print data
		Iterator bank = getIterator();
		while(bank.hasNext())
		{
			GameObject shipclone = (GameObject) bank.getNext();
			if(shipclone instanceof ShipPlayer) {
				System.out.println(shipclone.toString());
				break;
			}
		}
		
		//find NPS in vector, call tpString method to print data
		bank=getIterator();
		while (bank.hasNext())
		{
			GameObject shipclone=(GameObject) bank.getNext();
			if(shipclone instanceof ShipNonPlayer) {
				System.out.println(shipclone.toString());
				break;
			}
		}
		
		//find missile in vector, call tpString method to print data
		bank=getIterator();
		while (bank.hasNext())
		{
			GameObject missileclone=(GameObject) bank.getNext();
			if(missileclone instanceof Missile) {
				System.out.println(missileclone.toString());
				break;
			}
		}
		
		//find asteroid in vector, call tpString method to print data
		bank=getIterator();
		while (bank.hasNext())
		{
			GameObject asteroidclone=(GameObject) bank.getNext();
			if(asteroidclone instanceof Asteroid) {
				System.out.println(asteroidclone.toString());
				break;
			}
		}
		
	}
	
	public void turnPlayerMissileLauncher() {
		//find PS inside of vector
		boolean missilelauncher=false;
		Iterator bank=getIterator();
		while (bank.hasNext()) {
			//clone PS to call methods
			GameObject shipclone=(GameObject) bank.getNext();
			if(shipclone instanceof ShipPlayer) {
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				clone.revolveML();
				missilelauncher=true;
				proxy();
				break;
			}
		}
		if (missilelauncher==false) {
			System.out.println("There is no missile launcher to turn");
		}
	}
	public void print() {
		System.out.println("Score: "+playerScore+" Missiles: "+numPlayerMissiles+" Time Elapsed: "
				+gameTime+" Lives: "+numLives);
	}
	
	public void reloadMissiles() {
		boolean ps=false;
		Iterator bank = this.getIterator();
		while(bank.hasNext()) {
			GameObject shipclone = (GameObject) bank.getNext();
			if(shipclone instanceof ShipPlayer) {
				ps=true;
				//once ShipPlayer found, make clone of player ship
				ShipPlayer clone= new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				if(clone.getMissileCount()<10) {
					clone.setMissileCount(10);
					numPlayerMissiles=10;
					proxy();
					System.out.println("You have picked up missiles");
				}else
				{
					System.out.println("The player has the maximum amount of missiles already");
				}
			}
		}
	}
	
	public void PSKillAsteroid() {
		boolean asteroid = false;
		boolean missile=false;
		Iterator bank=getIterator();
		while (bank.hasNext()) {
			GameObject missileclone=(GameObject) bank.getNext();
			 if(missileclone instanceof Missile) {
				 missile=true;
				 Iterator bank2=getIterator();
					while (bank2.hasNext())
					{
						GameObject asteroidclone =(GameObject) bank2.getNext();
						 if(asteroidclone instanceof Asteroid) {
							 asteroid=true;
							 bank.remove(asteroidclone);
							 bank2.remove(missileclone);
							 System.out.println("PS missile has destroyed an asteroid");
							 System.out.println("+5 points");
							 playerScore+=5;
							 proxy();
							 
							 break;
						 }
					}
					break;
			 }
		}
		if((asteroid==false)&&(missile==false)) {
			System.out.println("No asteroids or missiles are in play");
		}else if(asteroid==false) {
			System.out.println("No asteroids are in play");
		}else if(missile==false) {
			System.out.println("No missiles are in play");
		}
	}
	
	//TODO There can be multiple non player ships,
	//for loop needs to be added to check for a random nonplayer ship
	public void NPSMissileKillPS() {
		boolean missile=false;
		boolean ship=false;
		Iterator bank=getIterator();
		while (bank.hasNext()){
			GameObject missileclone=(GameObject) bank.getNext();
			if(missileclone instanceof Missile) {
				missile=true;
				Iterator bank2=getIterator();
				GameObject shipclone=(GameObject) bank2.getNext();
				while(bank2.hasNext()) {
					if(shipclone instanceof ShipPlayer) {
					  	 ship=true;
						 bank.remove(missileclone);
						 //bank2.remove();
						 System.out.println("NPS Missile has struck you!");
						 System.out.println("-1 life");
						 numLives-=1;
						 proxy();
						 if (numLives==0) {
							 System.out.println("You have died. Player score: "+playerScore);
							 bank2.remove(shipclone);
							 hasPlayerShip=false;
							 proxy();
							 return;
						 }
						 break;
					}
				 }
				break;
			 }
		}
		if((hasPlayerShip==false)&&(missile==false)) {
			System.out.println("No PS's or missiles are in play");
		}else if(hasPlayerShip==false) {
			System.out.println("No PS's are in play");
		}else if(missile==false) {
			System.out.println("No missiles are in play");
		}
	}
	
	public void PSCollideAsteroid() {
		boolean asteroid=false;
		boolean ship = false;
		Iterator bank = getIterator();
		while (bank.hasNext()){
			GameObject asteroidclone=(GameObject) bank.getNext();
			if(asteroidclone instanceof Asteroid) {
				asteroid=true;
				Iterator bank2=getIterator();
				while(bank2.hasNext()) {
					GameObject shipclone=(GameObject) bank2.getNext();
					if(shipclone instanceof ShipPlayer) {
					  	 ship=true;
						 bank.remove(shipclone);
						 bank2.remove(asteroidclone);
						 System.out.println("An asteroid has struck you!");
						 System.out.println("-1 life");
						 numLives-=1;
						 proxy();
						 if (numLives==0) {
							 System.out.println("You have died. Player score: "+playerScore);
							 hasPlayerShip=false;
							 proxy();
							 return;
						 }
						 break;
					}
				 }
				break;
			 }
		}
		if((ship==false)&&(asteroid==false)) {
			System.out.println("No PS's or asteroids are in play");
		}else if(ship==false) {
			System.out.println("No PS's are in play");
		}else if(asteroid==false) {
			System.out.println("No asteroids are in play");
		}
	}
	
	//TODO check for if game is over
	//Same case for any numLives-=1;
	public void PSCollideNPS() {
		boolean ps=false;
		boolean nps=false;
		Iterator bank = getIterator();
		while (bank.hasNext())
		{
			GameObject shipclone= (GameObject) bank.getNext();
			 if(shipclone instanceof ShipPlayer) {
				 ps=true;
				 Iterator bank2 = getIterator();
					while (bank2.hasNext())
					{
						GameObject nonshipclone=(GameObject) bank2.getNext();
						 if(nonshipclone instanceof ShipNonPlayer) {
							 nps=true;
							 bank.remove(shipclone);
							 bank2.remove(nonshipclone);
							 System.out.println("PS has crashed into an NPS!");
							 System.out.println("-1 life");
							 numLives-=1;
							 if (numLives==0) {
								 System.out.println("You have died. Player score: "+playerScore);
								 hasPlayerShip=false;
								 return;
							 }
							 break;
						 }
					}
					break;
			 }
		}
		if((ps==false)&&(nps==false)) {
			System.out.println("No NPS's or NP's are in play");
		}else if(nps==false) {
			System.out.println("No NPS's are in play");
		}else if(ps==false) {
			System.out.println("No PS's are in play");
		}
	}
	
	public void AsteroidCollision() {
		boolean asteroid1=false;
		boolean asteroid2=false;
		Iterator bank = getIterator();
		while (bank.hasNext())
		{
			GameObject asteroidclone = (GameObject) bank.getNext();
			if(asteroidclone instanceof Asteroid) {
				asteroid1=true;
				Iterator bank2=getIterator();
				while (bank2.hasNext())
				{
					GameObject asteroidclone2 = (GameObject) bank2.getNext();
					if(asteroidclone2 instanceof Asteroid) {
						asteroid2=true;
						bank.remove(asteroidclone);
						bank2.remove(asteroidclone2);
						System.out.println("Two Asteroids have collided!");
						break;
					}
				}
				break;
			}
		}
		if((asteroid1==false)||(asteroid2==false)) {
			System.out.println("Only one asteroid exists");
		}else if((asteroid1==false)&&(asteroid2==false)) {
			System.out.println("No asteroids exists");

		}
	}
	
	public void NPSCollideAsteroid() {
		boolean nps=false;
		boolean asteroid=false;
		Iterator bank = getIterator();
		while (bank.hasNext())
		{
			GameObject nonshipclone=(GameObject) bank.getNext();
			 if(nonshipclone instanceof ShipNonPlayer) {
				 nps=true;
				 Iterator bank2 = getIterator();
					while (bank2.hasNext())
					{
						GameObject asteroidclone=(GameObject) bank2.getNext();
						 if(asteroidclone instanceof Asteroid) {
							 asteroid=true;
							 bank.remove(nonshipclone);
							 bank2.remove(asteroidclone);
							 System.out.println("NPS has crashed into an Asteroid!");
							 break;
						 }
					}
					break;
			 }
		}
		if((nps==false)&&(asteroid==false)) {
			System.out.println("No NPS's or asteroids exist");
		}else if(nps==false) {
			System.out.println("No NPS's exist");
		}else if(asteroid==false) {
			System.out.println("No asteroids exist");
		}
	}
	
	public Iterator getIterator() {
		return store.getIterator();
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	
	public float getElapsedTime() {
		return gameTime;
		
	}

	public boolean isSoundEnabled() {
		return soundToggle;
	}
	
	public int getLivesRemaining() {
		return numLives;
	}

	public void turnPlayerShipLeft() {
		boolean ps = false;
		Iterator bank = getIterator();
		while(bank.hasNext())
		{
			GameObject shipclone=(GameObject) bank.getNext();
			//clone PS to call methods
			if(shipclone instanceof ShipPlayer) {
				ps=true;
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				clone.turnLeft();
				proxy();
				break;
			}
		}
		if(ps==false) {
			System.out.println("A PS does not exist");
		}
	}

	public void decreasePSSpeed() {
		boolean ps=false;
		Iterator bank = getIterator();
		while (bank.hasNext())
		{
			GameObject shipclone=(GameObject) bank.getNext();
			//clone PS to call methods
			if(shipclone instanceof ShipPlayer) {
				ps=true;
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				clone.decreaseSpeed();
				proxy();
				break;
			}
		}
		if(ps==false) {
			System.out.println("A PS does not exist");
		}
	}
	
	public void increasePSSpeed() {
		boolean ps=false;
		Iterator bank = getIterator();
		while(bank.hasNext())
		{
			GameObject shipclone=(GameObject) bank.getNext();
			//clone PS to call methods
			if(shipclone instanceof ShipPlayer) {
				ps=true;
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				clone.increaseSpeed();
				proxy();
				break;
			}
		}
		if(ps==false) {
			System.out.println("A PS does not exist");
		}
	}

	public void turnPlayerShipRight() {
		boolean ps=false;
		Iterator bank = getIterator();
		while (bank.hasNext())
		{
			GameObject shipclone=(GameObject) bank.getNext();
			//clone PS to call methods
			if(shipclone instanceof ShipPlayer) {
				ps=true;
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				clone.turnRight();
				proxy();
				break;
			}
		}
		if(ps==false) {
			System.out.println("A PS does not exist");
		}
	}
	
	public void turnPSLauncherRight() {
		boolean launcher=false;
		Iterator bank = getIterator();
		while (bank.hasNext())
		{
			GameObject shipclone=(GameObject) bank.getNext();
			//clone PS to call methods
			if(shipclone instanceof ShipPlayer) {
				launcher=true;
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				clone.turnLauncherRight();
				proxy();
				break;
			}
		}
		if(launcher=false) {
			System.out.println("A PS does not exist");
		}
				
	}
	
	public void turnPSLauncherLeft() {
		boolean launcher=false;
		Iterator bank=getIterator();
		while (bank.hasNext())
		{
			GameObject shipclone=(GameObject) bank.getNext();
			//clone PS to call methods
			if(shipclone instanceof ShipPlayer) {
				launcher=true;
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				clone.turnLauncherLeft();
				proxy();
				break;
				
			}
		}
		if(launcher=false) {
			System.out.println("A PS does not exist");
		}		
	}

	public void jumpThroughHyperspace() {
		boolean ps = false;
		Iterator bank=getIterator();
		while (bank.hasNext())
		{
			GameObject shipclone=(GameObject) bank.getNext();
			//clone PS to call methods
			if(shipclone instanceof ShipPlayer) {
				ps=true;
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				clone.jumpThroughHyperspace();
				proxy();
				break;
			}
		}
		if (ps==false) {
			System.out.println("A PS does not exist");
		}
	}
	
	public void tick() {
		
		Iterator bank=getIterator();
		while (bank.hasNext())
		{
			GameObject shipclone=(GameObject) bank.getNext();
			//clone PS to call methods
			if(shipclone instanceof ShipPlayer) {
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) shipclone;
				clone.move(getMinHeight(), getMinWidth(), getMaxHeight(), getMaxWidth());
				proxy();
				break;
			}
		}
		bank=getIterator();
		while (bank.hasNext())
		{
			GameObject shipclone=(GameObject) bank.getNext();
			//clone PS to call methods
			if(shipclone instanceof ShipNonPlayer) {
				ShipNonPlayer clone = new ShipNonPlayer();
				clone = (ShipNonPlayer) shipclone;
				clone.move();
				proxy();
				break;
			}
		}
		bank=getIterator();
		while (bank.hasNext())
		{
			GameObject missileclone=(GameObject) bank.getNext();
			if(missileclone instanceof Missile) {
				Missile clone = (Missile) missileclone;
				clone.setFuelLevel(clone.getFuelLevel()-1);
				if(clone.getFuelLevel()==0) {
					bank.remove(missileclone);
					proxy();
				}
				clone.move();
				proxy();
			}
		}
		bank=getIterator();
		while (bank.hasNext())
		{
			GameObject asteroidClone=(GameObject) bank.getNext();
			if(asteroidClone instanceof Asteroid) {
				Asteroid clone = (Asteroid) asteroidClone;
				clone.move();
				proxy();
			}
		}
		
		bank=getIterator();
		while (bank.hasNext())
		{
			GameObject spacestationclone=(GameObject) bank.getNext();
			if(spacestationclone instanceof SpaceStation) {
				SpaceStation clone = new SpaceStation();
				clone = (SpaceStation) spacestationclone;
				if(this.gameTime%clone.getBlinkRate()==0) {
					clone.toggleLight();
					proxy();
				}
				break;
			}
		}
		collision();
		this.gameTime+=0.025;
		proxy();
	}
	public void collision() {
		Iterator iter = store.getIterator();
		while(iter.hasNext()) {
			ICollider currentObject=(ICollider) iter.getNext();
			Iterator iter2=store.getIterator();
			while(iter2.hasNext()) {
				ICollider otherObject=(ICollider) iter2.getNext();
				if(otherObject!=currentObject) {
					if(currentObject.collidesWith(otherObject)) {
						currentObject.handleCollision(otherObject);
					}
				}
			}
		}
		proxy();
	}

	public void quit() {
		System.exit(0);
		
	}

	public void about() {
		// TODO Auto-generated method stub
		
	}

	public void sound() {
		if(soundToggle) {
			soundToggle=false;
			gameBackgroundMusic.pause();
		}else
		{
			soundToggle=true;
			gameBackgroundMusic.play();
		}
		proxy();
	}

	public void undo() {
		// TODO Auto-generated method stub
		
	}

	public void save() {
		// TODO Auto-generated method stub
		
	}

	public void newGame() {
		store.getCollection().clear();
		numLives =3;
		gameTime=0;
		playerScore=0;
		numPlayerMissiles=10;
		numNonPlayerMissiles=4;
		hasPlayerShip=false;
	}

	public GameObjectCollection getGameObjects() {
		return store;
	}

	public int getMissileCount() {
		return numPlayerMissiles;
	}
	
	public double getHeight() {
		return windowHeight;
	}
	
	public double getWidth() {
		return windowWidth;
	}
	public void setDimension(int x, int y) {
		windowHeight=y;
		windowWidth=x;
	}
	public boolean isEmpty() {
		return store.getCollection().isEmpty();
	}
	
	public double getMaxWidth() {
		return windowWidth+minWidth;
	}
	
	public double getMaxHeight() {
		return windowHeight=minHeight;
	}

	public void setMinSize(Point minimumSize) {
		minHeight=minimumSize.getX();
		minWidth=minimumSize.getY();
	}
	
	public double getMinHeight() {
		return minHeight;
	}
	
	public double getMinWidth() {
		return minWidth;
	}

	public void diedCommand() {
		// TODO Auto-generated method stub
		
	}
}