package View;


import data.GameData;
import data.LoadFile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GameButtons;
import model.GameSubScenes;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


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
    private Stage stage;
    public ViewManager(){
        mainPane = new AnchorPane();
        button_list = new ArrayList<>();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        makeBackground();
        makeLogo();
        mainStage.setScene(mainScene);
        createButton();
        createSubscene();
    }

    public Stage getMainStage(){
        return mainStage;
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
                    manager.createNewGame(mainStage);                   //creates game with components
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
        final ImageView logo = new ImageView("View/Resources/logo.png");
        logo.setLayoutX(205);
        logo.setLayoutY(60);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);
    }

    private void createSubscene(){
        ScoresubScene = new GameSubScenes(950, 180, 600, 400);
        mainPane.getChildren().add(ScoresubScene);                  //creating subscenes;

        ResumesubScene = new GameSubScenes(950, 180, 600, 400);
        mainPane.getChildren().add(ResumesubScene);

        GameButtons test = new GameButtons("LOAD TEST");
        test.setLayoutX(50);
        test.setLayoutY(150);
        ResumesubScene.subPane.getChildren().add(test);

        test.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameManager manager = new GameManager();
                LoadFile loadFile = new LoadFile();
                GameData loadGameData = loadFile.loadGameData();
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

