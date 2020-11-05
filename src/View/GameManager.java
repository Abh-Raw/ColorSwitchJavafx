package View;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import model.GameAnimations;
import model.GameObstacles;

import java.util.*;

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
    private double color_switch_radius = 10.0f;
    private double point_radius = 10.0f;
    private GameObstacles temp1;
    private GameObstacles temp2;
    private GameObstacles curObstacle;
    private GameObstacles prevObstacle;
    private AnchorPane curPane;
    private GameObstacles gp1_obstacle;
    private GameObstacles gp2_obstacle;
    private Queue<GameObstacles> queue;
    private int startFlag = 1;

    public GameManager(){
        gamePane = new AnchorPane();
        gameStage = new Stage();
        gameScene = new Scene(gamePane, 600, 400);
        gameStage.setScene(gameScene);
        createSpaceListener();
        queue = new LinkedList<>();
    }

    private GameObstacles animateObstacle1(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new GameObstacles();              //calls game Obstacles
        GameAnimations animations = new GameAnimations();           //calls game animations
        Rotate rotation1 = new Rotate();
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        Rotate rotation4 = new Rotate();
        obstacles.createCircle(x, y);
        obstacles.setComponent1_flag(0);
        obstacles.setComponent2_flag(0);
        obstacles.setComponent3_flag(0);
        obstacles.setComponent4_flag(0);
        obstacles.arc_components.get(0).getTransforms().add(rotation1);
        obstacles.arc_components.get(1).getTransforms().add(rotation2);
        obstacles.arc_components.get(2).getTransforms().add(rotation3);
        obstacles.arc_components.get(3).getTransforms().add(rotation4);

        gp.getChildren().addAll(obstacles.arc_components);
        //gp.getChildren().add(curObstacle.getComponent1_flag());
        animations.arc_obstacle_animation(gp1, x, y, obstacles);
        return obstacles;
        //System.out.println(obstacle1.get(0).getTransforms().get(0) + " hahahahaha");
        //System.out.println(obstacle1.get(1).getTransforms().get(0) + " hahahhahaha");
        //System.out.println(obstacle1.get(2).getTransforms().get(0) + " hahahahahaha");
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
                //int sec = (int) ((curTime) / 1000000000.0 %10);
                //System.out.println(sec);
                //System.out.println(duration.toSeconds());
                //duration.add(duration);
                //System.out.println(curTime - prev_time);

                prev_time = curTime;
                time_per/=300.0;
                jump(time_per);
                start_ball.relocate(start_ball_pos_X - 10, start_ball_pos_Y);
                moveBackground();
                //System.out.println(curObstacle + " " + queue.peek() + " " + (gp1.getLayoutY() >= -200.0f  && startFlag == 0) + " " + (gp2.getLayoutY() >= -200.0f) + " " + (gp1.getLayoutY() >= 400) + " " + (gp2.getLayoutY() >= 400));

                checkCollisionColorSwitch(curPane);
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
        temp1 = chooseObstacleRandom(gp1, 300.0f, 150.0f);
        //queue.add(gp1_obstacle);
        curObstacle = temp1;
        createPoints(gp1);
        gp2.getChildren().add(background_image_gp2);
        temp2 = chooseObstacleRandom(gp2, 300.0f, 150.0f) ;
        queue.add(temp2);
        createPoints(gp2);
        gp2.setLayoutY(-400);
        createColorSwitch(gp2);
        gamePane.getChildren().addAll(gp1, gp2);
    }

    private void moveBackground(){
        curPane = gp1;
        Image background_image = new Image(BACKGROUND_IMAGE, 600, 400, false, true);
        ImageView background_image_gp = new ImageView(background_image);
        //boolean obstacle_flag_gp1;
        //boolean obstacle_flag_gp2;
        //System.out.println(start_ball.getTranslateY());

        if(start_ball_pos_Y < 150.0f){
            gp1.setLayoutY(gp1.getLayoutY() + 4);
            gp2.setLayoutY(gp2.getLayoutY() + 4);
        }

        if(gp1.getLayoutY() == -200.0f  && startFlag == 0){
            prevObstacle = curObstacle;
            curObstacle = queue.poll();
        }

        if(gp2.getLayoutY() == -200.0f){
            prevObstacle = curObstacle;
            curObstacle = queue.poll();
        }

        if(gp1.getLayoutY() >= 400){
            gp1.setLayoutY(-400);
            gp1.getChildren().removeAll();
            gp1.getChildren().add(background_image_gp);
            gp1_obstacle = chooseObstacleRandom(gp1, 300.0f, 150.0f);
            queue.add(gp1_obstacle);
            curPane = gp2;
            createColorSwitch(gp1);
            createPoints(gp1);
            if(startFlag == 1)
                startFlag = 0;
            for(int i=0; i<curPane.getChildren().size(); ++i)
                System.out.println(curPane.getChildren().get(i));
            //System.out.println(cur_pane.getChildren().get(0).getTransforms().get(0));
        }

        if(gp2.getLayoutY() >= 400) {
            gp2.setLayoutY(-400);
            gp2.getChildren().removeAll();
            gp2.getChildren().add(background_image_gp);
            gp2_obstacle = chooseObstacleRandom(gp2, 300.0f, 150.0f);
            queue.add(gp2_obstacle);
            curPane = gp1;
            createColorSwitch(gp2);
            createPoints(gp2);
            for (int i = 0; i < curPane.getChildren().size(); ++i)
                System.out.println(curPane.getChildren().get(i));
        }

    }

    private GameObstacles chooseObstacleRandom(AnchorPane gp, float x, float y){     //creates random obstacles
        /*
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

         */
        GameObstacles obstacles = new GameObstacles();
        obstacles = animateObstacle1(gp, x ,y);
        return obstacles;
    }

    private void checkCollisionColorSwitch(AnchorPane curPane){
        if(curObstacle.getComponent1_flag() == 1){
            if(start_ball_radius + arc_obstacle_radius > calcDist(start_ball_pos_Y + 10.0f, curObstacle.arc_components.get(0).getCenterY())){
                System.out.println("Arc 1 collision");
            }
        }

        else if(curObstacle.getComponent2_flag() == 1){
            if(start_ball_radius + arc_obstacle_radius > calcDist(start_ball_pos_Y + 10.0f, curObstacle.arc_components.get(1).getCenterY())){
                System.out.println("Arc 2 collision");
            }
        }

        else if(curObstacle.getComponent3_flag()==1){
            if(start_ball_radius + arc_obstacle_radius > calcDist(start_ball_pos_Y + 10.0f, curObstacle.arc_components.get(2).getCenterY())){
                System.out.println("Arc 3 collision");
            }
        }

        else if(curObstacle.getComponent4_flag() == 1){
            if(start_ball_radius + arc_obstacle_radius > calcDist(start_ball_pos_Y + 10.0f, curObstacle.arc_components.get(3).getCenterY())){
                System.out.println("Arc 4 collision");
            }
        }
    }

    private void createColorSwitch(AnchorPane gp){
        GameObstacles colorChange = new GameObstacles();
        ArrayList<Arc> colorSwitchComp = colorChange.makeColorSwitch(300.0f, 350.0f);
        gp.getChildren().addAll(colorSwitchComp);
    }

    private void createPoints(AnchorPane gp){
        GameObstacles pointShape = new GameObstacles();
        Circle points = pointShape.makePoints(300.0f, 150.0f);
        gp.getChildren().add(points);
    }

    private double calcDist(double y1, double y2){
        return Math.abs(y1 - y2);
    }
}
