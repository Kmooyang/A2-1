package com.mycompany.a2;

//import java.util.Iterator;
import java.util.Observable;

//import com.mycompany.a2.GameObjectCollection.Iterator;

public class GameWorldProxy extends Observable implements IGameWorld {
	
	private GameWorld gw;

	public GameWorldProxy(GameWorld gw) {
		this.gw = gw;
		this.setChanged();
		//this.notifyObservers();
	}
	
	public GameWorld getGW() {
		return this.gw;
	}
	
	public IIterator getIterator() {
		return gw.getIterator();
	}
	
	public int getPlayerScore() {
		return gw.getPlayerScore();
	}
	
	public int getMissileCount() {
		return gw.getMissileCount();
	}
	
	public float getElapsedTime() {
		return gw.getElapsedTime();
	}
	
	public boolean isSoundEnabled() {
		return gw.isSoundEnabled();
	}
	
	public int getLivesRemaining() {
		return gw.getLivesRemaining();
	}

	@Override
	public GameObjectCollection getGameObjects() {
		return gw.getGameObjects();
	}


}
