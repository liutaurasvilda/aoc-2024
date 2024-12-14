package solutions.day12;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void part1() {
        assertEquals(0L, Day12.fencingPrice(gardenPlots("day12_test1.txt")));
    }

    private List<List<String>> gardenPlots(String file) {
        return ResourceReader.stringLinesDelimited(file, ResourceReader.Delimiter.NO_SPACE);
    }
}