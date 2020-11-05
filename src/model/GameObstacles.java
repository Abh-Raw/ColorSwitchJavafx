package model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
//import sun.jvm.hotspot.debugger.win32.coff.SectionHeader;

import java.util.ArrayList;
import java.util.Random;

public class GameObstacles extends Shape {

    private int component1_flag;
    private int component2_flag;
    private int component3_flag;
    private int component4_flag;
    public ArrayList<Arc> arc_components;
    public ArrayList<Line> line_components;

    public GameObstacles(){
        arc_components = new ArrayList<>();
        line_components = new ArrayList<>();
    }

    public GameObstacles(ArrayList<Integer> flag_list, ArrayList<Arc> shape_components){
        component1_flag = flag_list.get(0);
        component2_flag = flag_list.get(1);
        component3_flag = flag_list.get(2);
        component4_flag = flag_list.get(3);
        arc_components = shape_components;
    }

    public void setComponent1_flag(int val){
        component1_flag = val;
    }

    public int getComponent1_flag(){
        return component1_flag;
    }

    public void setComponent2_flag(int val){
        component2_flag = val;
    }

    public int getComponent2_flag(){
        return component2_flag;
    }

    public void setComponent3_flag(int val){
        component3_flag = val;
    }

    public int getComponent3_flag(){
        return component3_flag;
    }

    public void setComponent4_flag(int val){
        component4_flag = val;
    }

    public int getComponent4_flag(){
        return component4_flag;
    }

    public void createCircle(float x, float y){
        Arc arc1 = new Arc(x, y, 60.0f, 60.0f, 0, 90);
        Arc arc2 = new Arc(x, y, 60.0f, 60.0f, 90, 90);
        Arc arc3 = new Arc(x, y, 60.0f, 60.0f, 180, 90);
        Arc arc4 = new Arc(x, y, 60.0f, 60.0f, 270, 90);


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

        arc4.setFill(Color.TRANSPARENT);
        arc4.setStroke(Color.YELLOW);
        arc4.setStrokeWidth(10.0f);
        arc4.setType(ArcType.OPEN);

        arc_components.add(arc1);
        arc_components.add(arc2);
        arc_components.add(arc3);
        arc_components.add(arc4);
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
