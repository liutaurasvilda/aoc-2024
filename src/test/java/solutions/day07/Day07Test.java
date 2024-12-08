package solutions.day07;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Test {

    @Test
    void part1() {
        assertEquals(3749, Day07.calibrationResult(equations()));
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