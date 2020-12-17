package View;


import data.GameData;
import data.LeaderBoard;
import data.LoadFile;
import data.PlayerData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import model.GameButtons;
import model.GameSubScenes;
import model.Logo_Obstacle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.Serializable;
import java.util.*;

class ScoreComparator implements Comparator<PlayerData>, Serializable {
    public int compare(PlayerData p1, PlayerData p2){
        if(p1.getScore() > p2.getScore()){
            return 1;
        }
        else if(p1.getScore() < p2.getScore()){
            return -1;
        }
        else
            return 0;
    }
}

public class ViewManager {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private List<GameButtons> button_list;
    private GameSubScenes ScoresubScene;
    private GameSubScenes ResumesubScene;
    private GameSubScenes hiddenSubScene;
    private LeaderBoard leaderBoard;
    private Stage stage;
    private GameManager obstacleImg;
    public ViewManager() throws FileNotFoundException {
        obstacleImg = new GameManager();
        mainPane = new AnchorPane();
        button_list = new ArrayList<>();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        makeBackground();
        makeLogo();
        mainStage.setScene(mainScene);
        obstacleImg.animateManager_Obstacle(mainPane, WIDTH/2, (HEIGHT/2)+30);
        //obstacleImg.animateLogo(mainPane, 0,0, 10.0f, 10.0f);
        //obstacleImg.animateLogo(mainPane, 5, 5, 10.0f, 10.0f);
        createButton();
        createSubscene();
        LoadFile loadFile = new LoadFile();
        if(loadFile.loadLeaderboard() != null)
            leaderBoard = loadFile.loadLeaderboard();
        else
            leaderBoard = new LeaderBoard();
        setCurrentLeaderboard();
    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void setCurrentLeaderboard() throws FileNotFoundException {
        ArrayList<Text> highScoreAchievers = new ArrayList<>();
        int scoreIndex = 1;
        int scoreLayout = 90;
        List<PlayerData> tempList = new ArrayList<PlayerData>(leaderBoard.getLeaderboard());
        Collections.sort(tempList, new ScoreComparator());
        Collections.reverse(tempList);
        Iterator<PlayerData> itr = tempList.listIterator();
        while(itr.hasNext()){
            PlayerData temp = itr.next();
            Text addScore = new Text(scoreIndex + "\t \t" + temp.getName() + "\t \t" + temp.getScore());
            highScoreAchievers.add(addScore);
            addScore.setLayoutX(60);
            addScore.setLayoutY(scoreLayout);
            addScore.setFont(Font.loadFont(new FileInputStream("src/model/Resources/kenvector_future.ttf"), 10));
            scoreLayout += 20;
            scoreIndex++;
            ScoresubScene.subPane.getChildren().add(addScore);
        }
    }

    private void createButton(){
        GameButtons start = new GameButtons("START");
        start.setLayoutX(295);
        start.setLayoutY(212.5);
        button_list.add(start);
        mainPane.getChildren().add(start);

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameManager manager = new GameManager();            //GameManager constructor called
                try {
                    manager.createNewGame(mainStage, leaderBoard, 0);                   //creates game with components
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        GameButtons resume = new GameButtons("RESUME");
        resume.setLayoutX(295);
        resume.setLayoutY(282.5);
        button_list.add(resume);
        mainPane.getChildren().add(resume);

        resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentSubScene(ResumesubScene);                    //Subscene called
            }
        });

        GameButtons high_scores = new GameButtons("SCORES");
        high_scores.setLayoutX(295);
        high_scores.setLayoutY(357.5);
        button_list.add(high_scores);
        mainPane.getChildren().add(high_scores);

