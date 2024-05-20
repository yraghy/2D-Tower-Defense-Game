//package com.csiiproject.javaFX;
//
//import game.engine.lanes.Lane;
//import game.engine.titans.*;
//import javafx.scene.control.Label;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//
//public class testing {
//
//    public void generateTitan() {
//
//        int c=0;
//        this.titanSpawner.getChildren().clear();
//        for (Lane lane:lanes ){                     //XXXXXXXXXXXXXXXXXXXX
//            for (Titan enemy:lane.getTitans() ) {
////            if (titan.hasReachedTarget()) {
////                titan.attack(lane.getLaneWall()); // Assuming Titan class has an attack method
////                // Update the TextArea content
////                healthTextArea.setText("Lane " + (lanes.indexOf(lane) + 1) + " Health: " + lane.getLaneWall().getCurrentHealth());
////            }
//                // Rectangle titanShape = new Rectangle(50, 50, Color.RED);
//                //   this.titan = enemy;
//
//
//                Circle titan=new Circle();
//
//                // Create a label to display the titan's current health
//                Label healthLabel = new Label("Health: " + enemy.getCurrentHealth() +"%");
//                healthLabel.setTextFill(Color.GREEN);
////            Text titanName = new Text(enemy.getName());
////            titanName.setTranslateX(enemy.getDistance()*20);
////            titanName.setTranslateY(c*220+enemy.getHeightInMeters()-20);
//                //  root.getChildren().add(titanName);
//
//                // Set the position of the health label to be above the titan
//                healthLabel.setTranslateX(enemy.getDistance() * 20);
//                healthLabel.setTranslateY(c * 220 + enemy.getHeightInMeters() - 20);
//
//
////XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX fix the method by  calcualting the distance of the map
//                if (enemy instanceof PureTitan) {
//
//                    titan.setFill(Color.RED);
//                    titan.setRadius(enemy.getHeightInMeters());
//
//                    titan.setTranslateX(enemy.getDistance()*20); // Randomly position the Titan on the x-axis
//                    titan.setTranslateY(c*220+enemy.getHeightInMeters()); // Randomly position the Titan on the y-axis
//                    //  titan = new Circle(enemy.getHeightInMeters(), Color.RED);
//                    //titan=setRadius(enemy.getHeightInMeters());
//
//                } else if (enemy instanceof AbnormalTitan) {
//                    // titan = new Circle(enemy.getHeightInMeters(), Color.BLUE);
//                    titan.setFill(Color.BLUE);
//                    titan.setRadius(enemy.getHeightInMeters());
//                    titan.setTranslateX(enemy.getDistance()*20);//titan.setCenterX(scene.getWidth()-250); // Randomly position the Titan on the x-axis
//                    titan.setTranslateY(c*220+enemy.getHeightInMeters());//titan.setCenterY(Math.random()*scene.getHeight()/2); // Randomly position the Titan on the y-axis
//                    //  titan = new Circle(enemy.getHeightInMeters(), Color.RED);
//                } else if (enemy instanceof ArmoredTitan){
//                    //titan = new Circle(enemy.getHeightInMeters(), Color.GOLD);
//                    titan.setFill(Color.GOLD);
//                    titan.setRadius(enemy.getHeightInMeters());
//                    titan.setTranslateX(enemy.getDistance()*20);//  titan.setCenterX(scene.getWidth()-350); // Randomly position the Titan on the x-axis
//                    titan.setTranslateY(c*220+enemy.getHeightInMeters());// titan.setCenterY(Math.random()*scene.getHeight()/2); // Randomly position the Titan on the y-axis
//                    //  titan = new Circle(enemy.getHeightInMeters(), Color.RED);
//                }
//                else if (enemy instanceof ColossalTitan){
//                    //titan = new Circle(enemy.getHeightInMeters(), Color.BLACK);
//                    titan.setFill(Color.BLACK);
//                    titan.setRadius(enemy.getHeightInMeters());
//                    titan.setTranslateX(enemy.getDistance()*20);// titan.setCenterX(scene.getWidth()-400); // Randomly position the Titan on the x-axis
//                    titan.setTranslateY(c*220+enemy.getHeightInMeters());// titan.setCenterY(Math.random()*scene.getHeight()/2); // Randomly position the Titan on the y-axis
//                    //  titan = new Circle(enemy.getHeightInMeters(), Color.RED);
//                }
//                this.titanSpawner.getChildren().add(titan);
//                this.titanSpawner.getChildren().add(healthLabel);
//                //titanName = new Text(enemy.getName());
////            titanName.setLayoutX(GameControl.getTitanSpawnDistance());
////            titanName.setLayoutY(GameControl.getTitanSpawnDistance());
////            root.getChildren().add(titanName);
//            }
//            c++;
//
//        }
//    }
//}
