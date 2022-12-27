package ElementSystem;

import java.util.*;
import javafx.scene.layout.AnchorPane;

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
    boolean isCannon;

    /**
     * 
     */
    private double max_Y_coordinates;
    
    /**
     */
    private boolean missedShot;
    
    /**
     * 
     */
    private Sprite sprite;
    
    /**
     * Default constructor
     */
    public Bullet(double speed, Coordinates size, boolean isCannon) {
        super(0.0, 0.0, speed, size);
        flagShot = false;
        this.isCannon = isCannon;
        
        if(isCannon) {
            max_Y_coordinates = 0;
        } else {
            max_Y_coordinates = size.getY();
        }
        
        sprite = new Sprite("sprites/tiro.png");
        
    }

    /**
     * 
     * 
     * @param x_coordinate
     */
    public void draw(AnchorPane main) {
        sprite.getImageView().setLayoutX(0);
        sprite.getImageView().setLayoutY(0);
        
        
        if(isCannon) {
            sprite.getImageView().setX(0);
            sprite.getImageView().setY((screen_size.getY() - sprite.getImageView().getImage().getHeight()));
        } else {
            sprite.getImageView().setX((screen_size.getX() - sprite.getImageView().getImage().getWidth()));
            sprite.getImageView().setY(0);
        }
        
        main.getChildren().add(sprite.getImageView());
        sprite.getImageView().setVisible(false);
    }
    
    public void drawMove() { 
        if(flagShot) {
            if(isCannon) {
                sprite.getImageView().setY((coordinates.getY() - sprite.getImageView().getImage().getHeight()));   
            } else {
                sprite.getImageView().setY((coordinates.getY() + sprite.getImageView().getImage().getHeight()));
            }
        } else {
            sprite.getImageView().setX(coordinates.getX());
        }        
    }
    
    /**
     * 
     * @return 
     */
    public boolean getFlagShot() {
        return flagShot;
    }
    
    /**
     *
     */
    public void move() {
        if(isCannon) {
            System.out.println("Coordenada Y: " + (coordinates.getY() -  sprite.getImageView().getImage().getHeight()) + ". Coordenada max Y: " + max_Y_coordinates);
            if((coordinates.getY() - sprite.getImageView().getImage().getHeight())  > max_Y_coordinates) {
                coordinates.setY(coordinates.getY()  -  speed);
            } else {
                missedShot = true;
            }
        } else {
            System.out.println("Coordenada Y: " + (coordinates.getY() + sprite.getImageView().getImage().getHeight()) + ". Coordenada max Y: " + max_Y_coordinates);
            if ((coordinates.getY() + sprite.getImageView().getImage().getHeight()) > max_Y_coordinates) {
                coordinates.setY(coordinates.getY() + speed);
            } else {
                missedShot = true;
            }
        }
        drawMove();
    }

    /**
     * 
     * @param ini_coordinates
     * @param max_coordinates
     * @param shoted
     */
    public void shot(Coordinates ini_coordinates, boolean shoted) {
        if(flagShot == false) {
            this.coordinates.setCoordinates(ini_coordinates);
            drawMove();
            sprite.getImageView().setVisible(true);
            flagShot = true;
            missedShot = false;            
        } else if ((shoted == false) && (missedShot == false)) {
            move();
        } else {
            flagShot = false;
            sprite.getImageView().setVisible(false);
        }
    }
}