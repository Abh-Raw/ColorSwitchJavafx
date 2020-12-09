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
        GameAnimations introAnimation = new GameAnimations();               //Making Class GameAnimation object
        Text text = new Text("R");
        text.setFont(Font.loadFont(new FileInputStream("src/model/Resources/AlexBrush-Regular.ttf"), 100));

        text.setLayoutX(INTRO_WIDTH/2);
        text.setLayoutY(INTRO_HEIGHT/2);
        Stop[] stop = {new Stop(0, Color.RED),
                new Stop(0.33, Color.GREEN),
                new Stop(0.66, Color.BLUE),
                new Stop(1, Color.YELLOW)};

        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, stop);
        text.setFill(linearGradient);
        text.setStroke(Color.BLACK);
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setStrokeWidth(2);
        IntromainPane.getChildren().add(text);
        ViewManager viewManager = new ViewManager();           //ViewManager contructor called
        introAnimation.IntroAnimation(text, viewManager, IntromainStage);
    }

    private void makeBackground(){
        Image backgroundImage = new Image("View/Resources/black_Background.jpg", 800, 600, false, true);
        ImageView imageView = new ImageView(backgroundImage);
        IntromainPane.getChildren().add(imageView);
    }
}
