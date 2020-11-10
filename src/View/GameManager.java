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
import model.*;

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
    private Stage menuStage;
    private boolean jumplock = false;
    private GameObstacles temp1;
    private GameObstacles temp2;
    private GameObstacles curObstacle;
    private GameObstacles prevObstacle;
    private Points curPoints;
    private AnchorPane curPane;
    private GameObstacles gp1_obstacle;
    private GameObstacles gp2_obstacle;
    private Queue<GameObstacles> queue_obs;
    private Ball start_ball_obj;
    private int startFlag = 1;

    public GameManager(){
        gamePane = new AnchorPane();
        gameStage = new Stage();
        gameScene = new Scene(gamePane, 600, 400);
        gameStage.setScene(gameScene);
        createSpaceListener();
        queue_obs = new LinkedList<>();
    }

    private GameObstacles animateObstacle1(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new Obstacle_1();             //calls game Obstacles
        Rotate rotation1 = new Rotate();
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        Rotate rotation4 = new Rotate();
        obstacles.createObstacle(x, y, start_ball);
        obstacles.getArc_components().get(0).getTransforms().add(rotation1);
        obstacles.getArc_components().get(1).getTransforms().add(rotation2);
        obstacles.getArc_components().get(2).getTransforms().add(rotation3);
        obstacles.getArc_components().get(3).getTransforms().add(rotation4);

        gp.getChildren().addAll(obstacles.getArc_components());
        obstacles.addAnimation(x, y, gp);
        return obstacles;
    }

    private GameObstacles animateObstacle2(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new Obstacle_2();                  //calls game Obstacles
        Rotate rotation1 = new Rotate();
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        System.out.println(start_ball);
        obstacles.createObstacle(x, y, start_ball);
        obstacles.getLine_components().get(0).getTransforms().add(rotation1);
        obstacles.getLine_components().get(1).getTransforms().add(rotation2);
        obstacles.getLine_components().get(2).getTransforms().add(rotation3);
        gp.getChildren().addAll(obstacles.getLine_components());
        obstacles.addAnimation(x, y, gp);
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
                    start_ball_obj.setStart_ball_vel_Y(-80.0f);
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
        start_ball_obj = new Ball();           //creates game obstacles object
        start_ball = start_ball_obj.makeStartBall();
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

                prev_time = curTime;
                time_per/=300.0;
                start_ball_obj.jump(time_per);
                start_ball.relocate(start_ball_obj.getStart_ball_pos_X() - 10, start_ball_obj.getStart_ball_pos_Y());
                moveBackground();
                System.out.println(curObstacle + " " + prevObstacle);
                checkCollisionObstacles();
            }
        };
        gameTimer.start();
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
        curObstacle = temp1;
        createPoints(gp1);
        gp2.getChildren().add(background_image_gp2);
        temp2 = chooseObstacleRandom(gp2, 300.0f, 150.0f) ;
        queue_obs.add(temp2);
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

        if(start_ball_obj.getStart_ball_pos_Y() < 150.0f){
            gp1.setLayoutY(gp1.getLayoutY() + 4);
            gp2.setLayoutY(gp2.getLayoutY() + 4);
        }

        if(gp1.getLayoutY() == -200.0f  && startFlag == 0){
            prevObstacle = curObstacle;
            curObstacle = queue_obs.poll();
            curPane = gp1;
        }

        if(gp2.getLayoutY()  == -200.0f){
            prevObstacle = curObstacle;
            curObstacle = queue_obs.poll();
            curPane = gp2;
        }

        if(gp1.getLayoutY() >= 400){
            gp1.setLayoutY(-400);
            gp1.getChildren().removeAll();
            gp1.getChildren().add(background_image_gp);
            gp1_obstacle = chooseObstacleRandom(gp1, 300.0f, 150.0f);
            queue_obs.add(gp1_obstacle);
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
            queue_obs.add(gp2_obstacle);
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
            intersect1_cur = Shape.intersect(start_ball, curObstacle.getArc_components().get(0));
            intersect2_cur = Shape.intersect(start_ball, curObstacle.getArc_components().get(1));
            intersect3_cur = Shape.intersect(start_ball, curObstacle.getArc_components().get(2));
            intersect4_cur = Shape.intersect(start_ball, curObstacle.getArc_components().get(3));

            if (((intersect1_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getArc_components().get(0).getStroke() && start_ball.getFill() != curObstacle.getArc_components().get(0).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect2_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getArc_components().get(1).getStroke() && start_ball.getFill() != curObstacle.getArc_components().get(1).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if (((intersect3_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getArc_components().get(2).getStroke() && start_ball.getFill() != curObstacle.getArc_components().get(2).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect4_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getArc_components().get(3).getStroke() && start_ball.getFill() != curObstacle.getArc_components().get(3).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            }

        } else if (curObstacle.getObstacle_id() == 2) {
            intersect1_cur = Shape.intersect(start_ball, curObstacle.getLine_components().get(0));
            intersect2_cur = Shape.intersect(start_ball, curObstacle.getLine_components().get(1));
            intersect3_cur = Shape.intersect(start_ball, curObstacle.getLine_components().get(2));

            if (((intersect1_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getLine_components().get(0).getStroke() && start_ball.getFill() != curObstacle.getLine_components().get(0).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect2_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getLine_components().get(1).getStroke() && start_ball.getFill() != curObstacle.getLine_components().get(1).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if (((intersect3_cur.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getLine_components().get(2).getStroke() && start_ball.getFill() != curObstacle.getLine_components().get(2).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            }
        }

        else if (prevObstacle != null && prevObstacle.getObstacle_id() == 1) {
            intersect1_prev = Shape.intersect(start_ball, prevObstacle.getArc_components().get(0));
            intersect2_prev = Shape.intersect(start_ball, prevObstacle.getArc_components().get(1));
            intersect3_prev = Shape.intersect(start_ball, prevObstacle.getArc_components().get(2));
            intersect4_prev = Shape.intersect(start_ball, prevObstacle.getArc_components().get(3));

            if (((intersect1_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getArc_components().get(0).getStroke() && start_ball.getFill() != curObstacle.getArc_components().get(0).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect2_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getArc_components().get(1).getStroke() && start_ball.getFill() != curObstacle.getArc_components().get(1).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if (((intersect3_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getArc_components().get(2).getStroke() && start_ball.getFill() != curObstacle.getArc_components().get(2).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect4_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getArc_components().get(3).getStroke() && start_ball.getFill() != curObstacle.getArc_components().get(3).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            }
        }

        else if (prevObstacle != null && prevObstacle.getObstacle_id() == 2) {
            intersect1_prev = Shape.intersect(start_ball, prevObstacle.getLine_components().get(0));
            intersect2_prev = Shape.intersect(start_ball, prevObstacle.getLine_components().get(1));
            intersect3_prev = Shape.intersect(start_ball, prevObstacle.getLine_components().get(2));

            if (((intersect1_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getLine_components().get(0).getStroke() && start_ball.getFill() != curObstacle.getLine_components().get(0).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if ((intersect2_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getLine_components().get(1).getStroke() && start_ball.getFill() != curObstacle.getLine_components().get(1).getFill()) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            } else if (((intersect3_prev.getBoundsInLocal().getWidth() != -1) && start_ball.getFill() != curObstacle.getLine_components().get(2).getStroke() && start_ball.getFill() != curObstacle.getLine_components() .get(2).getFill())) {
                System.out.println("Game Over");
                gameStage.close();
                gameTimer.stop();
            }
        }
    }

    private void createColorSwitch(AnchorPane gp){
        ColorSwitch color_switch_obj = new ColorSwitch();
        color_switch_obj.setCs_flag(true);
        ArrayList<Arc> colorSwitch = color_switch_obj.makeColorSwitch(300.0f, 350.0f);
        gp.getChildren().addAll(colorSwitch);
    }

    private void createPoints(AnchorPane gp){
        Points point_obj = new Points();
        point_obj.setFlag(true);
        Circle points = point_obj.makePoints(300.0f, 150.0f);
        gp.getChildren().add(points);
    }
}
