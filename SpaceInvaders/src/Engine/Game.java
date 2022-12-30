package Engine;

import ElementSystem.*;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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
    
    private AnimationTimer gameloop;

    private int gameOverType;

    
    /**
     *
     */
    boolean have_spaceShip;

    /**
     * 
     */
    private Player player;
    
    private boolean pressedLEFT;
    private boolean pressedRIGHT;
    private boolean pressedSPACE;
    
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
        aliensMatrix = new AliensMatrix(11,  15, 0.2, screen.getSize());
        cannon = new Cannon(15, screen.getSize());
        player = new Player(3, 3, label_life, label_score, text_score);
        have_spaceShip = false;
        barriers = new Barriers(3, screen.getSize());
        
        gameOverType = 0;

        pressedLEFT = false;
        pressedRIGHT = false;
        pressedSPACE = false;
        
        gameloop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(aliensMatrix.move()) {
                
                    

                }
                checkColision();
                
                if(pressedLEFT) {
                    cannon.moveLeft();
                    pressedLEFT = false;
                }
                
                if (pressedRIGHT) {
                    cannon.moveRight();
                    pressedRIGHT = false;
                }
                
                if (pressedSPACE) {
                    cannon.getBullet().shot(cannon.getCoordinates(), cannon.getSprite());
                    if(cannon.getBullet().getFlagShot() == false) {
                        pressedSPACE = false;
                    }
                }
            }
        };
    }
    
    public void checkColision() {
        //collision of the aliens with the barriers
        checkgameOver(aliensMatrix.move());
        aliensMatrix.checkColisionWithBarrier(barriers);

        int incscore = 0;
        //collision of the cannon shot
        if(cannon.getBullet().getFlagShot()) {
            if (barriers.checkColision(cannon.getBullet().getCoordinates(), cannon.getBullet().getSprite())) {
                cannon.getBullet().setShoted();
                player.decreasesLifes();
            } else if((incscore = aliensMatrix.checkColisionWithBullet(cannon.getBullet().getCoordinates(), cannon.getBullet().getSprite())) != 0) {
                cannon.getBullet().setShoted();
                player.increasesScore(incscore);
            }
        }
    }

    public void checkgameOver(boolean aliens_on_Earth) {
        if(aliens_on_Earth) {
            gameOverType = 2;
        } else if (player.getLifes() == 0) {
            gameOverType = 1;
        } else {
            gameOverType = 0;
        }

        if(gameOverType != 0) {
            gameOver();
        }
    }

    public void destructor(AnchorPane main) {
        cannon.destructor(main);
        aliensMatrix.destructor(main);
        barriers.destructor(main);
        player.destructor(main);
    }

    public void draw(AnchorPane main) {
        cannon.draw(main);
        aliensMatrix.draw(main);
        barriers.draw(main);
        player.draw(main);
    }


    public void destructor(AnchorPane main, AnchorPane pane_jogo) {
        cannon.destructor(pane_jogo);
        aliensMatrix.destructor(pane_jogo);
        barriers.destructor(pane_jogo);
        player.destructor(main);
    }

    public void draw(AnchorPane main, AnchorPane pane_jogo) {
        cannon.draw(pane_jogo);
        aliensMatrix.draw(pane_jogo);
        barriers.draw(pane_jogo);
        player.draw(main);
    }
    
    public void gameLoopStart() {
        gameloop.start();
    }

    public void gameLoopStop() {
        gameloop.stop();
    }

    private void gameOver() {
        switch (gameOverType) {
            case 1:
                //GameOver for Ended Lives
                System.out.println("You Died!!!");
                break;
            
            case 2:
                //GameOver for Aliens have arrived on Earth
                System.out.println("Aliens Arrived Earth!");
                break;
            
            case 3:
                //GameOver for Win Game (Exterminated all aliens)
                System.out.println("You exterminate ALL the Aliens!");
                break;       
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

    public void setPressedLEFT() {
        pressedLEFT = true;
    }

    public void setPressedRIGHT() {
        pressedRIGHT = true;
    }

    
    public void  setPressedSPACE() {
        pressedSPACE = true;
    }
}