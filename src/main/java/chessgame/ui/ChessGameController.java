package chessgame.ui;

import chessgame.model.ChessGameState;
import chessgame.model.Position;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.tinylog.Logger;
import puzzle.TwoPhaseMoveState;

import java.util.Objects;
import java.util.Set;

public class ChessGameController {

    @FXML
    private GridPane chessBoard;
    private final Pane[][] chessFields = new Pane[8][8];
    private ChessGameState chessGameState;
    private Position KingPosition;
    private Position KnightPosition;
    private Position selectedPos;
    @FXML
    private TextField usernameTextField;
    private String playerName;
    @FXML
    private Label messageLabel;
    @FXML
    private Label movesMadeLabel;
    private boolean isGameStarted = false;
    Image kingPNG = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/king.png")));
    Image knightPNG = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/knight.png")));
    Image goalPNG = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/goal.png")));

    @FXML
    public void startButtonClick() {
        Logger.debug("Start Game is pressed");
        String userInput = usernameTextField.getText().trim();

        if (!userInput.isEmpty() && !isGameStarted) {
            Position initialKingPosition = new Position(5, 1);
            Position initialKnightPosition = new Position(5, 2);
            chessGameState = new ChessGameState(initialKingPosition, initialKnightPosition, 0);

            playerName = usernameTextField.getText();

            isGameStarted = true;
            messageLabel.setText("Game Started");

            Logger.info("Starting game");

            currentState();
        }
    }


    private void currentState() {
        setupChessboard();

        ImageView KingView = new ImageView(kingPNG);
        ImageView KnightView = new ImageView(knightPNG);
        ImageView GoalView = new ImageView(goalPNG);

        KingPosition = chessGameState.getKingPosition();
        KnightPosition = chessGameState.getKnightPosition();

        chessFields[KingPosition.row()][KingPosition.col()].getChildren().add(KingView);
        chessFields[KnightPosition.row()][KnightPosition.col()].getChildren().add(KnightView);
        chessFields[7][6].getChildren().add(GoalView);

        KingView.setFitWidth(50);
        KingView.setFitHeight(50);

        KnightView.setFitWidth(50);
        KnightView.setFitHeight(50);

        GoalView.setFitWidth(50);
        GoalView.setFitHeight(50);
    }

    private void setupChessboard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Pane chessField = new Pane();
                chessFields[row][col] = chessField;
                chessBoard.add(chessField, col, row);
                chessboardColor(row, col);
                click(chessField, row, col);
            }
        }
        Logger.info("Set up Chessboard");
    }

    private void chessboardColor(int row, int col) {
        String black = "-fx-background-color: grey;";
        String white = "-fx-background-color: white;";

        if ((row + col) % 2 == 0) {
            chessFields[row][col].setStyle(white);
        } else {
            chessFields[row][col].setStyle(black);
        }
    }


    private void click(Pane chessField, int row, int col) {
        chessField.setOnMouseClicked(event -> {
            Position clickedPosition = new Position(row, col);

            if (selectedPos == null && chessGameState.isLegalToMoveFrom(clickedPosition) && !chessGameState.isSolved()) {
                highlightLegalMoves(clickedPosition);
                selectedPos = clickedPosition;
            }
            else if (selectedPos != null) {
                makeMove(selectedPos, clickedPosition);
                selectedPos = null;

                if(chessGameState.isSolved())
                {
                    messageLabel.setText("You Won!");
                    Logger.info("{} has solved the game in {} steps", playerName, chessGameState.getMoveCounter());
                }
            }
            Logger.debug("Click on square ({},{})", row, col);

        });
    }

    private void makeMove(Position from, Position to) {
        chessGameState.makeMove(new TwoPhaseMoveState.TwoPhaseMove<>(from, to));
        resetBoardColors();
        currentState();
        movesMadeLabel.setText((String.valueOf(chessGameState.getMoveCounter())));
        Logger.info("Move made to: {} ", to);
    }

    private void highlightLegalMoves(Position piece) {
        resetBoardColors();

        Set<TwoPhaseMoveState.TwoPhaseMove<Position>> legalMoves = chessGameState.getLegalMoves();

        for (TwoPhaseMoveState.TwoPhaseMove<Position> move : legalMoves) {
            Position to = move.to();
            chessFields[to.row()][to.col()].setStyle("-fx-background-color: green;");
        }

        Logger.info("Legal moves highlighted");
    }

    private void resetBoardColors() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                chessboardColor(row, col);
            }
        }
    }
    @FXML
    private void resetGame() {
        Logger.debug("Reset Game is pressed");
        if(isGameStarted) {
            Position initialKingPosition = new Position(5, 1);
            Position initialKnightPosition = new Position(5, 2);
            chessGameState = new ChessGameState(initialKingPosition, initialKnightPosition, 0);

            movesMadeLabel.setText((String.valueOf(chessGameState.getMoveCounter())));
            messageLabel.setText("Game Reseted");

            Logger.info("Resetting game");

            currentState();
        }
    }
}
