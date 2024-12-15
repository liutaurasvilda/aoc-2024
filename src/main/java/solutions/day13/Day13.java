package solutions.day13;

import common.Coordinate;

import java.util.List;
import java.util.function.Function;

final class Day13 {

    private static final int MAX_BUTTON_PRESS = 100;
    private static final int A_BUTTON_TOKENS = 3;
    private static final int B_BUTTON_TOKENS = 1;

    public static long fewestTokens(List<ClawMachine> clawMachines) {
        var result = 0L;
        for (ClawMachine clawMachine : clawMachines) {
            for (long aPress = 0; aPress < MAX_BUTTON_PRESS; aPress++) {
                for (long bPress = 0; bPress < MAX_BUTTON_PRESS; bPress++) {
                    if (clawMachine.won(aPress, bPress)) {
                        result += aPress * A_BUTTON_TOKENS + bPress * B_BUTTON_TOKENS;
                    }
                }
            }
        }
        return result;
    }

    record ClawMachine(Button a, Button b, Coordinate prize) {

        boolean won(long aPress, long bPress) {
            var aX = a().x().apply(aPress);
            var bX = b().x().apply(bPress);
            var abX = aX + bX;

            var aY = a().y().apply(aPress);
            var bY = b().y().apply(bPress);
            var abY = aY + bY;

            return prize().equals(new Coordinate(abX, abY));
        }

        record Button(String label, Function<Long, Long> x, Function<Long, Long> y) {
        }
    }
}
