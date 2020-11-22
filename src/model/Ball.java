package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ball {

    private double start_ball_pos_Y = 390.0f;
    private double start_ball_pos_X = 200.0f;
    private double start_ball_vel_Y = 0;
    private float gravity = 100;
    private Circle start_ball;
    private boolean blue_flag;
    private boolean red_flag;
    private boolean green_flag;
    private boolean yellow_flag;

    public Circle getStart_ball(){
        return start_ball;
    }

    public boolean isBlue_flag(){
        return blue_flag;
    }

    public void setBlue_flag(boolean blue_flag){
        this.blue_flag = blue_flag;
    }

    public boolean isRed_flag(){
        return red_flag;
    }

    public void setRed_flag(boolean red_flag){
        this.red_flag = red_flag;
    }

    public boolean isGreen_flag(){
        return green_flag;
    }

    public void setGreen_flag(boolean green_flag){
        this.green_flag = green_flag;
    }

    public boolean isYellow_flag(){
        return yellow_flag;
    }

    public void setYellow_flag(boolean yellow_flag){
        this.yellow_flag = yellow_flag;
    }

    public void setStart_ball_vel_Y(double z){
        start_ball_vel_Y = z;
    }

    public double getStart_ball_vel_Y() { return start_ball_vel_Y;}

    public double getStart_ball_pos_X(){
        return start_ball_pos_X;
    }

    public double getStart_ball_pos_Y(){
        return start_ball_pos_Y;
    }

    public void makeStartBall(){

        Random random = new Random();
        int n = random.nextInt(4);
        Circle circle;
        if(n==0)
            circle = new Circle(200.0f, 390.0f, 10.0f, Color.RED);
        else if(n==1)
            circle = new Circle(200.0f, 390.0f, 10.0f, Color.BLUE);
        else if(n==2)
            circle = new Circle(200.0f, 390.0f, 10.0f, Color.GREEN);
        else
            circle = new Circle(200.0f, 390.0f, 10.0f, Color.YELLOW);


        circle.setStroke(Color.WHITE);
        start_ball = circle;
    }

    public void jump(double time_per){
        double dist = start_ball_vel_Y*time_per + 0.5 * gravity * Math.pow(time_per, 2);        //ball falling due to gravity
        start_ball_vel_Y += gravity * time_per;
        start_ball_pos_Y += dist;

        if (start_ball_pos_Y > 450 - 10){
            start_ball_pos_Y = 450 - 10;
            start_ball_vel_Y = 0;
        }
    }
}
