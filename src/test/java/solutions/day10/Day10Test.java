package solutions.day10;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    @Test
    void part1() {
        assertEquals(501, Day10.trailheadsScore(topographicMap()));
    }

    private List<List<Integer>> topographicMap() {
        return ResourceReader.intLinesDelimited("day10.txt", ResourceReader.Delimiter.NO_SPACE);
    }
}