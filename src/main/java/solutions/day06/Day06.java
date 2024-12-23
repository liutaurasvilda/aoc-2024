package solutions.day06;

import common.Location;

import java.util.List;

final class Day06 {

    public static long guardPositions(List<List<String>> map) {
        return explore(map).getVisited().size();
    }

    public static long guardObstructions(List<List<String>> map) {
        var cycles = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.getFirst().size(); j++) {
                if (map.get(i).get(j).equals("#") || map.get(i).get(j).equals("^")) {
                    continue;
                }
                map.get(i).set(j, "#");
                if (explore(map).inCycle()) {
                    cycles++;
                }
                map.get(i).set(j, ".");
            }
        }
        return cycles;
    }

    private static Guard explore(List<List<String>> map) {
        var guard = new Guard(map);
        var next = guard.dryMove();
        while (inMap(next, map) && !guard.inCycle()) {
            var ahead = map.get(next.row()).get(next.column());
            if (ahead.equals("#")) {
                guard.turnRight();
            } else {
                guard.move();
            }
            next = guard.dryMove();
        }
        return guard;
    }

    private static boolean inMap(Location next, List<List<String>> map) {
        return next.row() >= 0 && next.row() < map.size() &&
                next.column() >= 0 && next.column() < map.getFirst().size();
    }
}
