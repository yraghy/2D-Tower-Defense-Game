package com.csiiproject.javaFX;
import game.engine.Battle;
import game.engine.base.Wall;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import com.csiiproject.javaFX.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.csiiproject.javaFX.easyController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.PriorityQueue;
import game.engine.lanes.*;

public class Controller {

    Battle easyGame;
    private Lane buyLane;
    public GridPane lanes;

    public Button bBuy1;
    public Button bBuy2;
    public Button bBuy3;
    public Button bBuy4;




    public void mainMenuReturn(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("CleanStart.fxml"));
        Scene mainMenuScene = new Scene(root);
        Stage window = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
    }

    public void retAfterLoss(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("CleanStart.fxml"));
        Scene mainMenuScene = new Scene(root);
        Stage window = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
    }



    //Trial Methods
    public void loseTrial(ActionEvent e) throws IOException{AlertBox.gameOver("Game Over");}


    //weeweeHacker code
//
//    public void initialize() {
//        assert bBuy1 != null;
//        bBuy1.setOnAction(e -> {
//            try {
//                purchaseAntiTitanShell(e);
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//        assert bBuy2 != null;
//        bBuy2.setOnAction(e -> {
//            try {
//                purchaseSpear(e);
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//        assert bBuy3 != null;
//        bBuy3.setOnAction(e -> {
//            try {
//                purchaseCannon(e);
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//        assert bBuy4 != null;
//        bBuy4.setOnAction(e -> {
//            try {
//                purchaseTrap(e);
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//    }
//
//    public int getLane(int laneNumber) {
//        SharedState sharedState = SharedState.getInstance();
//        return sharedState.getLaneNumber();  // Get the laneNumber from SharedState
//    }
//
//    public int getRow() {
//        SharedState sharedState = SharedState.getInstance();
//        int laneNumber = sharedState.getLaneNumber();
//        switch (laneNumber) {
//            case 1:
//                return 0;
//            case 2:
//                return 1;
//            case 3:
//                return 2;
//            default:
//                throw new IllegalArgumentException("Invalid lane number: " + laneNumber);
//        }
//    }
//
//    public void purchaseAntiTitanShell(ActionEvent e) throws InvalidLaneException, InsufficientResourcesException {
//
//        SharedState sharedState = SharedState.getInstance();
//        int laneNumber = sharedState.getLaneNumber();  // To get the value
//
//        Lane lane = easyGame.getLane(laneNumber);
//        easyGame.purchaseWeapon(1 , lane);
//
//
//        Optional<Image> overlayImageOpt = loadImage("WeaponShop/antiTitanShell.png");
//        if (overlayImageOpt.isPresent()) {
//            ImageView overlayImageView = new ImageView(overlayImageOpt.get());
//            GridPane gridPane = (GridPane) lanes;
//            gridPane.add(overlayImageView, 0, getRow());
//        } else {
//            System.out.println("Image not found: WeaponShop/antiTitanShell.png");
//        }
//
//
//    }
//
//    private Optional<Image> loadImage(String resourcePath) {
//        URL resourceUrl = getClass().getClassLoader().getResource(resourcePath);
//        if (resourceUrl != null) {
//            return Optional.of(new Image(resourceUrl.toExternalForm()));
//        } else {
//            return Optional.empty();
//        }
//    }
//
//    public void purchaseSpear(ActionEvent e) throws InvalidLaneException, InsufficientResourcesException {
//        SharedState sharedState = SharedState.getInstance();
//        int laneNumber = sharedState.getLaneNumber();  // To get the value
//
//        Lane lane = easyGame.getLane(laneNumber);
//        easyGame.purchaseWeapon(2 , lane);
//
//        Optional<Image> overlayImageOpt = loadImage("WeaponShop/spear.png");
//        if (overlayImageOpt.isPresent()) {
//            ImageView overlayImageView = new ImageView(overlayImageOpt.get());
//            GridPane gridPane = (GridPane) lanes;
//            gridPane.add(overlayImageView, 0, getRow());
//        } else {
//            System.out.println("Image not found: WeaponShop/spear.png");
//        }
//    }
//
//    public void purchaseCannon(ActionEvent e) throws InvalidLaneException, InsufficientResourcesException {
//        SharedState sharedState = SharedState.getInstance();
//        int laneNumber = sharedState.getLaneNumber();  // To get the value
//
//        Lane lane = easyGame.getLane(laneNumber);
//        easyGame.purchaseWeapon(3 , lane);
//
//        Optional<Image> overlayImageOpt = loadImage("WeaponShop/cannon.png");
//        if (overlayImageOpt.isPresent()) {
//            ImageView overlayImageView = new ImageView(overlayImageOpt.get());
//            GridPane gridPane = (GridPane) lanes;
//            gridPane.add(overlayImageView, 0, getRow());
//        } else {
//            System.out.println("Image not found: WeaponShop/cannon.png");
//        }
//    }
//
//    public void purchaseTrap(ActionEvent e) throws InvalidLaneException, InsufficientResourcesException {
//        SharedState sharedState = SharedState.getInstance();
//        int laneNumber = sharedState.getLaneNumber();  // To get the value
//
//        Lane lane = easyGame.getLane(laneNumber);
//        easyGame.purchaseWeapon(4 , lane);
//
//        Optional<Image> overlayImageOpt = loadImage("WeaponShop/mine.png");
//        if (overlayImageOpt.isPresent()) {
//            ImageView overlayImageView = new ImageView(overlayImageOpt.get());
//            GridPane gridPane = (GridPane) lanes;
//            gridPane.add(overlayImageView, 0, getRow());
//        } else {
//            System.out.println("Image not found: WeaponShop/mine.png");
//        }
//    }

    public void closeShop(ActionEvent event) {
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the stage
        stage.close();
    }

}
