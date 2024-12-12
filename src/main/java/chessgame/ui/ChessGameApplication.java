package chessgame.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;
import java.io.IOException;

public class ChessGameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Logger.info("Starting application");
        Parent root = FXMLLoader.load(getClass().getResource("/chessgameui.fxml"));
        stage.setTitle("Chess Game");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}