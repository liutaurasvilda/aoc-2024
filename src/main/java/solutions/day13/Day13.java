package solutions.day13;

import common.Coordinate;

import java.util.List;
import java.util.function.Function;

final class Day13 {

    private static final int MAX_BUTTON_PRESS = 100;
    private static final int A_BUTTON_TOKENS = 3;
    private static final int B_BUTTON_TOKENS = 1;

    public static long fewestTokens(List<ClawMachine> clawMachines) {
        // TODO
        return 0;
    }

    record ClawMachine(Button a, Button b, Coordinate prize) {
        record Button(String label, Function<Integer, Integer> x, Function<Integer, Integer> y) {
        }
    }
}
