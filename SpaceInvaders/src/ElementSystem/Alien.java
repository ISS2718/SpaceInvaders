package ElementSystem;

import javafx.scene.layout.AnchorPane;

/**
 * This class implements the invading aliens. 
 * The aliens are identified by 3 different types (1, 2, 3)
 * where each type gets a different sprite and a different placement.
 * The Aliens class extends the Move class which has the coordinate
 * methods and a standard move method.
 *  
 * @see Move
 * @author isaac
 */
public class Alien extends Move {
    /**
     * 
     */
    boolean isAlive;
    
    /**
     * Alien type:
     * 1 - type one charactere ('sprite'): @ -> Bot position. 
     * 2 - type two charactere ('sprite'): & -> Mid position
     * 3 - type three charactere ('sprite'): $ -> Top position
     */
    private int type;

    /**
     * This is Alien bullet.
     *  
     * @see Bullet
     */
    private  Bullet bullet;

    /**
     * This is sprite of alien.
     *  
     * @see Sprite
     */
    private Sprite sprite;

    

    /**
     * Default constructor for Alien.
     * It gets the alien's type and starts all attributes of the 
     * alien object with the default values and its proper sprite.
     * 
     * @param type type of alien (1, 2 or 3). Dwfault is type 1.
     */
    public Alien(int type,  double speed, Coordinates size) {
        super(0.0, 0.0, speed, size);
        this.type = type;
        setTypeSprite();
        bullet = new Bullet(1.0, size, false);
        isAlive = true;
    }
    
    public boolean checkColision(Coordinates coordinates_for_check, Sprite sprite_for_check) {
        boolean r = false;
        if (isAlive) {
            if (((coordinates.getY()) <= (coordinates_for_check.getY() - sprite_for_check.getImageView().getImage().getHeight()))
                    && (coordinates.getY() + sprite.getImageView().getImage().getHeight()) >= (coordinates_for_check.getY() - sprite_for_check.getImageView().getImage().getHeight())) {

                if (((coordinates.getX()) <= coordinates_for_check.getX())
                        && (coordinates.getX() + sprite.getImageView().getImage().getWidth()) >= coordinates_for_check.getX()) {
                    System.out.println(coordinates.getX() +  ", " + coordinates.getY());
                    setDead();
                    r = true;
                }
            }
        }
        return r;
    }
    
    public void draw(AnchorPane main) {
        sprite.getImageView().setLayoutX(0);
        sprite.getImageView().setLayoutY(0);
        sprite.getImageView().setX(coordinates.getX());
        sprite.getImageView().setY(coordinates.getY());
        main.getChildren().add(sprite.getImageView());
        bullet.draw(main);
    }
    
    public void drawMove() {
        sprite.getImageView().setX(coordinates.getX());
        sprite.getImageView().setY(coordinates.getY());
    }
    
     public void destructor(AnchorPane main) {
         main.getChildren().remove(sprite.getImageView());
     }
    
    /**
     * 
     * @return 
     */
    public Bullet getBullet() {
        return bullet;
    }
    
    public boolean getIsAlive() {
        return isAlive;
    }

    public void setAlive() {
        isAlive = true;
        sprite.getImageView().setVisible(true);
    }
    
    public void setDead() {
        isAlive = false;
        sprite.getImageView().setVisible(false);
    }
    
    /**
     * Get the alien sprite and return to user.
     * 
     * @return sprite of alien.
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Defines the alien sprite depending on the received type.
     */
    private void setTypeSprite() {
        switch (type) {
            case 1:
                sprite = new Sprite("sprites/alien1.png");
                break;
            case 2:
                sprite = new Sprite("sprites/alien2.png");
                break;
            case 3:
                sprite = new Sprite("sprites/alien3.png");
                break;
            default:
                sprite = new Sprite("sprites/alien1.png");
                break;
        }
    }
}