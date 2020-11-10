package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Points{
    private boolean flag;

    public Circle makePoints(float x, float y){
        Circle point = new Circle(x, y, 10.0f, Color.GRAY);
        point.setStroke(Color.WHITE);
        return point;
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public boolean getFlag(){
        return flag;
    }
}
