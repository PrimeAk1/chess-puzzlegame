package chessgame.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static chessgame.model.ChessGameState.TwoPhaseMove;
import static org.junit.jupiter.api.Assertions.*;


class ChessGameStateTest {

    ChessGameState state1 = new ChessGameState(new Position(5, 1), new Position(5, 2), 0); // initial state
    ChessGameState state2 = new ChessGameState(new Position(0, 0), new Position(1, 2), 0); // king is attacked by knight
    ChessGameState state3 = new ChessGameState(new Position(1, 1), new Position(2, 0), 0); // knight is attacked by king
    ChessGameState state4 = new ChessGameState(new Position(7, 6), new Position(5, 2), 0); // king at goal
    ChessGameState state5 = new ChessGameState(new Position(5, 1), new Position(7, 6), 0); // knight at goal
    ChessGameState state6 = new ChessGameState(new Position(2, 3), new Position(5, 4), 0); // no piece is attacked

    @Test
    void constructor() {
        var kingPosition = new Position(5, 1);
        var knightPosition = new Position(5, 2);
        ChessGameState state = new ChessGameState(kingPosition, knightPosition, 0);
        assertEquals(kingPosition, state.getKingPosition());
        assertEquals(knightPosition, state.getKnightPosition());
        assertEquals(0, state.getMoveCounter());
    }

    @Test
    void isSolved() {
        assertFalse(state1.isSolved());
        assertFalse(state2.isSolved());
        assertFalse(state3.isSolved());
        assertTrue(state4.isSolved());
        assertTrue(state5.isSolved());
        assertFalse(state6.isSolved());
    }

    @Test
    void isLegalToMoveFrom() {
        assertFalse(state1.isLegalToMoveFrom(state1.getKingPosition()));
        assertTrue(state1.isLegalToMoveFrom(state1.getKnightPosition()));

        assertTrue(state2.isLegalToMoveFrom(state2.getKingPosition()));
        assertFalse(state2.isLegalToMoveFrom(state2.getKnightPosition()));

        assertFalse(state3.isLegalToMoveFrom(state3.getKingPosition()));
        assertTrue(state3.isLegalToMoveFrom(state3.getKnightPosition()));

        assertFalse(state4.isLegalToMoveFrom(state4.getKingPosition()));
        assertFalse(state4.isLegalToMoveFrom(state4.getKnightPosition()));

        assertFalse(state5.isLegalToMoveFrom(state5.getKingPosition()));
        assertFalse(state5.isLegalToMoveFrom(state5.getKnightPosition()));

        assertFalse(state6.isLegalToMoveFrom(state6.getKingPosition()));
        assertFalse(state6.isLegalToMoveFrom(state6.getKnightPosition()));
    }

    @Test
    void isLegalMove() {
        TwoPhaseMove<Position> kingMove = new TwoPhaseMove<>(state1.getKingPosition(), new Position(6, 1));
        TwoPhaseMove<Position> illegalKingMove = new TwoPhaseMove<>(state1.getKingPosition(), new Position(7, 1));
        TwoPhaseMove<Position> knightMove = new TwoPhaseMove<>(state1.getKnightPosition(), new Position(7, 3));
        TwoPhaseMove<Position> illegalKnightMove = new TwoPhaseMove<>(state1.getKnightPosition(), new Position(7, 4));

        assertTrue(state1.isLegalMove(kingMove));
        assertFalse(state1.isLegalMove(illegalKingMove));
        assertTrue(state1.isLegalMove(knightMove));
        assertFalse(state1.isLegalMove(illegalKnightMove));
    }

    @Test
    void makeMove() {
        ChessGameState state = new ChessGameState(new Position(5, 1), new Position(5, 2), 0);
        TwoPhaseMove<Position> move = new TwoPhaseMove<>(state.getKingPosition(), new Position(6, 1));
        state.makeMove(move);

        assertEquals(new Position(6, 1), state.getKingPosition());
        assertEquals(1, state.getMoveCounter());
    }

    @Test
    void getLegalMoves() {
        Set<TwoPhaseMove<Position>> legalMovesState2 = state2.getLegalMoves();
        Set<TwoPhaseMove<Position>> expectedKingLegalMoves = new HashSet<>();

        Position kingPosition = state2.getKingPosition();

        for (KingDirection direction : KingDirection.values()) {
            Position newPos2 = kingPosition.move(direction);
            if (legalMovesState2.contains(new TwoPhaseMove<>(kingPosition, newPos2))) {
                expectedKingLegalMoves.add(new TwoPhaseMove<>(kingPosition, newPos2));
            }
        }
        assertEquals(expectedKingLegalMoves, legalMovesState2);

        // Knight is attacked
        Set<TwoPhaseMove<Position>> legalMovesState3 = state3.getLegalMoves();
        Set<TwoPhaseMove<Position>> expectedKnightLegalMoves = new HashSet<>();


        Position knightPosition = state3.getKnightPosition();

        for (KnightDirection direction : KnightDirection.values()) {
            Position newPos3 = knightPosition.move(direction);
            if (legalMovesState3.contains(new TwoPhaseMove<>(knightPosition, newPos3))) {
                expectedKnightLegalMoves.add(new TwoPhaseMove<>(knightPosition, newPos3));
            }
        }

        assertEquals(expectedKnightLegalMoves, legalMovesState3);
    }

    @Test
    void testClone() {
        var clone = state1.clone();
        assertEquals(state1, clone);
        assertNotSame(state1, clone);
    }

    @Test
    void testEquals() {
        assertTrue(state1.equals(state1));
        assertFalse(state1.equals(null));
        assertFalse(state1.equals("Hello, World!"));

        var clone = state1.clone();
        assertTrue(state1.equals(clone));

        clone.makeMove(new TwoPhaseMove<>(state1.getKingPosition(), new Position(6, 1)));
        assertFalse(state1.equals(clone));
    }

    @Test
    void testHashCode() {
        assertEquals(state1.hashCode(), state1.clone().hashCode());
    }

    @Test
    void testToString() {
        assertEquals("King: (5,1), Knight: (5,2)", state1.toString());
        assertEquals("King: (0,0), Knight: (1,2)", state2.toString());
        assertEquals("King: (1,1), Knight: (2,0)", state3.toString());
        assertEquals("King: (7,6), Knight: (5,2)", state4.toString());
        assertEquals("King: (5,1), Knight: (7,6)", state5.toString());
        assertEquals("King: (2,3), Knight: (5,4)", state6.toString());
    }
}