package common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public final class Location {

    private final int row;
    private final int column;

    private int maxRow;
    private int maxColumn;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Location withBoundaries(int maxRow, int maxColumn) {
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
        return this;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public Location left() {
        return Direction.LEFT.of(this);
    }

    public Location right() {
        return Direction.RIGHT.of(this);
    }

    public Location up() {
        return Direction.UP.of(this);
    }

    public Location down() {
        return Direction.DOWN.of(this);
    }

    public Location move(Direction direction) {
        return direction.of(this);
    }

    public List<Location> neighbourhood() {
        Predicate<Location> withinRowBoundaries = maxRow > 0
                ? e -> e.row >= 0 && e.row <= maxRow
                : e -> true;
        Predicate<Location> withinColumnBoundaries = maxColumn > 0
                ? e -> e.column >= 0 && e.column <= e.maxColumn
                : e -> true;

        return Arrays.stream(Direction.values())
                .map(e -> e.of(this))
                .filter(withinRowBoundaries)
                .filter(withinColumnBoundaries)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return row == location.row &&
                column == location.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public enum Direction {
        LEFT(0, -1), RIGHT(0, +1),
        UP(-1, 0), DOWN(+1, 0);

        private final int rowOffset;
        private final int columnOffset;

        Direction(int rowOffset, int columnOffset) {
            this.rowOffset = rowOffset;
            this.columnOffset = columnOffset;
        }

        private Location of(Location location) {
            return new Location(location.row + rowOffset, location.column + columnOffset)
                    .withBoundaries(location.maxRow, location.maxColumn);
        }
    }
}