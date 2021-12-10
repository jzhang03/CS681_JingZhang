//
// CS681: HW17
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw17;

public class RunnableClient {
	public static void main(String[] args) {
		StockQuoteObservable stockquoteobservable = new StockQuoteObservable();

		stockquoteobservable.addObserver(
				(Observable o, Object obj)-> { System.out.print("StockObserver.");});
		
		stockquoteobservable.changeQuote("LP", 600f);
		stockquoteobservable.notifyObservers(stockquoteobservable);
		DJIAQuoteObservable DJIAquoteobservable = new DJIAQuoteObservable();

		DJIAquoteobservable.addObserver(
				(Observable o, Object obj)-> { System.out.println("DJIAObserver.");});

		DJIAquoteobservable.setQuote(1300f);
		DJIAquoteobservable.notifyObservers(DJIAquoteobservable);
	    }
}
