package solutions.day07;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Test {

    @Test
    void part1() {
        assertEquals(3749, Day07.calibrationResult(equations()));
    }

    @Test
    void permutations() {
        var expected1 = new ArrayList<List<String>>();
        expected1.add(List.of("+"));
        expected1.add(List.of("*"));

        var expected2 = new ArrayList<List<String>>();
        expected2.add(List.of("+", "+"));
        expected2.add(List.of("+", "*"));
        expected2.add(List.of("*", "+"));
        expected2.add(List.of("*", "*"));

        assertEquals(expected1, Day07.permutations(List.of("+", "*"), 1));
        assertEquals(expected2, Day07.permutations(List.of("+", "*"), 2));
    }

    private List<Day07.Equation> equations() {
        var input = ResourceReader.stringLines("day07_test.txt");
        return input.stream().map(e -> {
            String[] splitInput = e.split(":");
            var testValue = Long.parseLong(splitInput[0]);
            var numbers = Arrays.stream(splitInput[1].substring(1).split(" ")).map(Long::parseLong).toList();
            return new Day07.Equation(testValue, numbers);
        }).toList();
    }
}