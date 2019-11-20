package com.mycompany.a2;

import java.util.Iterator;

public interface ICollection {
	
	public void add(Object thing);
	public void remove(Object thing);
	public IIterator getIterator();
	
}
