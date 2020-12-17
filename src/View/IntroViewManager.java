package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameAnimations;
import model.GameButtons;
import model.Logo_Obstacle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class IntroViewManager {
    private static final int INTRO_WIDTH = 800;
    private static final int INTRO_HEIGHT = 600;
    private AnchorPane IntromainPane;
    private Scene IntromainScene;
    private Stage IntromainStage;

    public IntroViewManager() throws InterruptedException, FileNotFoundException {
        IntromainPane = new AnchorPane();
        IntromainScene = new Scene(IntromainPane, INTRO_WIDTH, INTRO_HEIGHT);
        IntromainStage = new Stage();
        IntromainStage.setScene(IntromainScene);
        makeBackground();               //making background
        runIntroAnimation();            //Introduction animation

        IntromainStage.show();
    }

    private void runIntroAnimation() throws FileNotFoundException {
        Logo_Obstacle intro_obj_1 = new Logo_Obstacle();
        Logo_Obstacle intro_obj_2 = new Logo_Obstacle();
        Logo_Obstacle intro_obj_3 = new Logo_Obstacle();

        GameAnimations introAnimation = new GameAnimations();
        intro_obj_1.createLogoObstacle(INTRO_WIDTH/2, INTRO_HEIGHT/2, 60, 60);
        intro_obj_2.createLogoObstacle(INTRO_WIDTH/2, INTRO_HEIGHT/2, 120, 120);
        intro_obj_3.createLogoObstacle(INTRO_WIDTH/2, INTRO_HEIGHT/2, 180, 180);

        IntromainPane.getChildren().addAll(intro_obj_1.getArc_components());
        IntromainPane.getChildren().addAll(intro_obj_2.getArc_components());
        IntromainPane.getChildren().addAll(intro_obj_3.getArc_components());


        ViewManager viewManager = new ViewManager();           //ViewManager contructor called
        introAnimation.IntroAnimation(intro_obj_1, intro_obj_2, intro_obj_3, viewManager, IntromainStage);
    }

    private void makeBackground(){
        Image backgroundImage = new Image("View/Resources/black_Background.jpg", 800, 600, false, true);
        ImageView imageView = new ImageView(backgroundImage);
        IntromainPane.getChildren().add(imageView);
    }
}
