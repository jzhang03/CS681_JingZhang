//
// CS681: HW11
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableClient implements Runnable{
	private Address addAddress = new Address("Mayflower", "Cape Cod", "MA", 02657);

    Customer customer = new Customer(addAddress);
    public void run() {
        System.out.println("Get address:");
        customer.getAddress();
        System.out.println("Set address1 to address2:");
        customer.setAddress(new Address("Central Park", "New York", "NY", 10024));
        System.out.println("Change address: ");
        customer.setAddress(customer.getAddress()
                                    .change("changed street", "changed city", "changed State", 12345));
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnableClient());
        Thread t2 = new Thread(new RunnableClient());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
