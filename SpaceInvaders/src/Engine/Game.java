package Engine;

import ElementSystem.*;
import javafx.scene.control.Label;

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
    public Game(double width, double height, Label label_life, Label label_score, Label text_score) {
        screen = new Screen(width, height);
        aliensMatrix = new AliensMatrix(11,  20, 0.1, screen.getSize());
        cannon = new Cannon(15, screen.getSize());
        player = new Player(4, 3, label_life, label_score, text_score);
        have_spaceShip = false;
        barriers = new Barriers(4, screen.getSize());
    }
    
    public void checkColision() {
        //colisão dos aliens com as barreiras
        aliensMatrix.checkColisionWithBarrier(barriers);
        
        
        //colisão do tiro do canhão
        if(cannon.getBullet().getFlagShot()) {
            if (barriers.checkColision(cannon.getBullet().getCoordinates(), cannon.getBullet().getSprite())) {
                cannon.getBullet().setShoted();
                player.decreasesLifes();
            } else if(aliensMatrix.checkColisionWithBullet(cannon.getBullet().getCoordinates(), cannon.getBullet().getSprite())) {
                cannon.getBullet().setShoted();
                player.increasesScore(100);
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