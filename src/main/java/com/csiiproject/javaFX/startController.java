package com.csiiproject.javaFX;

import game.engine.Battle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class startController {

    public static Battle easyGame;
    private Battle hardGame;
    private Label eScore, eRes, eTurn, ePhase;
    private Label hScore, hRes, hTurn, hPhase;
    public static Scene easyScene;
    public Scene hardScene;

    //Switch scene methods
    public void switchEasy(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Easy.fxml"));
        Parent rootE = loader.load();
        Scene easyScene = new Scene(rootE);
        Stage window = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        easyController easyController = loader.getController();
        easyController.initEasy(rootE);
        window.setScene(easyScene);
        window.show();
    }



    public void switchHard(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hardTT.fxml"));
        Parent rootH = loader.load();
        Scene hardScene = new Scene(rootH);
        Stage window = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        hardController hardController = loader.getController();
        hardController.initHard(rootH);
        window.setScene(hardScene);
        window.show();
    }

    public void switchInstruct(ActionEvent e) throws IOException {
        AlertBox.instructions("Instructions");
    }

    public void switchSettings(ActionEvent e) throws IOException {

    }

    public void switchCredits(ActionEvent e) throws IOException {

    }


    //Button effect methods
    public void shadow(MouseEvent e) throws IOException {
        Button b = (Button) e.getSource();
        b.setEffect(new DropShadow());
    }

    public void removeShadow(MouseEvent e) throws IOException {
        Button b = (Button) e.getSource();
        b.setEffect(null);
    }

    public void setupButtonHoverEffect(Button button) {

            try {
                URL resource = getClass().getResource("sword.mp3");
                AudioClip clip = new AudioClip(resource.toString());
                clip.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }


    public void sound(javafx.scene.input.MouseEvent event) {
        Button button = (Button) event.getSource();
        URL resource = getClass().getResource("sword.mp3");
        AudioClip clip = new AudioClip(resource.toString());
        clip.setVolume(0.1);
        clip.play();
    }


    Button bPlayEasy;
    Button bPLayHard;
    Button bInstructions;
    Button bCredits;
    Button bSettings;

    public void initialize(URL arg0, ResourceBundle arg1) {

        setupButtonHoverEffect(bPLayHard);
        setupButtonHoverEffect(bPlayEasy);
        setupButtonHoverEffect(bInstructions);
        setupButtonHoverEffect(bCredits);
    }


}

