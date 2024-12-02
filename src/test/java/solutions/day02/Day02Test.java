package solutions.day02;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day02Test {

    @Test
    void part1() {
        assertEquals(282, Day02.safeReports(levelReports()));
    }

    @Test
    void part2() {
        assertEquals(349, Day02.safeReportsWithSingleBadLevelTolerance(levelReports()));
    }

    private List<List<Integer>> levelReports() {
        return ResourceReader.asIntList("day02.txt", ResourceReader.Delimiter.SPACE);
    }
}