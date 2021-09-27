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

public class ObserverTest {
	StockQuoteObservable stockE = new StockQuoteObservable();
    DJIAQuoteObservable djiaE = new DJIAQuoteObservable();
    PiechartObserver piechart = new PiechartObserver();
    TableObserver table = new TableObserver();
    ThreeDObserver threeD = new ThreeDObserver();
    ByteArrayOutputStream acual;
    PrintStream printout;

    @Test
    public void PiecharObserverStockTest() {
    	acual = new ByteArrayOutputStream();
        printout = new PrintStream(acual);
        System.setOut(printout);
        stockE.addObserver(piechart);
        stockE.changeQuote("A", 1500f);
        String expected = "\n Piechart: \n Ticker = A\n Quote = 1500.0\n";
        assertEquals(expected, acual.toString());
    }
    
    @Test
    public void PiechartObserverDJIATest() {
    	acual = new ByteArrayOutputStream();
        printout = new PrintStream(acual);
        System.setOut(printout);
        djiaE.addObserver(piechart);
        djiaE.changeQuote(2100.0f);
        String expected1 = "\n Piechart: \n DJIA = 2100.0";
        assertEquals(expected1, acual.toString());
    }
    
    @Test
    public void TableObserverStockTest() {
    	acual = new ByteArrayOutputStream();
        printout = new PrintStream(acual);
        System.setOut(printout);
        stockE.addObserver(table);
        stockE.changeQuote("B", 2500f);
        String expected = "\n Table: \n Ticker = B\n Quote = 2500.0\n";
        assertEquals(expected, acual.toString());
    }
    
    @Test
    public void TableObserverDJIATest() {
    	acual = new ByteArrayOutputStream();
        printout = new PrintStream(acual);
        System.setOut(printout);
        djiaE.addObserver(table);
        djiaE.changeQuote(3200.0f);
        String expected1 ="\n Table: \n DJIA = 3200.0";
        assertEquals(expected1, acual.toString());
    }
    
    @Test
    public void ThreeDObserverStockTest() {
    	acual = new ByteArrayOutputStream();
        printout = new PrintStream(acual);
        System.setOut(printout);
        stockE.addObserver(threeD);
        stockE.changeQuote("C", 4300f);
        String expected = "\n 3DObserver: \n Ticker = C\n Quote = 4300.0\n";
        assertEquals(expected, acual.toString());
    }

    @Test
    public void ThreeDObserverDJIATest() {
    	acual = new ByteArrayOutputStream();
        printout = new PrintStream(acual);
        System.setOut(printout);
        djiaE.addObserver(threeD);
        djiaE.changeQuote(5800.0f);
        String expected1 = "\n 3DObserver: \n DJIA = 5800.0";
        assertEquals(expected1, acual.toString());
    }
}
