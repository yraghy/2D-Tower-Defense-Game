package com.csiiproject.javaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SwitchingScenes extends Application {
    Scene scene1, scene2;

    public void start(Stage window){
        Label l1 = new Label("This is the first scene");
        Button b1 = new Button("Go to Scene 2");
        b1.setOnAction(e -> window.setScene(scene2));

//        Layout1
        //Creates a layout that separates elements n-pixels vertically according to the input
        VBox layout1 = new VBox(30);
        //Use addAll to immediately add multiple components to a layout
        layout1.getChildren().addAll(l1,b1);
        scene1 = new Scene(layout1,400,400);

//        Button2
        Button b2 = new Button("L scene, go back to scene 1");
        b2.setOnAction(e -> window.setScene(scene1));

//        Layout2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(b2);
        scene2 = new Scene(layout2,700,200);

        window.setScene(scene1);
        window.setTitle("Switching Scenes");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
