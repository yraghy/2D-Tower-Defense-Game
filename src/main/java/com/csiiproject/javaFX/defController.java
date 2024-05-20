package com.csiiproject.javaFX;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

//Controller for pop-up window methods (mainly for button effects)
public class defController {

    public void shadow(MouseEvent e) throws IOException{
        Button b = (Button)e.getSource();
        b.setEffect(new DropShadow());
    }
}
