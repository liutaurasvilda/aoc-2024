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
        return new Location(row, column - 1).withBoundaries(maxRow, maxColumn);
    }

    public Location right() {
        return new Location(row, column + 1).withBoundaries(maxRow, maxColumn);
    }

    public Location up() {
        return new Location(row - 1, column).withBoundaries(maxRow, maxColumn);
    }

    public Location down() {
        return new Location(row + 1, column).withBoundaries(maxRow, maxColumn);
    }

    public Location move(Direction direction) {
        return switch (direction) {
            case LEFT -> left();
            case RIGHT -> right();
            case UP -> up();
            case DOWN -> down();
        };
    }

    public List<Location> neighbourhood() {
        Predicate<Location> withinRowBoundaries = maxRow > 0
                ? e -> e.row >= 0 && e.row <= maxRow
                : e -> true;
        Predicate<Location> withinColumnBoundaries = maxColumn > 0
                ? e -> e.column >= 0 && e.column <= e.maxColumn
                : e -> true;

        return Arrays.stream(Direction.values())
                .map(this::move)
                .filter(withinRowBoundaries)
                .filter(withinColumnBoundaries)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return row == location.row && column == location.column;
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
}