//
// CS681: HW10
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton implements Runnable {
	private ConcurrentSingleton() {};
    private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<ConcurrentSingleton>();

    public static AtomicReference<ConcurrentSingleton> getInstance() {
            if (instance.get() == null) {
                instance.set(new ConcurrentSingleton());
            }
            return instance;
    }
    
    public void run() {
        AtomicReference<ConcurrentSingleton> currentInstance = ConcurrentSingleton.getInstance();
        System.out.println("currnent instance: " + currentInstance);
    }


    public static void main(String[] args) {
    	ConcurrentSingleton cs1 = new ConcurrentSingleton();
    	ConcurrentSingleton cs2 = new ConcurrentSingleton();
    	ConcurrentSingleton cs3 = new ConcurrentSingleton();

        Thread T1 = new Thread(cs1);
        Thread T2 = new Thread(cs2);
        Thread T3 = new Thread(cs3);

        T1.start();
        T2.start();
        T3.start();

        try {
        	T1.join();
            T2.join();
            T3.join();

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
