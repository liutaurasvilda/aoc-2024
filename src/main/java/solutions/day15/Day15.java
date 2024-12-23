package solutions.day15;

import common.Location;

import java.util.List;

final class Day15 {

    public static long sumOfGpsCoordinates(Warehouse warehouse) {
        Location start = startingLocation(warehouse.map());
        return 0;
    }

    private static Location startingLocation(List<List<String>> map) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.getFirst().size(); j++) {
                if (map.get(i).get(j).equals("@")) {
                    return new Location(i, j);
                }
            }
        }
        throw new IllegalStateException("Guard not found");
    }

    record Warehouse(List<List<String>> map, List<List<String>> movements) {
    }
}
