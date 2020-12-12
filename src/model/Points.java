package model;
//path class - arcTo()
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Points{
    private boolean flag;
    private Circle point;

    public Circle getPoint(){
        return point;
    }

    public void makePoints(float x, float y){
        Circle point = new Circle(x, y, 10.0f, Color.GRAY);
        point.setStroke(Color.WHITE);
        this.point = point;
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public boolean getFlag(){
        return flag;
    }
}
