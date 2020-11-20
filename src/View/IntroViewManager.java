package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GameAnimations;
import model.GameButtons;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class IntroViewManager {
    private static final int INTRO_WIDTH = 800;
    private static final int INTRO_HEIGHT = 600;
    private AnchorPane IntromainPane;
    private Scene IntromainScene;
    private Stage IntromainStage;

    public IntroViewManager() throws InterruptedException {
        IntromainPane = new AnchorPane();
        IntromainScene = new Scene(IntromainPane, INTRO_WIDTH, INTRO_HEIGHT);
        IntromainStage = new Stage();
        IntromainStage.setScene(IntromainScene);
        makeBackground();               //making background
        runIntroAnimation();            //Introduction animation
        //createPlayButton();
        //ViewManager viewManager = new ViewManager();
        //viewManager.showMainMenu(IntromainStage);
        IntromainStage.show();
    }

    public Stage getMainStage(){
        return IntromainStage;
    }

    private void runIntroAnimation(){
        GameAnimations introAnimation = new GameAnimations();               //Making Class GameAnimation object
        ImageView logo = new ImageView("model/Resources/logo.png");
        logo.setLayoutX(300);
        logo.setLayoutY(250);
        IntromainPane.getChildren().add(logo);
        ViewManager viewManager = new ViewManager();           //ViewManager contructor called
        introAnimation.IntroAnimation(logo, viewManager, IntromainStage);
    }

    private void makeBackground(){
        Image backgroundImage = new Image("View/Resources/black_Background.jpg", 800, 600, false, true);
        ImageView imageView = new ImageView(backgroundImage);
        IntromainPane.getChildren().add(imageView);
    }

    private void createPlayButton(){
        GameButtons playButton = new GameButtons("PLAY");           //Making GameButtons Object
        playButton.setLayoutX(320);
        playButton.setLayoutY(440);
        IntromainPane.getChildren().add(playButton);
         playButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {                      //Making event handler
                 ViewManager viewManager = new ViewManager();           //ViewManager contructor called
                 viewManager.showMainMenu(IntromainStage);
             }
         });
    }
}
