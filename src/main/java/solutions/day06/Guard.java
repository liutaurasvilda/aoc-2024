package solutions.day06;

import java.util.*;

public class Guard {

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    private Location location;
    private Direction direction;

    private final Set<Location> visited;
    private final Map<Map.Entry<Location, Direction>, Integer> rightTurns;

    public Guard(List<List<String>> map) {
        this.location = startingLocation(map);
        this.direction = Direction.UP;
        this.visited = new HashSet<>();
        this.visited.add(location);
        this.rightTurns = new HashMap<>();
    }

    public void turnRight() {
        direction = switch (direction) {
            case LEFT -> Direction.UP;
            case RIGHT -> Direction.DOWN;
            case UP -> Direction.RIGHT;
            case DOWN -> Direction.LEFT;
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

    public record Location(int row, int column) {
        private Location move(Direction direction) {
            return switch (direction) {
                case LEFT -> new Location(row, column - 1);
                case RIGHT -> new Location(row, column + 1);
                case UP -> new Location(row - 1, column);
                case DOWN -> new Location(row + 1, column);
            };
        }
    }

    private static Location startingLocation(List<List<String>> map) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.getFirst().size(); j++) {
                if (map.get(i).get(j).equals("^")) {
                    return new Guard.Location(i, j);
                }
            }
        }
        throw new IllegalStateException("Guard not found");
    }
}
