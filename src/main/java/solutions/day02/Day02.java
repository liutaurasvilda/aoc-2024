package solutions.day02;

import java.util.ArrayList;
import java.util.List;

public class Day02 {

    public static long safeReports(List<List<Integer>> levelReports) {
        return levelReports.stream()
                .filter(e -> (inOrder(e) || inOrder(e.reversed())) && safeGaps(e))
                .count();
    }

    public static long safeReportsWithSingleBadLevelTolerance(List<List<Integer>> levelReports) {
        return levelReports.stream()
                .filter(e -> withAtMostSingleBadLevel(e))
                .count();
    }

    private static boolean withAtMostSingleBadLevel(List<Integer> levels) {
        for (int i = 0; i < levels.size(); i++) {
            var trimmedList = new ArrayList<>(levels);
            trimmedList.remove(i);
            if ((inOrder(trimmedList) || inOrder(trimmedList.reversed())) && safeGaps(trimmedList)) {
                return true;
            }
        }
        return false;
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
}
