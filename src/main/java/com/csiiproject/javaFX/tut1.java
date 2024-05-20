package com.csiiproject.javaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class tut1 extends Application implements EventHandler<ActionEvent> {
    Button b1;
    @Override
    public void start(Stage primaryStage) throws IOException{
        primaryStage.setTitle("Learning JavaFX");
        b1 = new Button("Click me ;)");
        // Can use button.setText("Text");
        b1.setOnAction(this);

        Button b2 = new Button("Hmm");
        //Anon inner classes can be used directly to handle events for a certain button
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("manga");
            }
        });

        //e (lambda) shorter than creating an anonymous inner class
//        b2.setOnAction(e -> System.out.println("A7a neik I love oracle"));
        //Use {} after the -> op if you need multiple lines of code

//        b2.setOnAction(e -> AlertBox.display("Alert!", "Fumble"));



        StackPane layout = new StackPane();
        layout.getChildren().add(b1);
        layout.getChildren().add(b2);

        Scene scene = new Scene(layout,1000,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    @Override
    public void handle(ActionEvent actionEvent) {
        //action.getSource allows you to separate the reaction for diff actions
        if(actionEvent.getSource()==b1){
            System.out.println("HUH????");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
