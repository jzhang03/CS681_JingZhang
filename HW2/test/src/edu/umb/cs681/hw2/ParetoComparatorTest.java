//
// CS681: HW2
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ParetoComparatorTest {
	private static ArrayList<Car> cars = new ArrayList<>();
    
    @BeforeAll
    public static void init() {
        cars.add(new Car("Honda", "Civic", 5000, 1998, 35000f));
        cars.add(new Car("Toyota", "RAV4", 5000, 2004, 30000f));
        cars.add(new Car("Lexus", "ES300h", 4100, 2015, 20000f));
        cars.add(new Car("Tesla", "Model3", 1000, 2020, 10000f));
    }
    
    @Test
    public void ParetoCompareTest() {
        for(Car car: cars){ 
            car.setDominationCount(cars); 
        }

        String[] expected = {"Model3", "ES300h", "RAV4", "Civic"};
        Collections.sort(cars, Comparator.comparing((Car car) -> car.getDominationCount(), 
        		Comparator.reverseOrder()));
        String[] actual = {String.valueOf(cars.get(0).getModel()),
                String.valueOf(cars.get(1).getModel()),
                String.valueOf(cars.get(2).getModel()),
                String.valueOf(cars.get(3).getModel())};
        assertArrayEquals(expected, actual);
    } 
}
