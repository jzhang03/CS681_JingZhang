//
// CS681: HW1
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HW1Test {
	StockQuoteObservable stockquoteobservable = new StockQuoteObservable();
	DJIAQuoteObservable DJIAquoteobservable = new DJIAQuoteObservable();
	
	@Test
    public void StockQuoteObservableTest() {
		stockquoteobservable.addObserver(
				(Observable o, Object obj)->{System.out.println("Updated Pie Chart Stock Observer --" + " Stock Name: " + ((StockEvent)obj).getTicker() + " Stock Price: " + ((StockEvent)obj).getQuote());}
				);
		stockquoteobservable.addObserver(
				(Observable o, Object obj)->{System.out.println("Updated Table Stock Observer --" + " Stock Name: " + ((StockEvent)obj).getTicker() + " Stock Price: " + ((StockEvent)obj).getQuote());}
				);
		stockquoteobservable.addObserver(
				(Observable o, Object obj)->{System.out.println("Updated 3D Stock Observer --" + " Stock Name: " + ((StockEvent)obj).getTicker() + " Stock Price: " + ((StockEvent)obj).getQuote());}
				);
		stockquoteobservable.changeQuote("AAPL", 300f);
		stockquoteobservable.notifyObservers(new StockEvent("AAPL", 300f));
	}
	
	@Test
    public void DJIAQuoteObservableTest() {
		DJIAquoteobservable.addObserver(
				(Observable o, Object obj)->{System.out.println("Updated Pie Chart DJIA Observer --" + " Quote: " + ((DJIAEvent)obj).getDjia());}
				);
		DJIAquoteobservable.addObserver(
				(Observable o, Object obj)->{System.out.println("Updated Table DJIA Observer --" + " Quote: " + ((DJIAEvent)obj).getDjia());}
				);
		DJIAquoteobservable.addObserver(
				(Observable o, Object obj)->{System.out.println("Updated 3D Chart DJIA Observer --" + " Quote: " + ((DJIAEvent)obj).getDjia());}
				);
		DJIAquoteobservable.changeQuote(23782.68f);
		DJIAquoteobservable.notifyObservers(new DJIAEvent(23782.68f));
	}
}
