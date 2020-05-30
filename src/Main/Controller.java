package Main;

import TicTacToe.Field;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class Controller {
    @FXML private VBox difficultyRoot;
    @FXML private VBox endScreenRoot;
    @FXML private Label winLabel;
    @FXML private BorderPane root;
    @FXML private GridPane gameRoot;
    @FXML private VBox menuRoot;
    private boolean xTurn = true;
    private boolean singlePlayer = true;
    private int maxDepth = 0;

    @FXML private void initialize() {
        this.root.setStyle("-fx-background-color: rgb(48, 50, 48)");
        this.gameRoot.setVisible(false);
        this.endScreenRoot.setVisible(false);
        this.difficultyRoot.setVisible(false);
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
        if (!this.singlePlayer) this.xTurn = !this.xTurn;
        this.gameRoot.setVisible(true);
        this.endScreenRoot.setVisible(false);
    }

    @FXML private void backToMenu() {
        this.menuRoot.setVisible(true);
        this.endScreenRoot.setVisible(false);
    }

    @FXML private void difficultyEasy() {
        this.maxDepth = 2;
        this.difficultyRoot.setVisible(false);
        this.gameRoot.setVisible(true);
    }

    @FXML private void difficultyMedium() {
        this.maxDepth = 4;
        this.difficultyRoot.setVisible(false);
        this.gameRoot.setVisible(true);
    }

    @FXML private void difficultyHard() {
        this.maxDepth = 7;
        this.difficultyRoot.setVisible(false);
        this.gameRoot.setVisible(true);
    }

    @FXML private void difficultyImpossible() {
        this.maxDepth = 9;
        this.difficultyRoot.setVisible(false);
        this.gameRoot.setVisible(true);
    }

    private void changeSceneToGame() {
        if (this.singlePlayer) {
            this.difficultyRoot.setVisible(true);
        } else {
            this.gameRoot.setVisible(true);
        }
        this.menuRoot.setVisible(false);
    }

    public void fieldClicked(int number) {
        Field field = (Field)this.gameRoot.getChildren().get(number - 1);
        int state = field.getState();
        if (state == 0) {
            field.setState(this.xTurn ? 1 : -1);
            if (this.checkEndGame(this.gameRoot)) {
                this.endGame(this.xTurn ? 1 : -1);
            } else {
                boolean draw = true;
                for (int i = 0; i < 9; i++) {
                    if (((Field)this.gameRoot.getChildren().get(i)).getState() == 0) {
                        draw = false;
                        break;
                    }
                }
                if (draw) {
                    this.endGame(0);
                    return;
                }
                if (this.singlePlayer) {
                    this.aiMove();
                    if (this.checkEndGame(this.gameRoot)) {
                        this.endGame(-1);
                    }
                } else {
                    this.xTurn = !this.xTurn;
                }
            }
        }
    }

    private void aiMove() {
        GridPane boardCopy = new GridPane();
        for (int i = 0; i < 3; i++) boardCopy.addColumn(i, new Field(i * 3 + 1, this), new Field(i * 3 + 2, this), new Field(i * 3 + 3, this));
        for (int i = 0; i < 9; i++) ((Field)boardCopy.getChildren().get(i)).setState(((Field)this.gameRoot.getChildren().get(i)).getState());
        int pos = miniMax(boardCopy, false, -10, 10, true, 0, 0);
        if (pos != -1 ) ((Field)this.gameRoot.getChildren().get(pos)).setState(-1);
    }

    private int miniMax(GridPane boardCopy, boolean isMin, int alpha, int beta, boolean first, int depth, int backupScore) {
        if (this.checkEndGame(boardCopy)) return isMin ? 1 : -1;
        if (depth == this.maxDepth) {
            return backupScore;
        }
        int maxEval = isMin ? 10 : -10;
        int pos = -1;
        for (Node node : boardCopy.getChildren()) {
            if (((Field) node).getState() == 0) {
                ((Field) node).setState(isMin ? 1 : -1);
                int score = miniMax(boardCopy, !isMin, alpha, beta, false, depth + 1, maxEval);
                ((Field) node).setState(0);
                if (isMin && score < maxEval || !isMin && score > maxEval) {
                    maxEval = score;
                    pos = boardCopy.getChildren().indexOf(node);
                }
                alpha = isMin ? alpha : Math.max(alpha, score);
                beta = isMin ? Math.max(beta, score) : beta;
                if (beta <= alpha) break;
            }
        }
        return pos == -1 ? 0 : (first ? pos : maxEval);
    }

    private boolean checkEndGame(GridPane board) {
        ObservableList<Node> nodes = board.getChildren();
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

    private void endGame(int turn) {
        for (int i = 0; i < 9; i++) ((Field)this.gameRoot.getChildren().get(i)).setState(0);
        this.endScreenRoot.setVisible(true);
        this.gameRoot.setVisible(false);
        this.winLabel.setText(turn == 0 ? "Draw!" : ((turn == 1? "X" : "O" ) + " won!"));
    }

}
