/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import Engine.Game;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author isaac
 */
public class ScreenFXMLController implements Initializable {

    private Game g;
    
    private boolean onMenu;
    
    @FXML
    private AnchorPane pane_jogo;
    
    @FXML
    private ImageView menu;
     
    @FXML
    private AnchorPane main;
    
    @FXML
    private Label coppy_right;
       
    @FXML
    private Button b_Start;

    @FXML
    private Button b_Tutorial;
    
    
    @FXML
    private Label label_score;

    @FXML
    private Label life;
    
    @FXML
    private Label text_score;
    
    public void menuEnable() {
        menu.setVisible(true);
        coppy_right.setVisible(true);
        b_Start.setVisible(true);
        b_Start.setDisable(false);
        b_Tutorial.setVisible(true);
        b_Tutorial.setDisable(false);
    }
    
    public void menuDisable() {
        menu.setVisible(false);
        coppy_right.setVisible(false);
        b_Start.setVisible(false);
        b_Start.setDisable(true);
        b_Tutorial.setVisible(false);
        b_Tutorial.setDisable(true);
    }
    
    public void menuReturn() {
        g.gameLoopStop();
        g.destructor(main, pane_jogo);
        menuEnable();
        onMenu = true;
    }
    
    public void start() {       
        menuDisable();
        onMenu = false;

        g = new Game(pane_jogo.getPrefWidth(), pane_jogo.getPrefHeight(), life, label_score, text_score);
        
        g.draw(main, pane_jogo);
        
        g.gameLoopStart();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onMenu = true;
    }    
    
    public void  setupKeyListner(Scene scene) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                case KP_LEFT:
                    if(!onMenu) {
                        g.setPressedLEFT();
                    }
                    break;
                case RIGHT:
                case KP_RIGHT:
                    if(!onMenu) {
                        g.setPressedRIGHT();
                    }
                    break;
                case SPACE:
                    if(!onMenu) {
                        g.setPressedSPACE();
                    }
                    break;
                case ESCAPE:
                    if(!onMenu) {
                        menuReturn();
                    } else  {
                        System.exit(0);
                    }
                    break;
                default:
                    break;
            }
        });
    }
}
