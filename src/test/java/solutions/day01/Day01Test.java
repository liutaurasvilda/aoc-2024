package solutions.day01;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {

    @Test
    void totalDistance() {
        assertEquals(1580061, Day01.totalDistance(historianNotes()));
    }

    @Test
    void similarityScore() {
        assertEquals(23046913, Day01.similarityScore(historianNotes()));
    }

    private static Day01.HistorianNotes historianNotes() {
        var locationIds1 = new ArrayList<Long>();
        var locationIds2 = new ArrayList<Long>();

        ResourceReader.asString("day01.txt").stream()
                .map(e -> e.split("   ")).forEach(e -> {
                    locationIds1.add(Long.valueOf(e[0]));
                    locationIds2.add(Long.valueOf(e[1]));
                });

        return new Day01.HistorianNotes(locationIds1, locationIds2);
    }
}