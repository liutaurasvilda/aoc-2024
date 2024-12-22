package solutions.day14;

import common.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static solutions.day14.Day14.Robot.height;
import static solutions.day14.Day14.Robot.width;

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

    public static long easterEgg(List<Robot> robots) {
        List<Robot> movedRobots = new ArrayList<>(robots);

        int seconds = 1;
        outer:
        while (true) {
            movedRobots = movedRobots.stream().map(Robot::move).toList();
            Map<Long, List<Robot>> robotsByRows = movedRobots.stream().collect(Collectors.groupingBy(e -> e.position().y()));
            for (Map.Entry<Long, List<Robot>> entry : robotsByRows.entrySet()) {
                var xPositions = entry.getValue().stream().map(e -> e.position().x()).sorted().toList();
                int match = 1;
                for (int i = 0; i < xPositions.size()-1; i++) {
                    if (Math.abs(xPositions.get(i) - xPositions.get(i+1)) == 1) {
                        match++;
                    }
                }
                if (match >= 20) {
                    break outer;
                }
            }
            seconds++;
        }

        print(movedRobots);
        return seconds;
    }

    private static void print(List<Robot> robots) {
        var robotPositions = robots.stream().map(Robot::position).collect(Collectors.toSet());
        for (int y = 0; y < height; y++) {
            System.out.println();
            for (int x = 0; x < width; x++) {
                if (robotPositions.contains(new Coordinate(x, y))) {
                    System.out.print("1");
                } else {
                    System.out.print(".");
                }
            }
        }
    }

    record Robot(Coordinate position, Coordinate velocity) {
        public static final int width = 101;
        public static final int height = 103;

        Robot move() {
            long x = (position.x() + velocity.x() + width) % width;
            long y = (position.y() + velocity.y() + height) % height;
            return new Robot(new Coordinate(x, y), velocity);
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
