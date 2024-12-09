package solutions.day08;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {

    @Test
    void part1() {
        assertEquals(14, Day08.antinodeLocations(antennasMap()));
    }

    private List<List<String>> antennasMap() {
        return ResourceReader.stringLinesDelimited("day08_test.txt", ResourceReader.Delimiter.NO_SPACE);
    }
}