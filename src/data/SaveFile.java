package data;

import javafx.concurrent.Task;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveFile {
    public void savePlayerData(final LeaderBoard leaderBoard){
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {

                try {

                    FileOutputStream fileOut = new FileOutputStream("leaderboard.ser");
                    BufferedOutputStream bufferedStream = new BufferedOutputStream(fileOut);
                    ObjectOutputStream outputStream = new ObjectOutputStream(bufferedStream);

                    outputStream.writeObject(leaderBoard);
                    outputStream.close();
                    fileOut.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    public void saveGameData(final GameData gameData, final String file_path){
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {

                try {

                    FileOutputStream fileOut = new FileOutputStream(file_path);
                    BufferedOutputStream bufferedStream = new BufferedOutputStream(fileOut);
                    ObjectOutputStream outputStream = new ObjectOutputStream(bufferedStream);

                    outputStream.writeObject(gameData);
                    outputStream.close();
                    fileOut.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
