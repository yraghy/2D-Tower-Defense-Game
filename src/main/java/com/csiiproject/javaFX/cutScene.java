package com.csiiproject.javaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class cutScene implements Initializable {

    @FXML
    private MediaView mediaview;

    @FXML
    private Button bPause, bPlay, bReset, bSkip;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        file = new File("cutScene.mp4");
        URL resource = getClass().getResource("cutScene.mp4");
        media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        mediaview.setMediaPlayer(mediaPlayer);
        mediaview.setFitWidth(1052);
        mediaview.setFitHeight(550);

        setupButtonHoverEffect(bPause);
        setupButtonHoverEffect(bPlay);
        setupButtonHoverEffect(bReset);
        setupButtonHoverEffect(bSkip);
    }

    private void setupButtonHoverEffect(Button button) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #ffe100;"); // Change the button color to light green when mouse enters

            try {
                URL resource = getClass().getResource("click.mp3");
                AudioClip clip = new AudioClip(resource.toString());
                clip.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        button.setOnMouseExited(event -> {
            button.setStyle(""); // Reset the button color when mouse exits
        });
    }

    public void Play(ActionEvent e) {
        mediaPlayer.play();
    }

    public void Pause(ActionEvent e) {
        mediaPlayer.pause();
    }

    public void resetMedia(ActionEvent e) {
        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }

    public void Skip(ActionEvent e) {
        mediaPlayer.stop();
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cleanStart.fxml"));
            Parent root = loader.load();

            // Create a new stage and scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Show the new stage
            stage.show();

            // Close the current stage
            ((Stage)mediaview.getScene().getWindow()).close();
        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    public void Light(javafx.scene.input.MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #ffe100;");
    }
}