package solutions.day04;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {

    @Test
    void part1() {
        assertEquals(2344, Day04.xmas(wordMap()));
    }

    @Test
    void part2() {
        assertEquals(1815, Day04.masx(wordMap()));
    }

    private List<List<String>> wordMap() {
        return ResourceReader.stringLinesDelimited("day04.txt", ResourceReader.Delimiter.NO_SPACE);
    }
}