//
// CS681: HW18
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw18;

import java.util.ArrayList;

public class Car {
	private String make, model;
    private float price;
    private int mileage, year, dominationCount;

    public Car(String make, String model, int mileage, int year, float price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

	public void setDominationCount(ArrayList<Car> usedCars){
		int count = 0;
		for(Car car: usedCars) {
            if (car.getYear() <= this.getYear() && car.getMileage() >= this.getMileage() 
            		&& car.getPrice() >= this.getPrice()) {
                if (car.getYear() < this.getYear() || car.getMileage() > this.getMileage() 
                		|| car.getPrice() > this.getPrice()) {
                    count++;
                }
            }
		}
		this.dominationCount = count; 
	}
	
	public int getDominationCount(){
		return this.dominationCount;
	}
	
    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public int getMileage() {
        return this.mileage;
    }

    public int getYear() {
        return this.year;
    }

    public float getPrice() {
        return this.price;
    }
}
