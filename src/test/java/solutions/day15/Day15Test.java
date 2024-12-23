package solutions.day15;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Test {

    @Test
    void part1() {
        assertEquals(2028, Day15.sumOfGpsCoordinates(warehouse()));
    }

    private static Day15.Warehouse warehouse() {
        var map = ResourceReader.stringLinesDelimited("day15_test1_map.txt", ResourceReader.Delimiter.NO_SPACE);
        var movements = ResourceReader.stringLinesDelimited("day15_test1_movements.txt", ResourceReader.Delimiter.NO_SPACE);
        return new Day15.Warehouse(map, movements);
    }
}