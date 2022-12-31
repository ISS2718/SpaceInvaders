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
    private boolean onGameOver;

    @FXML
    private Button b_Start;

    @FXML
    private Button b_Tutorial;

    @FXML
    private Label coppy_right;

    @FXML
    private Label label_score;

    @FXML
    private Label life;

    @FXML
    private AnchorPane main;

    @FXML
    private ImageView menu;

    @FXML
    private AnchorPane game_pane;
    
    @FXML
    private ImageView turotial_image;
    
    @FXML
    private Label text_score;

    
    public void menuEnable() {
        menu.setVisible(true);
        coppy_right.setVisible(true);
        b_Start.setVisible(true);
        b_Start.setDisable(false);
        b_Tutorial.setVisible(true);
        b_Tutorial.setDisable(false);
        turotial_image.setVisible(false);
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
        g.destructor(main, game_pane);
        menuEnable();
        onMenu = true;
    }
    
    public void start() {       
        menuDisable();
        onMenu = false;

        g = new Game(3, game_pane, life, label_score, text_score);
        
        g.draw(main, game_pane);
        
        g.gameLoopStart();
        
    }

    public void tutorial() {
        menuDisable();
        turotial_image.setVisible(true);
        g = new Game(3, game_pane, life, label_score, text_score);
        g.draw(main, game_pane);
        onMenu = false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onMenu = true;
        onGameOver = false;
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
                    if(!onMenu || onGameOver) {
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
