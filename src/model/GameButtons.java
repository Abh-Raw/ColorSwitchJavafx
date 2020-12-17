package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameButtons extends Button {
    private final String FONT_STYLE = "src/model/Resources/kenvector_future.ttf";
    private final String BUTTON_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('model/Resources/grey_button_pressed.png');";
    private final String BUTTON_RELEASED = "-fx-background-color: transparent; -fx-background-image: url('model/Resources/grey_button.png');";

    public GameButtons(String txt){
        setText(txt);
        setButttonFont();
        setPrefHeight(49);
        setPrefWidth(190);
        setStyle(BUTTON_RELEASED);
        button_actions();
    }

    private void setButttonFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_STYLE), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana",23));
        }
    }

    private void setButtonPressed(){
        setStyle(BUTTON_PRESSED);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleased(){
        setStyle(BUTTON_RELEASED);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void button_actions(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
                    setStyle(BUTTON_PRESSED);
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
                    setStyle(BUTTON_RELEASED);
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DropShadow dropShadow = new DropShadow();
                dropShadow.setColor(Color.BLUEVIOLET);
                setEffect(dropShadow);

            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }
}
