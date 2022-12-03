/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import Engine.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author isaac
 */
public class ScreenFXMLController implements Initializable {
    private Game g;
    private Thread game_loop;
    private static Runnable t1;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g = new Game();
        game_loop = new Thread();
    }    
    
}