        high_scores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Iterator<PlayerData> itr = leaderBoard.getLeaderboard().iterator();
                while(itr.hasNext()){
                    PlayerData temp = itr.next();
                    System.out.println(temp.getName() + " " + temp.getScore());
                }
                currentSubScene(ScoresubScene);               //Subscene callec
            }
        });

        GameButtons exit = new GameButtons("EXIT");
        exit.setLayoutX(295);
        exit.setLayoutY(427.5);
        button_list.add(exit);
        mainPane.getChildren().add(exit);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();                      //closing the game
            }
        });
    }

    private void currentSubScene(GameSubScenes subScenes){
        if(hiddenSubScene != null){
            hiddenSubScene.moveSubScene(426);
        }
        subScenes.moveSubScene(-426);
        hiddenSubScene = subScenes;
    }

    private void makeBackground(){
        Image backgroundImage = new Image("View/Resources/black_Background.jpg", 800, 600, false, true);
        ImageView imageView = new ImageView(backgroundImage);
        mainPane.getChildren().add(imageView);
    }

    private void makeLogo(){
        final Text logo_p1 = new Text("C");
        Text logo_p2 = new Text("L");
        Text logo_p3 = new Text("R SWITCH");
        logo_p1.setLayoutX(110 - 70);
        logo_p1.setLayoutY(90);
        logo_p1.setFont(new Font(100));
        logo_p1.setFill(Color.WHITE);
        logo_p2.setLayoutX(250 - 70);
        logo_p2.setLayoutY(90);
        logo_p2.setFont(new Font(100));
        logo_p2.setFill(Color.WHITE);
        logo_p3.setLayoutX(390 - 70);
        logo_p3.setLayoutY(90);
        logo_p3.setFont(new Font(100));
        logo_p3.setFill(Color.WHITE);
        Logo_Obstacle o1 = new Logo_Obstacle();
        o1.createLogoObstacle(210 - 70, 55, 35, 35);
        Logo_Obstacle o2 = new Logo_Obstacle();
        o2.createLogoObstacle(340 - 70, 55, 35, 35);

        Rotate rotation1 = new Rotate();       //1 rotation obj for every component
        Rotate rotation2 = new Rotate();
        Rotate rotation3 = new Rotate();
        Rotate rotation4 = new Rotate();
        o1.getArc_components().get(0).getTransforms().add(rotation1);   //rotation obj added to every component
        o1.getArc_components().get(1).getTransforms().add(rotation2);
        o1.getArc_components().get(2).getTransforms().add(rotation3);
        o1.getArc_components().get(3).getTransforms().add(rotation4);

        Rotate rotation5 = new Rotate();       //1 rotation obj for every component
        Rotate rotation6 = new Rotate();
        Rotate rotation7 = new Rotate();
        Rotate rotation8 = new Rotate();
        o2.getArc_components().get(0).getTransforms().add(rotation5);   //rotation obj added to every component
        o2.getArc_components().get(1).getTransforms().add(rotation6);
        o2.getArc_components().get(2).getTransforms().add(rotation7);
        o2.getArc_components().get(3).getTransforms().add(rotation8);

        logo_p1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo_p1.setEffect(new DropShadow());
            }
        });

        logo_p1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo_p1.setEffect(null);
            }
        });
        o1.addAnimation(210 - 70, 55, mainPane);
        o2.addAnimation(340 - 70, 55, mainPane);
        mainPane.getChildren().add(logo_p1);
        mainPane.getChildren().add(logo_p2);
        mainPane.getChildren().addAll(o1.getArc_components());
        mainPane.getChildren().addAll(o2.getArc_components());
        mainPane.getChildren().add(logo_p3);
    }

    private void createSubscene() throws FileNotFoundException {
        ScoresubScene = new GameSubScenes(950, 180, 600, 400);
        mainPane.getChildren().add(ScoresubScene);                  //creating subscenes;

        Text heading1 = new Text("High Scores");
        heading1.setLayoutX(70);
        heading1.setLayoutY(50);
        heading1.setFont(Font.loadFont(new FileInputStream("src/model/Resources/AlexBrush-Regular.ttf"), 30));
        ScoresubScene.subPane.getChildren().add(heading1);

        ResumesubScene = new GameSubScenes(950, 180, 600, 400);
        mainPane.getChildren().add(ResumesubScene);

        Text heading = new Text("Saved Games");
        heading.setLayoutX(70);
        heading.setLayoutY(50);
        heading.setFont(Font.loadFont(new FileInputStream("src/model/Resources/AlexBrush-Regular.ttf"), 30));
        ResumesubScene.subPane.getChildren().add(heading);

        GameButtons saveSlot1 = new GameButtons("SLOT 1");
        saveSlot1.setLayoutX(50);
        saveSlot1.setLayoutY(80);
        ResumesubScene.subPane.getChildren().add(saveSlot1);

        GameButtons saveSlot2 = new GameButtons("SLOT 2");
        saveSlot2.setLayoutX(50);
        saveSlot2.setLayoutY(80 + 80);
        ResumesubScene.subPane.getChildren().add(saveSlot2);

        GameButtons saveSlot3 = new GameButtons("SLOT 3");
        saveSlot3.setLayoutX(50);
        saveSlot3.setLayoutY(80 + 160);
        ResumesubScene.subPane.getChildren().add(saveSlot3);

        GameButtons saveSlot4 = new GameButtons("SLOT 4");
        saveSlot4.setLayoutX(50);
        saveSlot4.setLayoutY(80 + 240);
        ResumesubScene.subPane.getChildren().add(saveSlot4);

        saveSlot1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameManager manager = new GameManager();
                LoadFile loadFile = new LoadFile();
                GameData loadGameData = loadFile.loadGameData("file1.ser");
                try {
                    manager.resumeGame(mainStage, loadGameData);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        saveSlot2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameManager manager = new GameManager();
                LoadFile loadFile = new LoadFile();
                GameData loadGameData = loadFile.loadGameData("file2.ser");
                try {
                    manager.resumeGame(mainStage, loadGameData);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        saveSlot3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameManager manager = new GameManager();
                LoadFile loadFile = new LoadFile();
                GameData loadGameData = loadFile.loadGameData("file3.ser");
                try {
                    manager.resumeGame(mainStage, loadGameData);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        saveSlot4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameManager manager = new GameManager();
                LoadFile loadFile = new LoadFile();
                GameData loadGameData = loadFile.loadGameData("file4.ser");
                try {
                    manager.resumeGame(mainStage, loadGameData);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void showMainMenu(Stage stage){
        this.stage = stage;
        this.stage.hide();
        mainStage.show();
    }
}

