package data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerData implements Serializable {
    private ArrayList<String> name_list;
    private ArrayList<Integer> score_list;

    public PlayerData(ArrayList<String> name_list, ArrayList<Integer> score_list){
        name_list = this.name_list;
        score_list = this.score_list;
    }

    public PlayerData(){
        name_list = new ArrayList<>();
        score_list = new ArrayList<>();
    }

    public ArrayList<String> getName_list(){return name_list;}
    public ArrayList<Integer> getScore_list(){return score_list;}
}
