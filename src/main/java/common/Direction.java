package common;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, UNDEFINED;

    public Direction turnRight() {
        return switch (this) {
            case UP -> Direction.RIGHT;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
            case RIGHT -> Direction.DOWN;
            default -> throw new IllegalStateException();
        };
    }
}
