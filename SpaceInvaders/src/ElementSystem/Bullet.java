package ElementSystem;

import java.util.*;

/**
 * 
 */
public class Bullet extends Move {
    /**
    */ 
    private boolean flagShot;
    
    /**
     * 
     */
    private Sprite sprite;

    /**
     * Default constructor
     */
    public Bullet(double speed, Coordinates size) {
        super(0.0, 0.0, speed, size);
        flagShot = false;
        sprite = new Sprite("sprites/tiro.png");
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
        //System.out.print(sprite.getSprite());
    } 
    
    /**
     *
     */
    public void move() {
        coordinates.setY(coordinates.getY() + speed);
    }

    /**
     * 
     * @param ini_coordinates
     * @param max_coordinates
     * @param shoted
     */
    public void shot(Coordinates ini_coordinates, Coordinates max_coordinates, boolean shoted) {
        if(flagShot == false) {
            flagShot = true;
            this.coordinates.setCoordinates(ini_coordinates);
            move();            
        } else if (ini_coordinates.equals(max_coordinates) && (shoted == false)) {
            this.move();
        } else {
            flagShot = false;
        }
    }
}