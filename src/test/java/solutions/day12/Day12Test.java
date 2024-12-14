package solutions.day12;

import common.Location;
import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void part1() {
        assertEquals(140, Day12.perimeterFencingPrice(gardenPlots("day12_test1.txt")));
        assertEquals(772, Day12.perimeterFencingPrice(gardenPlots("day12_test2.txt")));
        assertEquals(1930, Day12.perimeterFencingPrice(gardenPlots("day12_test3.txt")));
        assertEquals(1449902, Day12.perimeterFencingPrice(gardenPlots("day12.txt")));
    }

    @Test
    void part2() {
        assertEquals(80, Day12.sidesFencingPrice(gardenPlots("day12_test1.txt")));
        assertEquals(436, Day12.sidesFencingPrice(gardenPlots("day12_test2.txt")));
        assertEquals(236, Day12.sidesFencingPrice(gardenPlots("day12_test4.txt")));
        assertEquals(368, Day12.sidesFencingPrice(gardenPlots("day12_test5.txt")));
        assertEquals(0, Day12.sidesFencingPrice(gardenPlots("day12.txt")));
    }

    @Test
    void region_1() {
        var location1 = new Location(0, 0).withBoundaries(3, 3);
        var location2 = new Location(0, 1).withBoundaries(3, 3);
        var location3 = new Location(0, 2).withBoundaries(3, 3);
        var location4 = new Location(0, 3).withBoundaries(3, 3);

        var region = new Region("A");
        region.addGardenPlots(List.of(location1, location2, location3, location4));

        assertEquals(4, region.area());
        assertEquals(10, region.perimeter());
        assertEquals(40, region.perimeterFencingPrice());
    }

    @Test
    void region_2() {
        var location = new Location(1, 1).withBoundaries(4, 4);

        var region = new Region("X");
        region.addGardenPlots(List.of(location));

        assertEquals(1, region.area());
        assertEquals(4, region.perimeter());
    }

    @Test
    void region_3() {
        var location1 = new Location(0, 0).withBoundaries(4, 4);
        var location2 = new Location(0, 1).withBoundaries(4, 4);
        var location3 = new Location(0, 2).withBoundaries(4, 4);
        var location4 = new Location(0, 3).withBoundaries(4, 4);
        var location5 = new Location(0, 4).withBoundaries(4, 4);
        var location6 = new Location(1, 0).withBoundaries(4, 4);
        var location7 = new Location(1, 2).withBoundaries(4, 4);
        var location8 = new Location(1, 4).withBoundaries(4, 4);
        var location9 = new Location(2, 0).withBoundaries(4, 4);
        var location10 = new Location(2, 1).withBoundaries(4, 4);
        var location11 = new Location(2, 2).withBoundaries(4, 4);
        var location12 = new Location(2, 3).withBoundaries(4, 4);
        var location13 = new Location(2, 4).withBoundaries(4, 4);
        var location14 = new Location(3, 0).withBoundaries(4, 4);
        var location15 = new Location(3, 2).withBoundaries(4, 4);
        var location16 = new Location(3, 4).withBoundaries(4, 4);
        var location17 = new Location(4, 0).withBoundaries(4, 4);
        var location18 = new Location(4, 1).withBoundaries(4, 4);
        var location19 = new Location(4, 2).withBoundaries(4, 4);
        var location20 = new Location(4, 3).withBoundaries(4, 4);
        var location21 = new Location(4, 4).withBoundaries(4, 4);

        var region = new Region("O");
        region.addGardenPlot(location1);
        region.addGardenPlot(location2);
        region.addGardenPlot(location3);
        region.addGardenPlot(location4);
        region.addGardenPlot(location5);
        region.addGardenPlot(location6);
        region.addGardenPlot(location7);
        region.addGardenPlot(location8);
        region.addGardenPlot(location9);
        region.addGardenPlot(location10);
        region.addGardenPlot(location11);
        region.addGardenPlot(location12);
        region.addGardenPlot(location13);
        region.addGardenPlot(location14);
        region.addGardenPlot(location15);
        region.addGardenPlot(location16);
        region.addGardenPlot(location17);
        region.addGardenPlot(location18);
        region.addGardenPlot(location19);
        region.addGardenPlot(location20);
        region.addGardenPlot(location21);

        assertEquals(21, region.area());
        assertEquals(36, region.perimeter());
        assertEquals(756, region.perimeterFencingPrice());
    }

    private List<List<String>> gardenPlots(String file) {
        return ResourceReader.stringLinesDelimited(file, ResourceReader.Delimiter.NO_SPACE);
    }
}