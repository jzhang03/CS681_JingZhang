//
// CS681: HW6
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw6;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void generatePrimeFactors() {
        long divisor = 2;
        while( dividend != 1 && divisor <= to ){
            lock.lock();
            try {
                if(done) {
                    System.out.println("The Prime Factorizer Stopped running.");
                    break;
                }
                if(dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                }else {
                    if(divisor==2) {
                        divisor++;
                    }
                    else {
                        divisor += 2;
                    }

                }
            }finally {
                lock.unlock();
            }
        }
    }
    public void run() {
        generatePrimeFactors();
         }

    public static void main(String[] args) {
    	//get the result from input 84
        RunnableCancellablePrimeFactorizer gen1 = new RunnableCancellablePrimeFactorizer(84, 2, (long)Math.sqrt(84));
        Thread thread1 = new Thread(gen1);
        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Prime factors of 84: " + gen1.getPrimeFactors() + "\n");


        //stop thread running by calling setDone()
        RunnableCancellablePrimeFactorizer gen2 = new RunnableCancellablePrimeFactorizer(8633, 2, (long)Math.sqrt(8633));
        Thread thread2 = new Thread(gen2);
        thread2.start();
        gen2.setDone();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Prime factors of 8633: " + gen2.getPrimeFactors() + "\n");
    }
}
