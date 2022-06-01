package application;

import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane; // Foundation piece
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Slider;
import javafx.scene.control.Label;


public class SubviewSlider {
   
    private Label name;
    private BorderPane layout;
    private Slider slider;
    private Label valuePosition;

    public SubviewSlider(String name, double min, double max, double value) {
        this.name = new Label(name);
        this.slider = initialSlider(min, max, value);
        this.valuePosition = new Label(valueTemplate());
        this.layout = initialBorderPane();
    }

    // inititiate 
    private BorderPane initialBorderPane() {
        BorderPane layout = new BorderPane();

        
        slider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                valuePosition.setText(valueTemplate());
            }
        });

        layout.setLeft(name);
        layout.setCenter(slider);
        layout.setRight(valuePosition);

        return layout;
    }

    private Slider initialSlider(double min, double max, double value){
        Slider slider = new Slider(min, max, value);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.snapToTicksProperty();

        return slider;
    }

    private String valueTemplate() {
        return String.format("%.2f", this.slider.getValue());
    }

    // Adjust slider

        //for BOTH savings and interest
    public void adjustBlockIncrement(Double increment){ 
        this.slider.setBlockIncrement(increment);
    }

        // for ONLY savings 
    public void adjustMajorTickUnit(Double increment){
        this.slider.setMajorTickUnit(increment);
    }

    

    // 'get' section
    public BorderPane getBorderPane() {
        return this.layout;
    }

    public double getValuePosition(){
        return this.slider.getValue();
    }

    public Slider getSlider() {
        return this.slider;
    }

    
}
