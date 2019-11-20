package com.mycompany.a2;

//import java.util.Iterator;
import java.util.Vector;

public class GameObjectCollection implements ICollection {
	
	private Vector<GameObject> collection;
	
	public GameObjectCollection() {
		collection = new Vector<GameObject>();
	}
	
	public void add(Object thing) {
		collection.add((GameObject) thing);
	}
	
	public void remove(Object thing) {
		collection.remove((GameObject) thing);
	}
	
	public Iterator getIterator() {
		/*if(collection.size()<=0) {
			return null;
		}*/
		return new Iterator();
	}
	
	public Vector<GameObject> getCollection() {
		return collection;
	}
	/*public boolean isEmpty() {
		if(collection.size()==0)
		{
			return true;
		}
		return false;
		
	}*/

	public class Iterator implements IIterator {
		
		private int currElementIndex;
		
		public Iterator() {
			currElementIndex=-1;
		}
		
		public boolean hasNext() {
			if(collection.size()<=0) {
				return false;
			}
			if(currElementIndex==collection.size()-1) {;
			return false;
			}
			return true;
		}
		
		public GameObject getNext() {
			currElementIndex++;
			return(collection.elementAt(currElementIndex));
		}
		
		public GameObject get() {
			return(collection.elementAt(currElementIndex));
		}

		public void remove(GameObject object) {
			collection.remove(object);
		}
		
		public int getIndex() {
			return currElementIndex;
		}
	}
}
