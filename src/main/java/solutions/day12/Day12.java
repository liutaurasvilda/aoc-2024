package solutions.day12;

import common.Location;

import java.util.ArrayList;
import java.util.List;

final class Day12 {

    public static long perimeterFencingPrice(List<List<String>> gardenPlots) {
        return buildRegions(gardenPlots).stream().mapToLong(Region::perimeterFencingPrice).sum();
    }

    public static long sidesFencingPrice(List<List<String>> gardenPlots) {
        return buildRegions(gardenPlots).stream().mapToLong(Region::sidesFencingPrice).sum();
    }

    private static ArrayList<Region> buildRegions(List<List<String>> gardenPlots) {
        var regions = new ArrayList<Region>();
        int maxHeightIndex = gardenPlots.size()-1;
        int maxWidthIndex = gardenPlots.getFirst().size()-1;
        for (int i = 0; i < gardenPlots.size(); i++) {
            for (int j = 0; j < gardenPlots.getFirst().size(); j++) {
                int row = i, column = j;
                if (regions.stream().noneMatch(region -> region.contains(new Location(row, column)))) {
                    var region = new Region(gardenPlots.get(i).get(j));
                    regions.add(region);
                    dfs(new Location(row, column).withBoundaries(maxHeightIndex, maxWidthIndex), region, gardenPlots);
                }
            }
        }
        return regions;
    }

    private static void dfs(Location current, Region region, List<List<String>> gardenPlots) {
        if (!region.contains(current) && gardenPlots.get(current.row()).get(current.column()).equals(region.plant())) {
            region.addGardenPlot(current);
            current.neighbourhood().forEach(e -> dfs(e, region, gardenPlots));
        }
    }
}
