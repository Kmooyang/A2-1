package com.mycompany.a2;

import java.util.Vector;

public class GameObjectCollection implements ICollection {
	
	private Vector<GameObject> collection = new Vector<GameObject>();
	
	public GameObjectCollection() {
		
	}
	
	public void add(Object thing) {
		collection.add((GameObject) thing);
	}
	
	public void remove(Object thing) {
		collection.remove((GameObject) thing);
	}

	public GameObject elementAt(int i) {
		//collection.elementAt(i);
		return collection.elementAt(i);
	}

	public GameObject get(int i) {
		//collection.get(i);
		return collection.get(i);
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getIterator() {
		// TODO Auto-generated method stub
		
	}

	public int size() {
		
		return collection.size();
	}
	
	/*public Iterator getIterator{
		
	}*/
}
