//
// CS681: HW13
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable {
	private ReentrantLock wLock = new ReentrantLock();
    private ThreadSafeBankAccount2 bankAccount = null;
    private boolean done = false;

    public WithdrawRunnable(ThreadSafeBankAccount2 bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setDone() {
    	wLock.lock();
        try {
            done = true;
        } finally {
        	wLock.unlock();
        }
    }

    public void run() {
        while (true) {
        	wLock.lock();
            try{
                if (done) {
                    break;
                }
            } finally {
            	wLock.unlock();
            }
            bankAccount.withdraw(200);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                continue;
            }
        }
    }
}
