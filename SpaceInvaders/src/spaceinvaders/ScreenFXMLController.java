/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import Engine.Game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author isaac
 */
public class ScreenFXMLController implements Initializable {

    private Game g;
    
    private boolean onMenu;
    
    private boolean pressedLEFT;
    private boolean pressedRIGHT;
    private boolean pressedSPACE;
    
    private AnimationTimer timer;
    
    @FXML
    private AnchorPane main;
    
    @FXML
    private ImageView menu;
      
    @FXML
    private Label coppy_right;
        
    @FXML
    private Button b_Start;

    @FXML
    private Button b_Tutorial;
    
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
        timer.stop();
        g.getCannon().destructor(main);
        g.getAliensMatrix().destructor(main);
        menuEnable();
        onMenu = true;
    }
    
    public void start() {
        /*
        try {
            // carrega FXML e monta cena
            Parent root = FXMLLoader.load(getClass().getResource("StartFXML.fxml"));
            Scene scene = new Scene(root);
            
            scene.setOnKeyPressed(new EventHandler<KeyEvent> () {
               @Override
               public void handle(KeyEvent event) {
                   System.out.println(event.getCode());
                   switch (event.getCode()) {
                       case LEFT:
                       case KP_LEFT:
                           System.out.println("to the left");
                           break;
                       case RIGHT:
                       case KP_RIGHT:
                           System.out.println("to the right");
                           break;
                       case SPACE:
                           System.out.println("SPACEEEEEEEEEEE");
                           break;
                        case ESCAPE:
                           System.out.println("ESCAPEEEEEEEEEE");
                           menuReturn();
                           break;
                       default:
                           break;
                   }
               }
           });

            // troca a cena: de login para principal
            stage.setTitle("Space Invaders");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        */
        menuDisable();
        onMenu = false;
        
        main.getScene().setOnKeyPressed(new EventHandler<KeyEvent> () {
                @Override
                public void handle(KeyEvent event) {
                     switch (event.getCode()) {
                         case LEFT:
                         case KP_LEFT:
                             if(!onMenu) {
                                   pressedLEFT = true;
                             }
                             break;
                         case RIGHT:
                         case KP_RIGHT:
                             if(!onMenu) {
                                  pressedRIGHT = true;
                              }  
                             break;
                         case SPACE:
                             if(!onMenu) {
                                  pressedSPACE = true;
                             }
                             break;
                          case ESCAPE:
                             if(!onMenu) {
                                  menuReturn();
                              }
                             break;
                         default:
                             break;
                     }
                }
         });
        
        g = new Game(main.getPrefWidth(), main.getPrefHeight());
        
        g.getCannon().draw(main);
        g.getAliensMatrix().draw(main);
        
        timer.start();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pressedLEFT = false;
        pressedRIGHT = false;
        pressedSPACE = false;
        
        onMenu = true;
        
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                g.getAliensMatrix().move();
                g.checkColision();
                
                if(pressedLEFT) {
                    g.getCannon().moveLeft();
                    pressedLEFT = false;
                }
                
                if (pressedRIGHT) {
                    g.getCannon().moveRight();
                    pressedRIGHT = false;
                }
                
                if (pressedSPACE) {
                    g.getCannon().getBullet().shot( g.getCannon().getCoordinates());
                    if(g.getCannon().getBullet().getFlagShot() == false) {
                        pressedSPACE = false;
                    }
                }
                
            }
        };
    }    
    
}
