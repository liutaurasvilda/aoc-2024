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
                var symbolAtNextLocation = warehouseMap.get(nextLocation.row()).get(nextLocation.column());
                if (symbolAtNextLocation.equals(".")) {
                    var currentLocation = robot.getLocation();
                    warehouseMap.get(currentLocation.row()).set(currentLocation.column(), ".");
                    var newLocation = robot.move();
                    warehouseMap.get(newLocation.row()).set(newLocation.column(), "@");
                } else if (symbolAtNextLocation.equals("O")) {
                    var boxRow = nextLocation.row();
                    var boxColumn = nextLocation.column();
                    switch (robot.getDirection()) {
                        case UP -> {
                            var indexToPutBoxAt = -1;
                            for (int i = boxRow-1; i > 0; i--) {
                                if (warehouseMap.get(i).get(boxColumn).equals(".")) {
                                    indexToPutBoxAt = i;
                                    break;
                                } else if (warehouseMap.get(i).get(boxColumn).equals("#")) {
                                    break;
                                }
                            }
                            moveBoxesVertically(indexToPutBoxAt, warehouseMap, boxColumn, boxRow, robot);
                        }
                        case DOWN -> {
                            var indexToPutBoxAt = -1;
                            for (int i = boxRow+1; i < warehouseMap.size(); i++) {
                                if (warehouseMap.get(i).get(boxColumn).equals(".")) {
                                    indexToPutBoxAt = i;
                                    break;
                                } else if (warehouseMap.get(i).get(boxColumn).equals("#")) {
                                    break;
                                }
                            }
                            moveBoxesVertically(indexToPutBoxAt, warehouseMap, boxColumn, boxRow, robot);
                        }
                        case LEFT -> {
                            var indexToPutBoxAt = -1;
                            for (int i = boxColumn-1; i > 0; i--) {
                                if (warehouseMap.get(boxRow).get(i).equals(".")) {
                                    indexToPutBoxAt = i;
                                    break;
                                } else if (warehouseMap.get(i).get(boxColumn).equals("#")) {
                                    break;
                                }
                            }
                            moveBoxesHorizontally(indexToPutBoxAt, warehouseMap, boxColumn, boxRow, robot);
                        }
                        case RIGHT -> {
                            var indexToPutBoxAt = -1;
                            for (int i = boxColumn+1; i < warehouseMap.getFirst().size(); i++) {
                                if (warehouseMap.get(boxRow).get(i).equals(".")) {
                                    indexToPutBoxAt = i;
                                    break;
                                } else if (warehouseMap.get(i).get(boxColumn).equals("#")) {
                                    break;
                                }
                            }
                            moveBoxesHorizontally(indexToPutBoxAt, warehouseMap, boxColumn, boxRow, robot);
                        }
                    }
                }
            }
        }
        print(warehouseMap);
        return calculateGpsCoordinates(warehouseMap);
    }

    private static void moveBoxesVertically(int indexToPutBoxAt, List<List<String>> warehouseMap, int boxColumn, int boxRow, Robot robot) {
        if (indexToPutBoxAt != -1) {
            warehouseMap.get(indexToPutBoxAt).set(boxColumn, "O");
            warehouseMap.get(boxRow).set(boxColumn, "@");
            warehouseMap.get(robot.getLocation().row()).set(robot.getLocation().column(), ".");
            robot.move();
        }
    }

    private static void moveBoxesHorizontally(int indexToPutBoxAt, List<List<String>> warehouseMap, int boxColumn, int boxRow, Robot robot) {
        if (indexToPutBoxAt != -1) {
            warehouseMap.get(boxRow).set(indexToPutBoxAt, "O");
            warehouseMap.get(boxRow).set(boxColumn, "@");
            warehouseMap.get(robot.getLocation().row()).set(robot.getLocation().column(), ".");
            robot.move();
        }
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

    private static void print(List<List<String>> map) {
        for (int i = 0; i < map.size(); i++) {
            System.out.println();
            for (int j = 0; j < map.getFirst().size(); j++) {
                System.out.print(map.get(i).get(j));
            }
        }
    }

    record Warehouse(List<List<String>> map, List<List<String>> movements) {
    }
}
