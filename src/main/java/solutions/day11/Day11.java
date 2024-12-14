package solutions.day11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class Day11 {

    public static long stonesCount(int blinks, List<String> stones) {
        var mapping = stones.stream().collect(Collectors.toMap(e -> e, e -> 1L));
        for (int i = 0; i < blinks; i++) {
            mapping = blink(mapping);
        }
        return mapping.values().stream().mapToLong(e -> e).sum();
    }

    private static HashMap<String, Long> blink(Map<String, Long> mapping) {
        var newMapping = new HashMap<String, Long>();
        for (var entry : mapping.entrySet()) {
            var stone = entry.getKey();
            var stoneCount = entry.getValue();
            if (stone.equals("0")) {
                newMapping.merge("1", stoneCount, Long::sum);
            } else if ((stone.length() & 1) == 0) {
                var left = stone.substring(0, stone.length() / 2);
                var right = stone.substring(stone.length() / 2);
                while (right.startsWith("0") && !right.equals("0")) {
                    right = right.substring(1);
                }
                newMapping.merge(left, stoneCount, Long::sum);
                newMapping.merge(right, stoneCount, Long::sum);
            } else {
                newMapping.merge(String.valueOf(Long.parseLong(stone) * 2024), stoneCount, Long::sum);
            }
        }
        return newMapping;
    }
}
