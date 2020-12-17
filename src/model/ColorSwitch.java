package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;

public class ColorSwitch {
    private boolean cs_flag;

    public ArrayList<Arc> makeColorSwitch(float x, float y){
        Arc arc1 = new Arc(x, y, 10.0f, 10.0f, 0, 90);
        Arc arc2 = new Arc(x, y, 10.0f, 10.0f, 90, 90);
        Arc arc3 = new Arc(x, y, 10.0f, 10.0f, 180, 90);
        Arc arc4 = new Arc(x, y, 10.0f, 10.0f, 270, 90);

        arc1.setFill(Color.TURQUOISE);
        //arc1.setStroke(Color.BLACK);
        arc1.setType(ArcType.ROUND);

        arc2.setFill(Color.DEEPPINK);
        //arc2.setStroke(Color.BLACK);
        arc2.setType(ArcType.ROUND);

        arc3.setFill(Color.DARKVIOLET);
        //arc3.setStroke(Color.BLACK);
        arc3.setType(ArcType.ROUND);

        arc4.setFill(Color.YELLOW);
        //arc4.setStroke(Color.BLACK);
        arc4.setType(ArcType.ROUND);

        ArrayList<Arc> colorChange = new ArrayList<>();
        colorChange.add(arc1);
        colorChange.add(arc2);
        colorChange.add(arc3);
        colorChange.add(arc4);

        return colorChange;
    }

    public void setCs_flag(boolean cs_flag){
        this.cs_flag = cs_flag;
    }

    public boolean getCs_flag(){
        return cs_flag;
    }
}
