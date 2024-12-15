package solutions.day12;

import common.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class Region {

    private final String plant;
    private final Set<Location> gardenPlots;

    public Region(String plant) {
        this.plant = plant;
        this.gardenPlots = new HashSet<>();
    }

    public void addGardenPlot(Location location) {
        gardenPlots.add(location);
    }

    public boolean contains(Location location) {
        return gardenPlots.contains(location);
    }

    public String plant() {
        return plant;
    }

    public long area() {
        return gardenPlots.size();
    }

    public long perimeter() {
        var perimeter = 0L;
        for (Location location : gardenPlots) {
            var outboundSides = 4 - location.neighbourhood().size();
            var untouchedSides = location.neighbourhood().stream()
                    .mapToLong(e -> !gardenPlots.contains(e) ? 1 : 0).sum();
            perimeter += (outboundSides + untouchedSides);
        }
        return perimeter;
    }

    public long sides() {
        var leftSides = new HashMap<Integer, List<Integer>>();
        var rightSides = new HashMap<Integer, List<Integer>>();
        var topSides = new HashMap<Integer, List<Integer>>();
        var bottomSides = new HashMap<Integer, List<Integer>>();

        for (Location plot : gardenPlots) {
            if (!gardenPlots.contains(plot.leftNeighbour())) {
                if (leftSides.containsKey(plot.leftNeighbour().column())) {
                    leftSides.get(plot.leftNeighbour().column()).add(plot.row());
                } else {
                    leftSides.put(plot.leftNeighbour().column(), new ArrayList<>(List.of(plot.row())));
                }
            }
            if (!gardenPlots.contains(plot.rightNeighbour())) {
                if (rightSides.containsKey(plot.rightNeighbour().column())) {
                    rightSides.get(plot.rightNeighbour().column()).add(plot.row());
                } else {
                    rightSides.put(plot.rightNeighbour().column(), new ArrayList<>(List.of(plot.row())));
                }
            }
            if (!gardenPlots.contains(plot.topNeighbour())) {
                if (topSides.containsKey(plot.topNeighbour().row())) {
                    topSides.get(plot.topNeighbour().row()).add(plot.column());
                } else {
                    topSides.put(plot.topNeighbour().row(), new ArrayList<>(List.of(plot.column())));
                }
            }
            if (!gardenPlots.contains(plot.bottomNeighbour())) {
                if (bottomSides.containsKey(plot.bottomNeighbour().row())) {
                    bottomSides.get(plot.bottomNeighbour().row()).add(plot.column());
                } else {
                    bottomSides.put(plot.bottomNeighbour().row(), new ArrayList<>(List.of(plot.column())));
                }
            }
        }

        leftSides.values().forEach(Collections::sort);
        rightSides.values().forEach(Collections::sort);
        topSides.values().forEach(Collections::sort);
        bottomSides.values().forEach(Collections::sort);

        var gaps = 0;
        gaps += countGaps(leftSides);
        gaps += countGaps(rightSides);
        gaps += countGaps(topSides);
        gaps += countGaps(bottomSides);

        return leftSides.size() + rightSides.size() + topSides.size() + bottomSides.size() + gaps;
    }

    private static int countGaps(Map<Integer, List<Integer>> sides) {
        var gaps = 0;
        for (List<Integer> elevation : sides.values()) {
            for (int i = 0; i < elevation.size()-1; i++) {
                if (elevation.get(i) + 1 != elevation.get(i+1)) {
                    gaps++;
                }
            }
        }
        return gaps;
    }

    public long perimeterFencingPrice() {
        return area() * perimeter();
    }

    public long sidesFencingPrice() {
        return area() * sides();
    }
}
