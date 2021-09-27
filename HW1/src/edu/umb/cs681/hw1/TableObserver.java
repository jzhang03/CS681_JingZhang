//
// CS681: HW1
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw1;

public class TableObserver implements Observer {
	public void update(Observable o, Object arg) {
        if (arg instanceof StockEvent) {
            System.out.print("\n Table: \n Ticker = " + ((StockEvent)arg).getTicker()
                    + "\n Quote = "  + ((StockEvent)arg).getQuote() + "\n");
        }
        else if (arg instanceof DJIAEvent) {
             System.out.print("\n Table: \n DJIA = " + ((DJIAEvent)arg).getDjia());
        }
    }
}
