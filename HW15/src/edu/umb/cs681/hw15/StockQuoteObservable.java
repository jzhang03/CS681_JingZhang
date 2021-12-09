//
// CS681: HW15
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.Map;

public class StockQuoteObservable extends Observable {
	private Map<String, Float> tickerquotemap= new HashMap<String, Float>();

    public void changeQuote(String t, float q) {
    	tickerquotemap.put(t, q);
    	setChanged();
    	notifyObservers(new StockEvent(t,q));
        System.out.println("Stock, "+ "set Ticker = " + t + ", Quote = " + q);
    }
}
