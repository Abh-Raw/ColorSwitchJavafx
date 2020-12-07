package data;

import model.Ball;
import model.GameObstacles;

import java.io.Serializable;
import java.util.ArrayList;

public class GameData implements Serializable {
    private Ball startBall;

    private ArrayList<Integer> curObstacleColors;
    private ArrayList<Double> curObstacleAngles;
    private int curObstacleID;
    private ArrayList<Integer> prevObstacleColors;
    private ArrayList<Double> prevObstacleAngles;
    private int prevObstacleID;
    private boolean curPt;
    private boolean nextPt;
    private double gp1_layout;
    private double gp2_layout;
    private boolean cSwitch_flag;
    private int score;

    public GameData(Ball startBall, ArrayList<Integer> curObstacleColors, ArrayList<Double> curObstacleAngles, int curObstacleID, ArrayList<Integer> prevObstacleColors, ArrayList<Double> prevObstacleAngles, int prevObstacleID, boolean curPt, boolean nextPt, double gp1_layout, double gp2_layout, boolean cSwitch_flag, int score) {
        this.startBall = startBall;
        this.curObstacleColors = curObstacleColors;
        this.curObstacleAngles = curObstacleAngles;
        this.curObstacleID = curObstacleID;
        this.prevObstacleColors = prevObstacleColors;
        this.prevObstacleAngles = prevObstacleAngles;
        this.prevObstacleID = prevObstacleID;
        this.curPt = curPt;
        this.nextPt = nextPt;
        this.gp1_layout = gp1_layout;
        this.gp2_layout = gp2_layout;
        this.cSwitch_flag = cSwitch_flag;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Ball getStartBall() {
        return startBall;
    }

    public ArrayList<Integer> getCurObstacleColors() {
        return curObstacleColors;
    }

    public ArrayList<Double> getCurObstacleAngles() {
        return curObstacleAngles;
    }

    public int getCurObstacleID() {
        return curObstacleID;
    }

    public ArrayList<Integer> getPrevObstacleColors() {
        return prevObstacleColors;
    }

    public ArrayList<Double> getPrevObstacleAngles() {
        return prevObstacleAngles;
    }

    public int getPrevObstacleID() {
        return prevObstacleID;
    }

    public boolean isCurPt() {
        return curPt;
    }

    public boolean isNextPt() {
        return nextPt;
    }

    public double getGp1_layout() {
        return gp1_layout;
    }

    public double getGp2_layout() {
        return gp2_layout;
    }

    public boolean iscSwitch_flag() {
        return cSwitch_flag;
    }
}
