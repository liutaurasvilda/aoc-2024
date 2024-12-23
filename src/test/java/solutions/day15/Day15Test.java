package solutions.day15;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Test {

    @Test
    void part1() {
        assertEquals(0, Day15.sumOfGpsCoordinates(warehouse()));
    }

    private static Day15.Warehouse warehouse() {
        List<String> map = ResourceReader.stringLines("day15_test_map.txt");
        List<String> movements = ResourceReader.stringLines("day15_test_movements.txt");
        return new Day15.Warehouse(map, movements);
    }
}