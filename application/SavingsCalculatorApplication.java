package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane; // Foundation piece
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SavingsCalculatorApplication extends Application {

    public static void main(String[] args) {
        launch(SavingsCalculatorApplication.class, args);
        System.out.println("Hello world!");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // Create BorderPane, the foundation layout
        BorderPane layout = new BorderPane();

        // Create Sliders
        SubviewSlider savings = new SubviewSlider("Monthly savings", 25, 250, 25);
        savings.adjustMajorTickUnit((double) 25f);
        savings.adjustBlockIncrement((double) 5f);

        SubviewSlider interest = new SubviewSlider("Yearly interest rate", 0, 10, 0);
        interest.adjustBlockIncrement((double) .1f);
        
        SavingsGraph lineChart = new SavingsGraph("Savings calculator", savings, interest);

        // Add sliders to VBox
        VBox sliderLayout = new VBox();
        sliderLayout.getChildren().addAll(savings.getBorderPane(), interest.getBorderPane());


        // Add sub layouts
        layout.setCenter(lineChart.getLineChart());
        layout.setTop(sliderLayout);
        layout.setPadding(new Insets(20, 20, 20, 20));
        
        // events
        savings.getSlider().valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (!savings.getSlider().isValueChanging()) {
                    layout.setCenter(lineChart.getLineChart());
                }
            }
            
        });

        interest.getSlider().valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (!interest.getSlider().isValueChanging()) {
                    layout.setCenter(lineChart.getLineChart());
                }
            }
            
        });


        Scene view = new Scene(layout, 640, 480);


        primaryStage.setScene(view);
        primaryStage.show();
    }
    
}





/* Resources used:
    - Resource #1: How to update label 'valuePosition' in realtime using ChangeListener.
            - URL: https://docs.oracle.com/javafx/2/ui_controls/slider.htm
        - Attempted to use ".setOnDrag", but it was inaccurate and did not update
            in real time.

    - Resource #2
            - URL:https://stackoverflow.com/questions/68089808/javafx-slider-how-to-only-change-value-when-dragging-is-done-while-also-maintai

*/