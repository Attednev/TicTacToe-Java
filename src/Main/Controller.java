package Main;

import javafx.fxml.FXML;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML private GridPane gameRoot;
    @FXML private VBox menuRoot;
    private boolean xTurn = true;

    @FXML private void initialize() {
        this.gameRoot.setVisible(false);
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
