package solutions.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

final class Day08 {

    public static long antinodesLocations1(List<List<String>> antennasMap) {
        var antennasLocations = getAntennasLocations(antennasMap);
        var antinodes = new HashSet<Location>();
        antennasLocations.forEach((key, value) -> {
            for (int i = 0; i < value.size() - 1; i++) {
                for (int j = i + 1; j < value.size(); j++) {
                    var firstLocation = value.get(i);
                    var secondLocation = value.get(j);

                    var rowDiff = Math.abs(firstLocation.row() - secondLocation.row());
                    var colDiff = firstLocation.column() - secondLocation.column();

                    var horizontal = rowDiff == 0;
                    var vertical = colDiff == 0;
                    var leftToRight = colDiff < 0;
                    var rightToLeft = colDiff > 0;

                    if (horizontal) {
                        if (firstLocation.column() - colDiff >= 0) {
                            antinodes.add(new Location(firstLocation.row(), firstLocation.column() - colDiff));
                        }
                        if (secondLocation.column() + colDiff < antennasMap.size()) {
                            antinodes.add(new Location(secondLocation.row(), secondLocation.column() + colDiff));
                        }
                    } else if (vertical) {
                        if (firstLocation.row() - rowDiff >= 0) {
                            antinodes.add(new Location(firstLocation.row() - rowDiff, firstLocation.column()));
                        }
                        if (secondLocation.row() + rowDiff < antennasMap.size()) {
                            antinodes.add(new Location(secondLocation.row() + rowDiff, secondLocation.column()));
                        }
                    } else if (leftToRight) {
                        if (firstLocation.row() - rowDiff >= 0) {
                            if (firstLocation.column() + colDiff >= 0) {
                                antinodes.add(new Location(firstLocation.row() - rowDiff, firstLocation.column() + colDiff));
                            }
                        }
                        if (secondLocation.row() + rowDiff < antennasMap.size()) {
                            if (secondLocation.column() + Math.abs(colDiff) < antennasMap.size()) {
                                antinodes.add(new Location(secondLocation.row() + rowDiff, secondLocation.column() + Math.abs(colDiff)));
                            }
                        }
                    } else if (rightToLeft) {
                        if (firstLocation.row() - rowDiff >= 0) {
                            if (firstLocation.column() + colDiff < antennasMap.size()) {
                                antinodes.add(new Location(firstLocation.row() - rowDiff, firstLocation.column() + colDiff));
                            }
                        }
                        if (secondLocation.row() + rowDiff < antennasMap.size()) {
                            if (secondLocation.column() - colDiff >= 0) {
                                antinodes.add(new Location(secondLocation.row() + rowDiff, secondLocation.column() - colDiff));
                            }
                        }
                    }
                }
            }
        });
        return antinodes.size();
    }

