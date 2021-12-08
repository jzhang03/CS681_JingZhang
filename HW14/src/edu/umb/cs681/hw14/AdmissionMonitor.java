//
// CS681: HW14
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionMonitor {
	private int currentVisitors = 0;
    private ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void enter() {
    	System.out.println("Setting lock in enter():");
		lock.lock();
		try {
			while (currentVisitors >= 5) {
				System.out.println("Too many visitors. Please wait.");
				condition.await();
				}
				currentVisitors++;
		} catch (InterruptedException exception) {
			System.out.println(exception.toString());
		} finally {
			System.out.println("Releasing lock in enter():");
			lock.unlock();
		}
    }

    public void exit() {
    	System.out.println("Setting lock in exit():");
		lock.lock();
		try {
			currentVisitors--;
			System.out.println("Visitor exited --> signalAll()");
			condition.signalAll();
		} finally {
			System.out.println("Releasing lock in exit():");
			lock.unlock();
		}
    }

    public int countCurrentVisitors() {
        lock.lock();
        try {
        	System.out.println("Current visitors: " + currentVisitors);
            return currentVisitors;
        }
        finally {
            lock.unlock();
        }
    }
}
