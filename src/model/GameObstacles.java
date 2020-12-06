package model;

import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class GameObstacles extends Shape implements Serializable {

    protected ArrayList<Arc> arc_components;
    protected ArrayList<Line> line_components;
    protected int obstacle_id;
    protected Timeline animation;


    public GameObstacles(){
        arc_components = new ArrayList<>();
        line_components = new ArrayList<>();
    }

    public int getObstacle_id() {return obstacle_id; }

    public void createObstacle(float x, float y, Circle start_ball){};

    public void addAnimation(float x, float y, AnchorPane gp){};

    public ArrayList<Arc> getArc_components(){
        return arc_components;
    }

    public ArrayList<Line> getLine_components(){
        return line_components;
    }

    public Timeline getAnimation() { return animation;}
}
