package solutions.day12;

import common.Location;

import java.util.HashSet;
import java.util.List;
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

    public void addGardenPlots(List<Location> locations) {
        gardenPlots.addAll(locations);
    }

    public String plant() {
        return plant;
    }

    public long area() {
        return gardenPlots.size();
    }

    public long perimeter() {
        var perimeter = 0;
        for (Location location : gardenPlots) {
            perimeter += 4 - location.neighbourhood().size();
            for (Location neighbour : location.neighbourhood()) {
                perimeter += gardenPlots.contains(neighbour) ? 0 : 1;
            }
        }
        return perimeter;
    }

    public long fencingPrice() {
        return area() * perimeter();
    }
}
