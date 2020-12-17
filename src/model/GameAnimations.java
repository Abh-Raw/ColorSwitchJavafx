package model;

import View.ViewManager;
//import java.time.Duration;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class GameAnimations{
    public GameAnimations(){
    }
    public void IntroAnimation(Text text, final ViewManager viewManager, final Stage IntromainStage){

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), text);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);

        final FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), text);
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

    public void collisionAnimation(final AnchorPane gp, Ball start_ball_obj){
        final Ball[] collisionBalls = new Ball[9];
        Random random = new Random();
        for(int i=0; i<9; ++i){
            collisionBalls[i] = new Ball(start_ball_obj.getStart_ball_pos_X(), start_ball_obj.getStart_ball_pos_Y());
            collisionBalls[i].makeStartBall(start_ball_obj.getStart_ball_pos_X(), start_ball_obj.getStart_ball_pos_Y(), 5);
            int x_vel = random.nextInt(600) - 300;
            int y_vel = random.nextInt(100) - 100;
            collisionBalls[i].setStart_ball_vel_X(x_vel);
            collisionBalls[i].setStart_ball_vel_Y(y_vel);
            gp.getChildren().add(collisionBalls[i].getStart_ball());
        }

        AnimationTimer gameTimer = new AnimationTimer() {                  //Create Animation Timer object
            long prev_time = -1;
            //@Override
            public void   handle(long curTime) {

                if (prev_time == -1) {
                    prev_time = curTime;
                    return;
                }

                double time_per = (curTime - prev_time) / 1000000.0;
                prev_time = curTime;
                time_per /= 300.0;

                for (int i = 0; i < 9; ++i) {
                    collisionBalls[i].getStart_ball().relocate( collisionBalls[i].getStart_ball_pos_X() - 10,  collisionBalls[i].getStart_ball_pos_Y());
                    collisionBalls[i].jump(time_per);
                    collisionBalls[i].getStart_ball().relocate( collisionBalls[i].getStart_ball_pos_X() - 10,  collisionBalls[i].getStart_ball_pos_Y());
                    collisionBalls[i].getStart_ball().setOpacity(collisionBalls[i].getStart_ball().getOpacity() - 0.06);
                }
                //System.out.println(curTime%1000000000);
            }
        };
        gameTimer.start();
    }

    public void changeColor(Ball start_ball_obj){
        Random random = new Random();
        int n;
        do {
            n = random.nextInt(4);
        }while (!((n==0 && start_ball_obj.isBlue_flag() && start_ball_obj.getStart_ball().getFill() != Color.TURQUOISE) || (n==1 && start_ball_obj.isRed_flag() && start_ball_obj.getStart_ball().getFill() != Color.DEEPPINK) || (n==2 && start_ball_obj.isGreen_flag() && start_ball_obj.getStart_ball().getFill() != Color.DARKVIOLET) || (n==3 && start_ball_obj.isYellow_flag() && start_ball_obj.getStart_ball().getFill() != Color.YELLOW)));
          if(n==0) {
              start_ball_obj.getStart_ball().setFill(Color.TURQUOISE);
              start_ball_obj.setCur_color_ID(0);
          }
        else if(n==1) {
              start_ball_obj.getStart_ball().setFill(Color.DEEPPINK);
              start_ball_obj.setCur_color_ID(1);
          }
        else if(n==2) {
              start_ball_obj.getStart_ball().setFill(Color.DARKVIOLET);
              start_ball_obj.setCur_color_ID(2);
          }
        else if(n==3) {
              start_ball_obj.getStart_ball().setFill(Color.YELLOW);
              start_ball_obj.setCur_color_ID(3);
          }
    }
}
