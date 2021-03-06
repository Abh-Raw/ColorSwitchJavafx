package model;

import data.GameData;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;
import java.util.Random;

public class Ball implements Serializable {

    private double start_ball_pos_Y;
    private double start_ball_pos_X;
    private double start_ball_vel_Y = 0;
    private double start_ball_vel_X = 0;
    private float gravity = 100;
    private transient Circle start_ball;
    private boolean blue_flag;
    private boolean red_flag;
    private boolean green_flag;
    private boolean yellow_flag;
    private int cur_color_ID;

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

    public void setStart_ball_pos_X(double start_ball_pos_X) {
        this.start_ball_pos_X = start_ball_pos_X;
    }

    public double getStart_ball_vel_X() {
        return start_ball_vel_X;
    }

    public void setStart_ball_vel_X(double start_ball_vel_X) {
        this.start_ball_vel_X = start_ball_vel_X;
    }

    public double getStart_ball_pos_Y(){
        return start_ball_pos_Y;
    }

    public int getCur_color_ID() {return cur_color_ID; }

    public void setCur_color_ID(int cur_color_ID) {this.cur_color_ID = cur_color_ID; }

    public Ball(double x, double y){
        start_ball_pos_X = x;
        start_ball_pos_Y = y;
    }

    public void setStart_ball_pos_Y(double start_ball_pos_Y) {
        this.start_ball_pos_Y = start_ball_pos_Y;
    }

    public void reconstructStartBall(GameData gameData){
        Circle circle;
        if(gameData.getStartBall().getCur_color_ID() == 0)
            circle = new Circle(200.0f, 390.0f, 10.0f, Color.TURQUOISE);
        else if(gameData.getStartBall().getCur_color_ID() == 1)
            circle = new Circle(200.0f, 390.0f, 10.0f, Color.DEEPPINK);
        else if(gameData.getStartBall().getCur_color_ID() == 2)
            circle = new Circle(200.0f, 390.0f, 10.0f, Color.DARKVIOLET);
        else
            circle = new Circle(200.0f, 390.0f, 10.0f, Color.YELLOW);

        circle.setStroke(Color.WHITE);
        start_ball = circle;
    }

    public void makeStartBall(double x, double y, float radius){

        Random random = new Random();
        int n = random.nextInt(4);
        Circle circle;
        if(n==0) {
            circle = new Circle(x, y, radius, Color.TURQUOISE);
            cur_color_ID = 0;
        }
        else if(n==1) {
            circle = new Circle(x, y, radius, Color.DEEPPINK);
            cur_color_ID = 1;
        }
        else if(n==2) {
            circle = new Circle(x, y, radius, Color.DARKVIOLET);
            cur_color_ID = 2;
        }
        else {
            circle = new Circle(x, y, radius, Color.YELLOW);
            cur_color_ID = 3;
        }


        circle.setStroke(Color.WHITE);
        start_ball = circle;
    }

    public void jump(double time_per){
        double dist1 = start_ball_vel_Y*time_per + 0.5 * gravity * Math.pow(time_per, 2);        //ball falling due to gravity
        start_ball_vel_Y += gravity * time_per;
        start_ball_pos_Y += dist1;

        double dist2 = start_ball_vel_X*time_per;
        start_ball_pos_X += dist2;

        if(start_ball_pos_X > 400 - 10 || start_ball_pos_X < 10){
            start_ball_vel_X = -1*start_ball_vel_X;
        }

        if (start_ball_pos_Y > 450 - 20){
            start_ball_pos_Y = 450 - 20;
            start_ball_vel_Y = 0;
        }
    }
}
