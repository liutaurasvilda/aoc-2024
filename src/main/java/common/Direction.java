package common;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, UNDEFINED;

    public Direction turnClockwise() {
        return switch (this) {
            case UP -> Direction.RIGHT;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
            case RIGHT -> Direction.DOWN;
            default -> throw new IllegalStateException();
        };
    }

    public Direction turnAntiClockwise() {
        return switch (this) {
            case UP -> Direction.LEFT;
            case DOWN -> Direction.RIGHT;
            case LEFT -> Direction.DOWN;
            case RIGHT -> Direction.UP;
            default -> throw new IllegalStateException();
        };
    }
}
