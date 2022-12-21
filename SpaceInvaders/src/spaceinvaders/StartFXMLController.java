/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import Engine.Game;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isaac
 */
public class StartFXMLController implements Initializable {

    private Game g;
    
    private ImageView cannon;

    @FXML
    private AnchorPane main_start;
    
    
   public void menuReturn() {
        try {
            // carrega FXML e monta cena
            Parent root = FXMLLoader.load(getClass().getResource("ScreenFXML.fxml"));
            Scene scene = new Scene(root);
            
            // acessa palco corrente
            Stage stage = (Stage) main_start.getScene().getWindow();
            
            // troca a cena: de login para principal
            stage.setTitle("Space Invaders");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          g = new Game(main_start.getPrefWidth(), main_start.getPrefHeight());
    }    
    
}
