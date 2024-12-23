package solutions.day15;

import common.Location;
import common.Robot;

import java.util.ArrayList;
import java.util.List;

final class Day15 {

    public static long sumOfGpsCoordinates(Warehouse warehouse) {
        List<List<String>> warehouseMap = new ArrayList<>(warehouse.map());
        var robot = new Robot(startingLocation(warehouseMap));
        for (List<String> movements : warehouse.movements()) {
            for (String movement : movements) {
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
        return calculateGpsCoordinates(warehouseMap);
    }

    private static void setDirection(String movement, Robot robot) {
        switch (movement) {
            case "^" -> robot.faceUp();
            case "v" -> robot.faceDown();
            case "<" -> robot.faceLeft();
            case ">" -> robot.faceRight();
        }
    }

    private static long calculateGpsCoordinates(List<List<String>> warehouseMap) {
        var sum = 0L;
        for (int i = 0; i < warehouseMap.size(); i++) {
            for (int j = 0; j < warehouseMap.getFirst().size(); j++) {
                if (warehouseMap.get(i).get(j).equals("O")) {
                    sum += (i * 100L) + j;
                }
            }
        }
        return sum;
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
