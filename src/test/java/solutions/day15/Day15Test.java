package solutions.day15;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Test {

    @Test
    void part1() {
        assertEquals(2028, Day15.sumOfGpsCoordinates(warehouse("day15_test1_map.txt", "day15_test1_movements.txt")));
        assertEquals(10092, Day15.sumOfGpsCoordinates(warehouse("day15_test2_map.txt", "day15_test2_movements.txt")));
        assertEquals(1463512, Day15.sumOfGpsCoordinates(warehouse("day15_map.txt", "day15_movements.txt")));
    }

    private static Day15.Warehouse warehouse(String mapFile, String movementsFile) {
        var map = ResourceReader.stringLinesDelimited(mapFile, ResourceReader.Delimiter.NO_SPACE);
        var movements = ResourceReader.stringLinesDelimited(movementsFile, ResourceReader.Delimiter.NO_SPACE);
        return new Day15.Warehouse(map, movements);
    }
}