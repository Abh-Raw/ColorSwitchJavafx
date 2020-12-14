package data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.PriorityQueue;

 class ScoreComparator implements Comparator<PlayerData>, Serializable{
    public int compare(PlayerData p1, PlayerData p2){
        if(p1.getScore() > p2.getScore()){
            return 1;
        }
        else if(p1.getScore() < p2.getScore()){
            return -1;
        }
        else
            return 0;
    }
}

public class LeaderBoard implements Serializable {
    private PriorityQueue<PlayerData> leaderboard;

    public LeaderBoard(PriorityQueue<PlayerData> leaderboard) {
        this.leaderboard = leaderboard;
    }

    public LeaderBoard(){
        leaderboard = new PriorityQueue<>(8, new ScoreComparator());
    }

    public PriorityQueue<PlayerData> getLeaderboard() {return leaderboard; }

    public void setLeaderboard(PriorityQueue<PlayerData> leaderboard) {this.leaderboard = leaderboard; }
}
