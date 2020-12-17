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

public class Obstacle_7 extends GameObstacles{     //8-figure
    public Obstacle_7(){
        super();
        this.obstacle_id = 7;
    }

    @Override
    public void createObstacle(float x, float y, Circle start_ball){
        Arc arc1 = new Arc(x, y+80f, 75.0f, 75.0f, 0-45, 90);  //arc(center_x, center_y, radius_x, radius_y, start_angle, set_length)
        Arc arc2 = new Arc(x, y+80f, 75.0f, 75.0f, 90-45, 90);
        Arc arc3 = new Arc(x, y+80f, 75.0f, 75.0f, 180-45, 90);
        Arc arc4 = new Arc(x, y+80f, 75.0f, 75.0f, 270-45, 90);

        Arc arc5 = new Arc(x, y-80f, 75.0f, 75.0f, 0-45, 90);  //arc(center_x, center_y, radius_x, radius_y, start_angle, set_length)
        Arc arc6 = new Arc(x, y-80f, 75.0f, 75.0f, 90-45, 90);
        Arc arc7 = new Arc(x, y-80f, 75.0f, 75.0f, 180-45, 90);
        Arc arc8 = new Arc(x, y-80f, 75.0f, 75.0f, 270-45, 90);

        //Arc arc9 = new Arc(x, y-160, 75.0f, 75.0f, 0-45, 90);  //arc(center_x, center_y, radius_x, radius_y, start_angle, set_length)
        //Arc arc10 = new Arc(x, y-160, 75.0f, 75.0f, 90-45, 90);
        //Arc arc11 = new Arc(x, y-160, 75.0f, 75.0f, 180-45, 90);
        //Arc arc12 = new Arc(x, y-160, 75.0f, 75.0f, 270-45, 90);

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
        arc4.setStrokeType( StrokeType.INSIDE);
        arc4.setType(ArcType.OPEN);

        arc5.setFill(Color.TURQUOISE);
        arc5.setStroke(Color.TURQUOISE);
        arc5.setStrokeWidth(15.0f);
        arc5.setStrokeType(StrokeType.INSIDE);
        arc5.setType(ArcType.OPEN);

        arc6.setFill(Color.YELLOW);
        arc6.setStroke(Color.YELLOW);
        arc6.setStrokeWidth(15.0f);
        arc6.setStrokeType(StrokeType.INSIDE);
        arc6.setType(ArcType.OPEN);

        arc7.setFill(Color.DARKVIOLET);
        arc7.setStroke(Color.DARKVIOLET);
        arc7.setStrokeWidth(15.0f);
        arc7.setStrokeType(StrokeType.INSIDE);
        arc7.setType(ArcType.OPEN);

        arc8.setFill(Color.DEEPPINK);
        arc8.setStroke(Color.DEEPPINK);
        arc8.setStrokeWidth(15.0f);
        arc8.setStrokeType( StrokeType.INSIDE);
        arc8.setType(ArcType.OPEN);

/*        arc9.setFill(Color.TURQUOISE);
        arc9.setStroke(Color.TURQUOISE);
        arc9.setStrokeWidth(15.0f);
        arc9.setStrokeType(StrokeType.INSIDE);
        arc9.setType(ArcType.OPEN);

        arc10.setFill(Color.DEEPPINK);
        arc10.setStroke(Color.DEEPPINK);
        arc10.setStrokeWidth(15.0f);
        arc10.setStrokeType(StrokeType.INSIDE);
        arc10.setType(ArcType.OPEN);

        arc11.setFill(Color.DARKVIOLET);
        arc11.setStroke(Color.DARKVIOLET);
        arc11.setStrokeWidth(15.0f);
        arc11.setStrokeType(StrokeType.INSIDE);
        arc11.setType(ArcType.OPEN);

        arc12.setFill(Color.YELLOW);
        arc12.setStroke(Color.YELLOW);
        arc12.setStrokeWidth(15.0f);
        arc12.setStrokeType( StrokeType.INSIDE);
        arc12.setType(ArcType.OPEN);

 */

        arc_components.add(arc1);
        arc_components.add(arc2);
        arc_components.add(arc3);
        arc_components.add(arc4);
        arc_components.add(arc5);
        arc_components.add(arc6);
        arc_components.add(arc7);
        arc_components.add(arc8);
       // arc_components.add(arc9);
       // arc_components.add(arc10);
       // arc_components.add(arc11);
       // arc_components.add(arc12);
    }

