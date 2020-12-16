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

public class Obstacle_8 extends GameObstacles{    //circle in triangle
    public Obstacle_8(){
        super();
        this.obstacle_id = 8;
    }

    @Override
    public void createObstacle(float x, float y, Circle start_ball){
        Line line1 = new Line(x - 120.0f, y - (240 * Math.sqrt(3)) / 6, x + 120.0f, y - (240 * Math.sqrt(3)) / 6);
        Line line2 = new Line(x + 120.0f, y - (240 * Math.sqrt(3)) / 6, x, y + (240 * Math.sqrt(3)) / 3);
        Line line3 = new Line(x, y + (240 * Math.sqrt(3)) / 3, x - 120.0f, y - (240 * Math.sqrt(3)) / 6);

        Arc arc1 = new Arc(x, y, 65.0f, 65.0f, 0-45, 90);
        Arc arc2 = new Arc(x, y, 65.0f, 65.0f, 90-45, 90);
        Arc arc3 = new Arc(x, y, 65.0f, 65.0f, 180-45, 90);
        Arc arc4 = new Arc(x, y, 65.0f, 65.0f, 270-45, 90);


        Random random = new Random();

        int color_id;
        if (start_ball.getFill() == Color.BLUE)
            color_id = 0;
        else if (start_ball.getFill() == Color.RED)
            color_id = 1;
        else if (start_ball.getFill() == Color.GREEN)
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

        line2.setStroke(start_ball.getFill());
        line1.setStrokeWidth(12.0f);
        line2.setStrokeWidth(12.0f);
        line3.setStrokeWidth(12.0f);

        if(n1 == 0)
            line1.setStroke(Color.BLUE);
        else if(n1 == 1)
            line1.setStroke(Color.RED);
        else if(n1 == 2)
            line1.setStroke(Color.GREEN);
        else
            line1.setStroke(Color.YELLOW);

        if(n2 == 0)
            line3.setStroke(Color.BLUE);
        else if(n2 == 1)
            line3.setStroke(Color.RED);
        else if(n2 == 2)
            line3.setStroke(Color.GREEN);
        else
            line3.setStroke(Color.YELLOW);

        ArrayList<Integer> mapping_arr = new ArrayList<>();
        mapping_arr.add(color_id);
        mapping_arr.add(n1);
        mapping_arr.add(n2);

        for(int i=0; i<mapping_arr.size(); i++){
            if(mapping_arr.get(i) == color_id)
                line_components.add(line2);
            else if(mapping_arr.get(i) == n1)
                line_components.add(line1);
            else
                line_components.add(line3);
        }


        int color_id_circle;
        if (start_ball.getFill() == Color.BLUE)
            color_id_circle = 0;
        else if (start_ball.getFill() == Color.RED)
            color_id_circle = 1;
        else if (start_ball.getFill() == Color.GREEN)
            color_id_circle = 2;
        else
            color_id_circle = 3;

        int n1_circle;
        do {
            n1_circle = random.nextInt(4);
        } while (n1_circle == color_id);

        int n2_circle;
        do {
            n2_circle = random.nextInt(4);
        } while (n1_circle == n2_circle || n2_circle == color_id_circle);

        int n3_circle;
        do {
            n3_circle = random.nextInt(4);
        } while (n1_circle == n3_circle || n3_circle == color_id_circle || n2_circle == n3_circle);

        arc4.setFill(start_ball.getFill());
        arc4.setStroke(start_ball.getFill());
        arc4.setStrokeWidth(15.0f);
        arc4.setStrokeType(StrokeType.INSIDE);
        arc4.setType(ArcType.OPEN);

        if(n1_circle == 0){
            arc1.setFill(Color.BLUE);
            arc1.setStroke(Color.BLUE);
            arc1.setStrokeWidth(15.0f);
            arc1.setStrokeType(StrokeType.INSIDE);
            arc1.setType(ArcType.OPEN);
        }
        else if(n1_circle == 1){
            arc1.setFill(Color.RED);
            arc1.setStroke(Color.RED);
            arc1.setStrokeWidth(15.0f);
            arc1.setStrokeType(StrokeType.INSIDE);
            arc1.setType(ArcType.OPEN);
        }
        else if(n1_circle == 2){
            arc1.setFill(Color.GREEN);
            arc1.setStroke(Color.GREEN);
            arc1.setStrokeWidth(15.0f);
            arc1.setStrokeType(StrokeType.INSIDE);
            arc1.setType(ArcType.OPEN);
        }
        else{
            arc1.setFill(Color.YELLOW);
            arc1.setStroke(Color.YELLOW);
            arc1.setStrokeWidth(15.0f);
            arc1.setStrokeType(StrokeType.INSIDE);
            arc1.setType(ArcType.OPEN);
        }


        if(n2_circle == 0){
            arc2.setFill(Color.BLUE);
            arc2.setStroke(Color.BLUE);
            arc2.setStrokeWidth(15.0f);
            arc2.setStrokeType(StrokeType.INSIDE);
            arc2.setType(ArcType.OPEN);
        }
        else if(n2_circle == 1){
            arc2.setFill(Color.RED);
            arc2.setStroke(Color.RED);
            arc2.setStrokeWidth(15.0f);
            arc2.setStrokeType(StrokeType.INSIDE);
            arc2.setType(ArcType.OPEN);
        }
        else if(n2_circle == 2){
            arc2.setFill(Color.GREEN);
            arc2.setStroke(Color.GREEN);
            arc2.setStrokeWidth(15.0f);
            arc2.setStrokeType(StrokeType.INSIDE);
            arc2.setType(ArcType.OPEN);
        }
        else{
            arc2.setFill(Color.YELLOW);
            arc2.setStroke(Color.YELLOW);
            arc2.setStrokeWidth(15.0f);
            arc2.setStrokeType(StrokeType.INSIDE);
            arc2.setType(ArcType.OPEN);
        }


        if(n3_circle == 0){
            arc3.setFill(Color.BLUE);
            arc3.setStroke(Color.BLUE);
            arc3.setStrokeWidth(15.0f);
            arc3.setStrokeType(StrokeType.INSIDE);
            arc3.setType(ArcType.OPEN);
        }
        else if(n3_circle == 1){
            arc3.setFill(Color.RED);
            arc3.setStroke(Color.RED);
            arc3.setStrokeWidth(15.0f);
            arc3.setStrokeType(StrokeType.INSIDE);
            arc3.setType(ArcType.OPEN);
        }
        else if(n3_circle == 2){
            arc3.setFill(Color.GREEN);
            arc3.setStroke(Color.GREEN);
            arc3.setStrokeWidth(15.0f);
            arc3.setStrokeType(StrokeType.INSIDE);
            arc3.setType(ArcType.OPEN);
        }
        else{
            arc3.setFill(Color.YELLOW);
            arc3.setStroke(Color.YELLOW);
            arc3.setStrokeWidth(15.0f);
            arc3.setStrokeType(StrokeType.INSIDE);
            arc3.setType(ArcType.OPEN);
        }

        ArrayList<Integer> mapping_arr_CIRCLE = new ArrayList<>();
        mapping_arr_CIRCLE.add(color_id_circle);
        mapping_arr_CIRCLE.add(n1_circle);
        mapping_arr_CIRCLE.add(n2_circle);
        mapping_arr_CIRCLE.add(n3_circle);

        for(int i=0; i<mapping_arr_CIRCLE.size(); i++){
            if(mapping_arr_CIRCLE.get(i) == color_id_circle){
                arc_components.add(arc1);
            }
            else if(mapping_arr_CIRCLE.get(i) == n1_circle){
                arc_components.add(arc2);
            }
            else if (mapping_arr_CIRCLE.get(i) == n2_circle){
                arc_components.add(arc3);
            }
            else{
                arc_components.add(arc4);
            }
        }


    }

