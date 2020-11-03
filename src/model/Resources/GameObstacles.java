package model.Resources;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
//import sun.jvm.hotspot.debugger.win32.coff.SectionHeader;

import java.util.ArrayList;
import java.util.Random;

public class GameObstacles extends Shape {
    public GameObstacles(){

    }

    public ArrayList<Shape> createCircle(float x, float y){
        Arc arc1 = new Arc(x, y, 60.0f, 60.0f, 0, 120);
        Arc arc2 = new Arc(x, y, 60.0f, 60.0f, 120, 120);
        Arc arc3 = new Arc(x, y, 60.0f, 60.0f, 240, 120);

        arc1.setFill(Color.TRANSPARENT);
        arc1.setStroke(Color.BLUE);
        arc1.setStrokeWidth(10.0f);
        arc1.setType(ArcType.OPEN);

        arc2.setFill(Color.TRANSPARENT);
        arc2.setStroke(Color.RED);
        arc2.setStrokeWidth(10.0f);
        arc2.setType(ArcType.OPEN);

        arc3.setFill(Color.TRANSPARENT);
        arc3.setStroke(Color.GREEN);
        arc3.setStrokeWidth(10.0f);
        arc3.setType(ArcType.OPEN);

        ArrayList<Shape> obstacle = new ArrayList<>();
        obstacle.add(arc1);
        obstacle.add(arc2);
        obstacle.add(arc3);

        return obstacle;
    }

    public ArrayList<Shape> createTriangle(float x, float y){
        Line line1 = new Line(x - 65.0f, y - (130 * Math.sqrt(3))/6, x + 65, y - (130 * Math.sqrt(3))/6);
        Line line2 = new Line(x + 65, y - (130 * Math.sqrt(3))/6, x,y + (130 * Math.sqrt(3))/3);
        Line line3 = new Line(x,y + (130 * Math.sqrt(3))/3, x - 65.0f, y - (130 * Math.sqrt(3))/6);

        line1.setStroke(Color.BLUE);
        line1.setStrokeWidth(10.0f);

        line2.setStroke(Color.RED);
        line2.setStrokeWidth(10.0f);

        line3.setStroke(Color.GREEN);
        line3.setStrokeWidth(10.0f);

        ArrayList<Shape> obstacle = new ArrayList<>();
        obstacle.add(line1);
        obstacle.add(line2);
        obstacle.add(line3);

        return obstacle;
    }

    public ArrayList<Shape> createParallel(float x, float y){
        Line line1 = new Line(x - 30.0f, y - 65.0f, x - 30.0f, y + 65.0f);
        Line line2 = new Line(x + 30.0f, y - 65.0f, x + 30.0f, y + 65.0f);

        line1.setStrokeWidth(10.0f);
        line2.setStrokeWidth(10.0f);

        Random random = new Random();
        int n1 = random.nextInt(3);
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

        return obstacle;
    }

    public Circle startBall(){

        Random random = new Random();
        int n = random.nextInt(3);
        Circle circle = new Circle();
        if(n==0)
            circle = new Circle(300.0f, 350.0f, 10.0f, Color.RED);
        else if(n==1)
            circle = new Circle(300.0f, 350.0f, 10.0f, Color.BLUE);
        else
            circle = new Circle(300.0f, 350.0f, 10.0f, Color.GREEN);


        circle.setStroke(Color.WHITE);
        return circle;
    }
}
