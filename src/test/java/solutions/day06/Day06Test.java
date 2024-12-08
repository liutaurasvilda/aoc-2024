package solutions.day06;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

    @Test
    void part1() {
        assertEquals(4819, Day06.guardPositions(map()));
    }

    @Test
    void part2() {
        assertEquals(1796, Day06.guardObstructions(map()));
    }

    private List<List<String>> map() {
        return ResourceReader.stringLinesDelimited("day06.txt", ResourceReader.Delimiter.NO_SPACE);
    }
}