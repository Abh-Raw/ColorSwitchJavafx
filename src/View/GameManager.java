package View;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import model.*;

import java.util.*;

public class GameManager {
    private static final int GAME_WIDTH = 400;
    private static final int GAME_HEIGHT = 450;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private AnchorPane gp1;
    private AnchorPane gp2;
    private final static String BACKGROUND_IMAGE = "View/Resources/dark_background.jpg";
    private AnimationTimer gameTimer;
    private ArrayList<Arc> colorSwitch;
    private Stage menuStage;
    private boolean jumplock = false;
    private GameObstacles temp1;
    private GameObstacles temp2;
    private GameObstacles curObstacle;
    private GameObstacles prevObstacle;
    private Points curPoints;
    private Points nextPoints;
    private AnchorPane curPane;
    private GameObstacles gp1_obstacle;
    private GameObstacles gp2_obstacle;
    private Queue<GameObstacles> queue_obs;
    private Ball start_ball_obj;
    private ColorSwitch colorSwitch_obj;
    private boolean firstPointFlag = true;
    private Points points_obj;
    private int startFlag = 1;
    private int score;

    public GameManager(){
        gamePane = new AnchorPane();
        gameStage = new Stage();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
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
        obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());
        obstacles.getArc_components().get(0).getTransforms().add(rotation1);
        obstacles.getArc_components().get(1).getTransforms().add(rotation2);
        obstacles.getArc_components().get(2).getTransforms().add(rotation3);
        obstacles.getArc_components().get(3).getTransforms().add(rotation4);
        gp.getChildren().addAll(obstacles.getArc_components());

