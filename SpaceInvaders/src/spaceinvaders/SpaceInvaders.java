/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author isaac
 */
public class SpaceInvaders extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreenFXML.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        ScreenFXMLController Controller = loader.getController();
        Controller.setupKeyListner(scene);
        
        stage.setResizable(false);
        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
