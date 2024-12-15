package solutions.day12;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void part1() {
        assertEquals(140, Day12.perimeterFencingPrice(gardenPlots("day12_test1.txt")));
        assertEquals(772, Day12.perimeterFencingPrice(gardenPlots("day12_test2.txt")));
        assertEquals(1930, Day12.perimeterFencingPrice(gardenPlots("day12_test3.txt")));
        assertEquals(1449902, Day12.perimeterFencingPrice(gardenPlots("day12.txt")));
    }

    @Test
    void part2() {
        assertEquals(80, Day12.sidesFencingPrice(gardenPlots("day12_test1.txt")));
        assertEquals(436, Day12.sidesFencingPrice(gardenPlots("day12_test2.txt")));
        assertEquals(1206, Day12.sidesFencingPrice(gardenPlots("day12_test3.txt")));
        assertEquals(236, Day12.sidesFencingPrice(gardenPlots("day12_test4.txt")));
        assertEquals(368, Day12.sidesFencingPrice(gardenPlots("day12_test5.txt")));
        assertEquals(0, Day12.sidesFencingPrice(gardenPlots("day12.txt")));
    }

    private List<List<String>> gardenPlots(String file) {
        return ResourceReader.stringLinesDelimited(file, ResourceReader.Delimiter.NO_SPACE);
    }
}