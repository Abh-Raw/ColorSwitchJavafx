package model;

import View.ViewManager;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class GameAnimations{
    public GameAnimations(){
    }
    public void IntroAnimation(ImageView logo, final ViewManager viewManager, final Stage IntromainStage){
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(4), logo);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);

        final FadeTransition fadeOut = new FadeTransition(Duration.seconds(4), logo);
        fadeOut.setFromValue(10.0);
        fadeOut.setToValue(0.0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fadeOut.play();
            }
        });

        fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewManager.showMainMenu(IntromainStage);
            }
        });

    }

    public void changeColor(Ball start_ball_obj){
        Random random = new Random();
        int n;
        do {
            n = random.nextInt(4);
        }while (!((n==0 && start_ball_obj.isBlue_flag() && start_ball_obj.getStart_ball().getFill() != Color.BLUE) || (n==1 && start_ball_obj.isRed_flag() && start_ball_obj.getStart_ball().getFill() != Color.RED) || (n==2 && start_ball_obj.isGreen_flag() && start_ball_obj.getStart_ball().getFill() != Color.GREEN) || (n==3 && start_ball_obj.isYellow_flag() && start_ball_obj.getStart_ball().getFill() != Color.YELLOW)));
        System.out.println(n);
        if(n==0)
            start_ball_obj.getStart_ball().setFill(Color.BLUE);
        else if(n==1)
            start_ball_obj.getStart_ball().setFill(Color.RED);
        else if(n==2)
            start_ball_obj.getStart_ball().setFill(Color.GREEN);
        else if(n==3)
            start_ball_obj.getStart_ball().setFill(Color.YELLOW);
    }
}
