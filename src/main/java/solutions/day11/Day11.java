package solutions.day11;

import java.util.ArrayList;
import java.util.List;

final class Day11 {

    public static long stonesCount(int blinks, List<String> stones) {
        var newStones = new ArrayList<>(stones);
        for (int i = 0; i < blinks; i++) {
            var tempStones = new ArrayList<String>();
            for (var stone : newStones) {
                if (stone.equals("0")) {
                    tempStones.add("1");
                } else if ((stone.length() & 1) == 0) {
                    var left = stone.substring(0, stone.length() / 2);
                    var right = stone.substring(stone.length() / 2);
                    while (right.startsWith("0") && !right.equals("0")) {
                        right = right.substring(1);
                    }
                    tempStones.add(left);
                    tempStones.add(right);
                } else {
                    tempStones.add(String.valueOf(Long.parseLong(stone) * 2024));
                }
            }
            newStones = tempStones;
        }
        return newStones.size();
    }
}
