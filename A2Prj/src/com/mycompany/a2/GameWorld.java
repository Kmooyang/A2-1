package com.mycompany.a2;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
//import java.util.Vector.clone();

import com.codename1.charts.util.ColorUtil;

//import com.codename1.charts.util.ColorUtil;

public class GameWorld implements IGameWorld {
	
	//random will be used for randomized integers and floats etc
	Random random = new Random();
	//this will be the storage for the objects in the game
	//limitless, atleast until RAM runs out
	private GameObjectCollection store = new GameObjectCollection();

	int numLives =3;
	int gameTime=0;
	int playerScore=0;
	int numPlayerMissiles=10;
	double windowWidth;
	double windowLength;
	boolean hasPlayerShip;
	
	public GameWorld() {
		
	}
	
	public void init() {
		
	}
	public void addNewAsteroid() {
		
		Asteroid asteroid1 = new Asteroid();
		
		//Adds an asteroid to the vector
		store.add(asteroid1);
		System.out.println("An asteroid has been created");
	}
	
	public void addShipNonPlayer() {
		//Creates a non player ship object
		ShipNonPlayer NPS = new ShipNonPlayer();
		//Adds an asteroid to the vector
		store.add(NPS);
		
		System.out.println("An NPS has been added");
	}
	
	public void addBlinkingSpaceStation() {
		
		SpaceStation station = new SpaceStation();
		
		//Adds a station to the vector
		store.add(station);
		System.out.println("A space station has been added");
	}
	
	public void addShipPlayer() {
		if(hasPlayerShip==false) {
		ShipPlayer player= new ShipPlayer();
		store.add(player);
		System.out.println("A player ship has been added");
		//once a player ship is added, change boolean to show that
		//a player ship exists
		boolean hasPlayerShip=true;
		}else
		{
			System.out.println("Player ship already exists");
		}
	}
	
