//
// CS681: HW13
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount {
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().getId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().getId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		DepositRunnable deposit= new DepositRunnable(bankAccount);
		WithdrawRunnable withdraw = new WithdrawRunnable(bankAccount);
		Thread t1d  = new Thread(deposit);
		Thread t1w  = new Thread(withdraw);
		Thread t2d  = new Thread(deposit);
		Thread t2w  = new Thread(withdraw);
		Thread t3d  = new Thread(deposit);
		Thread t3w  = new Thread(withdraw);
		Thread t4d  = new Thread(deposit);
		Thread t4w  = new Thread(withdraw);
		
		t1d.start();
		t1w.start();
		t2d.start();
		t2w.start();
		t3d.start();
		t3w.start();
		t4d.start();
		t4w.start();
		
		deposit.setDone();
		withdraw.setDone();
		
		try {
			t1d.join();
			t2d.join();
			t3d.join();
			t4d.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
