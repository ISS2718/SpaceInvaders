package ElementSystem;

import java.util.*;

/**
 * 
 * @author isaac
 */
public class Cannon extends Move {
    /**
     * 
     */
    private Bullet bullet;
    
    /**
     * 
     */
    private Sprite sprite;

    /**
     * Default constructor
     * 
     * @param speed
     */
    public Cannon(double speed, Coordinates size) {
        super(size.getX()/2, 0, 1.0);
        this.bullet = new Bullet(1.0);
        sprite = new Sprite('A');
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
}