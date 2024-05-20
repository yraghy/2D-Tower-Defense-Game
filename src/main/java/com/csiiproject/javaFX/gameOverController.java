package com.csiiproject.javaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class gameOverController {
    Parent rootL;
    @FXML
    Label finalScore;

    public void initLossScreen(Parent rootL, int score, MediaPlayer oldMedia) {
        oldMedia.stop();
        finalScore = (Label) rootL.lookup("#finalScore");
        finalScore.setText("Final Score: " + score);
        finalScore.setAlignment(javafx.geometry.Pos.CENTER);

        String path = "src/main/resources/com/csiiproject/javaFX/DSDeath.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    public void mainMenuReturn(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CleanStart.fxml"));
        Scene mainMenuScene = new Scene(root);
        Stage window = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
    }
}
