package model;

//import javafx.scene.shape.Circle;
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

public class Logo_Obstacle {
    protected ArrayList<Arc> arc_components;
    protected transient Timeline animation;

    public Logo_Obstacle(){
        arc_components = new ArrayList<>();
    }

    public void createLogoObstacle(float x, float y){
        Arc arc1 = new Arc(x, y, 10.0f, 10.0f, 0, 90);  //arc(center_x, center_y, radius_x, radius_y, start_angle, set_length)
        Arc arc2 = new Arc(x, y, 10.0f, 10.0f, 90, 90);
        Arc arc3 = new Arc(x, y, 10.0f, 10.0f, 180, 90);
        Arc arc4 = new Arc(x, y, 10.0f, 10.0f, 270, 90);

        arc1.setFill(Color.TURQUOISE);
        arc1.setStroke(Color.TURQUOISE);
        arc1.setStrokeWidth(15.0f);
        arc1.setStrokeType(StrokeType.INSIDE);
        arc1.setType(ArcType.OPEN);

        arc2.setFill(Color.DEEPPINK);
        arc2.setStroke(Color.DEEPPINK);
        arc2.setStrokeWidth(15.0f);
        arc2.setStrokeType(StrokeType.INSIDE);
        arc2.setType(ArcType.OPEN);

        arc3.setFill(Color.DARKVIOLET);
        arc3.setStroke(Color.DARKVIOLET);
        arc3.setStrokeWidth(15.0f);
        arc3.setStrokeType(StrokeType.INSIDE);
        arc3.setType(ArcType.OPEN);

        arc4.setFill(Color.YELLOW);
        arc4.setStroke(Color.YELLOW);
        arc4.setStrokeWidth(15.0f);
        arc4.setStrokeType(StrokeType.INSIDE);
        arc4.setType(ArcType.OPEN);

        arc_components.add(arc1);
        arc_components.add(arc2);
        arc_components.add(arc3);
        arc_components.add(arc4);
    }

    public ArrayList<Arc> getArc_components(){
        return arc_components;
    }

    public void addAnimation(float x, float y, AnchorPane gp){
        Rotate rotation1 = (Rotate) arc_components.get(0).getTransforms().get(0);
        Rotate rotation2 = (Rotate) arc_components.get(1).getTransforms().get(0);
        Rotate rotation3 = (Rotate) arc_components.get(2).getTransforms().get(0);
        Rotate rotation4 = (Rotate) arc_components.get(3).getTransforms().get(0);

        rotation1.setPivotX(x);
        rotation1.setPivotY(y);
        rotation2.setPivotX(x);
        rotation2.setPivotY(y);
        rotation3.setPivotX(x);
        rotation3.setPivotY(y);
        rotation4.setPivotX(x);
        rotation4.setPivotY(y);

        rotation1.pivotXProperty().bind(arc_components.get(0).centerXProperty());
        rotation1.pivotYProperty().bind(arc_components.get(0).centerYProperty());
        rotation2.pivotXProperty().bind(arc_components.get(1).centerXProperty());
        rotation2.pivotYProperty().bind(arc_components.get(1).centerYProperty());
        rotation3.pivotXProperty().bind(arc_components.get(2).centerXProperty());
        rotation3.pivotYProperty().bind(arc_components.get(2).centerYProperty());
        rotation4.pivotXProperty().bind(arc_components.get(3).centerXProperty());
        rotation4.pivotYProperty().bind(arc_components.get(3).centerYProperty());

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

        animation = new Timeline();
        animation.getKeyFrames().addAll(frame1, frame2, frame3, frame4, frame5);

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    }
