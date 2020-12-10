package data;

import javafx.concurrent.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadFile {
    public GameData loadGameData(){
        GameData loadedData;

                try {

                    FileInputStream fileIn = new FileInputStream("test.ser");
                    ObjectInputStream inputStream = new ObjectInputStream(fileIn);

                    loadedData = (GameData) inputStream.readObject();
                    System.out.println(loadedData.getScore());
                    inputStream.close();
                    fileIn.close();

                } catch (IOException | ClassNotFoundException e) {
                    loadedData = null;
                }
           return loadedData;
    }

    public LeaderBoard loadLeaderboard() {
        return null;
    }
}
