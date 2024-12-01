package solutions.day01;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {

    @Test
    void distance() {
        assertEquals(1580061, Day01.distance(locationIDs()));
    }

    @Test
    void similarityScore() {
        assertEquals(23046913, Day01.similarityScore(locationIDs()));
    }

    private static Day01.LocationIDs locationIDs() {
        var leftList = new ArrayList<Long>();
        var rightList = new ArrayList<Long>();

        ResourceReader.asString("day01.txt").stream()
                .map(e -> e.split("   ")).forEach(e -> {
                    leftList.add(Long.valueOf(e[0]));
                    rightList.add(Long.valueOf(e[1]));
                });

        return new Day01.LocationIDs(leftList.stream(), rightList.stream());
    }
}