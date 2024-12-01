package solutions.day01;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day01 {

    public static long totalDistance(HistorianNotes historianNotes) {
        Collections.sort(historianNotes.locationIds1);
        Collections.sort(historianNotes.locationIds2);
        return LongStream.range(0, historianNotes.locationIds1().size()).map(e -> {
            var locationId1 = historianNotes.locationIds1().get((int) e);
            var locationId2 = historianNotes.locationIds2().get((int) e);
            return Math.abs(locationId1 - locationId2);
        }).sum();
    }

    public static long similarityScore(HistorianNotes historianNotes) {
        var locationIDs2Counts = historianNotes.locationIds2.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return historianNotes.locationIds1.stream()
                .map(e -> locationIDs2Counts.getOrDefault(e, 0L) * e)
                .mapToLong(e -> e)
                .sum();
    }

    public record HistorianNotes(List<Long> locationIds1, List<Long> locationIds2) {
    }
}
