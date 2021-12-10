//
// CS681: HW17
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw17;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
	private ConcurrentLinkedQueue<Observer> observers;
	// private LinkedList<Observer> observers;
	private AtomicBoolean changed = new AtomicBoolean(false); 
	// private ReentrantLock lock = new ReentrantLock();

	public Observable() {
		observers = new ConcurrentLinkedQueue<Observer>();
	}
	public void addObserver(Observer o) {
		if(o == null) {
			throw new NullPointerException();
		} 
	    observers.add(o);
	}
	public void deleteObserver(Observer o) {
	    if(observers.remove(o)) {
			System.out.println("Observer has removed");
		}else {
			System.out.println("This observer is not existed");
		}
	}

	protected void setChanged() {
	    changed.getAndSet(true); 
	}
	
	protected void clearChanged() {
		changed.getAndSet(false); 
	}
	
	public boolean hasChanged() {
	    if (changed.get()) 
	    	return true;
	    return false;
	}

	public void notifyObservers(Object obj) {
	    if (hasChanged()) {
	    	observers.forEach((ob) -> {
	              ob.update(this, obj);
	        });
	        clearChanged();
	        System.out.println("Notify complete.");
	    }
	}
}
