package model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
//import sun.jvm.hotspot.debugger.win32.coff.SectionHeader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class GameObstacles extends Shape {

    public ArrayList<Arc> arc_components;
    public ArrayList<Line> line_components;
    private int obstacle_id;

    public int getObstacle_id(){
        return obstacle_id;
    }


    public GameObstacles(int obstacle_id){
        arc_components = new ArrayList<>();
        line_components = new ArrayList<>();
        this.obstacle_id = obstacle_id;
    }

    public GameObstacles(){
        arc_components = new ArrayList<>();
        line_components = new ArrayList<>();
    }

    public void createObstacle(float x, float y){};

    public void createCircle(float x, float y){
        Arc arc1 = new Arc(x, y, 75.0f, 75.0f, 0, 90);
        Arc arc2 = new Arc(x, y, 75.0f, 75.0f, 90, 90);
        Arc arc3 = new Arc(x, y, 75.0f, 75.0f, 180, 90);
        Arc arc4 = new Arc(x, y, 75.0f, 75.0f, 270, 90);


        arc1.setFill(Color.BLUE);
        arc1.setStroke(Color.BLUE);
        arc1.setStrokeWidth(15.0f);
        arc1.setStrokeType(StrokeType.INSIDE);
        arc1.setType(ArcType.OPEN);

        arc2.setFill(Color.RED);
        arc2.setStroke(Color.RED);
        arc2.setStrokeWidth(15.0f);
        arc2.setStrokeType(StrokeType.INSIDE);
        arc2.setType(ArcType.OPEN);

        arc3.setFill(Color.GREEN);
        arc3.setStroke(Color.GREEN);
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

    public void createTriangle(float x, float y, Circle start_ball) {
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

    public void createParallel(float x, float y){
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

        //return obstacle;
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
        System.out.println(circle + "hahahahahahhahhahahahahaha");
        return circle;
    }

    public ArrayList<Arc> makeColorSwitch( float x, float y){
        Arc arc1 = new Arc(x, y, 10.0f, 10.0f, 0, 90);
        Arc arc2 = new Arc(x, y, 10.0f, 10.0f, 90, 90);
        Arc arc3 = new Arc(x, y, 10.0f, 10.0f, 180, 90);
        Arc arc4 = new Arc(x, y, 10.0f, 10.0f, 270, 90);

        arc1.setFill(Color.BLUE);
        arc1.setStroke(Color.BLACK);
        arc1.setType(ArcType.ROUND);

        arc2.setFill(Color.RED);
        arc2.setStroke(Color.BLACK);
        arc2.setType(ArcType.ROUND);

        arc3.setFill(Color.GREEN);
        arc3.setStroke(Color.BLACK);
        arc3.setType(ArcType.ROUND);

        arc4.setFill(Color.YELLOW);
        arc4.setStroke(Color.BLACK);
        arc4.setType(ArcType.ROUND);

        ArrayList<Arc> colorChange = new ArrayList<>();
        colorChange.add(arc1);
        colorChange.add(arc2);
        colorChange.add(arc3);
        colorChange.add(arc4);

        return colorChange;

    }

    public Circle makePoints(float x, float y){
        Circle point = new Circle(x, y, 10.0f, Color.GRAY);
        point.setStroke(Color.WHITE);
        return point;
    }
}
