package solutions.day12;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void part1() {
        assertEquals(1449902, Day12.perimeterFencingPrice(gardenPlots()));
    }

    @Test
    void part2() {
        assertEquals(908042, Day12.sidesFencingPrice(gardenPlots()));
    }

    private List<List<String>> gardenPlots() {
        return ResourceReader.stringLinesDelimited("day12.txt", ResourceReader.Delimiter.NO_SPACE);
    }
}