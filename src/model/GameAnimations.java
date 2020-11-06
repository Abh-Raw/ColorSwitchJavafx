package model;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Random;

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

    public void obstacle1_animation(float x, float y, final GameObstacles obstacles) {
        Rotate rotation1 = (Rotate) obstacles.arc_components.get(0).getTransforms().get(0);
        Rotate rotation2 = (Rotate) obstacles.arc_components.get(1).getTransforms().get(0);
        Rotate rotation3 = (Rotate) obstacles.arc_components.get(2).getTransforms().get(0);
        Rotate rotation4 = (Rotate) obstacles.arc_components.get(3).getTransforms().get(0);

        rotation1.setPivotX(x);
        rotation1.setPivotY(y);
        rotation2.setPivotX(x);
        rotation2.setPivotY(y);
        rotation3.setPivotX(x);
        rotation3.setPivotY(y);
        rotation4.setPivotX(x);
        rotation4.setPivotY(y);

        rotation1.pivotXProperty().bind(obstacles.arc_components.get(0).centerXProperty());
        rotation1.pivotYProperty().bind(obstacles.arc_components.get(0).centerYProperty());
        rotation2.pivotXProperty().bind(obstacles.arc_components.get(1).centerXProperty());
        rotation2.pivotYProperty().bind(obstacles.arc_components.get(1).centerYProperty());
        rotation3.pivotXProperty().bind(obstacles.arc_components.get(2).centerXProperty());
        rotation3.pivotYProperty().bind(obstacles.arc_components.get(2).centerYProperty());
        rotation4.pivotXProperty().bind(obstacles.arc_components.get(3).centerXProperty());
        rotation4.pivotYProperty().bind(obstacles.arc_components.get(3).centerYProperty());

        KeyValue arc1_val1 = new KeyValue(rotation1.angleProperty(), 0);
        KeyValue arc1_val2 = new KeyValue(rotation1.angleProperty(), 90);
        KeyValue arc1_val3 = new KeyValue(rotation1.angleProperty(), 180);
        KeyValue arc1_val4 = new KeyValue(rotation1.angleProperty(), 270);
        KeyValue arc1_val5 = new KeyValue(rotation1.angleProperty(), 360);


        KeyValue arc2_val1 = new KeyValue(rotation2.angleProperty(), 0);
        KeyValue arc2_val2 = new KeyValue(rotation2.angleProperty(), 90);
        KeyValue arc2_val3 = new KeyValue(rotation2.angleProperty(), 180);
        KeyValue arc2_val4 = new KeyValue(rotation2.angleProperty(), 270);
        KeyValue arc2_val5 = new KeyValue(rotation2.angleProperty(), 360);


        KeyValue arc3_val1 = new KeyValue(rotation3.angleProperty(), 0);
        KeyValue arc3_val2 = new KeyValue(rotation3.angleProperty(), 90);
        KeyValue arc3_val3 = new KeyValue(rotation3.angleProperty(), 180);
        KeyValue arc3_val4 = new KeyValue(rotation3.angleProperty(), 270);
        KeyValue arc3_val5 = new KeyValue(rotation3.angleProperty(), 360);

        KeyValue arc4_val1 = new KeyValue(rotation4.angleProperty(), 0);
        KeyValue arc4_val2 = new KeyValue(rotation4.angleProperty(), 90);
        KeyValue arc4_val3 = new KeyValue(rotation4.angleProperty(), 180);
        KeyValue arc4_val4 = new KeyValue(rotation4.angleProperty(), 270);
        KeyValue arc4_val5 = new KeyValue(rotation4.angleProperty(), 360);

        KeyFrame frame1 = new KeyFrame(Duration.ZERO, arc1_val1, arc2_val1, arc3_val1, arc4_val1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1), arc1_val2, arc2_val2, arc3_val2, arc4_val2);
        KeyFrame frame3 = new KeyFrame(Duration.seconds(2), arc1_val3, arc2_val3, arc3_val3, arc4_val3);
        KeyFrame frame4 = new KeyFrame(Duration.seconds(3), arc1_val4, arc2_val4, arc3_val4, arc4_val4);
        KeyFrame frame5 = new KeyFrame(Duration.seconds(4), arc1_val5, arc2_val5, arc3_val5, arc4_val5);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(frame1, frame2, frame3, frame4, frame5);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void obstacle2_animation(float x, float y, final GameObstacles obstacles){
        Rotate rotation1 = (Rotate) obstacles.line_components.get(0).getTransforms().get(0);
        Rotate rotation2 = (Rotate) obstacles.line_components.get(1).getTransforms().get(0);
        Rotate rotation3 = (Rotate) obstacles.line_components.get(2).getTransforms().get(0);

        rotation1.setPivotX(x);
        rotation1.setPivotY(y);
        rotation2.setPivotX(x);
        rotation2.setPivotY(y);
        rotation3.setPivotX(x);
        rotation3.setPivotY(y);

        KeyValue line1_val1 = new KeyValue(rotation1.angleProperty(), 0);
        KeyValue line1_val2 = new KeyValue(rotation1.angleProperty(), 120);
        KeyValue line1_val3 = new KeyValue(rotation1.angleProperty(), 240);
        KeyValue line1_val4 = new KeyValue(rotation1.angleProperty(), 360);

        KeyValue line2_val1 = new KeyValue(rotation2.angleProperty(), 0);
        KeyValue line2_val2 = new KeyValue(rotation2.angleProperty(), 120);
        KeyValue line2_val3 = new KeyValue(rotation2.angleProperty(), 240);
        KeyValue line2_val4 = new KeyValue(rotation2.angleProperty(), 360);

        KeyValue line3_val1 = new KeyValue(rotation3.angleProperty(), 0);
        KeyValue line3_val2 = new KeyValue(rotation3.angleProperty(), 120);
        KeyValue line3_val3 = new KeyValue(rotation3.angleProperty(), 240);
        KeyValue line3_val4 = new KeyValue(rotation3.angleProperty(), 360);

        KeyFrame frame1 = new KeyFrame(Duration.ZERO, line1_val1, line2_val1, line3_val1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1), line1_val2, line2_val2, line3_val2);
        KeyFrame frame3 = new KeyFrame(Duration.seconds(2), line1_val3, line2_val3, line3_val3);
        KeyFrame frame4 = new KeyFrame(Duration.seconds(3), line1_val4, line2_val4, line3_val4);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(frame1, frame2, frame3, frame4);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void changeColor(Circle start_ball){
        Random random = new Random();
        int n = random.nextInt(4);
        if(n==0  )
            start_ball.setFill(Color.BLUE);
        else if(n==1 )
            start_ball.setFill(Color.RED);
        else if(n==2  )
            start_ball.setFill(Color.GREEN);
        else if(n==3 )
            start_ball.setFill(Color.YELLOW);

    }
}
