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

public class Obstacle_4 extends GameObstacles{    //x shaped obstacle
    public Obstacle_4(){
        super();
        this.obstacle_id = 4;
    }

    @Override
    public void createObstacle(float x, float y, Circle start_ball){
        Line line1 = new Line(x - 65.0f, y - 0f, x - 0f, y + 0f);
        Line line2 = new Line(x + 0f, y - 0f, x + 65.0f, y + 0f);
        Line line3 = new Line(x - 0f, y - 0f, x - 0f, y + 65.0f);
        Line line4 = new Line(x + 0f, y - 65.0f, x + 0f, y + 0f);

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

        line1.setStroke(start_ball.getFill());
        line1.setStrokeWidth(12.0f);
        line2.setStrokeWidth(12.0f);
        line3.setStrokeWidth(12.0f);
        line4.setStrokeWidth(12.0f);

        if(n1 == 0)
            line2.setStroke(Color.TURQUOISE);
        else if(n1 == 1)
            line2.setStroke(Color.DEEPPINK);
        else if(n1 == 2)
            line2.setStroke(Color.DARKVIOLET);
        else
            line2.setStroke(Color.YELLOW);

        if(n2 == 0)
            line3.setStroke(Color.TURQUOISE);
        else if(n2 == 1)
            line3.setStroke(Color.DEEPPINK);
        else if(n2 == 2)
            line3.setStroke(Color.DARKVIOLET);
        else
            line3.setStroke(Color.YELLOW);

        if(n3 == 0)
            line4.setStroke(Color.TURQUOISE);
        else if(n3 == 1)
            line4.setStroke(Color.DEEPPINK);
        else if(n3 == 2)
            line4.setStroke(Color.DARKVIOLET);
        else
            line4.setStroke(Color.YELLOW);

        ArrayList<Integer> mapping_arr = new ArrayList<>();
        mapping_arr.add(color_id);
        mapping_arr.add(n1);
        mapping_arr.add(n2);
        mapping_arr.add(n3);

        for(int i=0; i<mapping_arr.size(); i++){
            if(mapping_arr.get(i) == color_id)
                line_components.add(line1);
            else if(mapping_arr.get(i) == n1)
                line_components.add(line2);
            else if (mapping_arr.get(i) == n2)
                line_components.add(line3);
            else
                line_components.add(line4);
        }
    }

    double time1 = 1;
    double time2 = 2;
    double time3 = 3;
    double time4 = 4;

    @Override
    public void addAnimation(float x, float y, AnchorPane gp, int scores){
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

        if(scores/5 == 0){   //increase the speed of the obstacle every 5 points
            time1 -= 0.05;
            time2 -= 0.05;
            time3 -= 0.05;
            time4 -= 0.05;
        }

        KeyFrame frame1 = new KeyFrame(Duration.ZERO, arc1_val1, arc2_val1, arc3_val1, arc4_val1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(time1), arc1_val2, arc2_val2, arc3_val2, arc4_val2);
        KeyFrame frame3 = new KeyFrame(Duration.seconds(time2), arc1_val3, arc2_val3, arc3_val3, arc4_val3);
        KeyFrame frame4 = new KeyFrame(Duration.seconds(time3), arc1_val4, arc2_val4, arc3_val4, arc4_val4);
        KeyFrame frame5 = new KeyFrame(Duration.seconds(time4), arc1_val5, arc2_val5, arc3_val5, arc4_val5);

        animation = new Timeline();
        animation.getKeyFrames().addAll(frame1, frame2, frame3, frame4, frame5);

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }
}
