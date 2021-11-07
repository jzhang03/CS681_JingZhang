//
// CS681: HW7
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton implements Runnable {
	private ConcurrentSingleton() {};
    private static ConcurrentSingleton instance = null;
    private static ReentrantLock lock = new ReentrantLock();

    public static ConcurrentSingleton getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ConcurrentSingleton();
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }
    
    @Override
	public void run() {
		System.out.println(ConcurrentSingleton.getInstance());
	}
    
	public static void main(String[] args) {
		Thread T1 = new Thread(new ConcurrentSingleton());
		Thread T2 = new Thread(new ConcurrentSingleton());
		Thread T3 = new Thread(new ConcurrentSingleton());
		Thread T4 = new Thread(new ConcurrentSingleton());
		T1.start();
		T2.start();
		T3.start();
		T4.start();
		try {
			T1.join();
			T2.join();
			T3.join();
			T4.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}