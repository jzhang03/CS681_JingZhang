//
// CS681: HW2
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;

public class StreamApiMileageComparatorTest {
	private static ArrayList<Car> cars = new ArrayList<>();
    
    @BeforeAll
    public static void init() {
        cars.add(new Car("Honda", "Civic", 2000, 1998, 35000f));
        cars.add(new Car("Toyota", "RAV4", 1500, 2004, 30000f));
        cars.add(new Car("Lexus", "ES300h", 1100, 2015, 20000f));
        cars.add(new Car("Tesla", "Model3", 5000, 2020, 10000f));
    }
    
    @Test
    public void MileageCompareTest() {
        String[] expected = {"1100", "1500", "2000", "5000"};
        List<Car> carList = cars.stream()
                .sorted((Car car1, Car car2) -> car1.getMileage()- car2.getMileage())
                .collect(Collectors.toList());
        String[] actual = {String.valueOf(carList.get(0).getMileage()),
                String.valueOf(carList.get(1).getMileage()),
                String.valueOf(carList.get(2).getMileage()),
                String.valueOf(carList.get(3).getMileage())};
        assertArrayEquals(expected, actual);
    }
}
