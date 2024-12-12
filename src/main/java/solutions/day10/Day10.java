package solutions.day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Day10 {

    public static long trailheadsScore(List<List<Integer>> topographicMap) {
        var trailsMap = new HashMap<Location, Integer>();
        for (int i = 0; i < topographicMap.size(); i++) {
            for (int j = 0; j < topographicMap.getFirst().size(); j++) {
                trailsMap.put(Location.of(i, j, topographicMap.size() - 1, topographicMap.getFirst().size() - 1), topographicMap.get(i).get(j));
            }
        }

        var trailHeads = trailsMap.entrySet().stream()
                .filter(e -> e.getValue().equals(0))
                .map(Map.Entry::getKey)
                .toList();

        return trailHeads.stream().map(trailhead -> trailheadScore(trailhead, trailsMap)).mapToLong(e -> e).sum();
    }

    private static long trailheadScore(Location trailhead, Map<Location, Integer> trailsMap) {
        var visited = new HashMap<Location, Integer>();
        dfs(trailhead, visited, trailsMap);
        return visited.entrySet().stream().filter(e -> e.getValue().equals(9)).count();
    }

    private static void dfs(Location current, Map<Location, Integer> visited, Map<Location, Integer> trailsMap) {
        if (!visited.containsKey(current)) {
            visited.put(current, trailsMap.get(current));
            current.neighborhood().stream()
                    .filter(e -> trailsMap.get(current) + 1 == trailsMap.get(e))
                    .forEach(e -> dfs(e, visited, trailsMap));
        }
    }
}
