package solutions.day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

final class Day10 {

    public static long trailheadsScore(List<List<Integer>> topographicMap) {
        var trailsMap = buildTrailsMap(topographicMap);
        var trailHeads = findTrailHeads(trailsMap);
        return trailHeads.stream().map(trailhead -> trailheadScore(trailhead, trailsMap)).mapToLong(e -> e).sum();
    }

    public static long trailheadsRating(List<List<Integer>> topographicMap) {
        var trailsMap = buildTrailsMap(topographicMap);
        var trailHeads = findTrailHeads(trailsMap);
        return trailHeads.stream().map(trailhead -> trailheadRating(trailhead, trailsMap)).mapToLong(e -> e).sum();
    }

    private static HashMap<Location, Integer> buildTrailsMap(List<List<Integer>> topographicMap) {
        var height = topographicMap.size() - 1;
        var width = topographicMap.getFirst().size() - 1;
        var trailsMap = new HashMap<Location, Integer>();
        for (int i = 0; i < topographicMap.size(); i++) {
            for (int j = 0; j < topographicMap.getFirst().size(); j++) {
                trailsMap.put(Location.of(i, j, height, width), topographicMap.get(i).get(j));
            }
        }
        return trailsMap;
    }

    private static List<Location> findTrailHeads(HashMap<Location, Integer> trailsMap) {
        return trailsMap.entrySet().stream()
                .filter(e -> e.getValue().equals(0))
                .map(Map.Entry::getKey)
                .toList();
    }

    private static long trailheadScore(Location trailhead, Map<Location, Integer> trailsMap) {
        var visited = new HashMap<Location, Integer>();
        dfs1(trailhead, visited, trailsMap);
        return visited.entrySet().stream().filter(e -> e.getValue().equals(9)).count();
    }

    private static long trailheadRating(Location trailhead, HashMap<Location, Integer> trailsMap) {
        var hit9 = new AtomicLong(0);
        dfs2(trailhead, hit9, trailsMap);
        return hit9.get();
    }

    private static void dfs1(Location current, Map<Location, Integer> visited, Map<Location, Integer> trailsMap) {
        if (!visited.containsKey(current)) {
            visited.put(current, trailsMap.get(current));
            current.neighborhood().stream()
                    .filter(e -> trailsMap.get(current) + 1 == trailsMap.get(e))
                    .forEach(e -> dfs1(e, visited, trailsMap));
        }
    }

    private static void dfs2(Location current, AtomicLong hit9, Map<Location, Integer> trailsMap) {
        if (trailsMap.get(current) == 9) {
            hit9.incrementAndGet();
        } else {
            current.neighborhood().stream()
                    .filter(e -> trailsMap.get(current) + 1 == trailsMap.get(e))
                    .forEach(e -> dfs2(e, hit9, trailsMap));
        }
    }
}
