package solutions.day12;

import common.Location;

import java.util.HashSet;
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
        // TODO
        return 0;
    }

    public long perimeterFencingPrice() {
        return area() * perimeter();
    }

    public long sidesFencingPrice() {
        return area() * sides();
    }
}
