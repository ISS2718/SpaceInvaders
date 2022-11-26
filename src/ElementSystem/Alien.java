package ElementSystem;

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
    public Alien(int type) {
        super(0.0, 0.0, 1.0);
        this.type = type;
        setTypeSprite();
        bullet = new Bullet(1.0);
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
                sprite = new Sprite('@');
                break;
            case 2:
                sprite = new Sprite('$');
                break;
            case 3:
                sprite = new Sprite('&');
                break;
            default:
                sprite = new Sprite('@');
                break;
        }
    }
}