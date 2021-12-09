//
// CS681: HW15
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
	private LinkedList<Observer> observers;
	private AtomicBoolean changed = new AtomicBoolean(false); 
	private ReentrantLock lock = new ReentrantLock();

	public Observable() {
		observers = new LinkedList<Observer>();
	}
	public void addObserver(Observer o) {
		if(o == null) {
			throw new NullPointerException();
		}
		lock.lock();  
	    observers.add(o);
	    lock.unlock();
	}
	public void deleteObserver(Observer o) {
	    lock.lock();
	    if(observers.remove(o)) {
			System.out.println("Observer has removed");
		}else {
			System.out.println("This observer is not existed");
		}
	    lock.unlock();
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
	    LinkedList<Observer> tempObservers = new LinkedList<>();
	    lock.lock();
	    tempObservers = new LinkedList<Observer>(observers);
	    lock.unlock();
	    if (hasChanged()) {
	    	for (Observer obs: tempObservers) {
	    		obs.update(this, obj);
	        }
	        clearChanged();
	        System.out.println("Notify complete.");
	    }
	}
}
