package solutions.day01;

import util.ResourceReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day01 {

    public static void main(String[] args) {
        var historianNotes = readHistorianNotes(ResourceReader.asString("day01.txt"));
        System.out.println(part1(historianNotes));
        System.out.println(part2(historianNotes));
    }

    private static long part1(HistorianNotes historianNotes) {
        return LongStream.range(0, historianNotes.locationIds1().size()).map(e -> {
            var locationId1 = historianNotes.locationIds1().get((int) e);
            var locationId2 = historianNotes.locationIds2().get((int) e);
            return Math.abs(locationId1 - locationId2);
        }).sum();
    }

    private static long part2(HistorianNotes historianNotes) {
        var locationIDs2Counts = historianNotes.locationIds2.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return historianNotes.locationIds1.stream()
                .map(e -> locationIDs2Counts.getOrDefault(e, 0L) * e)
                .mapToLong(e -> e)
                .sum();
    }

    private record HistorianNotes(List<Long> locationIds1, List<Long> locationIds2) {
    }

    private static HistorianNotes readHistorianNotes(List<String> input) {
        List<Long> locationIds1 = new ArrayList<>();
        List<Long> locationIds2 = new ArrayList<>();

        input.stream().map(e -> e.split("   ")).forEach(e -> {
            locationIds1.add(Long.valueOf(e[0]));
            locationIds2.add(Long.valueOf(e[1]));
        });

        Collections.sort(locationIds1);
        Collections.sort(locationIds2);

        return new HistorianNotes(locationIds1, locationIds2);
    }
}
