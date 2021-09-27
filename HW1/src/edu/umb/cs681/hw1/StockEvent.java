//
// CS681: HW1
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw1;

public class StockEvent {
	private String ticker;
    private float quote;
    
	public StockEvent(String t, float q) {
		this.ticker = t;
		this.quote = q;
	}
	
    public String getTicker() {
        return ticker;
    }
    
    public float getQuote() {
        return quote;
    }
}
