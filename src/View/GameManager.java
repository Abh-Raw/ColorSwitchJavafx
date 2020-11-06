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
    private AnchorPane gp1;
    private AnchorPane gp2;
    private final static String BACKGROUND_IMAGE = "View/Resources/dark_background.jpg";
    private AnimationTimer gameTimer;
    private Circle start_ball;
    private float gravity = 95;
    private Stage menuStage;
    private boolean initial_press = false;
    private boolean jumplock = false;
    private double start_ball_pos_Y = 350.0f;
    private double start_ball_pos_X = 300.0f;
    private double start_ball_vel_Y = 0;
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
        GameObstacles obstacles = new GameObstacles(1);              //calls game Obstacles
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
        animations.obstacle1_animation(x, y, obstacles);
        return obstacles;
        //System.out.println(obstacle1.get(0).getTransforms().get(0) + " hahahahaha");
        //System.out.println(obstacle1.get(1).getTransforms().get(0) + " hahahhahaha");
        //System.out.println(obstacle1.get(2).getTransforms().get(0) + " hahahahahaha");
    }

    private GameObstacles animateObstacle2(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new GameObstacles(2);                  //calls game Obstacles
        GameAnimations animations = new GameAnimations();               //calls game animations
        Rotate rotation1 = new Rotate();
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        System.out.println(start_ball);
        obstacles.createTriangle(x, y, start_ball);
        obstacles.setComponent1_flag(0);
        obstacles.setComponent2_flag(0);
        obstacles.setComponent3_flag(0);
        obstacles.line_components.get(0).getTransforms().add(rotation1);
        obstacles.line_components.get(1).getTransforms().add(rotation2);
        obstacles.line_components.get(2).getTransforms().add(rotation3);
        gp.getChildren().addAll(obstacles.line_components);

        animations.obstacle2_animation(x, y, obstacles);
        return  obstacles;
    }

    /*
    private void animateObstacle3(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new GameObstacles();                      //calls game Obstacles
        GameAnimations animations = new GameAnimations();                   //calls game animations
        ArrayList<Shape> obstacle1 = obstacles.createParallel(x, y);
        gp.getChildren().add(obstacle1.get(0));
        animations.obstacle1_animation(gp1, (Line) obstacle1.get(0), x, y);
        gp.getChildren().add(obstacle1.get(1));
        animations.obstacle1_animation(gp1, (Line) obstacle1.get(1), x, y);
    }
     */

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
        start_ball = starting.makeStartBall();
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
                if(prevObstacle != null )
                System.out.println(curObstacle.arc_components + " " + curObstacle.line_components + " " + prevObstacle.arc_components + " " + prevObstacle.line_components);
                checkCollisionObstacles();
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
        createStartBall();
        createBackGround();
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
        gamePane.getChildren().add(start_ball);
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
            curPane = gp1;
        }

        if(gp2.getLayoutY()  == -200.0f){
            prevObstacle = curObstacle;
            curObstacle = queue.poll();
            curPane = gp2;
        }

        if(gp1.getLayoutY() >= 400){
            gp1.setLayoutY(-400);
            gp1.getChildren().removeAll();
            gp1.getChildren().add(background_image_gp);
            gp1_obstacle = chooseObstacleRandom(gp1, 300.0f, 150.0f);
            queue.add(gp1_obstacle);
            createColorSwitch(gp1);
            createPoints(gp1);
            if(startFlag == 1)
                startFlag = 0;
        }

        if(gp2.getLayoutY() >= 400) {
            gp2.setLayoutY(-400);
            gp2.getChildren().removeAll();
            gp2.getChildren().add(background_image_gp);
            gp2_obstacle = chooseObstacleRandom(gp2, 300.0f, 150.0f);
            queue.add(gp2_obstacle);
            createColorSwitch(gp2);
            createPoints(gp2);
        }

    }

    private GameObstacles chooseObstacleRandom(AnchorPane gp, float x, float y){     //creates random obstacles

        Random chooseObstacle = new Random();
        int obstacle_id = chooseObstacle.nextInt(2);
        if(obstacle_id==0){
            return animateObstacle1(gp, x, y);
        }

        else {
           return animateObstacle2(gp, x, y);
        }
        /*
        else{
            animateObstacle3(gp, x, y);
        }

         */
        //GameObstacles obstacles = animateObstacle2(gp, x ,y);
    }

    private void checkCollisionObstacles() {
        Shape intersect1_cur, intersect2_cur, intersect3_cur, intersect4_cur, intersect1_prev, intersect2_prev, intersect3_prev, intersect4_prev;
        if (curObstacle.getObstacle_id() == 1) {
            intersect1_cur = Shape.intersect(start_ball, curObstacle.arc_components.get(0));
            intersect2_cur = Shape.intersect(start_ball, curObstacle.arc_components.get(1));
            intersect3_cur = Shape.intersect(start_ball, curObstacle.arc_components.get(2));
            intersect4_cur = Shape.intersect(start_ball, curObstacle.arc_components.get(3));

            if (((intersect1_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.arc_components.get(0).getStroke() && start_ball.getFill() != curObstacle.arc_components.get(0).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect2_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.arc_components.get(1).getStroke() && start_ball.getFill() != curObstacle.arc_components.get(1).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if (((intersect3_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.arc_components.get(2).getStroke() && start_ball.getFill() != curObstacle.arc_components.get(2).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect4_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.arc_components.get(3).getStroke() && start_ball.getFill() != curObstacle.arc_components.get(3).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            }

        } else if (curObstacle.getObstacle_id() == 2) {
            intersect1_cur = Shape.intersect(start_ball, curObstacle.line_components.get(0));
            intersect2_cur = Shape.intersect(start_ball, curObstacle.line_components.get(1));
            intersect3_cur = Shape.intersect(start_ball, curObstacle.line_components.get(2));

            if (((intersect1_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.line_components.get(0).getStroke() && start_ball.getFill() != curObstacle.line_components.get(0).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect2_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.line_components.get(1).getStroke() && start_ball.getFill() != curObstacle.line_components.get(1).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if (((intersect3_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.line_components.get(2).getStroke() && start_ball.getFill() != curObstacle.line_components.get(2).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            }
        }

        if (prevObstacle != null && prevObstacle.getObstacle_id() == 1) {
            intersect1_prev = Shape.intersect(start_ball, prevObstacle.arc_components.get(0));
            intersect2_prev = Shape.intersect(start_ball, prevObstacle.arc_components.get(1));
            intersect3_prev = Shape.intersect(start_ball, prevObstacle.arc_components.get(2));
            intersect4_prev = Shape.intersect(start_ball, prevObstacle.arc_components.get(3));

            if (((intersect1_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.arc_components.get(0).getStroke() && start_ball.getFill() != curObstacle.arc_components.get(0).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect2_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.arc_components.get(1).getStroke() && start_ball.getFill() != curObstacle.arc_components.get(1).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if (((intersect3_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.arc_components.get(2).getStroke() && start_ball.getFill() != curObstacle.arc_components.get(2).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect4_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.arc_components.get(3).getStroke() && start_ball.getFill() != curObstacle.arc_components.get(3).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            }
        }

        else if (prevObstacle != null && prevObstacle.getObstacle_id() == 2) {
            intersect1_prev = Shape.intersect(start_ball, prevObstacle.line_components.get(0));
            intersect2_prev = Shape.intersect(start_ball, prevObstacle.line_components.get(1));
            intersect3_prev = Shape.intersect(start_ball, prevObstacle.line_components.get(2));

            if (((intersect1_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.line_components.get(0).getStroke() && start_ball.getFill() != curObstacle.line_components.get(0).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect2_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.line_components.get(1).getStroke() && start_ball.getFill() != curObstacle.line_components.get(1).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if (((intersect3_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.line_components.get(2).getStroke() && start_ball.getFill() != curObstacle.line_components.get(2).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
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
