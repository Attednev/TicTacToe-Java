package Main;

import TicTacToe.Field;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Controller {
    @FXML private BorderPane root;
    @FXML private GridPane gameRoot;
    @FXML private VBox menuRoot;
    private boolean xTurn = true;

    @FXML private void initialize() {
        this.root.setStyle("-fx-background-color: rgb(48, 50, 48)");
        this.gameRoot.setVisible(false);
        this.gameRoot.setStyle("-fx-border-color: rgb(48, 50, 48)");
        for (int i = 0; i < 3; i++) this.gameRoot.addColumn(i, new Field(i * 3 + 1, this), new Field(i * 3 + 2, this), new Field(i * 3 + 3, this));
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

    public void fieldClicked(int number) {
        Field field = (Field)this.gameRoot.getChildren().get(number - 1);
        int state = field.getState();
        if (state == 0) {
            field.setState(this.xTurn ? 1 : -1);
            this.xTurn = !this.xTurn;
        }
    }

}