    public static long antinodesLocations2(List<List<String>> antennasMap) {
        var antennasLocations = getAntennasLocations(antennasMap);
        var antinodes = new HashSet<Location>();
        antennasLocations.forEach((key, value) -> {
            for (int i = 0; i < value.size() - 1; i++) {
                for (int j = i + 1; j < value.size(); j++) {
                    var firstLocation = value.get(i);
                    var secondLocation = value.get(j);

                    var rowDiff = Math.abs(firstLocation.row() - secondLocation.row());
                    var colDiff = firstLocation.column() - secondLocation.column();

                    var horizontal = rowDiff == 0;
                    var vertical = colDiff == 0;
                    var leftToRight = colDiff < 0;
                    var rightToLeft = colDiff > 0;

                    if (horizontal) {
                        antinodes.add(firstLocation);
                        antinodes.add(secondLocation);
                        var tempFirstCol = firstLocation.column();
                        var tempSecondCol = firstLocation.column();
                        while (tempFirstCol - colDiff >= 0) {
                            antinodes.add(new Location(firstLocation.row(), tempSecondCol - colDiff));
                            tempFirstCol = tempFirstCol - colDiff;
                        }
                        while (tempSecondCol + colDiff < antennasMap.size()) {
                            antinodes.add(new Location(secondLocation.row(), tempSecondCol + colDiff));
                            tempSecondCol = tempSecondCol + colDiff;
                        }
                    } else if (vertical) {
                        antinodes.add(firstLocation);
                        antinodes.add(secondLocation);
                        var tempFirstRow = firstLocation.row();
                        var tempSecondRow = firstLocation.row();
                        while (tempFirstRow - rowDiff >= 0) {
                            antinodes.add(new Location(tempFirstRow - rowDiff, firstLocation.column()));
                            tempFirstRow = tempFirstRow - rowDiff;
                        }
                        while (tempSecondRow + rowDiff < antennasMap.size()) {
                            antinodes.add(new Location(tempSecondRow + rowDiff, secondLocation.column()));
                            tempSecondRow = tempSecondRow + rowDiff;
                        }
                    } else if (leftToRight) {
                        antinodes.add(firstLocation);
                        antinodes.add(secondLocation);
                        var tempFirstRow = firstLocation.row();
                        var tempFirstCol = firstLocation.column();
                        var tempSecondRow = secondLocation.row();
                        var tempSecondCol = secondLocation.column();
                        while (tempFirstRow - rowDiff >= 0 && tempFirstCol + colDiff >= 0) {
                            antinodes.add(new Location(tempFirstRow - rowDiff, tempFirstCol + colDiff));
                            tempFirstRow = tempFirstRow - rowDiff;
                            tempFirstCol = tempFirstCol + colDiff;
                        }
                        while (tempSecondRow + rowDiff < antennasMap.size() && tempSecondCol + Math.abs(colDiff) < antennasMap.size()) {
                            antinodes.add(new Location(tempSecondRow + rowDiff, tempSecondCol + Math.abs(colDiff)));
                            tempSecondRow = tempSecondRow + rowDiff;
                            tempSecondCol = tempSecondCol + Math.abs(colDiff);
                        }
                    } else if (rightToLeft) {
                        antinodes.add(firstLocation);
                        antinodes.add(secondLocation);
                        var tempFirstRow = firstLocation.row();
                        var tempFirstCol = firstLocation.column();
                        var tempSecondRow = secondLocation.row();
                        var tempSecondCol = secondLocation.column();
                        while (tempFirstRow - rowDiff >= 0 && tempFirstCol + colDiff < antennasMap.size()) {
                            antinodes.add(new Location(tempFirstRow - rowDiff, tempFirstCol + colDiff));
                            tempFirstRow = tempFirstRow - rowDiff;
                            tempFirstCol = tempFirstCol + colDiff;
                        }
                        while (tempSecondRow + rowDiff < antennasMap.size() && tempSecondCol - colDiff >= 0) {
                            antinodes.add(new Location(tempSecondRow + rowDiff, tempSecondCol - colDiff));
                            tempSecondRow = tempSecondRow + rowDiff;
                            tempSecondCol = tempSecondCol - colDiff;
                        }
                    }
                }
            }
        });
        return antinodes.size();
    }

    private static Map<String, List<Location>> getAntennasLocations(List<List<String>> antennasMap) {
        var antennasLocations = new HashMap<String, List<Location>>();
        for (int i = 0; i < antennasMap.size(); i++) {
            for (int j = 0; j < antennasMap.getFirst().size(); j++) {
                var antennaFrequency = antennasMap.get(i).get(j);
                if (antennaFrequency.equals(".") || antennaFrequency.equals("#")) {
                    continue;
                }
                if (!antennasLocations.containsKey(antennaFrequency)) {
                    antennasLocations.put(antennaFrequency, new ArrayList<>());
                }
                antennasLocations.get(antennaFrequency).add(new Location(i, j));
            }
        }
        return antennasLocations;
    }

    private record Location(int row, int column) {
    }
}
