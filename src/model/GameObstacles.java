package model;

import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
//import sun.jvm.hotspot.debugger.win32.coff.SectionHeader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameObstacles extends Shape {

    protected ArrayList<Arc> arc_components;
    protected ArrayList<Line> line_components;
    protected int obstacle_id;
    protected Timeline animation;

    public int getObstacle_id(){
        return obstacle_id;
    }

    public GameObstacles(){
        arc_components = new ArrayList<>();
        line_components = new ArrayList<>();
    }

    public void createObstacle(float x, float y, Circle start_ball){};

    public void addAnimation(float x, float y, AnchorPane gp){};

    public ArrayList<Arc> getArc_components(){
        return arc_components;
    }

    public ArrayList<Line> getLine_components(){
        return line_components;
    }

}