	public void fireMissile() {
		//speed direction x and y
		Missile PSmissile= new Missile();
		//searching through vector for object of type ShipPlayer
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof ShipPlayer) {
				//once ShipPlayer found, make clone of player ship
				ShipPlayer clone= new ShipPlayer();
				clone = (ShipPlayer) store.get(i);
				//use clone to call methods
				PSmissile.setSpeed(clone.getSpeed());
				PSmissile.setDirection(clone.getDirection());
				PSmissile.setLocation(clone.getLocationX(), clone.getLocationY());
				//stop for loop
				i=store.size();
				clone.decrementMissileCount();
			}
		}
		store.add(PSmissile);
		System.out.println("Player has fired a missile");
		numPlayerMissiles-=1;
		//if player runs out of missiles, shoot message
		if(numPlayerMissiles==0) {
			System.out.println("You have run out of missiles!");
		}
	} 
	
	public void fireNonPlayerMissile() {
		Missile NPSmissile=new Missile();
		//looking through vector for object of type NPS
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof ShipNonPlayer) {
				//when found, create clone to call object methods
				ShipNonPlayer clone= new ShipNonPlayer();
				clone = (ShipNonPlayer) store.get(i);
				NPSmissile.setSpeed(clone.getSpeed());
				NPSmissile.setDirection(clone.getDirection());
				NPSmissile.setLocation(clone.getLocationX(), clone.getLocationY());
				//stop for loop
				i=store.size();
				clone.decrementMissileCount();
			}
		}
		store.add(NPSmissile);
		System.out.println("Non player has fired a missile");
	}

	public void eliminate() {
		
		//look through vector for object of NPS
		for (int i = 0; i<store.size(); i++)
		{
			 if(store.elementAt(i) instanceof ShipNonPlayer) {
				 //when found, make sure there is a missile in there too
					for (int k = 0; k<store.size(); k++)
					{
						//when found, remove the one with a higher index
						//that way, object doesn't change index and unwanted removal doesn't occur
						 if(store.elementAt(k) instanceof Missile) {
							 if(k>i) {
								 store.remove(k);
								 store.remove(i);
							 }else {
								 store.remove(i);
								 store.remove(k);
							 }
							 System.out.println("PS missile has destroyed an NPS");
							 System.out.println("+20 points");
							 playerScore+=20;
							 //prevent for loop from continuing
							 i=store.size();
							 k=store.size();
						 }
					}
			 }
		}
	}


	public void map() {
		
		//find PS in vector, call toString method to print data
		for (int i = 0; i<store.size(); i++)
		{
			if(store.elementAt(i) instanceof ShipPlayer) {
				System.out.println(store.get(i).toString());
				i=store.size();
			}
		}
		//find NPS in vector, call tpString method to print data
		for (int i = 0; i<store.size(); i++)
		{
			if(store.elementAt(i) instanceof ShipNonPlayer) {
				System.out.println(store.get(i).toString());
				i=store.size();
			}
		}
		//find missile in vector, call tpString method to print data
		for (int i = 0; i<store.size(); i++)
		{
			if(store.elementAt(i) instanceof Missile) {
				System.out.println(store.get(i).toString());
				i=store.size();
			}
		}
		//find asteroid in vector, call tpString method to print data
		for (int i = 0; i<store.size(); i++)
		{
			if(store.elementAt(i) instanceof Asteroid) {
				System.out.println(store.get(i).toString());
			}
		}
		
	}
	
	public void turnPlayerMissileLauncher() {
		//find PS inside of vector
		for (int i = 0; i<store.size(); i++)
		{
			//clone PS to call methods
			if(store.elementAt(i) instanceof ShipPlayer) {
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) store.get(i);
				clone.revolveML();
				i=store.size();
			}
		}
	}
	public void print() {
		System.out.println("Score: "+playerScore+" Missiles: "+numPlayerMissiles+" Time Elapsed: "
				+gameTime+" Lives: "+numLives);
	}
	
	public void reloadMissiles() {
		if(numPlayerMissiles<10) {
			numPlayerMissiles=10;
			System.out.println("You have picked up missiles");
		}else
		{
			System.out.println("The player has the maximum amount of missiles already");
		}
	}
	
	public void PSKillAsteroid() {
		
		for (int i = 0; i<store.size(); i++)
		{
			 if(store.elementAt(i) instanceof Missile) {
					for (int k = 0; k<store.size(); k++)
					{
						 if(store.elementAt(k) instanceof Asteroid) {
							 if(k>i) {
								 store.remove(k);
								 store.remove(i);
							 }else {
								 store.remove(i);
								 store.remove(k);
							 }
							 System.out.println("PS missile has destroyed an asteroid");
							 System.out.println("+5 points");
							 playerScore+=5;
							 k=store.size();
							 i=store.size();
						 }
					}
			 }
		}
	}
	
	//TODO There can be multiple non player ships,
	//for loop needs to be added to check for a random nonplayer ship
	public void NPSMissileKillPS() {
		for (int i = 0; i<store.size(); i++)
		{
			if(store.elementAt(i) instanceof Missile) {
				 store.remove(i);
				 System.out.println("NPS Missile has struck you!");
				 System.out.println("-1 life");
				 numLives-=1;
				 i=store.size();
				 if (numLives==0) {
					 System.out.println("You have died. Player score: "+playerScore);
					 hasPlayerShip=false;
					 return;
				 }
			 }
		}
	}
	
	public void PSCollideAsteroid() {
		for (int i = 0; i<store.size(); i++)
		{
			if(store.elementAt(i) instanceof Asteroid) {
				 store.remove(i);
				 System.out.println("An asteroid has struck you!");
				 System.out.println("-1 life");
				 numLives-=1;
				 i=store.size();
				 if (numLives==0) {
					 System.out.println("You have died. Player score: "+playerScore);
					 hasPlayerShip=false;
					 return;
				 }
			 }
		}
	}
	
	//TODO check for if game is over
	//Same case for any numLives-=1;
	public void PSCollideNPS() {
		for (int i = 0; i<store.size(); i++)
		{
			 if(store.elementAt(i) instanceof ShipPlayer) {
					for (int k = 0; k<store.size(); k++)
					{
						 if(store.elementAt(k) instanceof ShipNonPlayer) {
							 if(k>i) {
								 store.remove(k);
								 store.remove(i);
							 }else {
								 store.remove(i);
								 store.remove(k);
							 }
							 System.out.println("PS has crashed into an NPS!");
							 System.out.println("-1 life");
							 numLives-=1;
							 k=store.size();
							 i=store.size();
							 if (numLives==0) {
								 System.out.println("You have died. Player score: "+playerScore);
								 hasPlayerShip=false;
								 return;
							 }
						 }
					}
			 }
		}
	}
	
	public void AsteroidCollision() {
		for (int i = 0; i<store.size(); i++)
		{
			if(store.elementAt(i) instanceof Asteroid) {
				store.remove(i);
				i = store.size();
			}
		}
		for (int i = 0; i<store.size(); i++)
		{
			if(store.elementAt(i) instanceof Asteroid) {
				store.remove(i);
				i = store.size();
			}
		}
		System.out.println("Two Asteroids have collided!");
	}
	
	public void NPSCollideAsteroid() {
		for (int i = 0; i<store.size(); i++)
		{
			 if(store.elementAt(i) instanceof ShipNonPlayer) {
					for (int k = 0; k<store.size(); k++)
					{
						 if(store.elementAt(k) instanceof Asteroid) {
							 if(k>i) {
								 store.remove(k);
								 store.remove(i);
							 }else {
								 store.remove(i);
								 store.remove(k);
							 }
							 System.out.println("NPS has crashed into an Asteroid!");
							 k=store.size();
							 i=store.size();
						 }
					}
			 }
		}
	}
	
	public Iterator getIterator() {
		return null;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	public int getMissileCount() {
		return numPlayerMissiles;
		
	}
	
	public int getElapsedTime() {
		return gameTime;
		
	}

	public boolean isSoundEnabled() {
		return false;
	}
	
	public int getLivesRemaining() {
		return numLives;
	}

	public void turnPlayerShipLeft() {
		for (int i = 0; i<store.size(); i++)
		{
			//clone PS to call methods
			if(store.elementAt(i) instanceof ShipPlayer) {
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) store.get(i);
				clone.turnLeft();
				i=store.size();
			}
		}
	}

	public void decreasePSSpeed() {
		for (int i = 0; i<store.size(); i++)
		{
			//clone PS to call methods
			if(store.elementAt(i) instanceof ShipPlayer) {
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) store.get(i);
				clone.decreaseSpeed();
				i=store.size();
			}
		}
	}

	public void turnPlayerShipRight() {
		for (int i = 0; i<store.size(); i++)
		{
			//clone PS to call methods
			if(store.elementAt(i) instanceof ShipPlayer) {
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) store.get(i);
				clone.turnRight();
				i=store.size();
			}
		}
	}

	public void jumpThroughHyperspace() {
		for (int i = 0; i<store.size(); i++)
		{
			//clone PS to call methods
			if(store.elementAt(i) instanceof ShipPlayer) {
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) store.get(i);
				clone.jumpThroughHyperspace();
				i=store.size();
			}
		}
	}
	
	public void tick() {
		for (int i = 0; i<store.size(); i++)
		{
			//clone PS to call methods
			if(store.elementAt(i) instanceof ShipPlayer) {
				ShipPlayer clone = new ShipPlayer();
				clone = (ShipPlayer) store.get(i);
				clone.setLocation((clone.getLocationX()+(Math.cos(90-clone.getDirection())*clone.getSpeed())), 
						((clone.getLocationY()+(Math.sin(90-clone.getDirection())*clone.getSpeed()))));
				i=store.size();
			}
		}
		
		for (int i = 0; i<store.size(); i++)
		{
			if(store.elementAt(i) instanceof Missile) {
				Missile clone = new Missile();
				clone = (Missile) store.get(i);
				clone.setFuelLevel(clone.getFuelLevel()-1);
				if(clone.getFuelLevel()==0) {
					store.remove(i);
					i--;
				}
			}
		}
		
		for (int i = 0; i<store.size(); i++)
		{
			if(store.elementAt(i) instanceof SpaceStation) {
				SpaceStation clone = new SpaceStation();
				clone = (SpaceStation) store.get(i);
				if(this.gameTime%clone.getBlinkRate()==0) {
					clone.toggleLight();
				}
				i=store.size();
			}
		}
		this.gameTime+=1;
	}
}
