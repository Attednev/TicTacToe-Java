package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void start(String[] args) {
        launch(args);
    }

}
