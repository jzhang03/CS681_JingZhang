//
// CS681: HW12
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw12;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	Map<Path, Integer> map = new HashMap<Path, Integer>();
    private ReentrantLock nStaticLock = new ReentrantLock();
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
        nStaticLock.lock();
        try {
        	if (map.get(path) != null) {
				int c = map.get(path);
				map.put(path, c + 1);
			} else {
				map.put(path, 1);
			}
        }finally {
            nStaticLock.unlock();
        }
    }
    public int getCount(Path path) {
        int tmpCount = 0;
        nStaticLock.lock();
        try {
        	if (map.get(path) != null) {
				return map.get(path);
			}else {
				return tmpCount;
			}
        } finally {
            nStaticLock.unlock();
        }
    }
}
