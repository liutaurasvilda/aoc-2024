package solutions.day02;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

    @Test
    void part1() {
        assertEquals(282, Day02.safeReports(levelReports()));
    }

    @Test
    void part2() {
        assertEquals(349, Day02.safeReportsWithSingleBadLevelTolerance(levelReports()));
    }

    private Day02.Reports levelReports() {
        return new Day02.Reports(ResourceReader.intLinesDelimited("day02.txt", ResourceReader.Delimiter.SPACE));
    }
}