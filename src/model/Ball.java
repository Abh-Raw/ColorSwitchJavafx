package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ball {

    private double start_ball_pos_Y = 350.0f;
    private double start_ball_pos_X = 300.0f;
    private double start_ball_vel_Y = 0;
    private double start_ball_vel_X = 0;
    private float gravity = 95;

    public void setStart_ball_vel_Y(double z){
        start_ball_vel_Y = z;
    }

    public double getStart_ball_pos_X(){
        return start_ball_pos_X;
    }

    public double getStart_ball_pos_Y(){
        return start_ball_pos_Y;
    }

    public Circle makeStartBall(){

        Random random = new Random();
        int n = random.nextInt(4);
        Circle circle;
        if(n==0)
            circle = new Circle(300.0f, 350.0f, 10.0f, Color.RED);
        else if(n==1)
            circle = new Circle(300.0f, 350.0f, 10.0f, Color.BLUE);
        else if(n==2)
            circle = new Circle(300.0f, 350.0f, 10.0f, Color.GREEN);
        else
            circle = new Circle(300.0f, 350.0f, 10.0f, Color.YELLOW);


        circle.setStroke(Color.WHITE);
        return circle;
    }

    public void jump(double time_per){
        double dist = start_ball_vel_Y*time_per + 0.5 * gravity * Math.pow(time_per, 2);        //ball falling due to gravity
        start_ball_vel_Y += gravity * time_per;
        start_ball_pos_Y += dist;

        if (start_ball_pos_Y > 400 - 10){
            start_ball_pos_Y = 400 - 10;
            start_ball_vel_Y = 0;
        }
    }
}
