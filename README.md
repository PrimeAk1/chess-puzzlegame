# Chess Puzzle Game

## School Project

This project is a school assignment that implements a chess puzzle game. The task is to move either a knight or a king on a chessboard in such a way that one of the pieces, which is currently being attacked by the other piece, reaches the target square (marked as C). The game follows the rules of chess for movement, and the pieces can only move when they are in a position to be attacked by the other piece. The project follows the Model-View-Controller (MVC) design pattern, and it does not utilize JavaFX properties in the model.

## Implementation Details

### Key Components

The core components of the game logic are structured as follows:

- **KingDirection and KnightDirection (enum classes)**: These classes define the movement directions for the king and the knight.
- **Position (record class)**: This class is responsible for managing the position changes of the pieces.
- **ChessGameState**: This class handles the main logic of the game, including the movement and interaction between the pieces.
- **ChessGameController**: This class is responsible for managing the UI logic and user interactions.

## Features

- **Move based on attack**: The game enforces the rule that only the piece being attacked by the other can move.
- **Possible move highlights**: The game shows the possible moves for the currently moving piece, based on the rules of chess.
- **Chess-compliant movement**: The pieces move in accordance with the actual chess rules: the king moves one square in any direction, and the knight moves in an L-shape.
- **Step counter**: The game counts the number of steps taken to solve the puzzle and helps keep track of your progress towards the goal.
- **Reset functionality**: The game allows you to reset the puzzle and start over from the initial position.

## Best Solution

### Initial Setup
- **Start Position**: 
  - King: (5, 1)
  - Knight: (5, 2)
  - Goal: (7, 6)

### Moves Sequence

1. **Knight from (5, 2) to (6, 0)**  
   New positions: King: (5, 1), Knight: (6, 0)

2. **Knight from (6, 0) to (7, 2)**  
   New positions: King: (5, 1), Knight: (7, 2)

3. **King from (5, 1) to (6, 2)**  
   New positions: King: (6, 2), Knight: (7, 2)

4. **Knight from (7, 2) to (5, 1)**  
   New positions: King: (6, 2), Knight: (5, 1)

5. **Knight from (5, 1) to (4, 3)**  
   New positions: King: (6, 2), Knight: (4, 3)

6. **King from (6, 2) to (5, 3)**  
   New positions: King: (5, 3), Knight: (4, 3)

7. **Knight from (4, 3) to (6, 4)**  
   New positions: King: (5, 3), Knight: (6, 4)

8. **Knight from (6, 4) to (7, 6)**  
   New positions: King: (5, 3), Knight: (7, 6)  
   **Solution: SOLVED**

## Running the Project

To run the project, you need to execute the `.jar` file. Here are the steps:

1. **Download the JAR file**: You can download the `homework-template-project-1.0.jar` file from the repository.

2. **Ensure Java is Installed**: Make sure you have the Java Development Kit (JDK) installed.

3. **Run the JAR file**: Open a terminal or command prompt and navigate to the directory where the `homework-template-project-1.0.jar` file is located. Then, execute the following command:
java -jar homework-template-project-1.0.jar

This will launch the application and you will be able to interact with the chess puzzle game.


