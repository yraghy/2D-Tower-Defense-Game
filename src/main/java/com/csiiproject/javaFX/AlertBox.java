package com.csiiproject.javaFX;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertBox {

//    public static void display(String title, String message){
//        Stage box = new Stage();
//
//        //Blocks user interaction with other windows until issue is resolved
//        box.initModality(Modality.APPLICATION_MODAL);
//        box.setTitle(title);
//        box.setWidth(250);
//
//        Label l1 = new Label(message);
//        Button b1 = new Button("Close window");
//        b1.setOnAction(e -> box.close());
//
//        VBox lout = new VBox(10);
//        lout.getChildren().addAll(l1,b1);
//        lout.setAlignment(Pos.CENTER);
//
//        Scene scene = new Scene(lout);
//        box.setScene(scene);
//        box.showAndWait();
//    }



    public static void weaponShop(String title) throws IOException {
        Stage box = new Stage();
        Parent root = FXMLLoader.load(AlertBox.class.getResource("WeaponShop.fxml"));
        //Blocks user interaction with other windows until issue is resolved
        box.initModality(Modality.APPLICATION_MODAL);
        box.setTitle(title);
        box.setWidth(1052);
        box.setHeight(670);

        Button b1 = (Button)(root.lookup("#bClose"));
        b1.setOnAction(e -> box.close());
        box.setTitle(title);
        Scene scene = new Scene(root);
        box.setScene(scene);
        box.setResizable(false);
        box.setFullScreen(false);
        box.showAndWait();
    }

    public static void gameOver(String title) throws IOException{
        Stage box = new Stage();
        Parent root = FXMLLoader.load(AlertBox.class.getResource("GameOver.fxml"));
        //Blocks user interaction with other windows until issue is resolved
        box.initModality(Modality.APPLICATION_MODAL);
        box.setTitle(title);
        Scene scene = new Scene(root);
        String loseCss = AlertBox.class.getResource("gameOver.css").toExternalForm();
        scene.getStylesheets().add(loseCss);
        box.setScene(scene);
        box.setResizable(false);
        box.setFullScreen(false);
        box.showAndWait();
    }

    public static void instructions(String title) throws IOException{
        Stage box = new Stage();
        Parent root = FXMLLoader.load(AlertBox.class.getResource("instructions.fxml"));
        //Blocks user interaction with other windows until issue is resolved
        box.initModality(Modality.APPLICATION_MODAL);
        box.setTitle(title);
        Scene scene = new Scene(root);
        String loseCss = AlertBox.class.getResource("instructions.css").toExternalForm();
        scene.getStylesheets().add(loseCss);
        box.setScene(scene);
        box.setResizable(false);
        box.setFullScreen(false);
        box.showAndWait();
    }

    public static void invalidLanePop(String title) throws IOException{
        Stage box = new Stage();
        Pane root = new Pane();
        //Blocks user interaction with other windows until issue is resolved
        box.initModality(Modality.APPLICATION_MODAL);
        box.setTitle(title);
        Label text = new Label("Invalid Lane: this lane is lost");
        text.setStyle("-fx-accent: #e50404;");
        Scene scene = new Scene(root,400,400);
//        String loseCss = AlertBox.class.getResource("invalidLanePop.css").toExternalForm();
//        scene.getStylesheets().add(loseCss);
        root.getChildren().add(text);
        text.setAlignment(Pos.CENTER);
        box.setScene(scene);
        box.setResizable(false);
        box.setFullScreen(false);
        box.showAndWait();
    }

    public static void insuffResourcesPop(String title) throws IOException{
        Stage box = new Stage();
        Pane root = new Pane();
        //Blocks user interaction with other windows until issue is resolved
        box.initModality(Modality.APPLICATION_MODAL);
        box.setTitle(title);
        Scene scene = new Scene(root,400,400);
//        String loseCss = AlertBox.class.getResource("invalidLanePop.css").toExternalForm();
//        scene.getStylesheets().add(loseCss);
        box.setScene(scene);
        box.setResizable(false);
        box.setFullScreen(false);
        box.showAndWait();
    }
}
