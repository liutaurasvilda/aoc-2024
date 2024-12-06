package solutions.day05;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

    @Test
    void part1() {
        assertEquals(6051, Day05.correctlyOrderedUpdates(printManual()));
    }

    @Test
    void part2() {
        assertEquals(5093, Day05.incorrectlyOrderedUpdates(printManual()));
    }

    private Day05.PrintManual printManual() {
        var input = ResourceReader.stringLines("day05.txt");
        var pageOrdering = new HashSet<String>();
        var updates = new ArrayList<List<String>>();
        input.forEach(e -> {
            if (e.contains("|")) {
                pageOrdering.add(e);
            }
            else if (e.contains(",")) {
                updates.add(Arrays.stream(e.split(",")).collect(Collectors.toList()));
            }
        });
        return new Day05.PrintManual(pageOrdering, updates);
    }
}