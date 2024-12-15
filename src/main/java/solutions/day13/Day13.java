package solutions.day13;

import common.Coordinate;

import java.util.List;
import java.util.function.Function;

final class Day13 {

    private static final int MAX_BUTTON_PRESS = 100;
    private static final int A_BUTTON_TOKENS = 3;
    private static final int B_BUTTON_TOKENS = 1;

    public static long fewestTokens(List<ClawMachine> clawMachines) {
        var result = 0;
        for (ClawMachine clawMachine : clawMachines) {
            for (int aPress = 0; aPress < MAX_BUTTON_PRESS; aPress++) {
                for (int bPress = 0; bPress < MAX_BUTTON_PRESS; bPress++) {
                    var aX = clawMachine.a().x().apply(aPress);
                    var bX = clawMachine.b().x().apply(bPress);
                    var abX = aX + bX;

                    var aY = clawMachine.a().y().apply(aPress);
                    var bY = clawMachine.b().y().apply(bPress);
                    var abY = aY + bY;

                    if (clawMachine.prize().equals(new Coordinate(abX, abY))) {
                        result += aPress * A_BUTTON_TOKENS + bPress * B_BUTTON_TOKENS;
                    }
                }
            }
        }
        return result;
    }

    record ClawMachine(Button a, Button b, Coordinate prize) {
        record Button(String label, Function<Integer, Integer> x, Function<Integer, Integer> y) {
        }
    }
}
