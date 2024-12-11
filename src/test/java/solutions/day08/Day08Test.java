package solutions.day08;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {

    @Test
    void part1() {
        assertEquals(259, Day08.antinodesLocations1(antennasMap()));
    }

    @Test
    void part2() {
        assertEquals(927, Day08.antinodesLocations2(antennasMap()));
    }

    private List<List<String>> antennasMap() {
        return ResourceReader.stringLinesDelimited("day08.txt", ResourceReader.Delimiter.NO_SPACE);
    }
}