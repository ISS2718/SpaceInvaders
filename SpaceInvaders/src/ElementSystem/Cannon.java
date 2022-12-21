package ElementSystem;

import java.util.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
        super(size.getX()/2, size.getY(), speed, size);
        this.bullet = new Bullet(1.0, size);
        sprite = new Sprite("sprites/canhao.png");
    }
    
    /**
     * 
     * 
     * @param x_coordinate
     */
    public void draw(AnchorPane main) {
        sprite.getImageView().setLayoutX(coordinates.getX());
        sprite.getImageView().setLayoutY(coordinates.getY() - sprite.getImageView().getImage().getHeight());
        main.getChildren().add(sprite.getImageView());
    }
    
    public void destructor(AnchorPane main) {
        main.getChildren().remove(sprite.getImageView());
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public void moveLeft() {
        if(coordinates.getX()  !=  0) {
            if((coordinates.getX() - speed)  <  0) {
                coordinates.setX(0);
            } else if (coordinates.getX()  >  0) {
                coordinates.setX(coordinates.getX() - speed);
            }
        }
    }
    
        public void moveRight() {
        if((coordinates.getX() +  sprite.getImageView().getImage().getWidth())  !=  screen_size.getX()) {
            if(((coordinates.getX() +  sprite.getImageView().getImage().getWidth()) + speed)  >  screen_size.getX()) {
                coordinates.setX(screen_size.getX() -  sprite.getImageView().getImage().getWidth());
            } else if (((coordinates.getX() +  sprite.getImageView().getImage().getWidth()) + speed)  <  screen_size.getX()) {
                coordinates.setX(coordinates.getX() + speed);
            }
        }
    }
}