    @Override
    public void addAnimation(float x, float y, AnchorPane gp){
        Rotate rotation1 = (Rotate) arc_components.get(0).getTransforms().get(0);
        Rotate rotation2 = (Rotate) arc_components.get(1).getTransforms().get(0);
        Rotate rotation3 = (Rotate) arc_components.get(2).getTransforms().get(0);
        Rotate rotation4 = (Rotate) arc_components.get(3).getTransforms().get(0);

        Rotate rotation5 = (Rotate) arc_components.get(4).getTransforms().get(0);
        Rotate rotation6 = (Rotate) arc_components.get(5).getTransforms().get(0);
        Rotate rotation7 = (Rotate) arc_components.get(6).getTransforms().get(0);
        Rotate rotation8 = (Rotate) arc_components.get(7).getTransforms().get(0);
/*
        Rotate rotation9 = (Rotate) arc_components.get(8).getTransforms().get(0);
        Rotate rotation10 = (Rotate) arc_components.get(9).getTransforms().get(0);
        Rotate rotation11 = (Rotate) arc_components.get(10).getTransforms().get(0);
        Rotate rotation12 = (Rotate) arc_components.get(11).getTransforms().get(0);

 */

        rotation1.setPivotX(x);
        rotation1.setPivotY(y);
        rotation2.setPivotX(x);
        rotation2.setPivotY(y);
        rotation3.setPivotX(x);
        rotation3.setPivotY(y);
        rotation4.setPivotX(x);
        rotation4.setPivotY(y);

        rotation5.setPivotX(x);
        rotation5.setPivotY(y);
        rotation6.setPivotX(x);
        rotation6.setPivotY(y);
        rotation7.setPivotX(x);
        rotation7.setPivotY(y);
        rotation8.setPivotX(x);
        rotation8.setPivotY(y);
/*
        rotation9.setPivotX(x);
        rotation9.setPivotY(y);
        rotation10.setPivotX(x);
        rotation10.setPivotY(y);
        rotation11.setPivotX(x);
        rotation11.setPivotY(y);
        rotation12.setPivotX(x);
        rotation12.setPivotY(y);

 */

        rotation1.pivotXProperty().bind(arc_components.get(0).centerXProperty());
        rotation1.pivotYProperty().bind(arc_components.get(0).centerYProperty());
        rotation2.pivotXProperty().bind(arc_components.get(1).centerXProperty());
        rotation2.pivotYProperty().bind(arc_components.get(1).centerYProperty());
        rotation3.pivotXProperty().bind(arc_components.get(2).centerXProperty());
        rotation3.pivotYProperty().bind(arc_components.get(2).centerYProperty());
        rotation4.pivotXProperty().bind(arc_components.get(3).centerXProperty());
        rotation4.pivotYProperty().bind(arc_components.get(3).centerYProperty());

        rotation5.pivotXProperty().bind(arc_components.get(4).centerXProperty());
        rotation5.pivotYProperty().bind(arc_components.get(4).centerYProperty());
        rotation6.pivotXProperty().bind(arc_components.get(5).centerXProperty());
        rotation6.pivotYProperty().bind(arc_components.get(5).centerYProperty());
        rotation7.pivotXProperty().bind(arc_components.get(6).centerXProperty());
        rotation7.pivotYProperty().bind(arc_components.get(6).centerYProperty());
        rotation8.pivotXProperty().bind(arc_components.get(7).centerXProperty());
        rotation8.pivotYProperty().bind(arc_components.get(7).centerYProperty());
/*
        rotation9.pivotXProperty().bind(arc_components.get(8).centerXProperty());
        rotation9.pivotYProperty().bind(arc_components.get(8).centerYProperty());
        rotation10.pivotXProperty().bind(arc_components.get(9).centerXProperty());
        rotation10.pivotYProperty().bind(arc_components.get(9).centerYProperty());
        rotation11.pivotXProperty().bind(arc_components.get(10).centerXProperty());
        rotation11.pivotYProperty().bind(arc_components.get(10).centerYProperty());
        rotation12.pivotXProperty().bind(arc_components.get(11).centerXProperty());
        rotation12.pivotYProperty().bind(arc_components.get(11).centerYProperty());


 */
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

        KeyValue arc5_val1 = new KeyValue(rotation5.angleProperty(), 360);
        KeyValue arc5_val2 = new KeyValue(rotation5.angleProperty(), 270);
        KeyValue arc5_val3 = new KeyValue(rotation5.angleProperty(), 180);
        KeyValue arc5_val4 = new KeyValue(rotation5.angleProperty(), 90);
        KeyValue arc5_val5 = new KeyValue(rotation5.angleProperty(), 0);

        KeyValue arc6_val1 = new KeyValue(rotation6.angleProperty(), 360);
        KeyValue arc6_val2 = new KeyValue(rotation6.angleProperty(), 270);
        KeyValue arc6_val3 = new KeyValue(rotation6.angleProperty(), 180);
        KeyValue arc6_val4 = new KeyValue(rotation6.angleProperty(), 90);
        KeyValue arc6_val5 = new KeyValue(rotation6.angleProperty(), 0);

        KeyValue arc7_val1 = new KeyValue(rotation7.angleProperty(), 360);
        KeyValue arc7_val2 = new KeyValue(rotation7.angleProperty(), 270);
        KeyValue arc7_val3 = new KeyValue(rotation7.angleProperty(), 180);
        KeyValue arc7_val4 = new KeyValue(rotation7.angleProperty(), 90);
        KeyValue arc7_val5 = new KeyValue(rotation7.angleProperty(), 0);

        KeyValue arc8_val1 = new KeyValue(rotation8.angleProperty(), 360);
        KeyValue arc8_val2 = new KeyValue(rotation8.angleProperty(), 270);
        KeyValue arc8_val3 = new KeyValue(rotation8.angleProperty(), 180);
        KeyValue arc8_val4 = new KeyValue(rotation8.angleProperty(), 90);
        KeyValue arc8_val5 = new KeyValue(rotation8.angleProperty(), 0);
/*
        KeyValue arc9_val1 = new KeyValue(rotation1.angleProperty(), 0);
        KeyValue arc9_val2 = new KeyValue(rotation1.angleProperty(), 90);
        KeyValue arc9_val3 = new KeyValue(rotation1.angleProperty(), 180);
        KeyValue arc9_val4 = new KeyValue(rotation1.angleProperty(), 270);
        KeyValue arc9_val5 = new KeyValue(rotation1.angleProperty(), 360);

        KeyValue arc10_val1 = new KeyValue(rotation2.angleProperty(), 0);
        KeyValue arc10_val2 = new KeyValue(rotation2.angleProperty(), 90);
        KeyValue arc10_val3 = new KeyValue(rotation2.angleProperty(), 180);
        KeyValue arc10_val4 = new KeyValue(rotation2.angleProperty(), 270);
        KeyValue arc10_val5 = new KeyValue(rotation2.angleProperty(), 360);

        KeyValue arc11_val1 = new KeyValue(rotation3.angleProperty(), 0);
        KeyValue arc11_val2 = new KeyValue(rotation3.angleProperty(), 90);
        KeyValue arc11_val3 = new KeyValue(rotation3.angleProperty(), 180);
        KeyValue arc11_val4 = new KeyValue(rotation3.angleProperty(), 270);
        KeyValue arc11_val5 = new KeyValue(rotation3.angleProperty(), 360);

        KeyValue arc12_val1 = new KeyValue(rotation4.angleProperty(), 0);
        KeyValue arc12_val2 = new KeyValue(rotation4.angleProperty(), 90);
        KeyValue arc12_val3 = new KeyValue(rotation4.angleProperty(), 180);
        KeyValue arc12_val4 = new KeyValue(rotation4.angleProperty(), 270);
        KeyValue arc12_val5 = new KeyValue(rotation4.angleProperty(), 360);



        KeyFrame frame1 = new KeyFrame(Duration.ZERO, arc1_val1, arc2_val1, arc3_val1, arc4_val1, arc5_val1, arc6_val1, arc7_val1, arc8_val1, arc9_val1, arc10_val1, arc11_val1, arc12_val1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1), arc1_val2, arc2_val2, arc3_val2, arc4_val2, arc5_val2, arc6_val2, arc7_val2, arc8_val2, arc9_val2, arc10_val2, arc11_val2, arc12_val2);
        KeyFrame frame3 = new KeyFrame(Duration.seconds(2), arc1_val3, arc2_val3, arc3_val3, arc4_val3, arc5_val3, arc6_val3, arc7_val3, arc8_val3, arc9_val3, arc10_val3, arc11_val3, arc12_val3);
        KeyFrame frame4 = new KeyFrame(Duration.seconds(3), arc1_val4, arc2_val4, arc3_val4, arc4_val4, arc5_val4, arc6_val4, arc7_val4, arc8_val4, arc9_val4, arc10_val4, arc11_val4, arc12_val4);
        KeyFrame frame5 = new KeyFrame(Duration.seconds(4), arc1_val5, arc2_val5, arc3_val5, arc4_val5, arc5_val5, arc6_val5, arc7_val5, arc8_val5, arc9_val5, arc10_val5, arc11_val5, arc12_val5);

 */

        KeyFrame frame1 = new KeyFrame(Duration.ZERO, arc1_val1, arc2_val1, arc3_val1, arc4_val1, arc5_val1, arc6_val1, arc7_val1, arc8_val1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1), arc1_val2, arc2_val2, arc3_val2, arc4_val2, arc5_val2, arc6_val2, arc7_val2, arc8_val2);
        KeyFrame frame3 = new KeyFrame(Duration.seconds(2), arc1_val3, arc2_val3, arc3_val3, arc4_val3, arc5_val3, arc6_val3, arc7_val3, arc8_val3);
        KeyFrame frame4 = new KeyFrame(Duration.seconds(3), arc1_val4, arc2_val4, arc3_val4, arc4_val4, arc5_val4, arc6_val4, arc7_val4, arc8_val4);
        KeyFrame frame5 = new KeyFrame(Duration.seconds(4), arc1_val5, arc2_val5, arc3_val5, arc4_val5, arc5_val5, arc6_val5, arc7_val5, arc8_val5);

        animation = new Timeline();
        animation.getKeyFrames().addAll(frame1, frame2, frame3, frame4, frame5);

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }


}