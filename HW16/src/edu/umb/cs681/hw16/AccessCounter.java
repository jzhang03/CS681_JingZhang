//
// CS681: HW16
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw16;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	ConcurrentHashMap<Path, AtomicInteger> map = new ConcurrentHashMap <>();
	// Map<Path, Integer> map = new HashMap<Path, Integer>();
    // private ReentrantLock nStaticLock = new ReentrantLock();
    private static ReentrantLock staticLock = new ReentrantLock();
    private static AccessCounter instance = null;

    private AccessCounter() {};
    
    public static AccessCounter getInstance(){
        staticLock.lock();
        try {
            if(instance==null)
            {
                instance = new AccessCounter();
            }
        } finally {
            staticLock.unlock();
        }
        return instance;
    }

    public void increment(Path path){
    	map.compute(path, (java.nio.file.Path k, AtomicInteger i) -> {
			if(i == null) {
				System.out.println(Thread.currentThread().getName() + 
						"-> Increment : "+ path + " -"  + 1);
				return new AtomicInteger(1);
			} else {
				System.out.println(Thread.currentThread().getName() + 
						"-> Increment : " + path + " - " + (i.get()+1));
				return new AtomicInteger(i.incrementAndGet());
			}
		});
    }
    
    public int getCount(Path path) {
    	return map.compute(path, (java.nio.file.Path k, AtomicInteger i) -> {
			if(i == null) {
				System.out.println(Thread.currentThread().getName() + 
						"-> " + path + " count " + 0);
				return new AtomicInteger(0);
			} else {
				System.out.println(Thread.currentThread().getName() + 
						"-> " + path + " count " + i.get());
				return i;
			}
		}).get();
    }
}
