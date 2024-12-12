package chessgame.solver;

import chessgame.model.ChessGameState;
import chessgame.model.Position;
import puzzle.solver.BreadthFirstSearch;

public class Main {
    public static void main(String[] args) {
        Position initialKingPosition = new Position(5, 1);
        Position initialKnightPosition = new Position(5, 2);
        ChessGameState initialState = new ChessGameState(initialKingPosition, initialKnightPosition, 0);

        BreadthFirstSearch solver = new BreadthFirstSearch<>();
        solver.solveAndPrintSolution(initialState);
    }
}