package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Obstacle_2 extends GameObstacles {
    public Obstacle_2(){
        super();
        this.obstacle_id = 2;
    }

    @Override

    public void createObstacle(float x, float y, Circle start_ball){
        Line line1 = new Line(x - 65.0f, y - (130 * Math.sqrt(3)) / 6, x + 65, y - (130 * Math.sqrt(3)) / 6);
        Line line2 = new Line(x + 65, y - (130 * Math.sqrt(3)) / 6, x, y + (130 * Math.sqrt(3)) / 3);
        Line line3 = new Line(x, y + (130 * Math.sqrt(3)) / 3, x - 65.0f, y - (130 * Math.sqrt(3)) / 6);

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

        line1.setStroke(start_ball.getFill());
        line1.setStrokeWidth(12.0f);
        line2.setStrokeWidth(12.0f);
        line3.setStrokeWidth(12.0f);

        if(n1 == 0)
            line2.setStroke(Color.BLUE);
        else if(n1 == 1)
            line2.setStroke(Color.RED);
        else if(n1 == 2)
            line2.setStroke(Color.GREEN);
        else
            line2.setStroke(Color.YELLOW);

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
                line_components.add(line1);
            else if(mapping_arr.get(i) == n1)
                line_components.add(line2);
            else
                line_components.add(line3);
        }
    }

    public void addAnimation(float x, float y, AnchorPane gp){
        Rotate rotation1 = (Rotate) line_components.get(0).getTransforms().get(0);
        Rotate rotation2 = (Rotate) line_components.get(1).getTransforms().get(0);
        Rotate rotation3 = (Rotate) line_components.get(2).getTransforms().get(0);

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
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1.9), line1_val2, line2_val2, line3_val2);
        KeyFrame frame3 = new KeyFrame(Duration.seconds(3.8), line1_val3, line2_val3, line3_val3);
        KeyFrame frame4 = new KeyFrame(Duration.seconds(5.7), line1_val4, line2_val4, line3_val4);

        animation = new Timeline();
        animation.getKeyFrames().addAll(frame1, frame2, frame3, frame4);

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }
}
