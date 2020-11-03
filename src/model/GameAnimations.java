package model;

import View.ViewManager;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameAnimations{
    public GameAnimations(){
    }
    public void IntroAnimation(ImageView logo){
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), logo);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(10.0);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), logo);
        fadeOut.setFromValue(10.0);
        fadeOut.setToValue(0.0);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(fadeIn, fadeOut);
        sequentialTransition.setAutoReverse(true);
        sequentialTransition.setCycleCount(Animation.INDEFINITE);
        sequentialTransition.play();

    }

    public void arc_obstacle_animation(AnchorPane  gp, Arc obstacle, float x, float y) {
        Rotate rotation = new Rotate();
        //rotation.setAngle(360);
        //System.out.println(gp1.getLayoutX() + " " + gp1.getLayoutY());
        rotation.setPivotX(x);
        rotation.setPivotY(y);

        rotation.pivotXProperty().bind(obstacle.centerXProperty());
        rotation.pivotYProperty().bind(obstacle.centerYProperty());

        obstacle.getTransforms().add(rotation);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(3), new KeyValue(rotation.angleProperty(), 360)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void line_obstacle_animation(AnchorPane  gp, Line obstacle, float x, float y){
        Rotate rotation = new Rotate();
        rotation.setAngle(360);
        rotation.setPivotX(x);
        rotation.setPivotY(y);

        obstacle.getTransforms().add(rotation);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(3), new KeyValue(rotation.angleProperty(), 360)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
