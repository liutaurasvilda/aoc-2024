package solutions.day11;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    @Test
    void part1() {
        assertEquals(186175, Day11.stonesCount(25, stones()));
    }

    @Test
    void part2() {
        assertEquals(220566831337810L, Day11.stonesCount(75, stones()));
    }

    private List<String> stones() {
        return ResourceReader.oneLineStringsDelimited("day11.txt", ResourceReader.Delimiter.SPACE);
    }
}