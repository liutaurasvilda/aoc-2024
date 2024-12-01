package solutions.day01;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        var input = ResourceReader.asString("day01.txt");

        List<Long> locationIds1 = new ArrayList<>();
        List<Long> locationIds2 = new ArrayList<>();

        input.stream().map(e -> e.split("   ")).forEach(e -> {
            locationIds1.add(Long.valueOf(e[0]));
            locationIds2.add(Long.valueOf(e[1]));
        });

        return new Day01.HistorianNotes(locationIds1, locationIds2);
    }
}