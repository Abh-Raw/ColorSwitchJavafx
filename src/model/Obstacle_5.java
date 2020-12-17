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

public class Obstacle_5 extends GameObstacles{   //concentric rings

    public Obstacle_5(){
        super();
        this.obstacle_id = 5;
    }

    @Override
    public void createObstacle(float x, float y, Circle start_ball){
        Arc arc1 = new Arc(x, y, 90.0f, 90.0f, 0-45, 90);  //arc(center_x, center_y, radius_x, radius_y, start_angle, set_length)
        Arc arc2 = new Arc(x, y, 90.0f, 90.0f, 90-45, 90);
        Arc arc3 = new Arc(x, y, 90.0f, 90.0f, 180-45, 90);
        Arc arc4 = new Arc(x, y, 90.0f, 90.0f, 270-45, 90);

        Arc arc5 = new Arc(x, y, 145.0f, 145.0f, 0-45, 90);
        Arc arc6 = new Arc(x, y, 145.0f, 145.0f, 90-45, 90);
        Arc arc7 = new Arc(x, y, 145.0f, 145.0f, 180-45, 90);
        Arc arc8 = new Arc(x, y, 145.0f, 145.0f, 270-45, 90);

        Random random = new Random();

        int color_id;
        if (start_ball.getFill() == Color.TURQUOISE)
            color_id = 0;
        else if (start_ball.getFill() == Color.DEEPPINK)
            color_id = 1;
        else if (start_ball.getFill() == Color.DARKVIOLET)
            color_id = 2;
        else
            color_id = 3;

        int n1;
        do {
            n1 = random.nextInt(4);
        } while (n1 == color_id);

        int n2;
        do {
            n2 = random.nextInt(4);
        } while (n1 == n2 || n2 == color_id);

        int n3;
        do {
            n3 = random.nextInt(4);
        } while (n1 == n3 || n3 == color_id || n2 == n3);

        //arc 2
        arc2.setFill(start_ball.getFill());
        arc2.setStroke(start_ball.getFill());
        arc2.setStrokeWidth(15.0f);
        arc2.setStrokeType(StrokeType.INSIDE);
        arc2.setType(ArcType.OPEN);

        //arc 6
        arc6.setFill(start_ball.getFill());
        arc6.setStroke(start_ball.getFill());
        arc6.setStrokeWidth(15.0f);
        arc6.setStrokeType(StrokeType.INSIDE);
        arc6.setType(ArcType.OPEN);


        //arc 1 and 7
        if(n1 == 0){
            arc1.setFill(Color.TURQUOISE);
            arc1.setStroke(Color.TURQUOISE);
            arc7.setFill(Color.TURQUOISE);
            arc7.setStroke(Color.TURQUOISE);
        }
        else if(n1 == 1){
            arc1.setFill(Color.DEEPPINK);
            arc1.setStroke(Color.DEEPPINK);
            arc7.setFill(Color.DEEPPINK);
            arc7.setStroke(Color.DEEPPINK);
        }
        else if(n1 == 2){
            arc1.setFill(Color.DARKVIOLET);
            arc1.setStroke(Color.DARKVIOLET);
            arc7.setFill(Color.DARKVIOLET);
            arc7.setStroke(Color.DARKVIOLET);
        }
        else{
            arc1.setFill(Color.YELLOW);
            arc1.setStroke(Color.YELLOW);
            arc7.setFill(Color.YELLOW);
            arc7.setStroke(Color.YELLOW);
        }


        //arc 3 and 5
        if(n2 == 0){
            arc3.setFill(Color.TURQUOISE);
            arc3.setStroke(Color.TURQUOISE);
            arc5.setFill(Color.TURQUOISE);
            arc5.setStroke(Color.TURQUOISE);
        }
        else if(n2 == 1){
            arc3.setFill(Color.DEEPPINK);
            arc3.setStroke(Color.DEEPPINK);
            arc5.setFill(Color.DEEPPINK);
            arc5.setStroke(Color.DEEPPINK);
        }
        else if(n2 == 2){
            arc3.setFill(Color.DARKVIOLET);
            arc3.setStroke(Color.DARKVIOLET);
            arc5.setFill(Color.DARKVIOLET);
            arc5.setStroke(Color.DARKVIOLET);
        }
        else{
            arc3.setFill(Color.YELLOW);
            arc3.setStroke(Color.YELLOW);
            arc5.setFill(Color.YELLOW);
            arc5.setStroke(Color.YELLOW);
        }


        //arc 4 and 8
        if(n3 == 0){
            arc4.setFill(Color.TURQUOISE);
            arc4.setStroke(Color.TURQUOISE);
            arc8.setFill(Color.TURQUOISE);
            arc8.setStroke(Color.TURQUOISE);
        }
        else if(n3 == 1){
            arc4.setFill(Color.DEEPPINK);
            arc4.setStroke(Color.DEEPPINK);
            arc8.setFill(Color.DEEPPINK);
            arc8.setStroke(Color.DEEPPINK);
        }
        else if(n3 == 2){
            arc4.setFill(Color.DARKVIOLET);
            arc4.setStroke(Color.DARKVIOLET);
            arc8.setFill(Color.DARKVIOLET);
            arc8.setStroke(Color.DARKVIOLET);
        }
        else{
            arc4.setFill(Color.YELLOW);
            arc4.setStroke(Color.YELLOW);
            arc8.setFill(Color.YELLOW);
            arc8.setStroke(Color.YELLOW);
        }


        arc1.setStrokeWidth(15.0f);
        arc1.setStrokeType(StrokeType.INSIDE);
        arc1.setType(ArcType.OPEN);

        arc3.setStrokeWidth(15.0f);
        arc3.setStrokeType(StrokeType.INSIDE);
        arc3.setType(ArcType.OPEN);

        arc4.setStrokeWidth(15.0f);
        arc4.setStrokeType(StrokeType.INSIDE);
        arc4.setType(ArcType.OPEN);

        arc5.setStrokeWidth(15.0f);
        arc5.setStrokeType(StrokeType.INSIDE);
        arc5.setType(ArcType.OPEN);

        arc7.setStrokeWidth(15.0f);
        arc7.setStrokeType(StrokeType.INSIDE);
        arc7.setType(ArcType.OPEN);

        arc8.setStrokeWidth(15.0f);
        arc8.setStrokeType(StrokeType.INSIDE);
        arc8.setType(ArcType.OPEN);

        ArrayList<Integer> mapping_arr = new ArrayList<>();
        mapping_arr.add(color_id);
        mapping_arr.add(n1);
        mapping_arr.add(n2);
        mapping_arr.add(n3);

        for(int i=0; i<mapping_arr.size(); i++){
            if(mapping_arr.get(i) == color_id){
                arc_components.add(arc2);
                arc_components.add(arc6);
            }
            else if(mapping_arr.get(i) == n1){
                arc_components.add(arc1);
                arc_components.add(arc7);
            }
            else if (mapping_arr.get(i) == n2){
                arc_components.add(arc3);
                arc_components.add(arc5);
            }
            else{
                arc_components.add(arc4);
                arc_components.add(arc8);
            }
        }

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

        KeyValue arc1_val1 = new KeyValue(rotation1.angleProperty(), 0);
        KeyValue arc1_val2 = new KeyValue(rotation1.angleProperty(), 90);
        KeyValue arc1_val3 = new KeyValue(rotation1.angleProperty(), 180);
        KeyValue arc1_val4 = new KeyValue(rotation1.angleProperty(), 270);
        KeyValue arc1_val5 = new KeyValue(rotation1.angleProperty(), 360);

        KeyValue arc2_val1 = new KeyValue(rotation2.angleProperty(), 360);
        KeyValue arc2_val2 = new KeyValue(rotation2.angleProperty(), 270);
        KeyValue arc2_val3 = new KeyValue(rotation2.angleProperty(), 180);
        KeyValue arc2_val4 = new KeyValue(rotation2.angleProperty(), 90);
        KeyValue arc2_val5 = new KeyValue(rotation2.angleProperty(), 0);

        KeyValue arc3_val1 = new KeyValue(rotation3.angleProperty(), 0);
        KeyValue arc3_val2 = new KeyValue(rotation3.angleProperty(), 90);
        KeyValue arc3_val3 = new KeyValue(rotation3.angleProperty(), 180);
        KeyValue arc3_val4 = new KeyValue(rotation3.angleProperty(), 270);
        KeyValue arc3_val5 = new KeyValue(rotation3.angleProperty(), 360);

        KeyValue arc4_val1 = new KeyValue(rotation4.angleProperty(), 360);
        KeyValue arc4_val2 = new KeyValue(rotation4.angleProperty(), 270);
        KeyValue arc4_val3 = new KeyValue(rotation4.angleProperty(), 180);
        KeyValue arc4_val4 = new KeyValue(rotation4.angleProperty(), 90);
        KeyValue arc4_val5 = new KeyValue(rotation4.angleProperty(), 0);

        KeyValue arc5_val1 = new KeyValue(rotation5.angleProperty(), 0);
        KeyValue arc5_val2 = new KeyValue(rotation5.angleProperty(), 90);
        KeyValue arc5_val3 = new KeyValue(rotation5.angleProperty(), 180);
        KeyValue arc5_val4 = new KeyValue(rotation5.angleProperty(), 270);
        KeyValue arc5_val5 = new KeyValue(rotation5.angleProperty(), 360);

        KeyValue arc6_val1 = new KeyValue(rotation6.angleProperty(), 360);
        KeyValue arc6_val2 = new KeyValue(rotation6.angleProperty(), 270);
        KeyValue arc6_val3 = new KeyValue(rotation6.angleProperty(), 180);
        KeyValue arc6_val4 = new KeyValue(rotation6.angleProperty(), 90);
        KeyValue arc6_val5 = new KeyValue(rotation6.angleProperty(), 0);

        KeyValue arc7_val1 = new KeyValue(rotation7.angleProperty(), 0);
        KeyValue arc7_val2 = new KeyValue(rotation7.angleProperty(), 90);
        KeyValue arc7_val3 = new KeyValue(rotation7.angleProperty(), 180);
        KeyValue arc7_val4 = new KeyValue(rotation7.angleProperty(), 270);
        KeyValue arc7_val5 = new KeyValue(rotation7.angleProperty(), 360);

        KeyValue arc8_val1 = new KeyValue(rotation8.angleProperty(), 360);
        KeyValue arc8_val2 = new KeyValue(rotation8.angleProperty(), 270);
        KeyValue arc8_val3 = new KeyValue(rotation8.angleProperty(), 180);
        KeyValue arc8_val4 = new KeyValue(rotation8.angleProperty(), 90);
        KeyValue arc8_val5 = new KeyValue(rotation8.angleProperty(), 0);

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
