package TicTacToe;

import Main.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Field extends StackPane {
    private int state = 0;

    public Field(int number, Controller controller) {
        super();
        this.setPrefSize(100, 100);
        this.setStyle("-fx-border-color: " +
                (number != 1 && number != 4 && number != 7 ? "orange" : "#303230") + " " +
                (number < 7 ? "orange" : "#303230") + " " +
                (number % 3 != 0 ? "orange" : "#303230") + " " +
                (number > 3 ? "orange" : "#303230") +
                "; -fx-background-color: #303230");
        this.setBackground(new Background(new BackgroundFill(Color.rgb(48, 50, 48), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setOnMouseEntered(e -> this.setBackground(new Background(new BackgroundFill(Color.rgb(82, 85, 82), CornerRadii.EMPTY, Insets.EMPTY))));
        this.setOnMouseExited(e -> this.setBackground(new Background(new BackgroundFill(Color.rgb(48, 50, 48), CornerRadii.EMPTY, Insets.EMPTY))));
        this.setOnMouseClicked(e -> controller.fieldClicked(number));
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
        this.update();
    }

    private void update() {
        Label l = new Label(this.state == 1 ? "X" : "O");
        l.setTextFill(Color.ORANGE);
        l.setFont(Font.font("Comic Sans", 45));
        this.getChildren().add(l);
    }

}
