package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoLabel extends Label {
    private final static String FONT_PATH = "src/model/Resources/kenvector_future.ttf";

    public InfoLabel(String text){

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10,10,10,10));
        setLabelFont();
        setText(text);
    }

    private void setLabelFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 35));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana",35));
        }
    }
}
