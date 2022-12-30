package Engine;

import ElementSystem.*;

/**
 * 
 */
public class Game {
    /**
     * 
     */
    private AliensMatrix aliensMatrix;

    /**
     * 
     */
    private Barriers barriers;

    /**
     * 
     */
    private Cannon cannon;
    
    /**
     *
     */
    boolean have_spaceShip;

    /**
     * 
     */
    private Player player;

    /**
     * 
     */
    private Spaceship spaceShip;
    
    /**
     * 
     */
    private Screen screen;

    /**
     * 
     */
    public Game(double width, double height) {
        screen = new Screen(width, height);
        aliensMatrix = new AliensMatrix(11,  0.4, screen.getSize());
        cannon = new Cannon(20, screen.getSize());
        player = new Player(5);
        have_spaceShip = false;
        barriers = new Barriers(3, screen.getSize());
    }
    
    public void checkColision() {
        if(cannon.getBullet().getFlagShot()) {
            if(aliensMatrix.checkColision(cannon.getBullet().getCoordinates(), cannon.getBullet().getSprite())) {
                cannon.getBullet().setShoted();
            }
            
            if (barriers.checkColision(cannon.getBullet().getCoordinates(), cannon.getBullet().getSprite())) {
                cannon.getBullet().setShoted();
            }
        }
    }

    /**
     * 
     * @return
     */
    public AliensMatrix getAliensMatrix() {
        return aliensMatrix;
    }

    /**
     * 
     * @return
     */
    public Barriers getBarriers() {
        return barriers;
    }

    /**
     * 
     * @return
     */
    public Cannon getCannon() {
        return cannon;
    }

    /**
     * 
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * 
     * @return
     */
    public Spaceship getSpaceship() {
        return spaceShip;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getHaveSpaceShip() {
        return have_spaceShip;
    }
}