package common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Location {

    private final int rowIndex;
    private final int columnIndex;

    private final int maxRowIndex;
    private final int maxColumnIndex;

    private Location(int rowIndex, int columnIndex, int maxRowIndex, int maxColumnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.maxRowIndex = maxRowIndex;
        this.maxColumnIndex = maxColumnIndex;
    }

    public static Location of(int rowIndex, int columnIndex, int maxRowIndex, int maxColumnIndex) {
        return new Location(rowIndex, columnIndex, maxRowIndex, maxColumnIndex);
    }

    public List<Location> neighborhood() {
        return Arrays.stream(Direction.values())
                .map(direction -> direction.neighborOf(this))
                .filter(e -> e.rowIndex >= 0 && e.rowIndex <= maxRowIndex)
                .filter(e -> e.columnIndex >= 0 && e.columnIndex <= e.maxColumnIndex)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return rowIndex == location.rowIndex &&
                columnIndex == location.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }

    private enum Direction {
        LEFT(0, -1), RIGHT(0, +1), TOP(-1, 0), BOTTOM(+1, 0);

        private final int rowOffset;
        private final int columnOffset;

        Direction(int rowOffset, int columnOffset) {
            this.rowOffset = rowOffset;
            this.columnOffset = columnOffset;
        }

        private Location neighborOf(Location location) {
            return Location.of(location.rowIndex + rowOffset,
                    location.columnIndex + columnOffset, location.maxRowIndex, location.maxColumnIndex);
        }
    }
}