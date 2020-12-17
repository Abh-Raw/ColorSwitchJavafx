package View;

import data.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class GameManager {
    private static final int GAME_WIDTH = 400;
    private static final int GAME_HEIGHT = 450;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private AnchorPane gp1;
    private boolean pauseClicked = false;
    private AnchorPane gp2;
    private final static String BACKGROUND_IMAGE = "View/Resources/dark_background.jpg";
    private final static String PAUSE_IMAGE = "model/Resources/pause.png";
    private GameSubScenes pauseScreen;
    private GameSubScenes defeatScreen;
    private ImageView pauseButton;
    private GameButtons resumeButton;
    private GameButtons SaveAndExitButton;
    private GameButtons restartButton;
    private GameButtons exitToMainMenuButtonPause;
    private GameButtons exitToMainMenuButtonDefeat;
    private Button submitNameForHighScore = new Button("SUBMIT");
    private GameButtons SpendPointsToContinue;
    private AnimationTimer gameTimer;
    private ArrayList<Arc> colorSwitch;
    private Stage menuStage;
    private boolean spaceFirstPressed = false;
    private boolean jumplock = false;
    private TextField playerNameField;
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
    private ColorSwitch cur_colorSwitch_obj;
    private Points points_obj;
    private int startFlag = 1;
    private int score;
    private Label nameLabel;
    private boolean isPaused = false;
    private int requiredRevivingScore = 5;
    private LeaderBoard highScoresdata;
    private InfoLabel scoreDisplay;
    private HashSet<Integer> leaderBoardScores;
    private PriorityQueue<PlayerData> updatedLeaderboard;
    private GameAnimations collisionAnimations;
    private boolean obstacleCollision = false;
    private int j = 0;
    private int temp = 0;

    public GameManager(){
        gamePane = new AnchorPane();
        gameStage = new Stage();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage.setScene(gameScene);
        createSpaceListener();
        queue_obs = new LinkedList<>();
        collisionAnimations = new GameAnimations();
    }

    private void createSaveGameListener(){
        pauseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                isPaused = true;
                curObstacle.getAnimation().pause();
                if (prevObstacle != null)
                    prevObstacle.getAnimation().pause();
                BoxBlur bb = new BoxBlur();
                bb.setWidth(5);
                bb.setHeight(5);
                bb.setIterations(3);
                gp1.setEffect(bb);
                gp2.setEffect(bb);
                scoreDisplay.setEffect(bb);
                pauseButton.setEffect(bb);
                start_ball_obj.getStart_ball().setEffect(bb);
                pauseScreen.moveSubScene(-1*GAME_WIDTH);
            }
        });

        SaveAndExitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SaveFile saveFile = new SaveFile();
                GameData saveSlot = new GameData(start_ball_obj, obstacleColorList(curObstacle), obstacleAnglesList(curObstacle), curObstacle.getObstacle_id(), obstacleColorList(prevObstacle), obstacleAnglesList(prevObstacle), prevObstacle.getObstacle_id(),  curPoints.getFlag(), nextPoints.getFlag(), gp1.getLayoutY(), gp2.getLayoutY(), cur_colorSwitch_obj.getCs_flag(), score);
                //System.out.println(saveSlot.getScore());
                saveFile.saveGameData(saveSlot);
                new ViewManager().showMainMenu(gameStage);
            }
        });

        exitToMainMenuButtonPause.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new ViewManager().showMainMenu(gameStage);
            }
        });

        resumeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                isPaused = false;
                start_ball_obj.getStart_ball().setEffect(null);
                gp1.setEffect(null);
                gp2.setEffect(null);
                scoreDisplay.setEffect(null);
                pauseButton.setEffect(null );
                curObstacle.getAnimation().play();
                if(prevObstacle != null)
                    prevObstacle.getAnimation().play();
                gamePane.requestFocus();
                pauseScreen.moveSubScene(GAME_WIDTH);
                //createSpaceListener();
            }
        });

        SpendPointsToContinue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(score >= requiredRevivingScore){
                    isPaused = false;
                    score -= requiredRevivingScore;
                    requiredRevivingScore += 5;
                    scoreDisplay.setText(Integer.toString(score));
                    start_ball_obj.getStart_ball().setEffect(null);
                    gp1.setEffect(null);
                    gp2.setEffect(null);
                    scoreDisplay.setEffect(null);
                    pauseButton.setEffect(null );
                    curObstacle.getAnimation().play();
                    if(prevObstacle != null)
                        prevObstacle.getAnimation().play();
                    gamePane.requestFocus();
                    defeatScreen.moveSubScene(GAME_WIDTH);
                }
                else {
                    Text error = new Text("Required points to revive - " + Integer.toString(requiredRevivingScore));
                    error.setLayoutY(80+25+49+110 );
                    error.setLayoutX(52);
                    try{
                        error.setFont(Font.loadFont(new FileInputStream("src/model/Resources/kenvector_future.ttf"),10));
                    } catch (FileNotFoundException e) {
                        error.setFont(Font.font("Verdana",10));
                    }
                    defeatScreen.subPane.getChildren().add(error);
                }
            }
        });

        submitNameForHighScore.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(updatedLeaderboard.peek() != null) {
                    if (score > updatedLeaderboard.peek().getScore() && updatedLeaderboard.size() > 8)
                        updatedLeaderboard.poll();
                }
                updatedLeaderboard.add(new PlayerData(playerNameField.getText(), score));
                highScoresdata.setLeaderboard(updatedLeaderboard);
                SaveFile saveFile = new SaveFile();
                saveFile.savePlayerData(highScoresdata);
                new ViewManager().showMainMenu(gameStage);
            }
        });

        exitToMainMenuButtonDefeat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(updatedLeaderboard.peek()==null || (score > updatedLeaderboard.peek().getScore()) || (updatedLeaderboard.size() <= 8)){
                    nameLabel = new Label("Name: ");
                    playerNameField = new TextField("Enter name");
                    playerNameField.setPrefColumnCount(10);
                    playerNameField.setLayoutX(60);
                    playerNameField.setLayoutY(280);
                    nameLabel.setLayoutX(20);
                    nameLabel.setLayoutY(285);
                    defeatScreen.subPane.getChildren().add(nameLabel);
                    defeatScreen.subPane.getChildren().add(playerNameField);
                    submitNameForHighScore.setLayoutX(200);
                    submitNameForHighScore.setLayoutY(280);
                    defeatScreen.subPane.getChildren().add(submitNameForHighScore);
                }
                else if(score < updatedLeaderboard.peek().getScore() && updatedLeaderboard.peek()!=null){
                    new ViewManager().showMainMenu(gameStage);
                }
            }
        });

        restartButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    isPaused = false;
                    gamePane.requestFocus();
                    score = 0;
                    startFlag = 1;
                    start_ball_obj = null;
                    curObstacle = null;
                    prevObstacle = null;
                    curPoints = null;
                    nextPoints = null;
                    cur_colorSwitch_obj = null;
                    gp1 = null;
                    gp2 = null;
                    gamePane = null;
                    createNewGame(gameStage, highScoresdata);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ArrayList<Double> obstacleAnglesList(GameObstacles obstacle){
        ArrayList<Double> angleList = new ArrayList<>();

        if(obstacle.getArc_components().size()!=0) {
            for (int i = 0; i < obstacle.getArc_components().size(); ++i) {
                Rotate curRotate = (Rotate) obstacle.getArc_components().get(i).getTransforms().get(0);
                angleList.add(curRotate.getAngle());
            }
        }

        if(obstacle.getLine_components().size()!=0) {
            for (int i = 0; i < obstacle.getLine_components().size(); ++i) {
                Rotate curRotate = (Rotate)obstacle.getLine_components().get(i).getTransforms().get(0);
                angleList.add(curRotate.getAngle());
            }
        }

        return angleList;
    }

    private ArrayList<Integer> obstacleColorList(GameObstacles obstacle){
        ArrayList<Integer> colorList = new ArrayList<>();

        if(obstacle.getArc_components().size()!=0) {
            for (int i = 0; i < obstacle.getArc_components().size(); ++i) {
                if (obstacle.getArc_components().get(i).getStroke() == Color.TURQUOISE)
                    colorList.add(0);
                if (obstacle.getArc_components().get(i).getStroke() == Color.DEEPPINK)
                    colorList.add(1);
                if (obstacle.getArc_components().get(i).getStroke() == Color.DARKVIOLET)
                    colorList.add(2);
                if (obstacle.getArc_components().get(i).getStroke() == Color.YELLOW)
                    colorList.add(3);
            }
        }

        if(obstacle.getLine_components().size()!=0) {
            for (int i = 0; i < obstacle.getLine_components().size(); ++i) {
                if (obstacle.getLine_components().get(i).getStroke() == Color.TURQUOISE)
                    colorList.add(0);
                if (obstacle.getLine_components().get(i).getStroke() == Color.DEEPPINK)
                    colorList.add(1);
                if (obstacle.getLine_components().get(i).getStroke() == Color.DARKVIOLET)
                    colorList.add(2);
                if (obstacle.getLine_components().get(i).getStroke() == Color.YELLOW)
                    colorList.add(3);
            }
        }

        return colorList;
    }

    private GameObstacles animateObstacle1(AnchorPane gp, float x, float y, ArrayList<Double> anglesList, ArrayList<Integer> colorList){
        GameObstacles obstacles = new Obstacle_1();             //calls game Obstacles
        Rotate rotation1 = new Rotate();       //1 rotation obj for every component
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        Rotate rotation4 = new Rotate();
        if(anglesList == null)
            obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());  //create obj
        else
            obstacles.reconstructObstacle(x, y, anglesList, colorList);
        obstacles.getArc_components().get(0).getTransforms().add(rotation1);   //rotation obj added to every component
        obstacles.getArc_components().get(1).getTransforms().add(rotation2);
        obstacles.getArc_components().get(2).getTransforms().add(rotation3);
        obstacles.getArc_components().get(3).getTransforms().add(rotation4);
        gp.getChildren().addAll(obstacles.getArc_components());

        start_ball_obj.setBlue_flag(false);     //when we collide with colorswitch, we check next obstacle has which colors and out of those we choose a color for ball
        start_ball_obj.setRed_flag(false);      //to avoid deadlock
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        for(int i = 0; i<obstacles.getArc_components().size(); ++i){  //color components in the next obstacle are set true to avoid deadlock
            if(obstacles.getArc_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.DARKVIOLET)
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
            if(obstacles.getLine_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.DARKVIOLET)
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
            if(obstacles.getLine_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.DARKVIOLET)
                start_ball_obj.setGreen_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        obstacles.addAnimation(x, y, gp);
        return obstacles;
    }

    private GameObstacles animateObstacle4(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new Obstacle_4();

        Rotate rotation1 = new Rotate();    //1 rotation obj for every component
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        Rotate rotation4 = new Rotate();

        obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());
        obstacles.getLine_components().get(0).getTransforms().add(rotation1);
        obstacles.getLine_components().get(1).getTransforms().add(rotation2);
        obstacles.getLine_components().get(2).getTransforms().add(rotation3);
        obstacles.getLine_components().get(3).getTransforms().add(rotation4);

        gp.getChildren().addAll(obstacles.getLine_components());  //add shape to pane

        start_ball_obj.setBlue_flag(false);  //when we collide with colorswitch, we check next obstacle has which colors and out of those we choose a color for ball
        start_ball_obj.setRed_flag(false);   //to avoid deadlock
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        for(int i = 0; i<obstacles.getLine_components().size(); ++i){     //color components in the next obstacle are set true to avoid deadlock
            if(obstacles.getLine_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            if(obstacles.getLine_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            if(obstacles.getLine_components().get(i).getStroke() == Color.DARKVIOLET)
                start_ball_obj.setGreen_flag(true);
            if(obstacles.getLine_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        obstacles.addAnimation(x, y, gp);
        return obstacles;
    }

    private GameObstacles animateObstacle5(AnchorPane gp, float x, float y){
        GameObstacles obstacles = new Obstacle_5();

        Rotate rotation1 = new Rotate();       //1 rotation obj for every component
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        Rotate rotation4 = new Rotate();
        Rotate rotation5 = new Rotate();       //1 rotation obj for every component
        Rotate rotation6 = new Rotate();
        Rotate rotation7 = new Rotate();
        Rotate rotation8 = new Rotate();

        //if(anglesList == null)
        obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());  //create obj
        //else
        //  obstacles.reconstructObstacle(x, y, anglesList, colorList);

        obstacles.getArc_components().get(0).getTransforms().add(rotation1);   //rotation obj added to every component
        obstacles.getArc_components().get(1).getTransforms().add(rotation2);
        obstacles.getArc_components().get(2).getTransforms().add(rotation3);
        obstacles.getArc_components().get(3).getTransforms().add(rotation4);
        obstacles.getArc_components().get(4).getTransforms().add(rotation5);   //rotation obj added to every component
        obstacles.getArc_components().get(5).getTransforms().add(rotation6);
        obstacles.getArc_components().get(6).getTransforms().add(rotation7);
        obstacles.getArc_components().get(7).getTransforms().add(rotation8);

        gp.getChildren().addAll(obstacles.getArc_components());

        start_ball_obj.setBlue_flag(false);     //when we collide with colorswitch, we check next obstacle has which colors and out of those we choose a color for ball
        start_ball_obj.setRed_flag(false);      //to avoid deadlock
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        //color components in the next obstacle are set true to avoid deadlock --> one of the arc pairs meeting at the bottom must be the colour of the ball
        // otherwise results in a deadlock
        if(obstacles.getArc_components().get(0).getStroke() == Color.TURQUOISE)  //arc 2 is the same color of the ball and so set flag = true --> arc 2&6 will meet at the bottom and will have the same colour
            start_ball_obj.setBlue_flag(true);
        else if(obstacles.getArc_components().get(0).getStroke() == Color.DEEPPINK)
            start_ball_obj.setRed_flag(true);
        else if(obstacles.getArc_components().get(0).getStroke() == Color.DARKVIOLET)
            start_ball_obj.setGreen_flag(true);
        else if(obstacles.getArc_components().get(0).getStroke() == Color.YELLOW)
            start_ball_obj.setYellow_flag(true);

        if(obstacles.getArc_components().get(obstacles.getArc_components().size() - 1).getStroke() == Color.TURQUOISE)  //arc 4&8 will also meet at the bottom and will have the same colour so their flag is also set to be true
            start_ball_obj.setBlue_flag(true);
        else if(obstacles.getArc_components().get(obstacles.getArc_components().size() - 1).getStroke() == Color.DEEPPINK)
            start_ball_obj.setRed_flag(true);
        else if(obstacles.getArc_components().get(obstacles.getArc_components().size() - 1).getStroke() == Color.DARKVIOLET)
            start_ball_obj.setGreen_flag(true);
        else if(obstacles.getArc_components().get(obstacles.getArc_components().size() - 1).getStroke() == Color.YELLOW)
            start_ball_obj.setYellow_flag(true);

        obstacles.addAnimation(x, y, gp);
        return obstacles;
    }

    private GameObstacles animateObstacle6(AnchorPane gp, float x, float y) {
        GameObstacles obstacles = new Obstacle_6();

        Rotate rotation1 = new Rotate();       //1 rotation obj for every component
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        Rotate rotation4 = new Rotate();
        Rotate rotation5 = new Rotate();       //1 rotation obj for every component
        Rotate rotation6 = new Rotate();
        Rotate rotation7 = new Rotate();
        Rotate rotation8 = new Rotate();

        obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());  //create obj

        obstacles.getArc_components().get(0).getTransforms().add(rotation1);   //rotation obj added to every component
        obstacles.getArc_components().get(1).getTransforms().add(rotation2);
        obstacles.getArc_components().get(2).getTransforms().add(rotation3);
        obstacles.getArc_components().get(3).getTransforms().add(rotation4);
        obstacles.getArc_components().get(4).getTransforms().add(rotation5);   //rotation obj added to every component
        obstacles.getArc_components().get(5).getTransforms().add(rotation6);
        obstacles.getArc_components().get(6).getTransforms().add(rotation7);
        obstacles.getArc_components().get(7).getTransforms().add(rotation8);

        gp.getChildren().addAll(obstacles.getArc_components());

        start_ball_obj.setBlue_flag(false);     //when we collide with colorswitch, we check next obstacle has which colors and out of those we choose a color for ball
        start_ball_obj.setRed_flag(false);      //to avoid deadlock
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        for(int i = 0; i<obstacles.getArc_components().size(); ++i){  //color components in the next obstacle are set true to avoid deadlock
            if(obstacles.getArc_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.DARKVIOLET)
                start_ball_obj.setGreen_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        obstacles.addAnimation(x, y, gp);

        return obstacles;
    }

    private GameObstacles animateObstacle7(AnchorPane gp, float x, float y) {
        GameObstacles obstacles = new Obstacle_7();

        Rotate rotation1 = new Rotate();       //1 rotation obj for every component
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        Rotate rotation4 = new Rotate();
        Rotate rotation5 = new Rotate();       //1 rotation obj for every component
        Rotate rotation6 = new Rotate();
        Rotate rotation7 = new Rotate();
        Rotate rotation8 = new Rotate();
//        Rotate rotation9 = new Rotate();       //1 rotation obj for every component
//        Rotate rotation10 = new Rotate();
//        Rotate rotation11 = new Rotate();
//        Rotate rotation12 = new Rotate();

        obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());  //create obj

        obstacles.getArc_components().get(0).getTransforms().add(rotation1);   //rotation obj added to every component
        obstacles.getArc_components().get(1).getTransforms().add(rotation2);
        obstacles.getArc_components().get(2).getTransforms().add(rotation3);
        obstacles.getArc_components().get(3).getTransforms().add(rotation4);
        obstacles.getArc_components().get(4).getTransforms().add(rotation5);   //rotation obj added to every component
        obstacles.getArc_components().get(5).getTransforms().add(rotation6);
        obstacles.getArc_components().get(6).getTransforms().add(rotation7);
        obstacles.getArc_components().get(7).getTransforms().add(rotation8);
//        obstacles.getArc_components().get(8).getTransforms().add(rotation9);
//        obstacles.getArc_components().get(9).getTransforms().add(rotation10);
//        obstacles.getArc_components().get(10).getTransforms().add(rotation11);
//        obstacles.getArc_components().get(11).getTransforms().add(rotation12);

        gp.getChildren().addAll(obstacles.getArc_components());

        start_ball_obj.setBlue_flag(false);     //when we collide with colorswitch, we check next obstacle has which colors and out of those we choose a color for ball
        start_ball_obj.setRed_flag(false);      //to avoid deadlock
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        for(int i = 0; i<obstacles.getArc_components().size(); ++i){  //color components in the next obstacle are set true to avoid deadlock
            if(obstacles.getArc_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.DARKVIOLET)
                start_ball_obj.setGreen_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        obstacles.addAnimation(x, y, gp);

        return obstacles;
    }

    private GameObstacles animateObstacle8(AnchorPane gp, float x, float y) {
        GameObstacles obstacles = new Obstacle_8();                  //calls game Obstacles
        Rotate rotation1 = new Rotate();
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();

        Rotate rotation4 = new Rotate();
        Rotate rotation5 = new Rotate();
        Rotate rotation6 = new Rotate();
        Rotate rotation7 = new Rotate();


        //System.out.println(start_ball_obj.getStart_ball());
        obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());

        obstacles.getLine_components().get(0).getTransforms().add(rotation1);
        obstacles.getLine_components().get(1).getTransforms().add(rotation2);
        obstacles.getLine_components().get(2).getTransforms().add(rotation3);

        obstacles.getArc_components().get(0).getTransforms().add(rotation4);
        obstacles.getArc_components().get(1).getTransforms().add(rotation5);   //rotation obj added to every component
        obstacles.getArc_components().get(2).getTransforms().add(rotation6);
        obstacles.getArc_components().get(3).getTransforms().add(rotation7);


        gp.getChildren().addAll(obstacles.getLine_components());
        gp.getChildren().addAll(obstacles.getArc_components());

        start_ball_obj.setBlue_flag(false);
        start_ball_obj.setRed_flag(false);
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        for(int i = 0; i<obstacles.getLine_components().size(); ++i){
            if(obstacles.getLine_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.DARKVIOLET)
                start_ball_obj.setGreen_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        for(int i = 0; i<obstacles.getArc_components().size(); ++i){  //color components in the next obstacle are set true to avoid deadlock
            if(obstacles.getArc_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.DARKVIOLET)
                start_ball_obj.setGreen_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        obstacles.addAnimation(x, y, gp);
        return  obstacles;

    }

    private GameObstacles animateObstacle9(AnchorPane gp, float x, float y) {
        GameObstacles obstacles = new Obstacle_9();             //calls game Obstacles

        Rotate rotation1 = new Rotate();       //1 rotation obj for every component
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        Rotate rotation4 = new Rotate();


        obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());

        obstacles.getLine_components().get(0).getTransforms().add(rotation1);
        obstacles.getLine_components().get(1).getTransforms().add(rotation2);
        obstacles.getLine_components().get(2).getTransforms().add(rotation3);
        obstacles.getLine_components().get(3).getTransforms().add(rotation4);

        gp.getChildren().addAll(obstacles.getLine_components());

        start_ball_obj.setBlue_flag(false);
        start_ball_obj.setRed_flag(false);
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        for(int i = 0; i<obstacles.getLine_components().size(); ++i){
            if(obstacles.getLine_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.DARKVIOLET)
                start_ball_obj.setGreen_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        obstacles.addAnimation(x, y, gp);
        return  obstacles;
    }

    private GameObstacles animateObstacle10(AnchorPane gp, float x, float y) {
        GameObstacles obstacles = new Obstacle_10();             //calls game Obstacles

        Rotate rotation1 = new Rotate();       //1 rotation obj for every component
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        Rotate rotation4 = new Rotate();

        Rotate rotation5 = new Rotate();       //1 rotation obj for every component
        Rotate rotation6 = new Rotate();
        Rotate rotation7 = new Rotate();
        Rotate rotation8 = new Rotate();



        obstacles.createObstacle(x, y, start_ball_obj.getStart_ball());

        obstacles.getLine_components().get(0).getTransforms().add(rotation1);
        obstacles.getLine_components().get(1).getTransforms().add(rotation2);
        obstacles.getLine_components().get(2).getTransforms().add(rotation3);
        obstacles.getLine_components().get(3).getTransforms().add(rotation4);

        obstacles.getArc_components().get(0).getTransforms().add(rotation5);   //rotation obj added to every component
        obstacles.getArc_components().get(1).getTransforms().add(rotation6);
        obstacles.getArc_components().get(2).getTransforms().add(rotation7);
        obstacles.getArc_components().get(3).getTransforms().add(rotation8);



        gp.getChildren().addAll(obstacles.getLine_components());
        gp.getChildren().addAll(obstacles.getArc_components());

        start_ball_obj.setBlue_flag(false);
        start_ball_obj.setRed_flag(false);
        start_ball_obj.setGreen_flag(false);
        start_ball_obj.setYellow_flag(false);

        for(int i = 0; i<obstacles.getLine_components().size(); ++i){
            if(obstacles.getLine_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.DARKVIOLET)
                start_ball_obj.setGreen_flag(true);
            else if(obstacles.getLine_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }


        for(int i = 0; i<obstacles.getArc_components().size(); ++i){  //color components in the next obstacle are set true to avoid deadlock
            if(obstacles.getArc_components().get(i).getStroke() == Color.TURQUOISE)
                start_ball_obj.setBlue_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.DEEPPINK)
                start_ball_obj.setRed_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.DARKVIOLET)
                start_ball_obj.setGreen_flag(true);
            if(obstacles.getArc_components().get(i).getStroke() == Color.YELLOW)
                start_ball_obj.setYellow_flag(true);
        }

        obstacles.addAnimation(x, y, gp);
        return  obstacles;
    }


        private void createSpaceListener(){
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE && !jumplock && !isPaused ) {
                    spaceFirstPressed = true;
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
        start_ball_obj = new Ball(200.0f, 390.0f);           //creates game obstacles object
        start_ball_obj.makeStartBall(200.0f, 390.0f, 10.0f);
    }

    private void createGameLoop(){
        gameTimer = new AnimationTimer() {                  //Create Animation Timer object
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
                    start_ball_obj.getStart_ball().relocate(start_ball_obj.getStart_ball_pos_X() - 10, start_ball_obj.getStart_ball_pos_Y());
                if(!isPaused && spaceFirstPressed) {
                    start_ball_obj.jump(time_per);
                    start_ball_obj.getStart_ball().relocate(start_ball_obj.getStart_ball_pos_X() - 10, start_ball_obj.getStart_ball_pos_Y());
                    moveBackground();
                    checkCollisionObstacles();
                    checkCollisionPoints();
                    checkCollisionColorSwitch();
                    System.out.println(j + " " + temp);
                    //System.out.println(start_ball_obj.getStart_ball_pos_Y() + " " + start_ball_obj.getStart_ball_vel_Y() + " " + time_per);
                }
            }
        };
        gameTimer.start();
    }


    public void createNewGame(Stage menuStage, LeaderBoard leaderBoard) throws FileNotFoundException {
        highScoresdata = leaderBoard;
        updatedLeaderboard = highScoresdata.getLeaderboard();
        leaderBoardScores = new HashSet<>();
        Iterator<PlayerData> itr = highScoresdata.getLeaderboard().iterator();
        while(itr.hasNext()){
            PlayerData temp = itr.next();
            leaderBoardScores.add(temp.getScore());
        }
        this.menuStage = menuStage;
        this.menuStage.close();
        createStartBall();
        createBackGround();
        createScoreDisplay();
        createPauseButton();
        createSubScenes();
        createSaveGameListener();
        createGameLoop();
        gameStage.show();
}

    public void resumeGame(Stage menuStage, GameData gameData) throws FileNotFoundException {
        this.menuStage = menuStage;
        this.menuStage.hide();
        start_ball_obj = gameData.getStartBall();
        start_ball_obj.reconstructStartBall(gameData);
        createResumeGameBackground(gameData);
        score = gameData.getScore();
        createScoreDisplay();
        createPauseButton();
        createSubScenes();
        createSaveGameListener();
        createGameLoop();
        gameStage.show();
    }

    private void createPauseButton(){
        pauseButton = new ImageView(PAUSE_IMAGE);
        pauseButton.setLayoutX(GAME_WIDTH - 50);
        pauseButton.setLayoutY(15);
        gamePane.getChildren().add(pauseButton);
    }

    private void createSubScenes() throws FileNotFoundException {


        defeatScreen = new GameSubScenes(50 + GAME_WIDTH, 50, GAME_WIDTH - 100, GAME_HEIGHT - 100);
        gamePane.getChildren().add(defeatScreen);

        pauseScreen = new GameSubScenes(50 + GAME_WIDTH, 50, GAME_WIDTH - 100, GAME_HEIGHT - 100);
        gamePane.getChildren().add(pauseScreen);

        Text pauseLabel = new Text("Pause");
        pauseLabel.setLayoutX(110);
        pauseLabel.setLayoutY(55);
        pauseLabel.setFont(Font.loadFont(new FileInputStream("src/model/Resources/AlexBrush-Regular.ttf"), 30));
        pauseScreen.subPane.getChildren().add(pauseLabel);

        Text gameOverLabel = new Text("Game Over");
        gameOverLabel.setLayoutX(90);
        gameOverLabel.setLayoutY(55);
        gameOverLabel.setFont(Font.loadFont(new FileInputStream("src/model/Resources/AlexBrush-Regular.ttf"), 30));
        defeatScreen.subPane.getChildren().add(gameOverLabel);

        resumeButton = new GameButtons("RESUME");
        resumeButton.setLayoutX(55);
        resumeButton.setLayoutY(80);
        pauseScreen.subPane.getChildren().add(resumeButton);

        SpendPointsToContinue = new GameButtons("REVIVE");
        SpendPointsToContinue.setLayoutX(55);
        SpendPointsToContinue.setLayoutY(80);
        defeatScreen.subPane.getChildren().add(SpendPointsToContinue);

        exitToMainMenuButtonDefeat = new GameButtons("EXIT");
        exitToMainMenuButtonDefeat.setLayoutX(55);
        exitToMainMenuButtonDefeat.setLayoutY(80 + 25 + 49);
        defeatScreen.subPane.getChildren().add(exitToMainMenuButtonDefeat);

        restartButton = new GameButtons("RESTART");
        restartButton.setLayoutX(55);
        restartButton.setLayoutY(80 + 15 + 49);
        pauseScreen.subPane.getChildren().add(restartButton);

        exitToMainMenuButtonPause = new GameButtons("EXIT");
        exitToMainMenuButtonPause.setLayoutX(55);
        exitToMainMenuButtonPause.setLayoutY(80 + 30 + 98);
        pauseScreen.subPane.getChildren().add(exitToMainMenuButtonPause);

        SaveAndExitButton = new GameButtons("SAVE/EXIT");
        SaveAndExitButton.setLayoutX(55);
        SaveAndExitButton.setLayoutY(80 + 45 + 147);
        pauseScreen.subPane.getChildren().add(SaveAndExitButton);

        Text finalScore = new Text("Score - " + Integer.toString(score));
        finalScore.setLayoutY(80+25+49+90);
        finalScore.setLayoutX(120);
        try{
            finalScore.setFont(Font.loadFont(new FileInputStream("src/model/Resources/kenvector_future.ttf"), 10));
        } catch (FileNotFoundException e) {
            finalScore.setFont(Font.font("Verdana",23));
        }
    }

    private void createScoreDisplay(){
        scoreDisplay = new InfoLabel(Integer.toString(score));
        scoreDisplay.setLayoutX(10);
        scoreDisplay.setLayoutY(7);
        scoreDisplay.setTextFill(Color.WHITE);
        gamePane.getChildren().add(scoreDisplay);
    }

    private void createResumeGameScoreDisplay(GameData gameData){
        scoreDisplay = new InfoLabel(Integer.toString(gameData.getScore()));
        scoreDisplay.setLayoutX(10);
        scoreDisplay.setLayoutY(7);
        scoreDisplay.setTextFill(Color.WHITE);
        gamePane.getChildren().add(scoreDisplay);
    }

    private void createResumeGameBackground(GameData gamedata){
        gp1 = new AnchorPane();
        gp2 = new AnchorPane();
        Image background_image = new Image(BACKGROUND_IMAGE, GAME_WIDTH, GAME_HEIGHT, false, true);
        ImageView background_image_gp1 = new ImageView(background_image);
        ImageView background_image_gp2 = new ImageView(background_image);
        gp1.getChildren().add(background_image_gp1);
        gp2.getChildren().add(background_image_gp2);
        if(gamedata.getGp1_layout() > gamedata.getGp2_layout()){
            gp1.setLayoutY(gamedata.getGp1_layout());
            gp2.setLayoutY(gamedata.getGp2_layout()+2);
        }
        else{
            gp1.setLayoutY(gamedata.getGp2_layout());
            gp2.setLayoutY(gamedata.getGp1_layout()+2);
        }
        if(gamedata.isCurPt())
            curPoints = createPoints(gp2);
        else
            curPoints = createPoints(gp1);
        if(gamedata.iscSwitch_flag())
            cur_colorSwitch_obj = createColorSwitch(gp2);
        else
            cur_colorSwitch_obj = createColorSwitch(gp1);
        curObstacle = chooseObstacleUsingLoadData(gp1, GAME_WIDTH/2, 200.0f, gamedata.getCurObstacleAngles(), gamedata.getCurObstacleColors(), gamedata.getCurObstacleID());
        prevObstacle = chooseObstacleUsingLoadData(gp2, GAME_WIDTH/2, 200.0f, gamedata.getPrevObstacleAngles(), gamedata.getPrevObstacleColors(), gamedata.getPrevObstacleID());
        gamePane.getChildren().addAll(gp1, gp2);
        gamePane.getChildren().add(start_ball_obj.getStart_ball());
    }



    private void createBackGround(){
        gp1 = new AnchorPane();
        gp2 = new AnchorPane();
        Image background_image = new Image(BACKGROUND_IMAGE, GAME_WIDTH, GAME_HEIGHT, false, true);
        ImageView background_image_gp1 = new ImageView(background_image);
        ImageView background_image_gp2 = new ImageView(background_image);
        gp1.getChildren().add(background_image_gp1);
        curObstacle = chooseObstacleRandom(gp1, GAME_WIDTH/2, 200.0f);
        curPoints = createPoints(gp1);
        gp2.getChildren().add(background_image_gp2);
        temp2 = chooseObstacleRandom(gp2, GAME_WIDTH/2, 200.0f) ;
        queue_obs.add(temp2);
        nextPoints = createPoints(gp2);
        gp2.setLayoutY(-1 * GAME_HEIGHT);
        cur_colorSwitch_obj = createColorSwitch(gp2);
        gamePane.getChildren().addAll(gp1, gp2);
        gamePane.getChildren().add(start_ball_obj.getStart_ball());
    }

    private void moveBackground(){
        curPane = gp1;
        Image background_image = new Image(BACKGROUND_IMAGE, GAME_WIDTH, GAME_HEIGHT + 20, false, true);
        ImageView background_image_gp = new ImageView(background_image);

        if(start_ball_obj.getStart_ball_pos_Y() < GAME_HEIGHT/2){
            gp1.setLayoutY(gp1.getLayoutY() + 4);
            gp2.setLayoutY(gp2.getLayoutY() + 4);
            //start_ball_obj.setStart_ball_vel_Y(7);
        }

        if(gp1.getLayoutY() == -270.0f  && startFlag == 0){
            prevObstacle = curObstacle;
            curObstacle = queue_obs.poll();
            curPoints = nextPoints;
            //curPane = gp1;
        }

        if(gp2.getLayoutY()  == -270.0f){
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
            cur_colorSwitch_obj = createColorSwitch(gp1);
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
            cur_colorSwitch_obj = createColorSwitch(gp2);
            curPane = gp1;
        }
    }

    private GameObstacles chooseObstacleUsingLoadData(AnchorPane gp, float x, float y, ArrayList<Double> anglesList, ArrayList<Integer> colorList, int obstacle_id){
        if(obstacle_id == 1){
            return animateObstacle1(gp, x, y, anglesList, colorList);
        }
        return null;
    }

    private GameObstacles chooseObstacleRandom(AnchorPane gp, float x, float y){     //creates random obstacles

        Random chooseObstacle = new Random();
        int obstacle_id = chooseObstacle.nextInt(10)+1;
        if(obstacle_id==1){
            return animateObstacle1(gp, x, y, null, null);
        }

        else if(obstacle_id==2) {
           return animateObstacle2(gp, x, y);
        }

        else if(obstacle_id==3){
            return animateObstacle3(gp, x, y);
        }

        else if(obstacle_id==4){
            return animateObstacle4(gp, x+32.5f, y+32.5f);
        }

        else if(obstacle_id == 5){
            return animateObstacle5(gp, x, y);
        }

        else if(obstacle_id == 6){
            return animateObstacle6(gp, x, y);
        }

        else if(obstacle_id == 7){
            return animateObstacle7(gp, x, y);
        }

        else if(obstacle_id == 8){
            return animateObstacle8(gp, x, y);
        }

        else if(obstacle_id == 9){
            return animateObstacle9(gp, x, y);
        }

        else if(obstacle_id == 10){
            return animateObstacle10(gp, x, y);
        }

         return null;
    }

    private void checkCollisionObstacles() {
        Shape intersect;
        j++;
        if(curObstacle.getArc_components().size() != 0) {
            for (int i = 0; i < curObstacle.getArc_components().size(); ++i) {
                intersect = Shape.intersect(start_ball_obj.getStart_ball(), curObstacle.getArc_components().get(i));
                if ((intersect.getBoundsInLocal().getWidth() != -1) && start_ball_obj.getStart_ball().getFill() != curObstacle.getArc_components().get(i).getStroke() && start_ball_obj.getStart_ball().getFill() != curObstacle.getArc_components().get(i).getFill()) {
                    if(!obstacleCollision) {
                        start_ball_obj.getStart_ball().setOpacity(0);
                        collisionAnimations.collisionAnimation(gamePane, start_ball_obj);
                        obstacleCollision = true;
                        temp = j;
                    }
                }
            }
        }

        if(curObstacle.getLine_components().size() != 0) {
            for (int i = 0; i < curObstacle.getLine_components().size(); ++i) {
                intersect = Shape.intersect(start_ball_obj.getStart_ball(), curObstacle.getLine_components().get(i));
                if ((intersect.getBoundsInLocal().getWidth() != -1) && start_ball_obj.getStart_ball().getFill() != curObstacle.getLine_components().get(i).getStroke() && start_ball_obj.getStart_ball().getFill() != curObstacle.getLine_components().get(i).getFill()) {
                    if(!obstacleCollision) {
                        start_ball_obj.getStart_ball().setOpacity(0);
                        collisionAnimations.collisionAnimation(gamePane, start_ball_obj);
                        obstacleCollision = true;
                        temp = j;
                    }
                }
            }
        }

        if(prevObstacle != null) {
            if(prevObstacle.getArc_components().size() != 0) {
                for (int i = 0; i < prevObstacle.getArc_components().size(); ++i) {
                    intersect = Shape.intersect(start_ball_obj.getStart_ball(), prevObstacle.getArc_components().get(i));
                    if ((intersect.getBoundsInLocal().getWidth() != -1) && start_ball_obj.getStart_ball().getFill() != prevObstacle.getArc_components().get(i).getStroke() && start_ball_obj.getStart_ball().getFill() != prevObstacle.getArc_components().get(i).getFill()) {
                        if(!obstacleCollision) {
                            start_ball_obj.getStart_ball().setOpacity(0);
                            collisionAnimations.collisionAnimation(gamePane, start_ball_obj);
                            obstacleCollision = true;
                            temp = j;
                        }
                    }
                }
            }

            if(prevObstacle.getLine_components().size() != 0) {
                for (int i = 0; i < prevObstacle.getArc_components().size(); ++i) {
                    intersect = Shape.intersect(start_ball_obj.getStart_ball(), prevObstacle.getLine_components().get(i));
                    if ((intersect.getBoundsInLocal().getWidth() != -1) && start_ball_obj.getStart_ball().getFill() != prevObstacle.getLine_components().get(i).getStroke() && start_ball_obj.getStart_ball().getFill() != prevObstacle.getLine_components().get(i).getFill()) {
                        if(!obstacleCollision) {
                            start_ball_obj.getStart_ball().setOpacity(0);
                            collisionAnimations.collisionAnimation(gamePane, start_ball_obj);
                            obstacleCollision = true;
                            temp = j;
                        }
                    }
                }
            }
        }
        if(j-temp==20 && obstacleCollision) {
            isPaused = true;
            curObstacle.getAnimation().pause();
            if (prevObstacle != null)
                prevObstacle.getAnimation().pause();
            BoxBlur bb = new BoxBlur();
            bb.setWidth(5);
            bb.setHeight(5);
            bb.setIterations(3);
            gp1.setEffect(bb);
            gp2.setEffect(bb);
            scoreDisplay.setEffect(bb);
            pauseButton.setEffect(bb);
            start_ball_obj.getStart_ball().setEffect(bb);
            Text finalScore = new Text("Score - " + Integer.toString(score));
            finalScore.setLayoutY(80 + 25 + 49 + 90);
            finalScore.setLayoutX(120);
            try {
                finalScore.setFont(Font.loadFont(new FileInputStream("src/model/Resources/kenvector_future.ttf"), 10));
            } catch (FileNotFoundException e) {
                finalScore.setFont(Font.font("Verdana", 23));
            }
            defeatScreen.subPane.getChildren().add(finalScore);
            defeatScreen.moveSubScene(-1 * GAME_WIDTH);
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
                scoreDisplay.setText(Integer.toString(score));
                curPoints.setFlag(false);
                curPoints.getPoint().setOpacity(0);
            }
        }
    }

    private void checkCollisionColorSwitch(){
        Shape intersect;
        GameAnimations colorAnimation = new GameAnimations();
        if(cur_colorSwitch_obj.getCs_flag() == true){
            intersect = Shape.intersect(start_ball_obj.getStart_ball(), colorSwitch.get(2));
            if(intersect.getBoundsInLocal().getWidth() != -1){
                colorAnimation.changeColor(start_ball_obj);
                cur_colorSwitch_obj.setCs_flag(false);
                for(int i=0; i<colorSwitch.size(); ++i){
                    colorSwitch.get(i).setOpacity(0);
                }
            }
        }
    }



    private ColorSwitch createColorSwitch(AnchorPane gp){
        ColorSwitch colorSwitch_obj = new ColorSwitch();
        colorSwitch_obj.setCs_flag(true);
        colorSwitch = colorSwitch_obj.makeColorSwitch(GAME_WIDTH/2, GAME_HEIGHT - 20.0f);
        gp.getChildren().addAll(colorSwitch);
        return colorSwitch_obj;
    }

    private Points createPoints(AnchorPane gp){
        points_obj = new Points();
        points_obj.setFlag(true);
        points_obj.makePoints(GAME_WIDTH/2, 200.0f);
        gp.getChildren().add(points_obj.getPoint());
        return points_obj;
    }
}
