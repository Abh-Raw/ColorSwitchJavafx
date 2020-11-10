package model;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Random;

public class GameAnimations{
    public GameAnimations(){
    }
    public void IntroAnimation(ImageView logo){
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), logo);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(10.0);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), logo);
        fadeOut.setFromValue(10.0);
        fadeOut.setToValue(0.0);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(fadeIn, fadeOut);
        sequentialTransition.setAutoReverse(true);
        sequentialTransition.setCycleCount(Animation.INDEFINITE);
        sequentialTransition.play();

    }

    public void changeColor(Circle start_ball){
        Random random = new Random();
        int n = random.nextInt(4);
        if(n==0  )
            start_ball.setFill(Color.BLUE);
        else if(n==1 )
            start_ball.setFill(Color.RED);
        else if(n==2  )
            start_ball.setFill(Color.GREEN);
        else if(n==3 )
            start_ball.setFill(Color.YELLOW);

    }
}