    public void addAnimation(float x, float y, AnchorPane gp){
        Rotate rotation1 = (Rotate) line_components.get(0).getTransforms().get(0);
        Rotate rotation2 = (Rotate) line_components.get(1).getTransforms().get(0);
        Rotate rotation3 = (Rotate) line_components.get(2).getTransforms().get(0);

        Rotate rotation4 = (Rotate) arc_components.get(0).getTransforms().get(0);
        Rotate rotation5 = (Rotate) arc_components.get(1).getTransforms().get(0);
        Rotate rotation6 = (Rotate) arc_components.get(2).getTransforms().get(0);
        Rotate rotation7 = (Rotate) arc_components.get(3).getTransforms().get(0);



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

        rotation4.pivotXProperty().bind(arc_components.get(0).centerXProperty());
        rotation4.pivotYProperty().bind(arc_components.get(0).centerYProperty());
        rotation5.pivotXProperty().bind(arc_components.get(1).centerXProperty());
        rotation5.pivotYProperty().bind(arc_components.get(1).centerYProperty());
        rotation6.pivotXProperty().bind(arc_components.get(2).centerXProperty());
        rotation6.pivotYProperty().bind(arc_components.get(2).centerYProperty());
        rotation7.pivotXProperty().bind(arc_components.get(3).centerXProperty());
        rotation7.pivotYProperty().bind(arc_components.get(3).centerYProperty());



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



        KeyValue arc1_val1 = new KeyValue(rotation4.angleProperty(), 360);
        KeyValue arc1_val2 = new KeyValue(rotation4.angleProperty(), 240);
        KeyValue arc1_val4 = new KeyValue(rotation4.angleProperty(), 180);
        KeyValue arc1_val5 = new KeyValue(rotation4.angleProperty(), 0);

        KeyValue arc2_val1 = new KeyValue(rotation5.angleProperty(), 360);
        KeyValue arc2_val2 = new KeyValue(rotation5.angleProperty(), 240);
        KeyValue arc2_val4 = new KeyValue(rotation5.angleProperty(), 180);
        KeyValue arc2_val5 = new KeyValue(rotation5.angleProperty(), 0);

        KeyValue arc3_val1 = new KeyValue(rotation6.angleProperty(), 360);
        KeyValue arc3_val2 = new KeyValue(rotation6.angleProperty(), 240);
        KeyValue arc3_val4 = new KeyValue(rotation6.angleProperty(), 180);
        KeyValue arc3_val5 = new KeyValue(rotation6.angleProperty(), 0);

        KeyValue arc4_val1 = new KeyValue(rotation7.angleProperty(), 360);
        KeyValue arc4_val2 = new KeyValue(rotation7.angleProperty(), 240);
        KeyValue arc4_val4 = new KeyValue(rotation7.angleProperty(), 180);
        KeyValue arc4_val5 = new KeyValue(rotation7.angleProperty(), 0);


        KeyFrame frame1 = new KeyFrame(Duration.ZERO, line1_val1, line2_val1, line3_val1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1.9), line1_val2, line2_val2, line3_val2);
        KeyFrame frame3 = new KeyFrame(Duration.seconds(3.8), line1_val3, line2_val3, line3_val3);
        KeyFrame frame4 = new KeyFrame(Duration.seconds(5.7), line1_val4, line2_val4, line3_val4);

        KeyFrame frame5 = new KeyFrame(Duration.ZERO, arc1_val1, arc2_val1, arc3_val1, arc4_val1);
        KeyFrame frame6 = new KeyFrame(Duration.seconds(1.9), arc1_val2, arc2_val2, arc3_val2, arc4_val2);
        KeyFrame frame8 = new KeyFrame(Duration.seconds(3.8), arc1_val4, arc2_val4, arc3_val4, arc4_val4);
        KeyFrame frame9 = new KeyFrame(Duration.seconds(5.7), arc1_val5, arc2_val5, arc3_val5, arc4_val5);



        animation = new Timeline();
        animation.getKeyFrames().addAll(frame1, frame2, frame3, frame4, frame5, frame6, frame9);

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

}
