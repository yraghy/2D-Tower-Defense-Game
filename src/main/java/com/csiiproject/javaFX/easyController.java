package com.csiiproject.javaFX;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class easyController {
    //Static variables
    static Parent rootE;
    Battle easyGame;
    @FXML
    static Pane titanSpawner1, titanSpawner2, titanSpawner3;
    private Label eScore, eRes, eTurn, ePhase;
    private ProgressBar wallH1, wallH2, wallH3;
    private ArrayList<Lane> lanesList;
    ArrayList<Lane> lanesOrder;
    private Lane lane1, lane2, lane3;
    private Label laneDanger1, laneDanger2, laneDanger3;
    PriorityQueue<Lane> lanes, lanesClone;
    Lane currentLane;
    GridPane grid;
    GridPane wall1W;
    MediaPlayer mediaPlayer;

    public void initEasy(Parent root) throws IOException {
        rootE = root;
        grid = (GridPane) rootE.lookup("#lanes");
        wall1W =  (GridPane) rootE.lookup("#wall1W");
        easyGame = new Battle(1,0,9,3,250);
        initStats();
        initLanes();
        initWalls();
        titanSpawner1 = (Pane) rootE.lookup("#titanSpawner1E");
        titanSpawner2 = (Pane) rootE.lookup("#titanSpawner2E");
        titanSpawner3 = (Pane) rootE.lookup("#titanSpawner3E");
        lanesOrder = new ArrayList<>(List.copyOf(easyGame.getOriginalLanes()));
        String path = "src/main/resources/com/csiiproject/javaFX/Soul of Cinder.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.1);
    }

    //Initialization methods
    public void initStats(){
        eScore = (Label) rootE.lookup("#score");
        eRes = (Label) rootE.lookup("#resources");
        eTurn = (Label) rootE.lookup("#turn");
        ePhase = (Label) rootE.lookup("#phase");
        eScore.setText("Score: " + easyGame.getScore());
        eRes.setText("Resources: " + easyGame.getResourcesGathered());
        eTurn.setText("Turn: " + easyGame.getNumberOfTurns());
        ePhase.setText("Phase: " + easyGame.getBattlePhase());
    }
    public void initLanes(){
        lanesList = new ArrayList<>();
        lanesList.addAll(easyGame.getLanes());
        lane1 = lanesList.get(0);
        lane2 = lanesList.get(1);
        lane3 = lanesList.get(2);
        laneDanger1 = (Label) rootE.lookup("#eDangerLane1");
        laneDanger2 = (Label) rootE.lookup("#eDangerLane2");
        laneDanger3 = (Label) rootE.lookup("#eDangerLane3");
        laneDanger1.setText("Danger: " + lane1.getDangerLevel());
        laneDanger2.setText("Danger: " + lane2.getDangerLevel());
        laneDanger3.setText("Danger: " + lane3.getDangerLevel());
    }
    public void initWalls(){
        wallH1 = (ProgressBar) rootE.lookup("#eWallH1");
        wallH2 = (ProgressBar) rootE.lookup("#eWallH2");
        wallH3 = (ProgressBar) rootE.lookup("#eWallH3");
        wallH1.setProgress(1);
        wallH2.setProgress(1);
        wallH3.setProgress(1);
        wallH1.setStyle("-fx-accent: #14ec14;");
        wallH2.setStyle("-fx-accent: #14ec14;");
        wallH3.setStyle("-fx-accent: #14ec14;");
    }
    public void initSpawn(){
        titanSpawner1 = (Pane) rootE.lookup("#titanSpawner1");
        titanSpawner2 = (Pane) rootE.lookup("#titanSpawner2");
        titanSpawner3 = (Pane) rootE.lookup("#titanSpawner3");
    }

    public void updateLabels() {
        eScore.setText("Score: " + easyGame.getScore());
        eRes.setText("Resources: " + easyGame.getResourcesGathered());
        eTurn.setText("Turn: " + easyGame.getNumberOfTurns());
        ePhase.setText("Phase: " + easyGame.getBattlePhase());
        if(lane1.isLaneLost()) {
            laneDanger1.setText("Lane Lost");
            laneDanger1.setTextFill(Color.RED);
        }
        if(lane2.isLaneLost()) {
            laneDanger2.setText("Lane Lost");
            laneDanger2.setTextFill(Color.RED);
        }
        if(lane3.isLaneLost()) {
            laneDanger3.setText("Lane Lost");
            laneDanger3.setTextFill(Color.RED);
        }
        if(!lane1.isLaneLost())
            laneDanger1.setText("Danger: " + lane1.getDangerLevel());
        if(!lane2.isLaneLost())
            laneDanger2.setText("Danger: " + lane2.getDangerLevel());
        if(!lane3.isLaneLost())
            laneDanger3.setText("Danger: " + lane3.getDangerLevel());
        double wall1 = (double) (lane1.getLaneWall().getCurrentHealth()) /lane1.getLaneWall().getBaseHealth();
        double wall2 = (double) (lane2.getLaneWall().getCurrentHealth()) /lane2.getLaneWall().getBaseHealth();
        double wall3 = (double) (lane3.getLaneWall().getCurrentHealth()) /lane3.getLaneWall().getBaseHealth();
        wallH1.setProgress(wall1);
        wallH2.setProgress(wall2);
        wallH3.setProgress(wall3);
    }

    public void passTurn(ActionEvent e) throws IOException {
        easyGame.passTurn();
        spawnTitans();
        updateLabels();
        if(easyGame.isGameOver()){
            switchLoss(e);
        }
    }

    public void switchLoss(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
        Parent rootL = loader.load();
        Scene lossScene = new Scene(rootL);
        lossScene.getStylesheets().add(this.getClass().getResource("GameOver.css").toExternalForm());
        Stage window = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow();
        gameOverController gameOverController = loader.getController();
        int finalScore = easyGame.getScore();
        gameOverController.initLossScreen(rootL, finalScore, mediaPlayer);
        window.setScene(lossScene);
        window.show();
    }

    //Buy methods
    public void selectLane1(ActionEvent e) throws IOException {
        Lane lane1 = lanesOrder.get(0);
        if(!lane1.isLaneLost())
            currentLane = lanesOrder.get(0);
        else{
            AlertBox.invalidLanePop("Invalid Lane");
        }
    }
    public void selectLane2(ActionEvent e) throws IOException {
        Lane lane1 = lanesOrder.get(1);
        if(!lane1.isLaneLost())
            currentLane = lanesOrder.get(1);
        else{
            AlertBox.invalidLanePop("Invalid Lane");
        }
    }
    public void selectLane3(ActionEvent e) throws IOException {
        Lane lane1 = lanesOrder.get(2);
        if(!lane1.isLaneLost())
            currentLane = lanesOrder.get(2);
        else{
            AlertBox.invalidLanePop("Invalid Lane");
        }
    }

    public void buyATS(ActionEvent e) throws IOException, InvalidLaneException, InsufficientResourcesException {
        try {
            easyGame.purchaseWeapon(1, currentLane);
            spawnTitans();
            updateLabels();
            if(easyGame.isGameOver()){
                switchLoss(e);
            }
            Image ATS = new Image("antiTitanShell.png");
            ImageView weapon = new ImageView(ATS);
            wall1W.add(weapon,1,0);
        }
        catch(InsufficientResourcesException InsRes){
            System.out.println(InsRes.getMessage());
            AlertBox.insuffResourcesPop("Insufficient Resources");
        }
        catch(InvalidLaneException InvLn){
            System.out.println(InvLn.getMessage());
            AlertBox.invalidLanePop("Invalid Lane");

        }
    }
    public void buyLRS(ActionEvent e) throws IOException, InvalidLaneException, InsufficientResourcesException {
        try {
            easyGame.purchaseWeapon(2, currentLane);
            spawnTitans();
            updateLabels();
            if(easyGame.isGameOver()){
                switchLoss(e);
            }
            Image LRS = new Image("spear.png");
            ImageView weapon = new ImageView(LRS);
            wall1W.add(weapon,1,0);
        }
        catch(InsufficientResourcesException InsRes){
            System.out.println(InsRes.getMessage());
            AlertBox.insuffResourcesPop("Insufficient Resources");
        }
        catch(InvalidLaneException InvLn){
            System.out.println(InvLn.getMessage());
            AlertBox.invalidLanePop("Invalid Lane");

        }
    }
    public void buyWSC(ActionEvent e) throws IOException, InvalidLaneException, InsufficientResourcesException {
        try {
            easyGame.purchaseWeapon(3, currentLane);
            spawnTitans();
            updateLabels();
            if(easyGame.isGameOver()){
                switchLoss(e);
            }
            Image WSC = new Image("cannon.png");
            ImageView weapon = new ImageView(WSC);
            wall1W.add(weapon,1,0);
        }
        catch(InsufficientResourcesException InsRes){
            System.out.println(InsRes.getMessage());
            AlertBox.insuffResourcesPop("Insufficient Resources");
        }
        catch(InvalidLaneException InvLn){
            System.out.println(InvLn.getMessage());
            AlertBox.invalidLanePop("Invalid Lane");

        }
    }
    public void buyPT(ActionEvent e) throws IOException, InvalidLaneException, InsufficientResourcesException {
        try {
            easyGame.purchaseWeapon(4, currentLane);
            spawnTitans();
            updateLabels();
            if(easyGame.isGameOver()){
                switchLoss(e);
            }
            Image PT = new Image("mine.png");
            ImageView weapon = new ImageView(PT);
            wall1W.add(weapon,1,0);
        }
        catch(InsufficientResourcesException InsRes){
            System.out.println(InsRes.getMessage());
            AlertBox.insuffResourcesPop("Insufficient Resources");
        }
        catch(InvalidLaneException InvLn){
            System.out.println(InvLn.getMessage());
            AlertBox.invalidLanePop("Invalid Lane");

        }
    }

    public void spawnTitans(){
        ArrayList<Lane> lanesAR = new ArrayList<Lane>(List.copyOf(easyGame.getLanes()));
        int len = lanesAR.size();
        int tCount = 0;
        for(int i=0; i<len; i++){
            Lane lane = lanesAR.get(i);
            PriorityQueue<Titan> titansPQ = lane.getTitans();
            for(Titan titan:titansPQ) {
                Circle spawn = new Circle();
                int height = titan.getHeightInMeters();
                int distance = titan.getDistance();
                if(titan instanceof PureTitan) {
                    spawn.setFill(Color.BEIGE);
                    spawn.setRadius(titan.getHeightInMeters());
                    spawn.setTranslateX(distance * 20);
                    spawn.setTranslateY(tCount * titan.getHeightInMeters() * 220);
//                    spawn.setLayoutX(870);
//                    spawn.setLayoutY(100);
                    spawn.toFront();
                    Label hBar = new Label("Health: " + titan.getCurrentHealth() +"%");
                    hBar.setTextFill(Color.GREEN);
                    hBar.setTranslateX(titan.getDistance() * 20);
                    hBar.setTranslateY(tCount * 220 + titan.getHeightInMeters() - 20);
                    hBar.toFront();
                    if (i == 0) {
                        titanSpawner1.getChildren().add(spawn);
                    } else if (i == 1) {
                        titanSpawner2.getChildren().add(spawn);
                    } else {
                        titanSpawner3.getChildren().add(spawn);
                    }
                }
                else if(titan instanceof AbnormalTitan) {
                    spawn.setFill(Color.ORANGE);
                    spawn.setRadius(titan.getHeightInMeters());
                    spawn.setTranslateX(distance * 20);
                    spawn.setTranslateY(tCount * titan.getHeightInMeters() * 220);
                    Label hBar = new Label("Health: " + titan.getCurrentHealth() +"%");
                    hBar.setTextFill(Color.GREEN);
                    hBar.setTranslateX(titan.getDistance() * 20);
                    hBar.setTranslateY(tCount * 220 + titan.getHeightInMeters() - 20);
                    if (i == 0) {
                        titanSpawner1.getChildren().add(spawn);
                    } else if (i == 1) {
                        titanSpawner2.getChildren().add(spawn);
                    } else {
                        titanSpawner3.getChildren().add(spawn);
                    }
                }
                else if(titan instanceof ArmoredTitan) {
                    spawn.setFill(Color.YELLOW);
                    spawn.setRadius(titan.getHeightInMeters());
                    spawn.setTranslateX(distance * 20);
                    spawn.setTranslateY(tCount * titan.getHeightInMeters() * 220);
                    Label hBar = new Label("Health: " + titan.getCurrentHealth() +"%");
                    hBar.setTextFill(Color.GREEN);
                    hBar.setTranslateX(titan.getDistance() * 20);
                    hBar.setTranslateY(tCount * 220 + titan.getHeightInMeters() - 20);

                    if (i == 0) {
                        titanSpawner1.getChildren().add(spawn);
                    } else if (i == 1) {
                        titanSpawner2.getChildren().add(spawn);
                    } else {
                        titanSpawner3.getChildren().add(spawn);
                    }
                }
                else if(titan instanceof ColossalTitan){
                    spawn.setFill(Color.RED);
                    spawn.setRadius(titan.getHeightInMeters());
                    spawn.setTranslateX(distance * 20);
                    spawn.setTranslateY(tCount * titan.getHeightInMeters() * 220);
                    Label hBar = new Label("Health: " + titan.getCurrentHealth() +"%");
                    hBar.setTextFill(Color.GREEN);
                    hBar.setTranslateX(titan.getDistance() * 20);
                    hBar.setTranslateY(tCount * 220 + titan.getHeightInMeters() - 20);
                    if (i == 0) {
                        titanSpawner1.getChildren().add(spawn);
                    } else if (i == 1) {
                        titanSpawner2.getChildren().add(spawn);
                    } else {
                        titanSpawner3.getChildren().add(spawn);
                    }
                }
            }
            tCount++;
        }
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
    }

    public void Light(javafx.scene.input.MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #ffe100;");
    }
}
