package com.mycompany.a2;

//import java.util.Iterator;
//import com.mycompany.a2.GameObjectCollection.Iterator;

public interface IGameWorld {

	public IIterator getIterator();
	public int getPlayerScore();
	public int getMissileCount();
	public float getElapsedTime();
	public boolean isSoundEnabled();
	public int getLivesRemaining();
	public GameObjectCollection getGameObjects();
}
