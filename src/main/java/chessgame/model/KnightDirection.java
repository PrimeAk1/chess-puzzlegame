package chessgame.model;
/**
 * Represents the main directions of the Knight.
 */
public enum KnightDirection {
    UP_LEFT(-2, -1),
    UP_RIGHT(-2, 1),
    DOWN_LEFT(2, -1),
    DOWN_RIGHT(2, 1),
    LEFT_UP(-1, -2),
    LEFT_DOWN(1, -2),
    RIGHT_UP(-1, 2),
    RIGHT_DOWN(1, 2);

    private final int rowChange;
    private final int colChange;

    KnightDirection(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }
    /**
     * {@return the change in the row coordinate when moving to the direction}
     */
    public int getRowChange() {
        return rowChange;
    }
    /**
     * {@return the change in the column coordinate when moving to the
     * direction}
     */
    public int getColChange() {
        return colChange;
    }
    /**
     * {@return the direction that corresponds to the coordinate changes
     * specified}
     *
     * @param rowChange the change in the row coordinate
     * @param colChange the change in the column coordinate
     */
    public static KnightDirection of(int rowChange, int colChange) {
        for (var direction : values()) {
            if (direction.rowChange == rowChange && direction.colChange == colChange) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }
}
