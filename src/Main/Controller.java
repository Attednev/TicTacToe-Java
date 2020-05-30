package Main;

import TicTacToe.Field;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Controller {
    @FXML private VBox endScreenRoot;
    @FXML private Label winLabel;
    @FXML private BorderPane root;
    @FXML private GridPane gameRoot;
    @FXML private VBox menuRoot;
    private boolean xTurn = true;
    private boolean singlePlayer = true;

    @FXML private void initialize() {
        this.root.setStyle("-fx-background-color: rgb(48, 50, 48)");
        this.gameRoot.setVisible(false);
        this.endScreenRoot.setVisible(false);
        this.gameRoot.setStyle("-fx-border-color: rgb(48, 50, 48)");
        for (int i = 0; i < 3; i++) this.gameRoot.addColumn(i, new Field(i * 3 + 1, this), new Field(i * 3 + 2, this), new Field(i * 3 + 3, this));
    }

    @FXML private void startSinglePlayer() {
        this.singlePlayer = true;
        this.changeSceneToGame();
    }

    @FXML private void startMultiPlayer() {
        this.singlePlayer = false;
        this.changeSceneToGame();
    }

    @FXML private void rematch() {
        this.xTurn = !this.xTurn;
        this.gameRoot.setVisible(true);
        this.endScreenRoot.setVisible(false);
    }

    @FXML private void backToMenu() {
        this.menuRoot.setVisible(true);
        this.endScreenRoot.setVisible(false);
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
            if (this.checkEndGame()) {
                this.endGame();
            } else if (this.singlePlayer) {
                this.aiMove();
            } else {
                this.xTurn = !this.xTurn;
            }
        }
    }

    private void aiMove() {
        // Start MinMax
    }

    private boolean checkEndGame() {
        ObservableList<Node> nodes = this.gameRoot.getChildren();
        return (((Field)nodes.get(0)).getState() == ((Field)nodes.get(1)).getState() && ((Field)nodes.get(0)).getState() == ((Field)nodes.get(2)).getState() && ((Field)nodes.get(0)).getState() != 0 ||
                ((Field)nodes.get(3)).getState() == ((Field)nodes.get(4)).getState() && ((Field)nodes.get(3)).getState() == ((Field)nodes.get(5)).getState() && ((Field)nodes.get(3)).getState() != 0 ||
                ((Field)nodes.get(6)).getState() == ((Field)nodes.get(7)).getState() && ((Field)nodes.get(6)).getState() == ((Field)nodes.get(8)).getState() && ((Field)nodes.get(6)).getState() != 0 ||
                ((Field)nodes.get(6)).getState() == ((Field)nodes.get(7)).getState() && ((Field)nodes.get(6)).getState() == ((Field)nodes.get(8)).getState() && ((Field)nodes.get(6)).getState() != 0 ||
                ((Field)nodes.get(0)).getState() == ((Field)nodes.get(3)).getState() && ((Field)nodes.get(0)).getState() == ((Field)nodes.get(6)).getState() && ((Field)nodes.get(0)).getState() != 0 ||
                ((Field)nodes.get(1)).getState() == ((Field)nodes.get(4)).getState() && ((Field)nodes.get(1)).getState() == ((Field)nodes.get(7)).getState() && ((Field)nodes.get(1)).getState() != 0 ||
                ((Field)nodes.get(2)).getState() == ((Field)nodes.get(5)).getState() && ((Field)nodes.get(2)).getState() == ((Field)nodes.get(8)).getState() && ((Field)nodes.get(2)).getState() != 0 ||
                ((Field)nodes.get(0)).getState() == ((Field)nodes.get(4)).getState() && ((Field)nodes.get(0)).getState() == ((Field)nodes.get(8)).getState() && ((Field)nodes.get(0)).getState() != 0 ||
                ((Field)nodes.get(2)).getState() == ((Field)nodes.get(4)).getState() && ((Field)nodes.get(2)).getState() == ((Field)nodes.get(6)).getState() && ((Field)nodes.get(2)).getState() != 0);
    }

    private void endGame() {
        for (int i = 0; i < 9; i++) ((Field)this.gameRoot.getChildren().get(i)).setState(0);
        this.endScreenRoot.setVisible(true);
        this.gameRoot.setVisible(false);
        this.winLabel.setText((this.xTurn ? "X" : "O" ) + " won!");
    }

}
