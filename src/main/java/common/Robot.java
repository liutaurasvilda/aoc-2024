package common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Robot {

    private Location location;
    private Direction direction;

    private final Set<Location> visited;
    private final Map<Map.Entry<Location, Direction>, Integer> rightTurns;

    public Robot(Location location) {
        this(location, Direction.UNDEFINED);
    }

    public Robot(Location location, Direction direction) {
        this.location = location;
        this.direction = direction;
        this.visited = new HashSet<>();
        this.visited.add(this.location);
        this.rightTurns = new HashMap<>();
    }

    public void faceUp() {
        direction = Direction.UP;
    }

    public void faceDown() {
        direction = Direction.DOWN;
    }

    public void faceLeft() {
        direction = Direction.LEFT;
    }

    public void faceRight() {
        direction = Direction.RIGHT;
    }

    public void turnRight() {
        direction = direction.turnRight();
        rightTurns.merge(Map.entry(location, direction), 1, Integer::sum);
    }

    public Location move() {
        location = location.move(direction);
        visited.add(location);
        return location;
    }

    public Location dryMove() {
        return location.move(direction);
    }

    /* Getters */
    public Location getLocation() {
        return location;
    }

    public Direction getDirection() {
        return direction;
    }

    public Set<Location> getVisited() {
        return visited;
    }

    public boolean inCycle() {
        return rightTurns.values().stream().anyMatch(e -> e > 1);
    }
}
