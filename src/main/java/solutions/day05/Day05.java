package solutions.day05;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

final class Day05 {

    public static long correctlyOrderedUpdates(PrintManual printManual) {
        return orderedUpdates(printManual).stream()
                .map(e -> Long.parseLong(e.get(e.size() / 2)))
                .mapToLong(e -> e).sum();
    }

    public static long incorrectlyOrderedUpdates(PrintManual printManual) {
        var orderedUpdates = orderedUpdates(printManual);
        return printManual.updates().stream()
                .filter(e -> !orderedUpdates.contains(e))
                .peek(e -> e.sort(new PagesComparator(printManual.pageOrdering())))
                .map(e -> Long.parseLong(e.get(e.size() / 2)))
                .mapToLong(e -> e).sum();
    }

    private static Set<List<String>> orderedUpdates(PrintManual printManual) {
        var comparator = new PagesComparator(printManual.pageOrdering());
        return printManual.updates().stream()
                .filter(e -> e.equals(e.stream().sorted(comparator).toList()))
                .collect(Collectors.toSet());
    }

    private record PagesComparator(Set<String> pageOrdering) implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return pageOrdering.contains(o1 + "|" + o2) ? -1 : 1;
        }
    }

    record PrintManual(Set<String> pageOrdering, List<List<String>> updates) {
    }
}
