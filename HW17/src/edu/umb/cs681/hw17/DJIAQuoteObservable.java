//
// CS681: HW17
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw17;

public class DJIAQuoteObservable extends Observable {
	private float quote;
	
	public void setQuote(float q) {
		quote = q;
        setChanged();
        notifyObservers(new DJIAEvent(quote));
        System.out.println("DJIA setChanged.");
    }
}
