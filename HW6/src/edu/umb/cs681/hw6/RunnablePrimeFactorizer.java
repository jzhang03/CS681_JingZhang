//
// CS681: HW6
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw6;

import java.util.LinkedList;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {
	public RunnablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend);
		if (from >= 2 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException("Incorrect input");
		}
	}
	
	protected boolean isEven(long n){
		if(n%2 == 0){ return true; }
		else{ return false; }
	}

	public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
	    	if( divisor > 2 && isEven(divisor)) {
	    		divisor++;
	    		continue;
	    	}
		    if(dividend % divisor == 0) {
		        factors.add(divisor);
		        dividend /= divisor;
		    }else {
		    	if(divisor==2){ divisor++; }
		    	else{ divisor += 2; }
		    }
		}
	}
	
	public void run() {
		generatePrimeFactors();
		System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
	}

	public static void main(String[] args) {
		// Factorization of 6 with a separate thread
		System.out.println("Prime factors of 6");
		RunnablePrimeFactorizer runnable = new RunnablePrimeFactorizer(6, 2, 6);
		Thread thread = new Thread(runnable);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
		
		// Factorization of 17 with two threads
		System.out.println("Prime factors of 17");
		LinkedList<RunnablePrimeFactorizer> runnables = new LinkedList<RunnablePrimeFactorizer>();
		LinkedList<Thread> threads = new LinkedList<Thread>();

		runnables.add( new RunnablePrimeFactorizer(17, 2, (long)Math.sqrt(17) ));
		runnables.add( new RunnablePrimeFactorizer(17, 1+(long)Math.sqrt(17), 17 ));
		
		thread = new Thread(runnables.get(0));
		threads.add(thread);
		thread = new Thread(runnables.get(1));
		threads.add(thread);
		
		threads.forEach( (t)->t.start() );
		threads.forEach( (t)->{	try{t.join();}
								catch(InterruptedException e){e.printStackTrace(); }} );
		
		LinkedList<Long> factors = new LinkedList<Long>();
		runnables.forEach( (factorizer) -> factors.addAll(factorizer.getPrimeFactors()) );
		System.out.println("Final result: " + factors + "\n");
		
		runnables.clear();
		threads.clear();
		
		// Factorization of 8633 with two threads
		System.out.println("Prime factors of 8633");
		runnables.add( new RunnablePrimeFactorizer(8633, 2, (long)Math.sqrt(8633) ));
		runnables.add( new RunnablePrimeFactorizer(8633, 1+(long)Math.sqrt(8633), 8633 ));
		
		thread = new Thread(runnables.get(0));
		threads.add(thread);
		thread = new Thread(runnables.get(1));
		threads.add(thread);
		
		threads.forEach( (t)->t.start() );
		threads.forEach( (t)->{	try{t.join();}
								catch(InterruptedException e){e.printStackTrace(); }} );
		
		LinkedList<Long> factors2 = new LinkedList<Long>();
		runnables.forEach( (factorizer) -> factors2.addAll(factorizer.getPrimeFactors()) );
		System.out.println("Final result: " + factors2);		
	}
}