        start_ball_obj.setBlue_flag(false);
        start_ball_obj.setRed_flag(false);
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        for(int i = 0; i<obstacles.getArc_components().size(); ++i){
            if(obstacles.getArc_components().get(i).getStroke() == Color.BLUE)
                start_ball_obj.setBlue_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.RED)
                start_ball_obj.setRed_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.GREEN)
                start_ball_obj.setGreen_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        obstacles.addAnimation(x, y, gp);
        return obstacles;
    }

    private GameObstacles animateObstacle2(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new Obstacle_2();                  //calls game Obstacles
        Rotate rotation1 = new Rotate();
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        //System.out.println(start_ball_obj.getStart_ball());
        obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());
        obstacles.getLine_components().get(0).getTransforms().add(rotation1);
        obstacles.getLine_components().get(1).getTransforms().add(rotation2);
        obstacles.getLine_components().get(2).getTransforms().add(rotation3);
        gp.getChildren().addAll(obstacles.getLine_components());

        start_ball_obj.setBlue_flag(false);
        start_ball_obj.setRed_flag(false);
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        for(int i = 0; i<obstacles.getLine_components().size(); ++i){
            if(obstacles.getLine_components().get(i).getStroke() == Color.BLUE)
                start_ball_obj.setBlue_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.RED)
                start_ball_obj.setRed_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.GREEN)
                start_ball_obj.setGreen_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        obstacles.addAnimation(x, y, gp);
        return  obstacles;
    }


    private GameObstacles animateObstacle3(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new Obstacle_3();                  //calls game Obstacles
        Rotate rotation1 = new Rotate();
        Rotate rotation2 = new Rotate();
        obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());
        obstacles.getLine_components().get(0).getTransforms().add(rotation1);
        obstacles.getLine_components().get(1).getTransforms().add(rotation2);
        gp.getChildren().addAll(obstacles.getLine_components());

        start_ball_obj.setBlue_flag(false);
        start_ball_obj.setRed_flag(false);
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        for(int i = 0; i<obstacles.getLine_components().size(); ++i){
            if(obstacles.getLine_components().get(i).getStroke() == Color.BLUE)
                start_ball_obj.setBlue_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.RED)
                start_ball_obj.setRed_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.GREEN)
                start_ball_obj.setGreen_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        obstacles.addAnimation(x, y, gp);
        return obstacles;
    }


    private void createSpaceListener(){
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE && !jumplock) {
                    start_ball_obj.setStart_ball_vel_Y(-70.0f);
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
        start_ball_obj.makeStartBall();
    }

    private void createGameLoop(){
        gameTimer = new AnimationTimer() {                  //Create Animation Timer object
            long prev_time = -1;
            //@Override
            public void   handle(long curTime) {
                if(prev_time == -1){
                    prev_time = curTime;
                    return;
                }

                double time_per = (curTime - prev_time) / 1000000.0;

                prev_time = curTime;
                time_per/=300.0;
                start_ball_obj.jump(time_per);
                start_ball_obj.getStart_ball().relocate(start_ball_obj.getStart_ball_pos_X() - 10, start_ball_obj.getStart_ball_pos_Y());
                moveBackground();
                checkCollisionObstacles();
                checkCollisionPoints();
                checkCollisionColorSwitch();
                //System.out.println(start_ball_obj.isBlue_flag() + " " +  start_ball_obj.isRed_flag() + " " + start_ball_obj.isGreen_flag() + " "+ start_ball_obj.isYellow_flag());
                //System.out.println(curObstacle + " "+ prevObstacle);
                //System.out.println(gp2.getLayoutY());
                System.out.println(gp1.getLayoutY() + " " + gp2.getLayoutY() + " " + start_ball_obj.getStart_ball_pos_Y());
                //System.out.println(score + " " + curObstacle.getArc_components().size());
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
        Image background_image = new Image(BACKGROUND_IMAGE, GAME_WIDTH, GAME_HEIGHT, false, true);
        ImageView background_image_gp1 = new ImageView(background_image);
        ImageView background_image_gp2 = new ImageView(background_image);
        gp1.getChildren().add(background_image_gp1);
        temp1 = chooseObstacleRandom(gp1, GAME_WIDTH/2, 200.0f);
        start_ball_obj.setBlue_flag(false);
        start_ball_obj.setRed_flag(false);
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);
        curObstacle = temp1;
        curPoints = createPoints(gp1);
        gp2.getChildren().add(background_image_gp2);
        temp2 = chooseObstacleRandom(gp2, GAME_WIDTH/2, 200.0f) ;
        queue_obs.add(temp2);
        nextPoints = createPoints(gp2);
        gp2.setLayoutY(-1 * GAME_HEIGHT);
        createColorSwitch(gp2);
        gamePane.getChildren().addAll(gp1, gp2);
        gamePane.getChildren().add(start_ball_obj.getStart_ball());
    }

    private void moveBackground(){
        curPane = gp1;
        Image background_image = new Image(BACKGROUND_IMAGE, 600, 400, false, true);
        ImageView background_image_gp = new ImageView(background_image);

        if(start_ball_obj.getStart_ball_pos_Y() < 150.0f){
            gp1.setLayoutY(gp1.getLayoutY() + 4);
            gp2.setLayoutY(gp2.getLayoutY() + 4);
        }

        if(gp1.getLayoutY() == -264.0f  && startFlag == 0){
            prevObstacle = curObstacle;
            curObstacle = queue_obs.poll();
            curPoints = nextPoints;
            //curPane = gp1;
        }

        if(gp2.getLayoutY()  == -264.0f){
            prevObstacle = curObstacle;
            curObstacle = queue_obs.poll();
            curPoints = nextPoints;
            //curPane = gp2;
        }

        if(gp1.getLayoutY() >= GAME_HEIGHT){
            gp1.setLayoutY(-1 * GAME_HEIGHT);
            gp1.getChildren().removeAll();
            gp1.getChildren().add(background_image_gp);
            gp1_obstacle = chooseObstacleRandom(gp1, GAME_WIDTH/2, 200.0f);
            nextPoints = createPoints(gp1);
            queue_obs.add(gp1_obstacle);
            createColorSwitch(gp1);
            if(startFlag == 1)
                startFlag = 0;
            curPane = gp2;
        }

        if(gp2.getLayoutY() >= GAME_HEIGHT) {
            gp2.setLayoutY(-1 * GAME_HEIGHT);
            gp2.getChildren().removeAll();
            gp2.getChildren().add(background_image_gp);
            gp2_obstacle = chooseObstacleRandom(gp2, GAME_WIDTH/2, 200.0f);
            nextPoints = createPoints(gp2);
            queue_obs.add(gp2_obstacle);
            createColorSwitch(gp2);
            curPane = gp1;
        }

    }

    private GameObstacles chooseObstacleRandom(AnchorPane gp, float x, float y){     //creates random obstacles

        Random chooseObstacle = new Random();
        int obstacle_id = chooseObstacle.nextInt(3);
        if(obstacle_id==0){
            return animateObstacle1(gp, x, y);
        }

        else if(obstacle_id==1) {
           return animateObstacle2(gp, x, y);
        }

        else if(obstacle_id==2){
            return animateObstacle3(gp, x, y);
        }

         return null;
        //GameObstacles obstacles = animateObstacle2(gp, x ,y);
    }

    private void checkCollisionObstacles() {
        Shape intersect = null;
        if(curObstacle.getArc_components().size() != 0) {
            for (int i = 0; i < curObstacle.getArc_components().size(); ++i) {
                intersect = Shape.intersect(start_ball_obj.getStart_ball(), curObstacle.getArc_components().get(i));
                if ((intersect.getBoundsInLocal().getWidth() != -1) && start_ball_obj.getStart_ball().getFill() != curObstacle.getArc_components().get(i).getStroke() && start_ball_obj.getStart_ball().getFill() != curObstacle.getArc_components().get(i).getFill()) {
                    System.out.println("Game Over");
                    //gameStage.close();
                    //gameTimer.stop();
                }
            }
        }

        if(curObstacle.getLine_components().size() != 0) {
            for (int i = 0; i < curObstacle.getLine_components().size(); ++i) {
                intersect = Shape.intersect(start_ball_obj.getStart_ball(), curObstacle.getLine_components().get(i));
                if ((intersect.getBoundsInLocal().getWidth() != -1) && start_ball_obj.getStart_ball().getFill() != curObstacle.getLine_components().get(i).getStroke() && start_ball_obj.getStart_ball().getFill() != curObstacle.getLine_components().get(i).getFill()) {
                    System.out.println("Game Over");
                    //gameStage.close();
                    //gameTimer.stop();
                }
            }
        }

        if(prevObstacle != null) {
            if(prevObstacle.getArc_components().size() != 0) {
                for (int i = 0; i < prevObstacle.getArc_components().size(); ++i) {
                    intersect = Shape.intersect(start_ball_obj.getStart_ball(), prevObstacle.getArc_components().get(i));
                    if ((intersect.getBoundsInLocal().getWidth() != -1) && start_ball_obj.getStart_ball().getFill() != prevObstacle.getArc_components().get(i).getStroke() && start_ball_obj.getStart_ball().getFill() != prevObstacle.getArc_components().get(i).getFill()) {
                        System.out.println("Game Over");
                        //gameStage.close();
                        //gameTimer.stop();
                    }
                }
            }

            if(prevObstacle.getLine_components().size() != 0) {
                for (int i = 0; i < prevObstacle.getArc_components().size(); ++i) {
                    intersect = Shape.intersect(start_ball_obj.getStart_ball(), prevObstacle.getLine_components().get(i));
                    if ((intersect.getBoundsInLocal().getWidth() != -1) && start_ball_obj.getStart_ball().getFill() != prevObstacle.getLine_components().get(i).getStroke() && start_ball_obj.getStart_ball().getFill() != prevObstacle.getLine_components().get(i).getFill()) {
                        System.out.println("Game Over");
                      //gameStage.close();
                      //gameTimer.stop();
                    }
                }
            }
        }

    }

    private void checkCollisionPoints(){
        Shape intersect;
        //System.out.println(points_obj.getFlag());
        if(curPoints.getFlag() == true){
            intersect = Shape.intersect(start_ball_obj.getStart_ball(), curPoints.getPoint());
            //System.out.println((intersect.getBoundsInLocal().getWidth() != -1));
            if(intersect.getBoundsInLocal().getWidth() != -1){
                ++score;
                curPoints.setFlag(false);
                curPoints.getPoint().setOpacity(0);
            }
        }
    }

    private void checkCollisionColorSwitch(){
        Shape intersect;
        GameAnimations colorAnimation = new GameAnimations();
        //System.out.println(points_obj.getFlag());
        if(colorSwitch_obj.getCs_flag() == true){
            intersect = Shape.intersect(start_ball_obj.getStart_ball(), colorSwitch.get(2));
            //System.out.println((intersect.getBoundsInLocal().getWidth() != -1));
            if(intersect.getBoundsInLocal().getWidth() != -1){
                colorAnimation.changeColor(start_ball_obj);
                colorSwitch_obj.setCs_flag(false);
                for(int i=0; i<colorSwitch.size(); ++i){
                    colorSwitch.get(i).setOpacity(0);
                }
            }
        }
    }



    private void createColorSwitch(AnchorPane gp){
        colorSwitch_obj = new ColorSwitch();
        colorSwitch_obj.setCs_flag(true);
        colorSwitch = colorSwitch_obj.makeColorSwitch(GAME_WIDTH/2, GAME_HEIGHT - 20.0f);
        gp.getChildren().addAll(colorSwitch);
    }

    private Points createPoints(AnchorPane gp){
        points_obj = new Points();
        points_obj.setFlag(true);
        points_obj.makePoints(GAME_WIDTH/2, 200.0f);
        gp.getChildren().add(points_obj.getPoint());
        return points_obj;
    }
}
