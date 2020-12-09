package data;

import javafx.concurrent.Task;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveFile {
    public void savePlayerData(PlayerData playerData){

    }

    public void saveGameData(final GameData gameData){
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {

                try {

                    FileOutputStream fileOut = new FileOutputStream("test.ser");
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
