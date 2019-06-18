package com.mycompany.a2;

import java.util.Iterator;
import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld {
	
	private GameWorld cloneworld;

	public GameWorldProxy(GameWorld gWorld) {
		cloneworld = gWorld;
	}
	
	public Iterator getIterator() {
		return cloneworld.getIterator();
	}
	
	public int getPlayerScore() {
		return cloneworld.getPlayerScore();
	}
	
	public int getMissileCount() {
		return cloneworld.getMissileCount();
	}
	
	public int getElapsedTime() {
		return cloneworld.getElapsedTime();
	}
	
	public boolean isSoundEnabled() {
		return cloneworld.isSoundEnabled();
	}
	
	public int getLivesRemaining() {
		return cloneworld.getLivesRemaining();
	}


}
