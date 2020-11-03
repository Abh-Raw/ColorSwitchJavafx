package sample;

import View.IntroViewManager;
import View.ViewManager;
import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            IntroViewManager introViewManager = new IntroViewManager();
            //primaryStage = introViewManager.getMainStage();
            //primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
