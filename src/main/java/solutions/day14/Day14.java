package solutions.day14;

import common.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class Day14 {

    public static long safetyFactor(List<Robot> robots) {
        List<Robot> movedRobots = new ArrayList<>(robots);
        for (int i = 0; i < 100; i++) {
            movedRobots = movedRobots.stream().map(Robot::move).toList();
        }
        var quadrants = movedRobots.stream()
                .filter(e -> !e.quadrant().equals(Robot.Quadrant.NONE))
                .collect(Collectors.groupingBy(Robot::quadrant, Collectors.counting()));

        return quadrants.values().stream().reduce(1L, (subtotal, element) -> subtotal * element);
    }

    record Robot(Coordinate position, Coordinate velocity) {
        private static final int width = 101;
        private static final int height = 103;

        Robot move() {
            long x = (position.x() + velocity.x() + width) % width;
            long y = (position.y() + velocity.y() + height) % height;
            var newPosition = new Coordinate(x < 0 ? x + width : x, y < 0 ? y + height : y);
            return new Robot(newPosition, velocity);
        }

        Quadrant quadrant() {
            var halfWidth = width / 2;
            var halfHeight = height / 2;

            if (position.x() < halfWidth && position.y() < halfHeight) {
                return Quadrant.UPPER_LEFT;
            } else if (position.x() > halfWidth && position.y() < halfHeight) {
                return Quadrant.UPPER_RIGHT;
            } else if (position.x() < halfWidth && position.y() > halfHeight) {
                return Quadrant.BOTTOM_LEFT;
            } else if (position.x() > halfWidth && position.y() > halfHeight) {
                return Quadrant.BOTTOM_RIGHT;
            } else {
                return Quadrant.NONE;
            }
        }

        enum Quadrant {
            UPPER_LEFT, UPPER_RIGHT,
            BOTTOM_LEFT, BOTTOM_RIGHT,
            NONE;
        }
    }
}
