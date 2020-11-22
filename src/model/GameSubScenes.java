package model;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.SubScene;
import javafx.animation.*;
import javafx.util.Duration;

public class GameSubScenes extends SubScene {

    public final static String FONT_PATH = "src/model/Resources/kenvector_future.ttf";
    public final static String BACKGROUND_IMAGE = "model/Resources/grey_panel.png";
    int flag = 0;
    public AnchorPane subPane;

    public GameSubScenes(float x_layout, float y_layout, float width, float height) {
        super(new AnchorPane(), 300, 400);
        prefWidth(width);
        prefHeight(height);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, width, height, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        subPane = (AnchorPane) getRoot();
        subPane.setBackground(new Background(backgroundImage));
        setLayoutX(x_layout);
        setLayoutY(y_layout);
    }

    public void moveSubScene(float x){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.3));                 //animated subscene transition
        translateTransition.setNode(this);
        if(flag==0) {
            translateTransition.setToX(x);
            flag = 1;
        }
        else
        {
            translateTransition.setToX(x);
            flag = 0;
        }
        translateTransition.play();
    }
}
