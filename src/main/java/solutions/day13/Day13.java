package solutions.day13;

import common.Coordinate;

import java.util.List;
import java.util.function.Function;

final class Day13 {

    private static final int MAX_BUTTON_PRESS = 100;
    private static final int A_BUTTON_TOKENS = 3;
    private static final int B_BUTTON_TOKENS = 1;

    public static long fewestTokens(List<ClawMachine> clawMachines) {
        return clawMachines.stream().mapToLong(clawMachine -> {
            var result = 0L;
            for (long aPressed = 0; aPressed < MAX_BUTTON_PRESS; aPressed++) {
                for (long bPressed = 0; bPressed < MAX_BUTTON_PRESS; bPressed++) {
                    if (clawMachine.won(aPressed, bPressed)) {
                        result += aPressed * A_BUTTON_TOKENS + bPressed * B_BUTTON_TOKENS;
                    }
                }
            }
            return result;
        }).sum();
    }

    record ClawMachine(Button a, Button b, Coordinate prize) {

        boolean won(long aPressed, long bPressed) {
            var aX = a.x.apply(aPressed);
            var bX = b.x.apply(bPressed);
            var abX = aX + bX;

            var aY = a.y.apply(aPressed);
            var bY = b.y.apply(bPressed);
            var abY = aY + bY;

            return prize.equals(new Coordinate(abX, abY));
        }

        record Button(String label, Function<Long, Long> x, Function<Long, Long> y) {
        }
    }
}
