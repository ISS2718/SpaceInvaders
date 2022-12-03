package Engine;

import ElementSystem.*;

import java.util.Random;



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
    public Game() {
        screen = new Screen(50, 20);
        aliensMatrix = new AliensMatrix(11, screen.getSize());
        cannon = new Cannon(1.0, screen.getSize());
        player = new Player(5);
        have_spaceShip = false;
    }

    /**
     */
    public void gameLoop() {
        int loop = 0;
        Random r = new Random();
        while(true) {
            screen.drawScreen(this);
            try { Thread.sleep(500); } catch (Exception e) {}
            aliensMatrix.move(new Coordinates(screen.getSize().getX(),0));
            if((loop > 2 * screen.getSize().getX() + 1) && (have_spaceShip == false)) {
                spaceShip = new Spaceship(r.nextBoolean(), screen.getSize());
                have_spaceShip = r.nextBoolean();
            } else if (have_spaceShip == true) {
                spaceShip.move();
                if(((spaceShip.getCoordinates().getX()) == 0.0) || ((spaceShip.getCoordinates().getX()) == (screen.getSize().getX()))) {
                    have_spaceShip = false;
                }
            }
            loop++;
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