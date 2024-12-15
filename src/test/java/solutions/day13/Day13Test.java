package solutions.day13;

import common.Coordinate;
import org.junit.jupiter.api.Test;
import util.ResourceReader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    @Test
    void part1() {
        assertEquals(32067, Day13.fewestTokens(clawMachines()));
    }

    @Test
    void part1_machine1() {
        var clawMachine = new Day13.ClawMachine(
                new Day13.ClawMachine.Button("A", x -> x * 94, y -> y * 34),
                new Day13.ClawMachine.Button("B", x -> x * 22, y -> y * 67),
                new Coordinate(8400, 5400)
        );
        assertEquals(280, Day13.fewestTokens(List.of(clawMachine)));
    }

    @Test
    void part1_machine2() {
        var clawMachine = new Day13.ClawMachine(
                new Day13.ClawMachine.Button("A", x -> x * 26, y -> y * 66),
                new Day13.ClawMachine.Button("B", x -> x * 67, y -> y * 21),
                new Coordinate(12748, 12176)
        );
        assertEquals(0, Day13.fewestTokens(List.of(clawMachine)));
    }

    @Test
    void part1_machine3() {
        var clawMachine = new Day13.ClawMachine(
                new Day13.ClawMachine.Button("A", x -> x * 17, y -> y * 86),
                new Day13.ClawMachine.Button("B", x -> x * 84, y -> y * 37),
                new Coordinate(7870, 6450)
        );
        assertEquals(200, Day13.fewestTokens(List.of(clawMachine)));
    }

    @Test
    void part1_machine4() {
        var clawMachine = new Day13.ClawMachine(
                new Day13.ClawMachine.Button("A", x -> x * 69, y -> y * 23),
                new Day13.ClawMachine.Button("B", x -> x * 27, y -> y * 71),
                new Coordinate(18641, 10279)
        );
        assertEquals(0, Day13.fewestTokens(List.of(clawMachine)));
    }

    private List<Day13.ClawMachine> clawMachines() {
        var input = ResourceReader.stringLines("day13.txt");
        var clawMachines = new ArrayList<Day13.ClawMachine>();
        for (int i = 0; i < input.size(); i = i + 3) {
            var a = input.get(i);
            var b = input.get(i+1);
            var prize = input.get(i+2);

            var aSplit = a.split(": ");
            var aLabel = aSplit[0].substring(aSplit[0].length()-1);
            var aF = aSplit[1].split(", ");
            var aX = Integer.parseInt(aF[0].split("\\+")[1]);
            var aY = Integer.parseInt(aF[1].split("\\+")[1]);

            var bSplit = b.split(": ");
            var bLabel = bSplit[0].substring(bSplit[0].length()-1);
            var bF = bSplit[1].split(", ");
            var bX = Integer.parseInt(bF[0].split("\\+")[1]);
            var bY = Integer.parseInt(bF[1].split("\\+")[1]);

            var prizeSplit = prize.split(", ");
            var prizeLeftSplit = prizeSplit[0].split("=");
            var prizeRightSplit = prizeSplit[1].split("=");
            var prizeX = Integer.parseInt(prizeLeftSplit[1]);
            var prizeY = Integer.parseInt(prizeRightSplit[1]);

            var clawMachine = new Day13.ClawMachine(
                    new Day13.ClawMachine.Button(aLabel, x -> x * aX, y -> y * aY),
                    new Day13.ClawMachine.Button(bLabel, x -> x * bX, y -> y * bY),
                    new Coordinate(prizeX, prizeY)
            );
            clawMachines.add(clawMachine);

            i++;
        }
        return clawMachines;
    }
}