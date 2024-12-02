package solutions.day02;

import java.util.ArrayList;
import java.util.List;

final class Day02 {

    public static long safeReports(Reports reports) {
        return reports.levels().stream().filter(Day02::safe).count();
    }

    static long safeReportsWithSingleBadLevelTolerance(Reports reports) {
        return reports.levels().stream().filter(Day02::safeWithAtMostSingleBadLevel).count();
    }

    private static boolean safeWithAtMostSingleBadLevel(List<Integer> levels) {
        for (int i = 0; i < levels.size(); i++) {
            var trimmedLevels = new ArrayList<>(levels);
            trimmedLevels.remove(i);
            if (safe(trimmedLevels)) {
                return true;
            }
        }
        return false;
    }

    private static boolean safe(List<Integer> levels) {
        return (inOrder(levels) || inOrder(levels.reversed())) && safeGaps(levels);
    }

    private static boolean inOrder(List<Integer> levels) {
        for (int i = 0; i < levels.size() - 1; i++) {
            if (levels.get(i) > levels.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean safeGaps(List<Integer> levels) {
        var gaps = new ArrayList<Integer>();
        for (int i = 0; i < levels.size()-1; i++) {
            gaps.add(Math.abs(levels.get(i) - levels.get(i+1)));
        }
        return gaps.stream().allMatch(e -> e >= 1 && e <= 3);
    }

    record Reports(List<List<Integer>> levels) {
    }
}
