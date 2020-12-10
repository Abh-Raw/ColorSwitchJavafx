package data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerData implements Serializable{
    private String name;
    private int score;

    public PlayerData(String name, int score){
        this.name = name;
        score = this.score;
    }

    public String getName() {return name; }

    public int getScore() {return score; }
}
