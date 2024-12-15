package solutions.day13;

import common.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    @Test
    void part1() {
        assertEquals(480, Day13.fewestTokens(clawMachines()));
    }

    @Test
    void part1_machine1() {
        var clawMachine = new Day13.ClawMachine(
                new Day13.ClawMachine.Button("A", x -> x + 94, y -> y + 34),
                new Day13.ClawMachine.Button("B", x -> x + 22, y -> y + 67),
                new Coordinate(8400, 5400)
        );
        assertEquals(280, Day13.fewestTokens(List.of(clawMachine)));
    }

    @Test
    void part1_machine2() {
        var clawMachine = new Day13.ClawMachine(
                new Day13.ClawMachine.Button("A", x -> x + 26, y -> y + 66),
                new Day13.ClawMachine.Button("B", x -> x + 67, y -> y + 21),
                new Coordinate(12748, 12176)
        );
        assertEquals(0, Day13.fewestTokens(List.of(clawMachine)));
    }

    @Test
    void part1_machine3() {
        var clawMachine = new Day13.ClawMachine(
                new Day13.ClawMachine.Button("A", x -> x + 17, y -> y + 86),
                new Day13.ClawMachine.Button("B", x -> x + 84, y -> y + 37),
                new Coordinate(7870, 6450)
        );
        assertEquals(200, Day13.fewestTokens(List.of(clawMachine)));
    }

    @Test
    void part1_machine4() {
        var clawMachine = new Day13.ClawMachine(
                new Day13.ClawMachine.Button("A", x -> x + 69, y -> y + 23),
                new Day13.ClawMachine.Button("B", x -> x + 27, y -> y + 71),
                new Coordinate(18641, 10279)
        );
        assertEquals(0, Day13.fewestTokens(List.of(clawMachine)));
    }

    private List<Day13.ClawMachine> clawMachines() {
        // TODO parse
        return List.of();
    }
}