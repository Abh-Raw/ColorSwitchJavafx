package model;
//path class - arcTo()
import com.sun.javafx.scene.paint.GradientUtils;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

public class Points{
    private boolean flag;
    private SVGPath point;

    public SVGPath getPoint(){
        return point;
    }

    public void makePoints(float x, float y){
        point = new SVGPath();
        point.setContent("m23.363 8.584-7.378-1.127-3.307-7.044c-.247-.526-1.11-.526-1.357 0l-3.306 7.044-7.378 1.127c-.606.093-.848.83-.423 1.265l5.36 5.494-1.267 7.767c-.101.617.558 1.08 1.103.777l6.59-3.642 6.59 3.643c.54.3 1.205-.154 1.103-.777l-1.267-7.767 5.36-5.494c.425-.436.182-1.173-.423-1.266z");
        point.setFill(Color.WHITE);
        point.setLayoutX(x-10);
        point.setLayoutY(y-10);
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public boolean getFlag(){
        return flag;
    }
}
