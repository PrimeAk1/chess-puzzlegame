package chessgame.model;

/**
 * Represents a 2D position.
 */
public record Position(int row, int col) {
    /**
     * Moves this position according to the coordinate changes specified by the given king direction.
     *
     * {@return the position whose vertical and horizontal distances from this
     * position are equal to the coordinate changes of the king direction given}
     *
     * @param direction a king direction that specifies a change in the coordinates
     */
    public Position move(KingDirection direction) {
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }
    /**
     * Moves this position according to the coordinate changes specified by the given knight direction.
     *
     * {@return the position whose vertical and horizontal distances from this
     * position are equal to the coordinate changes of the knight direction given}
     *
     * @param direction a knight direction that specifies a change in the coordinates
     */
    public Position move(KnightDirection direction) {
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }
    /**
     * Returns a string representation of this position in the format (row,col).
     *
     * {@return a string representation of the position}
     */
    @Override
    public String toString() {
        return String.format("(%d,%d)", row, col);
    }
}
