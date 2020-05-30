package Main;

import TicTacToe.Field;
import javafx.fxml.FXML;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML private BorderPane root;
    @FXML private GridPane gameRoot;
    @FXML private VBox menuRoot;
    private boolean xTurn = true;

    @FXML private void initialize() {
        this.root.setStyle("-fx-background-color: rgb(48, 50, 48)");
        this.gameRoot.setVisible(false);
        this.gameRoot.setStyle("-fx-border-color: rgb(48, 50, 48)");
        for (int i = 0; i < 3; i++) this.gameRoot.addColumn(i, new Field(i * 3 + 1), new Field(i * 3 + 2), new Field(i * 3 + 3));
    }

    @FXML private void startSinglePlayer() {
        this.changeSceneToGame();
    }

    @FXML private void startMultiPlayer() {
        this.changeSceneToGame();
    }

    private void changeSceneToGame() {
        this.gameRoot.setVisible(true);
        this.menuRoot.setVisible(false);
    }

}
