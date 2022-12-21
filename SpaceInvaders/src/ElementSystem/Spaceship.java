package ElementSystem;

import java.util.*;
import javafx.scene.layout.AnchorPane;

/**
 * 
 */
public class Spaceship extends Move {
    /**
     * 
     */
    private boolean front_move;
    
    /**
    * 
    */
    private final Sprite sprite;
    
    /**
     * 
     * @param front_move
     */
    public Spaceship(boolean front_move, Coordinates size) {
        super((front_move ? 0.0 : size.getX()), 0, 2.0, size);      
        this.front_move = front_move;
        sprite = new Sprite("sprites/nave.png");
    }
   
    /**
     * 
     * 
     * @param x_coordinate
     */
   public void draw(AnchorPane main) {
        sprite.getImageView().setLayoutX(coordinates.getX() - sprite.getImageView().getImage().getWidth());
        sprite.getImageView().setLayoutY(coordinates.getY());
        main.getChildren().add(sprite.getImageView());
    }
   
    public void destructor(AnchorPane main) {
        main.getChildren().remove(sprite.getImageView());
    }   
    
    /**
     * 
     * @param max_coordinates 
     */
    public void move() {
         if(front_move == true) {
             if((coordinates.getX() + 1) < (screen_size.getX() - sprite.getImageView().getImage().getWidth())) {
                 coordinates.setX(coordinates.getX() + speed);
             }
        } else if((coordinates.getX() - 1) > 0) {
            coordinates.setX(coordinates.getX() - speed);
        }
    }
    
    /**
     * 
     * @param front_move 
     */
    public void setSideToMove(boolean front_move) {
        if(front_move) {
               coordinates.setX(0.0);
        } else {
            coordinates.setX(screen_size.getX() - sprite.getImageView().getImage().getWidth());
        }
    }
}