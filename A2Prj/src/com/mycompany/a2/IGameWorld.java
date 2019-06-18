package com.mycompany.a2;

import java.util.Iterator;

public interface IGameWorld {

	public Iterator getIterator();
	public int getPlayerScore();
	public int getMissileCount();
	public int getElapsedTime();
	public boolean isSoundEnabled();
	public int getLivesRemaining();
}
