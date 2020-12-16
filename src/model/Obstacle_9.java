package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Obstacle_9 extends GameObstacles{    //square
    public Obstacle_9(){
        super();
        this.obstacle_id = 9;
    }

    @Override
    public void createObstacle(float x, float y, Circle start_ball){
        Line line1 = new Line(x - 65.0f, y - 65.0f, x + 65.0f, y - 65.0f);
        Line line2 = new Line(x + 65.0f, y - 65.0f, x + 65.0f, y + 65.0f);
        Line line3 = new Line(x - 65.0f, y + 65.0f, x + 65.0f, y + 65.0f);
        Line line4 = new Line(x-65.0f, y+65.0f, x-65.0f, y-65.0f);

        Random random = new Random();

        line1.setStrokeWidth(12.0f);
        line2.setStrokeWidth(12.0f);
        line3.setStrokeWidth(12.0f);
        line4.setStrokeWidth(12.0f);

        line1.setStroke(Color.BLUE);
        line2.setStroke(Color.RED);
        line3.setStroke(Color.GREEN);
        line4.setStroke(Color.YELLOW);

        line_components.add(line1);
        line_components.add(line2);
        line_components.add(line3);
        line_components.add(line4);

    }

    public void addAnimation(float x, float y, AnchorPane gp){
        Rotate rotation1 = (Rotate) line_components.get(0).getTransforms().get(0);
        Rotate rotation2 = (Rotate) line_components.get(1).getTransforms().get(0);
        Rotate rotation3 = (Rotate) line_components.get(2).getTransforms().get(0);
        Rotate rotation4 = (Rotate) line_components.get(3).getTransforms().get(0);

        rotation1.setPivotX(x);
        rotation1.setPivotY(y);
        rotation2.setPivotX(x);
        rotation2.setPivotY(y);
        rotation3.setPivotX(x);
        rotation3.setPivotY(y);
        rotation4.setPivotX(x);
        rotation4.setPivotY(y);

        KeyValue line1_val1 = new KeyValue(rotation1.angleProperty(), 0);
        KeyValue line1_val2 = new KeyValue(rotation1.angleProperty(), 90);
        KeyValue line1_val3 = new KeyValue(rotation1.angleProperty(), 180);
        KeyValue line1_val4 = new KeyValue(rotation1.angleProperty(), 270);
        KeyValue line1_val5 = new KeyValue(rotation1.angleProperty(), 360);

        KeyValue line2_val1 = new KeyValue(rotation2.angleProperty(), 0);
        KeyValue line2_val2 = new KeyValue(rotation2.angleProperty(), 90);
        KeyValue line2_val3 = new KeyValue(rotation2.angleProperty(), 180);
        KeyValue line2_val4 = new KeyValue(rotation2.angleProperty(), 270);
        KeyValue line2_val5 = new KeyValue(rotation2.angleProperty(), 360);

        KeyValue line3_val1 = new KeyValue(rotation3.angleProperty(), 0);
        KeyValue line3_val2 = new KeyValue(rotation3.angleProperty(), 90);
        KeyValue line3_val3 = new KeyValue(rotation3.angleProperty(), 180);
        KeyValue line3_val4 = new KeyValue(rotation3.angleProperty(), 270);
        KeyValue line3_val5 = new KeyValue(rotation3.angleProperty(), 360);

        KeyValue line4_val1 = new KeyValue(rotation4.angleProperty(), 0);
        KeyValue line4_val2 = new KeyValue(rotation4.angleProperty(), 90);
        KeyValue line4_val3 = new KeyValue(rotation4.angleProperty(), 180);
        KeyValue line4_val4 = new KeyValue(rotation4.angleProperty(), 270);
        KeyValue line4_val5 = new KeyValue(rotation4.angleProperty(), 360);

        KeyFrame frame1 = new KeyFrame(Duration.ZERO, line1_val1, line2_val1, line3_val1, line4_val1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1), line1_val2, line2_val2, line3_val2, line4_val2);
        KeyFrame frame3 = new KeyFrame(Duration.seconds(2), line1_val3, line2_val3, line3_val3, line4_val3);
        KeyFrame frame4 = new KeyFrame(Duration.seconds(3), line1_val4, line2_val4, line3_val4, line4_val4);
        KeyFrame frame5 = new KeyFrame(Duration.seconds(4), line1_val5, line2_val5, line3_val5, line4_val5);


        animation = new Timeline();
        animation.getKeyFrames().addAll(frame1, frame2, frame3, frame4, frame5);

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

    }

}
