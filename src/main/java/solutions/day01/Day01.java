package solutions.day01;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Day01 {

    public static long distance(LocationIDs locationIDs) {
        var leftList = locationIDs.leftList.sorted().toList();
        var rightList = locationIDs.rightList.sorted().toList();
        return LongStream.range(0, leftList.size())
                .map(e -> Math.abs(leftList.get((int) e) - rightList.get((int) e)))
                .sum();
    }

    public static long similarityScore(LocationIDs locationIDs) {
        var rightList = locationIDs.rightList
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return locationIDs.leftList
                .map(e -> rightList.getOrDefault(e, 0L) * e)
                .mapToLong(e -> e)
                .sum();
    }

    public record LocationIDs(Stream<Long> leftList, Stream<Long> rightList) {
    }
}
