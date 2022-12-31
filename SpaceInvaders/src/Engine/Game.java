package Engine;

import ElementSystem.*;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * 
 */
public class Game {
    
    /**
     * 
     */
    private ImageView alieninvasion; 
    
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
    
    /**
     * 
     */
    private ImageView gameover;
    
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
    private ImageView saveearth;
    
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
    private ImageView youdied;

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
                if(gameOverType == 0) {
                    checkgameOver(aliensMatrix.move());
                    aliensMatrix.randomShot();
                    checkColision();
                } else {
                    aliensMatrix.move();
                }
                
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
        aliensMatrix.checkColisionWithBarrier(barriers);

        //If it was cannon it was shot diminished life.
        if(aliensMatrix.moveShotsCheckColision(cannon, barriers)) {
            player.decreasesLifes();
        }

        int incscore;
        //Collision of the cannon shot
        if(cannon.getBullet().getFlagShot()) {
            if (barriers.checkColision(cannon.getBullet().getCoordinates(), cannon.getBullet().getSprite())) {
                cannon.getBullet().setShoted();
            } else if((incscore = aliensMatrix.checkColisionWithBullet(cannon.getBullet().getCoordinates(), cannon.getBullet().getSprite())) != 0) {
                cannon.getBullet().setShoted();
                player.increasesScore(incscore);
            }
        }
    }

    public void checkgameOver(boolean aliens_on_Earth) {
        if (aliensMatrix.quantityAliensAlived() == 0) {
            gameOverType = 3;
        } else if (aliens_on_Earth) {
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
        aliensMatrix.destructor(main);
        barriers.destructor(main);
        cannon.destructor(main);
        player.destructor(main);
        gameOverDestructor(main);
    }

    public void draw(AnchorPane main) {
        cannon.draw(main);
        aliensMatrix.draw(main);
        barriers.draw(main);
        player.draw(main);
        gameOverSetup(main);
    }


    public void destructor(AnchorPane main, AnchorPane pane_jogo) {
        cannon.destructor(pane_jogo);
        aliensMatrix.destructor(pane_jogo);
        barriers.destructor(pane_jogo);
        player.destructor(main);
        gameOverDestructor(pane_jogo);
    }

    public void draw(AnchorPane main, AnchorPane pane_jogo) {
        cannon.draw(pane_jogo);
        aliensMatrix.draw(pane_jogo);
        barriers.draw(pane_jogo);
        player.draw(main);
        gameOverSetup(pane_jogo);
    }
    
    public void gameLoopStart(){
        gameloop.start();
    }

    public void gameLoopStop() {
        gameloop.stop();
    }

    private void gameOver() {
        gameover.setVisible(true);
        switch (gameOverType) {
            case 1:
                //GameOver for Ended Lives
                youdied.setVisible(true);
                break;
            
            case 2:
                //GameOver for Aliens have arrived on Earth
                alieninvasion.setVisible(true);
                break;
            
            case 3:
                //GameOver for Win Game (Exterminated all aliens)
                saveearth.setVisible(true);
                break;       
        }
    }

    private void gameOverSetup(AnchorPane main) {
        alieninvasion = new ImageView("sprites/Aliens_Invaded.png");
        gameover = new ImageView("sprites/GameOver.png");
        saveearth = new ImageView("sprites/Save_Earth.png");
        youdied = new ImageView("sprites/You_Died.png");

        alieninvasion.setLayoutX(0);
        alieninvasion.setLayoutY(0);
        gameover.setLayoutX(0);
        gameover.setLayoutY(0);
        saveearth.setLayoutX(0);
        saveearth.setLayoutY(0);
        youdied.setLayoutX(0);
        youdied.setLayoutY(0);

        alieninvasion.setVisible(false);
        gameover.setVisible(false);
        saveearth.setVisible(false);
        youdied.setVisible(false);

        main.getChildren().add(alieninvasion);
        main.getChildren().add(gameover);
        main.getChildren().add(saveearth);
        main.getChildren().add(youdied);
    }

    private void gameOverDestructor(AnchorPane main) {
        main.getChildren().remove(alieninvasion);
        main.getChildren().remove(gameover);
        main.getChildren().remove(saveearth);
        main.getChildren().remove(youdied);
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