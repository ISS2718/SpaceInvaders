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
    private Barrier[] barriers;

    /**
     * 
     */
    private Cannon cannon;

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
     boolean have_spaceShip;
    
    /**
     * 
     */
    private Screen screen;

    /**
     * 
     */
    public Game(double width, double height) {
        screen = new Screen(width, height);
        aliensMatrix = new AliensMatrix(11, screen.getSize());
        cannon = new Cannon(1.0, screen.getSize());
        player = new Player(5);
        have_spaceShip = false;
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
    public Barrier[] getBarrier() {
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