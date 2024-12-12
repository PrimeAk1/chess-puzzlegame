# Chess Puzzle Game

"Given a chessboard, we place a knight and a king on it as shown in Figure 1. The task is to move one of the pieces to the square marked C. You can only move the piece that is currently being attacked by the other piece, according to the rules of chess."

## Implementation details
The project follows the MVC pattern.
This implementation does not make use of JavaFX properties in the model.

These classes constitute the most essential components of the game logic:

- KingDirection and KnightDirection enum class are responsible for the movement directions.
- Position record class is responsible for the position changes.
- ChessGameState class is responsible for the main logic of the game.
- ChessGameController is responsible for the UI logic.

## Best solution
**START**: King(5, 1) Knight(5, 2) Goal(7, 6)

1. Knight from (5, 2) to (6, 0) | **New positions**: King: (5, 1), Knight: (6, 0)

2. Knight from (6, 0) to (7, 2) | **New positions**: King: (5, 1), Knight: (7, 2)

3. King from (5, 1) to (6, 2) | **New positions**: King: (6, 2), Knight: (7, 2)

4. Knight from (7, 2) to (5, 1) | **New positions**: King: (6, 2), Knight: (5, 1)

5. Knight from (5, 1) to (4, 3) | **New positions**: King: (6, 2), Knight: (4, 3)

6. King from (6, 2) to (5, 3) | **New positions**: King: (5, 3), Knight: (4, 3)

7. Knight from (4, 3) to (6, 4) | **New positions**: King: (5, 3), Knight: (6, 4)

8. Knight from (6, 4) to (7, 6) | **New positions**: King: (5, 3), Knight: (7, 6) | **SOLVED**


