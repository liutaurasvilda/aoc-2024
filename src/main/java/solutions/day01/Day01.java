package solutions.day01;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

final class Day01 {

    public static long distance(LocationIDs locationIDs) {
        Collections.sort(locationIDs.leftList());
        Collections.sort(locationIDs.rightList());
        return LongStream.range(0, locationIDs.leftList().size())
                .map(e -> Math.abs(locationIDs.leftList().get((int) e) - locationIDs.rightList().get((int) e)))
                .sum();
    }

    public static long similarityScore(LocationIDs locationIDs) {
        var rightList = locationIDs.rightList().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return locationIDs.leftList().stream()
                .map(e -> rightList.getOrDefault(e, 0L) * e)
                .mapToLong(e -> e)
                .sum();
    }

    record LocationIDs(List<Long> leftList, List<Long> rightList) {
    }
}
