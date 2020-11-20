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
    public AnchorPane subPane = new AnchorPane();

    public GameSubScenes() {
        super(new AnchorPane(), 300, 400);
        prefWidth(600);
        prefHeight(400);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root = (AnchorPane)this.getRoot();
        subPane.setBackground(new Background(backgroundImage));
        setLayoutX(1024);
        setLayoutY(180);
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
