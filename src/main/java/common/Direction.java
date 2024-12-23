package common;

public enum Direction {
    LEFT, RIGHT, UP, DOWN, UNDEFINED;

    public Direction turnRight() {
        return switch (this) {
            case LEFT -> Direction.UP;
            case RIGHT -> Direction.DOWN;
            case UP -> Direction.RIGHT;
            case DOWN -> Direction.LEFT;
            case UNDEFINED -> throw new IllegalStateException("Direction is Undefined");
        };
    }
}
