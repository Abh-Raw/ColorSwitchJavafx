package data;

import javafx.concurrent.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadFile {
    public GameData loadGameData(String file_path){
        GameData loadedData;

                try {
                    FileInputStream fileIn = new FileInputStream(file_path);
                    ObjectInputStream inputStream = new ObjectInputStream(fileIn);

                    loadedData = (GameData) inputStream.readObject();
                    inputStream.close();
                    fileIn.close();

                } catch (IOException | ClassNotFoundException e) {
                    loadedData = null;
                }
           return loadedData;
    }

    public LeaderBoard loadLeaderboard() {
        LeaderBoard leaderBoard;

        try {

            FileInputStream fileIn = new FileInputStream("leaderboard.ser");
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);

            leaderBoard = (LeaderBoard) inputStream.readObject();
            inputStream.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            leaderBoard = null;
        }
        return leaderBoard;
    }
}
