package solutions.day02;

import java.util.ArrayList;
import java.util.List;

final class Day02 {

    public static long safeReports(Reports reports) {
        return reports.levels().stream().filter(Day02::safe).count();
    }

    static long safeReportsWithSingleBadLevelTolerance(Reports reports) {
        return reports.levels().stream().filter(Day02::safeWithinLimits).count();
    }

    private static boolean safe(List<Integer> levels) {
        return safeLevels(levels) || safeLevels(levels.reversed());
    }

    private static boolean safeWithinLimits(List<Integer> levels) {
        for (int i = 0; i < levels.size(); i++) {
            var trimmedLevels = new ArrayList<>(levels);
            trimmedLevels.remove(i);
            if (safe(trimmedLevels)) {
                return true;
            }
        }
        return false;
    }

    private static boolean safeLevels(List<Integer> levels) {
        var gaps = new ArrayList<Integer>();
        for (int i = 0; i < levels.size() - 1; i++) {
            gaps.add(levels.get(i) - levels.get(i + 1));
        }
        return gaps.stream().allMatch(e -> e < 0 && Math.abs(e) >= 1 && Math.abs(e) <= 3);
    }

    record Reports(List<List<Integer>> levels) {
    }
}
