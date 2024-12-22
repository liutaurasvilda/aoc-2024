package solutions.day14;

import common.Coordinate;
import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    @Test
    void part1() {
        assertEquals(229421808, Day14.safetyFactor(robots()));
    }

    @Test
    void part2() {
        assertEquals(6577, Day14.easterEgg(robots()));
    }

    private List<Day14.Robot> robots() {
        return ResourceReader.stringLines("day14.txt").stream()
                .map(e -> {
                    var split = e.split(" ");
                    var positionStr = split[0].split("=")[1];
                    var velocityStr = split[1].split("=")[1];
                    var positionSplit = positionStr.split(",");
                    var velocitySplit = velocityStr.split(",");
                    var position = new Coordinate(Integer.parseInt(positionSplit[0]), Integer.parseInt(positionSplit[1]));
                    var velocity = new Coordinate(Integer.parseInt(velocitySplit[0]), Integer.parseInt(velocitySplit[1]));
                    return new Day14.Robot(position, velocity);
                }).toList();
    }
}