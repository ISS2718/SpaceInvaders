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
     * Default constructor for Alien.It gets the alien's type and starts all attributes of the 
     *  alien object with the default values and its proper sprite.
     * 
     * @param type type of alien (1, 2 or 3). Dwfault is type 1.
     * @param speed speed of the aliens.
     * @param size screen size.
     */
    public Alien(int type,  double speed, Coordinates size) {
        super(0, 0, speed, size);
        this.type = type;
        setTypeSprite();
        bullet = new Bullet(speed, size, false);
        isAlive = true;
    }
    
    /**
     * 
     * Checks for collision of objects.
     * 
     * @param coordinates_for_check coordinates for the object collision check.
     * @param sprite_for_check sprite to get the collision area of the object.
     * @return returns points from the dead aliens. 
     */
    public int checkColision(Coordinates coordinates_for_check, Sprite sprite_for_check) {
        double lower_limit = (coordinates.getY() + sprite.getImageView().getImage().getHeight());
        double upper_limit = (coordinates.getY() - sprite.getImageView().getImage().getHeight());
        double left_limit = coordinates.getX();
        double rigth_limit = (coordinates.getX() + sprite.getImageView().getImage().getWidth());

        double lower_limit_for_check = (coordinates_for_check.getY() + sprite_for_check.getImageView().getImage().getHeight());
        double upper_limit_for_check = (coordinates_for_check.getY() - sprite_for_check.getImageView().getImage().getHeight());
        double left_limit_for_check = coordinates_for_check.getX();
        double rigth_limit_for_check = (coordinates_for_check.getX() + sprite_for_check.getImageView().getImage().getWidth());

        int r = 0;
        if (isAlive) {
            if (((upper_limit <= upper_limit_for_check) && (coordinates.getY() >= upper_limit_for_check))
                    || ((coordinates.getY() <= lower_limit_for_check) && (lower_limit >= lower_limit_for_check))) {

                if (((left_limit <= left_limit_for_check) && (rigth_limit >= left_limit_for_check))
                        || ((left_limit <= rigth_limit_for_check) && (rigth_limit >= rigth_limit_for_check))) {

                    setDead();
                    r = getPointWithKill();
                }
            }
        }
        return r;
    }
    
    
    /**
     * draws on the screen.
     * 
     * @param main screen to draw the sprite.
     */
    public void draw(AnchorPane main) {
        sprite.getImageView().setLayoutX(0);
        sprite.getImageView().setLayoutY(0);
        sprite.getImageView().setX(coordinates.getX());
        sprite.getImageView().setY(coordinates.getY());
        main.getChildren().add(sprite.getImageView());
        bullet.draw(main);
    }

    /**
     * moves the sprite already drawn earlier.
     */
    public void drawMove() {
        sprite.getImageView().setX(coordinates.getX());
        sprite.getImageView().setY(coordinates.getY());
    }
    
    /**
     * Removes the sprite from the screen.
     * 
     * @param main screen from which the sprite will be removed.
     */
     public void destructor(AnchorPane main) {
         bullet.destructor(main);
         main.getChildren().remove(sprite.getImageView());
     }
    
    /**
     *  Get the Bullet.
     * 
     * @return  return the Bullet.
     */
    public Bullet getBullet() {
        return bullet;
    }
    
    /**
     * Check to see if the alien is alive.
     * @return returns true if the alien is alive and false otherwise.
     */
    public boolean getIsAlive() {
        return isAlive;
    }
    
    /**
     * It takes the score according to the type of the alien.
     * 
     * @return returns the score according to the type of the dead alien.
     */
    public int getPointWithKill() {
        int r;
        switch(type) {
            case 1:
                r = 100;
                break;
            case 2:
                r = 150;
                break;
            case 3:
                r = 200;
                break;
            default:
                r = 100;
                break;
        }
        return r;
    }

    /**
     * Get the alien sprite and return to user.
     * 
     * @return sprite of alien.
     */
    public Sprite getSprite() {
        return sprite;
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