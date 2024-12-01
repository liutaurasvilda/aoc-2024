package solutions.day01;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day01 {

    public static long distance(LocationIDs locationIDs) {
        Collections.sort(locationIDs.leftList);
        Collections.sort(locationIDs.rightList);
        return LongStream.range(0, locationIDs.leftList.size()).map(e -> {
            var locationId1 = locationIDs.leftList.get((int) e);
            var locationId2 = locationIDs.rightList.get((int) e);
            return Math.abs(locationId1 - locationId2);
        }).sum();
    }

    public static long similarityScore(LocationIDs locationIDs) {
        var locationIDs2Counts = locationIDs.rightList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return locationIDs.leftList.stream()
                .map(e -> locationIDs2Counts.getOrDefault(e, 0L) * e)
                .mapToLong(e -> e)
                .sum();
    }

    public record LocationIDs(List<Long> leftList, List<Long> rightList) {
    }
}
