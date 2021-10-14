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

public class PriceComparatorTest {
	private static ArrayList<Car> cars = new ArrayList<>();
    
    @BeforeAll
    public static void init() {
        cars.add(new Car("Honda", "Civic", 2000, 1998, 35000f));
        cars.add(new Car("Toyota", "RAV4", 1500, 2004, 30000f));
        cars.add(new Car("Lexus", "ES300h", 1100, 2015, 20000f));
        cars.add(new Car("Tesla", "Model3", 5000, 2020, 10000f));
    }
    
    @Test
    public void PriceCompareTest() {
        String[] expected = {"10000.0", "20000.0", "30000.0", "35000.0"};
        Collections.sort(cars, Comparator.comparing(Car::getPrice));
        String[] actual = {String.valueOf(cars.get(0).getPrice()),
                String.valueOf(cars.get(1).getPrice()),
                String.valueOf(cars.get(2).getPrice()),
                String.valueOf(cars.get(3).getPrice())};
        assertArrayEquals(expected, actual);
    }
}
