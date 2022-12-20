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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author isaac
 */
public class ScreenFXMLController implements Initializable {
    private Game g;
    
    @FXML
    private AnchorPane main;

    @FXML
    private Button m_Start;

    @FXML
    private Button m_Tutorial;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g = new Game(main.getWidth(), main.getHeight());
    }    
    
}
