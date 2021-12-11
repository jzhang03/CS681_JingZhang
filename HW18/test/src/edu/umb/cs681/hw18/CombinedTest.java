package edu.umb.cs681.hw18;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CombinedTest {
	private static ArrayList<Car> cars = new ArrayList<>();
    
    @BeforeAll
    public static void init() {
        cars.add(new Car("Honda", "Civic", 2000, 1998, 35000.0f));
        cars.add(new Car("Toyota", "RAV4", 1500, 2004, 30000.0f));
        cars.add(new Car("Lexus", "ES300h", 1100, 2015, 20000.0f));
        cars.add(new Car("Tesla", "Model3", 5000, 2020, 10000.0f));
    }
    
    @Test
    public void countTest() {
        String expected = "4";
        Integer num = cars.stream()
                .parallel()
                .map((Car car) -> car.getModel())
                .reduce(0, (result, car) -> {
                            if (car != null)
                                ++result;
                            return result;
                        },
                        (finalResult, intermediateResult) ->  finalResult + intermediateResult
                );
        String actual = String.valueOf(num);
        assertEquals(expected, actual);
    }
    
    @Test
    public void minTest() {
        String expected = "10000.0";
        float price = cars.stream()
                .parallel()
                .map((Car car) -> car.getPrice())
                .reduce((float) 0, (result, carPrice) -> {
                    if (result == 0) return carPrice;
                    else if (carPrice < result) return carPrice;
                    else return result;
                },(finalResult, interMediateResult)->

                     (finalResult < interMediateResult)? finalResult:interMediateResult
                );
        String actual = String.valueOf(price);
        assertEquals(expected, actual);
    }
    
    @Test
    public void maxTest() {
        String expected = "35000.0";
        float price = cars.stream()
                .parallel()
                .map((Car car) -> car.getPrice())
                .reduce(0.0f, (result, carPrice) -> {
                    if (result == 0) return carPrice;
                    else if (carPrice > result) return carPrice;
                    else return result;
                },
                        (finalResult, interMediateResult)->
                                (finalResult > interMediateResult)? finalResult:interMediateResult
                           );
        String actual = String.valueOf(price);
        assertEquals(expected, actual);
    }
}
