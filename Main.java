import javafx.application.Application;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Label whose color we will change
        Label text = new Label("Show Colors");
        text.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Four sliders: red, green, blue (0–255), and opacity (0.0–1.0)
        Slider redSlider   = createSlider(0, 255, 0);
        Slider greenSlider = createSlider(0, 255, 0);
        Slider blueSlider  = createSlider(0, 255, 0);
        Slider opSlider    = createSlider(0, 1,   1);
        opSlider.setBlockIncrement(0.05);

        // Bind the text fill to an ObjectBinding<Color> that watches all four sliders
        ObjectBinding<Color> colorBinding = Bindings.createObjectBinding(() ->
            Color.rgb(
                (int) redSlider.getValue(),
                (int) greenSlider.getValue(),
                (int) blueSlider.getValue(),
                opSlider.getValue()
            ),
            redSlider.valueProperty(),
            greenSlider.valueProperty(),
            blueSlider.valueProperty(),
            opSlider.valueProperty()
        );
        text.textFillProperty().bind(colorBinding);

        // Layout: GridPane with labels and sliders
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(8);
        pane.setPadding(new Insets(12));
        pane.setAlignment(Pos.CENTER);

        pane.add(text,    0, 0, 2, 1);
        pane.add(new Label("Red:"),      0, 1);
        pane.add(redSlider,               1, 1);
        pane.add(new Label("Green:"),    0, 2);
        pane.add(greenSlider,             1, 2);
        pane.add(new Label("Blue:"),     0, 3);
        pane.add(blueSlider,              1, 3);
        pane.add(new Label("Opacity:"),  0, 4);
        pane.add(opSlider,                1, 4);

        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setTitle("Exercise 16.17: Use ScrollBars or Sliders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
