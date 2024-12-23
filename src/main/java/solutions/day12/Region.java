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
            if (!gardenPlots.contains(plot.left())) {
                addSide(leftSides, plot.left().column(), plot.row());
            }
            if (!gardenPlots.contains(plot.right())) {
                addSide(rightSides, plot.right().column(), plot.row());
            }
            if (!gardenPlots.contains(plot.up())) {
                addSide(topSides, plot.up().row(), plot.column());
            }
            if (!gardenPlots.contains(plot.down())) {
                addSide(bottomSides, plot.down().row(), plot.column());
            }
        }

        leftSides.values().forEach(Collections::sort);
        rightSides.values().forEach(Collections::sort);
        topSides.values().forEach(Collections::sort);
        bottomSides.values().forEach(Collections::sort);

        var levelGaps = countGaps(leftSides) + countGaps(rightSides) + countGaps(topSides) + countGaps(bottomSides);

        return leftSides.size() + rightSides.size() + topSides.size() + bottomSides.size() + levelGaps;
    }

    private static void addSide(Map<Integer, List<Integer>> sides, int side, int level) {
        if (sides.containsKey(side)) {
            sides.get(side).add(level);
        } else {
            sides.put(side, new ArrayList<>(List.of(level)));
        }
    }

    private static int countGaps(Map<Integer, List<Integer>> sides) {
        var gaps = 0;
        for (List<Integer> level : sides.values()) {
            for (int i = 0; i < level.size()-1; i++) {
                if (level.get(i) + 1 != level.get(i+1)) {
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
