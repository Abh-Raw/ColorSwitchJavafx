package model;

import View.ViewManager;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class GameAnimations{
    public GameAnimations(){
    }
    public void IntroAnimation(Logo_Obstacle obstacle1, Logo_Obstacle obstacle2, Logo_Obstacle obstacle3, final ViewManager viewManager, final Stage IntromainStage){

        for(int i=0; i<obstacle2.getArc_components().size(); ++i){
            obstacle2.getArc_components().get(i).setOpacity(0);
            obstacle3.getArc_components().get(i).setOpacity(0);
        }

        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(2), obstacle1.getArc_components().get(0));
        fadeIn1.setFromValue(0.0);
        fadeIn1.setToValue(1.0);
        fadeIn1.setCycleCount(1);

        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(2), obstacle1.getArc_components().get(1));
        fadeIn2.setFromValue(0.0);
        fadeIn2.setToValue(1.0);
        fadeIn2.setCycleCount(1);

        FadeTransition fadeIn3 = new FadeTransition(Duration.seconds(2), obstacle1.getArc_components().get(2));
        fadeIn3.setFromValue(0.0);
        fadeIn3.setToValue(1.0);
        fadeIn3.setCycleCount(1);

        FadeTransition fadeIn4 = new FadeTransition(Duration.seconds(2), obstacle1.getArc_components().get(3));
        fadeIn4.setFromValue(0.0);
        fadeIn4.setToValue(1.0);
        fadeIn4.setCycleCount(1);

        FadeTransition fadeIn5 = new FadeTransition(Duration.seconds(2), obstacle2.getArc_components().get(0));
        fadeIn5.setFromValue(0.0);
        fadeIn5.setToValue(1.0);
        fadeIn5.setCycleCount(1);

        FadeTransition fadeIn6 = new FadeTransition(Duration.seconds(2), obstacle2.getArc_components().get(1));
        fadeIn6.setFromValue(0.0);
        fadeIn6.setToValue(1.0);
        fadeIn6.setCycleCount(1);

        FadeTransition fadeIn7 = new FadeTransition(Duration.seconds(2), obstacle2.getArc_components().get(2));
        fadeIn7.setFromValue(0.0);
        fadeIn7.setToValue(1.0);
        fadeIn7.setCycleCount(1);

        FadeTransition fadeIn8 = new FadeTransition(Duration.seconds(2), obstacle2.getArc_components().get(3));
        fadeIn8.setFromValue(0.0);
        fadeIn8.setToValue(1.0);
        fadeIn8.setCycleCount(1);

        FadeTransition fadeIn9 = new FadeTransition(Duration.seconds(2), obstacle3.getArc_components().get(0));
        fadeIn9.setFromValue(0.0);
        fadeIn9.setToValue(1.0);
        fadeIn9.setCycleCount(1);

        FadeTransition fadeIn10 = new FadeTransition(Duration.seconds(2), obstacle3.getArc_components().get(1));
        fadeIn10.setFromValue(0.0);
        fadeIn10.setToValue(1.0);
        fadeIn10.setCycleCount(1);

        FadeTransition fadeIn11 = new FadeTransition(Duration.seconds(2), obstacle3.getArc_components().get(2));
        fadeIn11.setFromValue(0.0);
        fadeIn11.setToValue(1.0);
        fadeIn11.setCycleCount(1);

        FadeTransition fadeIn12 = new FadeTransition(Duration.seconds(2), obstacle3.getArc_components().get(3));
        fadeIn12.setFromValue(0.0);
        fadeIn12.setToValue(1.0);
        fadeIn12.setCycleCount(1);

        final ParallelTransition parallelTransition1 = new ParallelTransition();
        final ParallelTransition parallelTransition2 = new ParallelTransition();
        final ParallelTransition parallelTransition3 = new ParallelTransition();

        parallelTransition1.getChildren().addAll(fadeIn1, fadeIn2, fadeIn3, fadeIn4);
        parallelTransition2.getChildren().addAll(fadeIn5, fadeIn6, fadeIn7, fadeIn8);
        parallelTransition3.getChildren().addAll(fadeIn9, fadeIn10, fadeIn11, fadeIn12);

        parallelTransition1.play();

        parallelTransition1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parallelTransition2.play();
            }
        });

        parallelTransition2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parallelTransition3.play();
            }
        });

        parallelTransition3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewManager.showMainMenu(IntromainStage);
            }
        });

    }

    public void collisionAnimationPoint(final AnchorPane gp){
        final Points[] collisionStars = new Points[5];
        for(int i=0; i<5; ++i){
            collisionStars[i] = new Points();
        }
        collisionStars[0].makePoints(200.0f, 200.0f - 15.0f);
        collisionStars[1].makePoints(200.0f + 15.0f, 200.0f);
        collisionStars[2].makePoints(200.0f + 10.608f, 200.0f + 10.608f);
        collisionStars[3].makePoints(200.0f - 10.608f, 200.0f + 10.608f);
        collisionStars[4].makePoints(200.0f - 15.0f, 200.0f);

        for(int i=0; i<5; ++i){
            collisionStars[i].getPoint().setScaleX(0.5);
            collisionStars[i].getPoint().setScaleY(0.5);
            collisionStars[i].getPoint().setFill(Color.YELLOW);
            gp.getChildren().addAll(collisionStars[i].getPoint());
        }

       Duration time = Duration.millis(700);

        TranslateTransition star_1_translate = new TranslateTransition(time, collisionStars[0].getPoint());
        star_1_translate.setByY(-15.0f);

        FadeTransition star_1_fade = new FadeTransition(time, collisionStars[0].getPoint());
        star_1_fade.setFromValue(0);
        star_1_fade.setToValue(1);

        TranslateTransition star_2_translate = new TranslateTransition(time, collisionStars[1].getPoint());
        star_2_translate.setByX(15.0f);

        FadeTransition star_2_fade = new FadeTransition(time, collisionStars[1].getPoint());
        star_1_fade.setFromValue(0);
        star_1_fade.setToValue(1);

        TranslateTransition star_3_translate = new TranslateTransition(time, collisionStars[2].getPoint());
        star_3_translate.setByX(10.608f);
        star_3_translate.setByY(15.0f);

        FadeTransition star_3_fade = new FadeTransition(time, collisionStars[2].getPoint());
        star_1_fade.setFromValue(0);
        star_1_fade.setToValue(1);

        TranslateTransition star_4_translate = new TranslateTransition(time, collisionStars[3].getPoint());
        star_4_translate.setByX(-10.608f);
        star_4_translate.setByY(15.0f);

        FadeTransition star_4_fade = new FadeTransition(time, collisionStars[3].getPoint());
        star_1_fade.setFromValue(0);
        star_1_fade.setToValue(1);

        TranslateTransition star_5_translate = new TranslateTransition(time, collisionStars[4].getPoint());
        star_5_translate.setByX(-15.0f);

        FadeTransition star_5_fade = new FadeTransition(time, collisionStars[4].getPoint());
        star_1_fade.setFromValue(0);
        star_1_fade.setToValue(1);

        ParallelTransition parallelTransition = new ParallelTransition(star_1_translate, star_2_translate, star_3_translate, star_4_translate, star_5_translate, star_1_fade, star_2_fade, star_3_fade, star_4_fade, star_5_fade);
        parallelTransition.play();

        parallelTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for(int i=0; i<5; ++i){
                    collisionStars[i].getPoint().setOpacity(0);
                }
            }
        });
    }

    public void collisionAnimationObstacle(final AnchorPane gp, Ball start_ball_obj){
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
