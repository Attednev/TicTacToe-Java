package TicTacToe;

import Main.Controller;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Field extends StackPane {

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
    }

}
