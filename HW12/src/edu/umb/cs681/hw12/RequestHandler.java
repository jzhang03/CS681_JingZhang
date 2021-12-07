//
// CS681: HW12
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw12;


import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
	private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public void setDone()
    {
        lock.lock();
        try {
            done = true;

        } finally {
            lock.unlock();
        }

    }
    
    public void run() {	
		String[] thread = {"AccessCounter.class", "RequestHandler.class",
				"a.html", "b.html", "c.html", "d.html", "e.html", "f.html"};
		AccessCounter ac = AccessCounter.getInstance();
		
		while (true) {			
			lock.lock();
			try {
				if(done) {
	    			System.out.println("Thread terminated ");
	    			break;
	    		}
				Random random = new Random();	
				int num = random.nextInt(thread.length);
				Path path = FileSystems.getDefault().getPath("..", thread[num]);					
				ac.increment(path);
				System.out.println(thread[num] + " -> " + ac.getCount(path));
			}
			finally {
				lock.unlock();
			}
			
			try {
				Thread.sleep(300);
			}
			catch(InterruptedException e) {
				System.out.println(e);
				continue;
			}
		}
	}

	public static void main(String[] args) {
		RequestHandler rh1  = new RequestHandler();
		RequestHandler rh2  = new RequestHandler();
		RequestHandler rh3  = new RequestHandler();
		RequestHandler rh4  = new RequestHandler();
		RequestHandler rh5  = new RequestHandler();
		RequestHandler rh6  = new RequestHandler();
		RequestHandler rh7  = new RequestHandler();
		RequestHandler rh8  = new RequestHandler();
		RequestHandler rh9  = new RequestHandler();		
		RequestHandler rh10 = new RequestHandler();
		RequestHandler rh11= new RequestHandler();
		RequestHandler rh12= new RequestHandler();
		RequestHandler rh13= new RequestHandler();
		RequestHandler rh14 = new RequestHandler();
		RequestHandler rh15 = new RequestHandler();
		RequestHandler rh16= new RequestHandler();
		RequestHandler rh17= new RequestHandler();
		RequestHandler rh18 = new RequestHandler();
		RequestHandler rh19 = new RequestHandler();
		RequestHandler rh20 = new RequestHandler();
		
		Thread t1  = new Thread(rh1);
		Thread t2  = new Thread(rh2);
		Thread t3  = new Thread(rh3);
		Thread t4  = new Thread(rh4);
		Thread t5  = new Thread(rh5);
		Thread t6  = new Thread(rh6);
		Thread t7  = new Thread(rh7);
		Thread t8  = new Thread(rh8);
		Thread t9  = new Thread(rh9);
		Thread t10 = new Thread(rh10);
		Thread t11 = new Thread(rh11);
		Thread t12 = new Thread(rh12);
		Thread t13 = new Thread(rh13);
		Thread t14 = new Thread(rh14);
		Thread t15 = new Thread(rh15);
		Thread t16 = new Thread(rh16);
		Thread t17 = new Thread(rh17);
		Thread t18 = new Thread(rh18);
		Thread t19 = new Thread(rh19);
		Thread t20 = new Thread(rh20);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		t11.start();
		t12.start();
		t13.start();
		t14.start();
		t15.start();
		t16.start();
		t17.start();
		t18.start();
		t19.start();
		t20.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		rh1.setDone();
		rh2.setDone();
		rh3.setDone();
		rh4.setDone();
		rh5.setDone();
		rh6.setDone();
		rh7.setDone();
		rh8.setDone();
		rh9.setDone();
		rh10.setDone();
		rh11.setDone();
		rh12.setDone();
		rh13.setDone();
		rh14.setDone();
		rh15.setDone();
		rh16.setDone();
		rh17.setDone();
		rh18.setDone();
		rh19.setDone();
		rh20.setDone();
		
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		t4.interrupt();
		t5.interrupt();
		t6.interrupt();
		t7.interrupt();
		t8.interrupt();
		t9.interrupt();
		t10.interrupt();
		t11.interrupt();
		t12.interrupt();
		t13.interrupt();
		t14.interrupt();
		t15.interrupt();
		t16.interrupt();
		t17.interrupt();
		t18.interrupt();
		t19.interrupt();
		t20.interrupt();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
			t10.join();
			t11.join();
			t12.join();
			t13.join();
			t14.join();
			t15.join();
			t16.join();
			t17.join();
			t18.join();
			t19.join();
			t20.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}   		
	}	
}
