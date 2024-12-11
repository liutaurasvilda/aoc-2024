package solutions.day09;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {

    @Test
    void part1() {
        assertEquals(6435922584968L, Day09.filesystemChecksum(diskMap()));
    }

    private List<Integer> diskMap() {
        return ResourceReader.oneLineIntsDelimited("day09.txt", ResourceReader.Delimiter.NO_SPACE);
    }
}