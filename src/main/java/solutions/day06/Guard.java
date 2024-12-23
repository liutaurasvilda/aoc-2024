package solutions.day06;

import common.Location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Guard {

    private Location location;
    private Location.Direction direction;

    private final Set<Location> visited;
    private final Map<Map.Entry<Location, Location.Direction>, Integer> rightTurns;

    public Guard(List<List<String>> map) {
        this.location = startingLocation(map);
        this.direction = Location.Direction.UP;
        this.visited = new HashSet<>();
        this.visited.add(location);
        this.rightTurns = new HashMap<>();
    }

    public void turnRight() {
        direction = switch (direction) {
            case LEFT -> Location.Direction.UP;
            case RIGHT -> Location.Direction.DOWN;
            case UP -> Location.Direction.RIGHT;
            case DOWN -> Location.Direction.LEFT;
        };
        rightTurns.merge(Map.entry(location, direction), 1, Integer::sum);
    }

    public void move() {
        location = location.move(direction);
        visited.add(location);
    }

    public Location dryMove() {
        return location.move(direction);
    }

    public Set<Location> getVisited() {
        return visited;
    }

    public boolean inCycle() {
        return rightTurns.values().stream().anyMatch(e -> e > 1);
    }

    private static Location startingLocation(List<List<String>> map) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.getFirst().size(); j++) {
                if (map.get(i).get(j).equals("^")) {
                    return new Location(i, j);
                }
            }
        }
        throw new IllegalStateException("Guard not found");
    }
}
