package View;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GameAnimations;
import model.Resources.GameObstacles;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private boolean isSpacePressed;
    private AnchorPane gp1;
    private AnchorPane gp2;
    private final static String BACKGROUND_IMAGE = "View/Resources/dark_background.jpg";
    private AnimationTimer gameTimer;
    private Circle start_ball;
    private float gravity = 80;
    private Stage menuStage;
    private boolean initial_press = false;
    private boolean jumplock = false;
    private double start_ball_pos_Y = 350.0f;
    private double start_ball_pos_X = 300.0f;
    private double start_ball_vel_Y = 0;
    private double start_ball_vel_X = 0;
    private double start_ball_radius = 10.0f;
    private double arc_obstacle_radius = 60.0f;


    public GameManager(){
        gamePane = new AnchorPane();
        gameStage = new Stage();
        gameScene = new Scene(gamePane, 600, 400);
        gameStage.setScene(gameScene);
        createSpaceListener();
    }

    private void animateObstacle1(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new GameObstacles();              //calls game Obstacles
        GameAnimations animations = new GameAnimations();           //calls game animations
        ArrayList<Shape> obstacle1 = obstacles.createCircle(x, y);
        gp.getChildren().add(obstacle1.get(0));
        animations.arc_obstacle_animation(gp1, (Arc)obstacle1.get(0), x, y);
        gp.getChildren().add(obstacle1.get(1));
        animations.arc_obstacle_animation(gp1, (Arc)obstacle1.get(1), x, y);
        gp.getChildren().add(obstacle1.get(2));
        animations.arc_obstacle_animation(gp1, (Arc)obstacle1.get(2), x, y);
    }

    private void animateObstacle2(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new GameObstacles();                  //calls game Obstacles
        GameAnimations animations = new GameAnimations();               //calls game animations
        ArrayList<Shape> obstacle1 = obstacles.createTriangle(x, y);
        gp.getChildren().add(obstacle1.get(0));
        animations.line_obstacle_animation(gp1, (Line) obstacle1.get(0), x, y);
        gp.getChildren().add(obstacle1.get(1));
        animations.line_obstacle_animation(gp1, (Line) obstacle1.get(1), x, y);
        gp.getChildren().add(obstacle1.get(2));
        animations.line_obstacle_animation(gp1, (Line) obstacle1.get(2), x, y);
    }

    private void animateObstacle3(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new GameObstacles();                      //calls game Obstacles
        GameAnimations animations = new GameAnimations();                   //calls game animations
        ArrayList<Shape> obstacle1 = obstacles.createParallel(x, y);
        gp.getChildren().add(obstacle1.get(0));
        animations.line_obstacle_animation(gp1, (Line) obstacle1.get(0), x, y);
        gp.getChildren().add(obstacle1.get(1));
        animations.line_obstacle_animation(gp1, (Line) obstacle1.get(1), x, y);
    }

    private void createSpaceListener(){
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE && !jumplock) {
                    start_ball_vel_Y = -80.0f;
                    jumplock = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.SPACE)
                    jumplock = false;
            }
        });
    }

    private void createStartBall(){
        GameObstacles starting = new GameObstacles();           //creates game obstacles object
        start_ball = starting.startBall();
        gamePane.getChildren().add(start_ball);
    }

    private void createGameLoop(){
        gameTimer = new AnimationTimer() {                  //Create Animation Timer object
            long prev_time = -1;
            //@Override
            public void handle(long curTime) {
                if(prev_time == -1){
                    prev_time = curTime;
                    return;
                }

                double time_per = (curTime - prev_time) / 1000000.0;
                //System.out.println((curTime-prev_time) / 40000000.0);
                //System.out.println(duration.toSeconds());
                //duration.add(duration);
                prev_time = curTime;
                time_per/=300.0;
                jump(time_per);
                start_ball.relocate(start_ball_pos_X - 10, start_ball_pos_Y);
                moveBackground();
                //checkCollision();
            }
        };
        gameTimer.start();
    }


    private void jump(double time_per){
        double dist = start_ball_vel_Y*time_per + 0.5 * gravity * Math.pow(time_per, 2);        //ball falling due to gravity
        start_ball_vel_Y += gravity * time_per;
        start_ball_pos_Y += dist;

        if (start_ball_pos_Y > 400 - 10){
            start_ball_pos_Y = 400 - 10;
            start_ball_vel_Y = 0;
        }
        //System.out.println(start_ball_pos_Y);
    }

    public void createNewGame(Stage menuStage){
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackGround();
        createStartBall();
        createGameLoop();
        gameStage.show();
}

    private void createBackGround(){
        gp1 = new AnchorPane();
        gp2 = new AnchorPane();
        Image background_image = new Image(BACKGROUND_IMAGE, 600, 400, false, true);
        ImageView background_image_gp1 = new ImageView(background_image);
        ImageView background_image_gp2 = new ImageView(background_image);
        gp1.getChildren().add(background_image_gp1);
        chooseObstacleRandom(gp1, 300.0f, 150.0f);
        gp2.getChildren().add(background_image_gp2);
        chooseObstacleRandom(gp2, 300.0f, 150.0f);
        gp2.setLayoutY(-400);
        gamePane.getChildren().addAll(gp1, gp2);
    }

    private void moveBackground(){
        AnchorPane cur_pane = gp1;
        Image background_image = new Image(BACKGROUND_IMAGE, 600, 400, false, true);
        ImageView background_image_gp = new ImageView(background_image);
        //boolean obstacle_flag_gp1;
        //boolean obstacle_flag_gp2;
        //System.out.println(start_ball.getTranslateY());
        if(start_ball_pos_Y < 150.0f){
            gp1.setLayoutY(gp1.getLayoutY() + 4);
            gp2.setLayoutY(gp2.getLayoutY() + 4);
        }


        if(gp1.getLayoutY() >= 400){
            gp1.setLayoutY(-400);
            for(int i=0; i < gp1.getChildren().size(); ++i)
                System.out.println(gp1.getChildren().get(i));
            gp1.getChildren().removeAll();
            gp1.getChildren().add(background_image_gp);
            chooseObstacleRandom(gp1, 300.0f, 150.0f);
            cur_pane = gp2;
            //for(int i=0; i<cur_pane.getChildren().size(); ++i)
                //System.out.println(cur_pane.getChildren().get(i));
        }

        if(gp2.getLayoutY() >= 400){
            gp2.setLayoutY(-400);
            gp2.getChildren().removeAll();
            gp2.getChildren().add(background_image_gp);
            chooseObstacleRandom(gp2, 300.0f, 150.0f);
            cur_pane = gp1;
            for(int i=0; i<cur_pane.getChildren().size(); ++i)
                System.out.println(cur_pane.getChildren().get(i));
        }
    }

    private void chooseObstacleRandom(AnchorPane gp, float x, float y){     //creates random obstacles

        Random chooseObstacle = new Random();
        int obstacle_id = chooseObstacle.nextInt(3);

        if(obstacle_id==0){
            animateObstacle1(gp, x, y);
        }

        else if(obstacle_id==1){
            animateObstacle2(gp, x, y);
        }

        else{
            animateObstacle3(gp, x, y);
        }
    }

    private void checkCollision(){
        if(start_ball_radius + arc_obstacle_radius > calcDist(start_ball_pos_Y + 10.0f, 150.0f)){
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double calcDist(double y1, double y2){
        return Math.abs(y1 - y2);
    }
}
