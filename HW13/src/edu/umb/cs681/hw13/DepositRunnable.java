//
// CS681: HW13
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class DepositRunnable implements Runnable {
	private ReentrantLock dLock = new ReentrantLock();
    private ThreadSafeBankAccount2 bankAccount = null;
    private boolean done = false;

    public DepositRunnable(ThreadSafeBankAccount2 bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setDone() {
    	dLock.lock();
        try {
            done = true;
        } finally {
        	dLock.unlock();
        }
    }

    public void run() {
        while (true) {
        	dLock.lock();
            try{
                if (done) {
                   break;
                }
            } finally {
            	dLock.unlock();
            }
            bankAccount.deposit(300);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {

                continue;
            }
        }
    }
}
