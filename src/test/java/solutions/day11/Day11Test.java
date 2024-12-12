package solutions.day11;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    @Test
    void part1() {
        assertEquals(55312, Day11.stonesCount(stones()));
    }

    private List<Integer> stones() {
        return ResourceReader.oneLineIntsDelimited("day11_test.txt", ResourceReader.Delimiter.SPACE);
    }
}