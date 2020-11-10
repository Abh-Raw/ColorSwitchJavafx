package model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Random;

public class Obstacle_3 extends GameObstacles {
    public Obstacle_3(){
        super();
        this.obstacle_id = 3;
    }

    @Override
    public void createObstacle(float x, float y, Circle start_ball){
        Line line1 = new Line(x - 30.0f, y - 65.0f, x - 30.0f, y + 65.0f);
        Line line2 = new Line(x + 30.0f, y - 65.0f, x + 30.0f, y + 65.0f);

        line1.setStrokeWidth(10.0f);
        line2.setStrokeWidth(10.0f);

        Random random = new Random();
        int n1 = random.nextInt(4);
        int n2;

        do {
            n2 = random.nextInt(3);
        }while (n1==n2);

        if(n1 == 0){
            line1.setStroke(Color.BLUE);
        }

        if(n1 == 1){
            line1.setStroke(Color.RED);
        }

        if(n1 == 2){
            line1.setStroke(Color.GREEN);
        }

        if(n2 == 0){
            line2.setStroke(Color.BLUE);
        }

        if(n2 == 1){
            line2.setStroke(Color.RED);
        }

        if(n2 == 2){
            line2.setStroke(Color.GREEN);
        }

        ArrayList<Shape> obstacle = new ArrayList<>();
        obstacle.add(line1);
        obstacle.add(line2);
    };

    @Override

    public void addAnimation(float x, float y, AnchorPane gp){

    };
}
