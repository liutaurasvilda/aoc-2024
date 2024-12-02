package solutions.day02;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day02Test {

    @Test
    void part1() {
        assertEquals(2, Day02.safeReports(levelReports()));
//        assertEquals(282, Day02.safeReports(levelReports()));
    }

    @Test
    void part2() {
        assertEquals(6, Day02.safeReportsWithBadLevelTolerance(levelReports()));
    }

    private List<List<Integer>> levelReports() {
        return ResourceReader.asIntList("day02_test.txt", ResourceReader.Delimiter.SPACE);
    }
}