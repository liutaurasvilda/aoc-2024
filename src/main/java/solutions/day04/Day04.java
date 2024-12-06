package solutions.day04;

import java.util.List;

final class Day04 {

    public static long xmas(List<List<String>> wordMap) {
        var result = 0;
        for (int i = 0; i < wordMap.size(); i++) {
            for (int j = 0; j < wordMap.get(i).size(); j++) {
                result += left(wordMap, i, j);
                result += right(wordMap, i, j);
                result += up(wordMap, i, j);
                result += down(wordMap, i, j);
                result += upLeft(wordMap, i, j);
                result += upRight(wordMap, i, j);
                result += downLeft(wordMap, i, j);
                result += downRight(wordMap, i, j);
            }
        }
        return result;
    }

    public static long masx(List<List<String>> wordMap) {
        var result = 0;
        for (int i = 0; i < wordMap.size(); i++) {
            for (int j = 0; j < wordMap.get(i).size(); j++) {
                result += x(wordMap, i, j);
            }
        }
        return result;
    }

    private static int x(List<List<String>> wordMap, int row, int column) {
        try {
            String m1 = wordMap.get(row - 1).get(column - 1);
            String a1 = wordMap.get(row).get(column);
            String s1 = wordMap.get(row + 1).get(column + 1);

            String m2 = wordMap.get(row - 1).get(column + 1);
            String a2 = wordMap.get(row).get(column);
            String s2 = wordMap.get(row + 1).get(column - 1);

            return ((m1 + a1 + s1).equals("MAS") || (s1 + a1 + m1).equals("MAS")) &&
                    ((m2 + a2 + s2).equals("MAS") || (s2 + a2 + m2).equals("MAS")) ? 1 : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static int left(List<List<String>> wordMap, int row, int column) {
        try {
            var x = wordMap.get(row).get(column);
            var m = wordMap.get(row).get(column - 1);
            var a = wordMap.get(row).get(column - 2);
            var s = wordMap.get(row).get(column - 3);
            return (x + m + a + s).equals("XMAS") ? 1 : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static int right(List<List<String>> wordMap, int row, int column) {
        try {
            var x = wordMap.get(row).get(column);
            var m = wordMap.get(row).get(column + 1);
            var a = wordMap.get(row).get(column + 2);
            var s = wordMap.get(row).get(column + 3);
            return (x + m + a + s).equals("XMAS") ? 1 : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static int up(List<List<String>> wordMap, int row, int column) {
        try {
            var x = wordMap.get(row).get(column);
            var m = wordMap.get(row - 1).get(column);
            var a = wordMap.get(row - 2).get(column);
            var s = wordMap.get(row - 3).get(column);
            return (x + m + a + s).equals("XMAS") ? 1 : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static int down(List<List<String>> wordMap, int row, int column) {
        try {
            var x = wordMap.get(row).get(column);
            var m = wordMap.get(row + 1).get(column);
            var a = wordMap.get(row + 2).get(column);
            var s = wordMap.get(row + 3).get(column);
            return (x + m + a + s).equals("XMAS") ? 1 : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static int upLeft(List<List<String>> wordMap, int row, int column) {
        try {
            var x = wordMap.get(row).get(column);
            var m = wordMap.get(row - 1).get(column - 1);
            var a = wordMap.get(row - 2).get(column - 2);
            var s = wordMap.get(row - 3).get(column - 3);
            return (x + m + a + s).equals("XMAS") ? 1 : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static int upRight(List<List<String>> wordMap, int row, int column) {
        try {
            var x = wordMap.get(row).get(column);
            var m = wordMap.get(row - 1).get(column + 1);
            var a = wordMap.get(row - 2).get(column + 2);
            var s = wordMap.get(row - 3).get(column + 3);
            return (x + m + a + s).equals("XMAS") ? 1 : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static int downLeft(List<List<String>> wordMap, int row, int column) {
        try {
            var x = wordMap.get(row).get(column);
            var m = wordMap.get(row + 1).get(column - 1);
            var a = wordMap.get(row + 2).get(column - 2);
            var s = wordMap.get(row + 3).get(column - 3);
            return (x + m + a + s).equals("XMAS") ? 1 : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static int downRight(List<List<String>> wordMap, int row, int column) {
        try {
            var x = wordMap.get(row).get(column);
            var m = wordMap.get(row + 1).get(column + 1);
            var a = wordMap.get(row + 2).get(column + 2);
            var s = wordMap.get(row + 3).get(column + 3);
            return (x + m + a + s).equals("XMAS") ? 1 : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}
