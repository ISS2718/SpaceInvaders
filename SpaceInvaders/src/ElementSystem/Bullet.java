package ElementSystem;

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
    
    private boolean shoted;
    
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
        
        shoted =false;
        
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
       
        setInitialPosition();
        
        main.getChildren().add(sprite.getImageView());
        sprite.getImageView().setVisible(false);
    }
    
    public void destructor(AnchorPane main) {
        main.getChildren().remove(sprite.getImageView());
    }
    
    public void drawMove() { 
        if(flagShot) {
            if(isCannon) {
                sprite.getImageView().setY((coordinates.getY() - sprite.getImageView().getImage().getHeight()));   
            } else {
                sprite.getImageView().setY(coordinates.getY());
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
    
    public Sprite getSprite() {
        return sprite;
    }
    
    /**
     *
     */
    public void move() {
        if(isCannon) {
            //System.out.println("Coordenada Y: " + (coordinates.getY() -  sprite.getImageView().getImage().getHeight()) + ". Coordenada max Y: " + max_Y_coordinates);
            if((coordinates.getY() - sprite.getImageView().getImage().getHeight())  > max_Y_coordinates) {
                coordinates.setY(coordinates.getY()  -  speed);
            } else {
                missedShot = true;
            }
        } else {
            //System.out.println("Coordenada Y: " + (coordinates.getY() + sprite.getImageView().getImage().getHeight()) + ". Coordenada max Y: " + max_Y_coordinates);
            if ((coordinates.getY() + sprite.getImageView().getImage().getHeight()) < max_Y_coordinates) {
                
                coordinates.setY(coordinates.getY() + speed);
                
            } else {
                missedShot = true;
            }
        }
        drawMove();
    }
    
    public void setInitialPosition() {
         if(isCannon) {
            sprite.getImageView().setX(0);
            sprite.getImageView().setY((screen_size.getY() - sprite.getImageView().getImage().getHeight()));
        } else {
            sprite.getImageView().setX((screen_size.getX() - sprite.getImageView().getImage().getWidth()));
            sprite.getImageView().setY(0);
        }
    }
    
    public void setShoted() {
        shoted = true;
    }

    /**
     * 
     * @param ini_coordinates
     * @param max_coordinates
     * @param shoted
     */
    public void shot(Coordinates ini_coordinates, Sprite shoter_sprite) {
        if(flagShot == false) {
            coordinates.setX(ini_coordinates.getX() + shoter_sprite.getImageView().getImage().getWidth()/2);
            coordinates.setY(ini_coordinates.getY() - (shoter_sprite.getImageView().getImage().getHeight() - sprite.getImageView().getImage().getHeight()));
            drawMove();
            flagShot = true;
            missedShot = false;
            shoted = false;
            sprite.getImageView().setVisible(true);            
        } else if ((shoted == false) && (missedShot == false)) {
            move();
        } else {
            flagShot = false;
            sprite.getImageView().setVisible(false);
            setInitialPosition();
        }
    }
}