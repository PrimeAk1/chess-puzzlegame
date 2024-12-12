package chessgame.model;

import puzzle.TwoPhaseMoveState;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * Represents the state of the chess puzzle game.
 * Implements the {@link puzzle.TwoPhaseMoveState} interface.
 */
public class ChessGameState implements TwoPhaseMoveState<Position> {
    /**
     * The position of the king.
     */
    private Position kingPosition;
    /**
     * The position of the knight.
     */
    private Position knightPosition;
    /**
     * Number of the moves you made
     */
    private int moveCounter = 0;
    /**
     * The position of the goal.
     */
    private static final Position GOAL_POSITION = new Position(7, 6);
    /**
     * The size of the board.
     */
    private static final int BOARD_SIZE = 8;
    /**
     * Initializes a {@code ChessGameState} with the specified positions for the king and knight
     * and the move count.
     *
     * @param kingPosition the position of the king on the chessboard
     * @param knightPosition the position of the knight on the chessboard
     * @param moveCounter the number of moves made in the game
     */
    public ChessGameState(Position kingPosition, Position knightPosition, int moveCounter) {
        this.kingPosition = kingPosition;
        this.knightPosition = knightPosition;
        this.moveCounter = moveCounter;
    }
    /**
     * @return The position of the king
     */
    public Position getKingPosition() {
        return kingPosition;
    }
    /**
     * @return The position of the knight
     */
    public Position getKnightPosition() {
        return knightPosition;
    }
    /**
     * @return The number of moves made in the game
     */
    public int getMoveCounter() {
        return moveCounter;
    }
    /**
     * {@return whether the puzzle is solved}
     */
    @Override
    public boolean isSolved() {
        return kingPosition.equals(GOAL_POSITION) || knightPosition.equals(GOAL_POSITION);
    }
    /**
     * Checks if it is legal to move a piece from the specified position.
     * The legality is based on whether the piece at the given position is currently under attack.
     *
     * @param piece the position of the piece to check
     * @return {@code true} if it is legal to move the piece from the specified position,
     *         {@code false} if it is not legal to move
     */
    @Override
    public boolean isLegalToMoveFrom(Position piece) {
        if (piece.equals(kingPosition)) {
            return isKingAttacked(kingPosition);
        }
        else if (piece.equals(knightPosition)) {
            return isKnightAttacked(knightPosition);
        }
        return false;
    }

    private boolean isKingAttacked(Position pos) {
        for (KnightDirection direction : KnightDirection.values()) {
            if (knightPosition.move(direction).equals(pos)) {
                return true;
            }
        }
        return false;
    }

    private boolean isKnightAttacked(Position pos) {
        for (KingDirection direction : KingDirection.values()) {
            if (kingPosition.move(direction).equals(pos)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if a specified move is legal in the current game state.
     * The legality of the move is based on the type of piece being moved.
     *
     * @param move the move to be checked
     * @return {@code true} if the move is legal,
     *         {@code false} if it is not legal
     */
    @Override
    public boolean isLegalMove(TwoPhaseMove<Position> move) {
        Position piece = move.from();
        Position newPos = move.to();

        if (piece.equals(kingPosition)) {
            return isLegalKingMove(newPos);
        }
        else if (piece.equals(knightPosition)) {
            return isLegalKnightMove(newPos);
        }
        return false;
    }

    private boolean isLegalKingMove(Position newPos) {
        for (KingDirection direction : KingDirection.values()) {
            if (kingPosition.move(direction).equals(newPos) && isOnBoard(newPos)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLegalKnightMove(Position newPos) {
        for (KnightDirection direction : KnightDirection.values()) {
            if (knightPosition.move(direction).equals(newPos) && isOnBoard(newPos)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Executes a specified move if it is legal and the game is not solved.
     * The method updates the position of the king or knight and increments the move counter.
     *
     * @param move the move to be made
     */
    @Override
    public void makeMove(TwoPhaseMove<Position> move) {
        if (isLegalMove(move) && !isSolved()) {
            Position piece = move.from();
            Position newPos = move.to();

            if (piece.equals(kingPosition)) {
                kingPosition = newPos;
            }
            else if (piece.equals(knightPosition)) {
                knightPosition = newPos;
            }

            moveCounter++;
        }
    }
    /**
     * Returns a set of all legal moves for the king or knight from their current positions.
     * The method checks each possible move direction for the king or knight and adds the legal moves to the set.
     *
     * @return a set of {@code TwoPhaseMove<Position>} all legal moves
     */
    @Override
    public Set<TwoPhaseMove<Position>> getLegalMoves() {

        Set<TwoPhaseMove<Position>> legalMoves = new HashSet<>();

        if (isLegalToMoveFrom(kingPosition)) {
            for (KingDirection direction : KingDirection.values()) {
                Position newPos = kingPosition.move(direction);
                TwoPhaseMove<Position> move = new TwoPhaseMove<>(kingPosition, newPos);
                if(isLegalMove(move)) {
                    legalMoves.add(move);
                }
            }
        }
        else if (isLegalToMoveFrom(knightPosition)) {
            for (KnightDirection direction : KnightDirection.values()) {
                Position newPos = knightPosition.move(direction);
                TwoPhaseMove<Position> move = new TwoPhaseMove<>(knightPosition, newPos);
                if(isLegalMove(move)) {
                    legalMoves.add(move);
                }
            }

        }
        return legalMoves;
    }

    private boolean isOnBoard(Position pos) {
        return pos.row() >= 0 && pos.row() < BOARD_SIZE && pos.col() >= 0 && pos.col() < BOARD_SIZE;
    }
    /**
     * @return A clone of the current state.
     */
    @Override
    public TwoPhaseMoveState<Position> clone() {
        return new ChessGameState(new Position(kingPosition.row(), kingPosition.col()), new Position(knightPosition.row(), knightPosition.col()), moveCounter);
    }
    /**
     *
     * Checks if ChessGameState objects have the same move counter, king position, and knight position.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is the same as the o argument;
     *         {@code false} if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGameState that = (ChessGameState) o;
        return moveCounter == that.moveCounter &&
                Objects.equals(kingPosition, that.kingPosition) &&
                Objects.equals(knightPosition, that.knightPosition);
    }
    /**
     * @return a hash code value for this ChessGameState object
     */
    @Override
    public int hashCode() {
        return Objects.hash(kingPosition, knightPosition, moveCounter);
    }
    /**
     * @return a string representation of this ChessGameState object
     */
    @Override
    public String toString() {
        return String.format("King: %s, Knight: %s", kingPosition, knightPosition);
    }
}