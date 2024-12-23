package solutions.day15;

import common.Location;
import common.Robot;

import java.util.List;

final class Day15 {

    public static long sumOfGpsCoordinates(Warehouse warehouse) {
        var robot = new Robot(startingLocation(warehouse.map()));
        for (List<String> rows : warehouse.movements) {
            for (String movement : rows) {
                setDirection(movement, robot);
                var nextLocation = robot.dryMove();
                var symbol = warehouse.map().get(nextLocation.row()).get(nextLocation.column());
                if (symbol.equals(".")) {
                    robot.move();
                } else if (symbol.equals("O")) {
                    // TODO
                }
            }
        }
        return 0;
    }

    private static void setDirection(String movement, Robot robot) {
        switch (movement) {
            case "^" -> robot.faceUp();
            case "v" -> robot.faceDown();
            case "<" -> robot.faceLeft();
            case ">" -> robot.faceRight();
        }
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
