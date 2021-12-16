//
// CS681: HW19
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PVIdataReader {
	public static void main(String[] args) throws IOException{
		Path path = Paths.get("/Users/skill/Desktop/CS681/HW19data/Model_12.4_20211015_results.csv");
		
		try( Stream<String> lines = Files.lines(path)) {
			List<List<String>> matrix =
					lines.filter((String line) -> line.contains("Massachuset"))
					//	 .reduce((x1, x2) -> x1.charAt(0) > x2.charAt(0) ? x1 : x2)
						 .map( line -> {
							return Stream.of(line.split(","))
									 .parallel()
									 .map(value->value.substring(1, value.length()-2))
									 .collect(Collectors.toList());	})
						 .collect( Collectors.toList());
			matrix.forEach(value -> System.out.println(value));
		} catch (IOException ex) {} 	
	}
}
