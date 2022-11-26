package ElementSystem;

import java.util.*;

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
     private final double max_x_coordinate;
    
    /**
    * 
    */
    private final Sprite sprite;
    
    /**
     * 
     * @param front_move
     */
    public Spaceship(boolean front_move, Coordinates size) {
        super((front_move ? 0.0 : size.getX()), size.getY(), 2.0);      
        this.front_move = front_move;
        max_x_coordinate = size.getX();
        sprite = new Sprite('V');
    }
   
    /**
     * 
     * 
     * @param x_coordinate
     */
    public void draw(double x_coordinate) {
        for(int j = 0; j < x_coordinate; j++) {
            System.out.print(' ');
        }
        System.out.print(sprite.getSprite());
    } 
    
    /**
     * 
     * @param max_coordinates 
     */
    public void move() {
         if(front_move == true) {
             if((coordinates.getX() + 1) < max_x_coordinate) {
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
            coordinates.setX(max_x_coordinate);
        }
    }
}