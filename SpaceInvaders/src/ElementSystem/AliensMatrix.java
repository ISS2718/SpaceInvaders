package ElementSystem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;

/**
 * This class implements the array of invading aliens 
 * heading toward the cannon.
 * The alien array has a height of 5, and its width is
 * defined in the constructor.
 * Each height level has its own alien type. Levels 0 
 * and 1 have type 1 aliens, levels 2 and 3 have type 2 
 * aliens and level 4 has type 3 aliens.
 * The AliensMatrix class extends the Move class by receiving 
 * the default coordinate and move methods. Despite extending 
 * the class Move this class reimplements the move() method to adapt 
 * to the specific and continuous movement of the array of aliens 
 * towards earth.
 *  
 * @see Move
 * @author isaac
 */
public class AliensMatrix extends Move {
    /**
     * Aliens matrix.
     */
    private Alien[][] aliens;
    
    /**
     * Variable to define the direction of moviment of the aliens matrix.
     * If front_move its true the aliens matrix move to the right, but 
     * if front_move its false the aliens matrix move to the left.
     */
    private boolean front_move = true;

    /**
     * Quantity of columns in the aliens matrix.
     */
    private int quantity_columns;

    /**
     * Quantity of rows in the alies matrix.
     */
    private int quantity_row = 5;
    
    /**
     *  Sprite size (width, height).
     */
    private Coordinates sprite_size;

    /**
     * Default constructor for AliensMatrix.
     * It takes the number of columns and the 
     * dimensions of the game display screen.
     * It starts the coordinate at the maximum height 
     * and minimum width point. Starts the speed as 1 
     * and builds the matrix starting all rows and all columns 
     * with their respective aliens.
     * 
     * @param quantity_columns Quantity of columns in the aliens matrix.
     * @param size Dimensions of the game display screen
     */
    public AliensMatrix(int quantity_columns, double speed, Coordinates size) {
       super(0, 0, speed, size);
       this.quantity_columns = quantity_columns;
       aliens = new Alien[5][this.quantity_columns];
       for (int i = 0; i < this.quantity_columns; i++) {
           aliens[0][i] = new Alien(1, speed,  size);
           aliens[1][i] = new Alien(1, speed,  size);
           aliens[2][i] = new Alien(2, speed,  size);
           aliens[3][i] = new Alien(2, speed,  size);
           aliens[4][i] = new Alien(3, speed,  size);
       }
       sprite_size = new Coordinates(aliens[0][0].getSprite().getImageView().getImage().getWidth(), aliens[0][0].getSprite().getImageView().getImage().getHeight());
    }
    
    public boolean checkColision(Coordinates coordinates_for_check, Sprite sprite_for_check)  {
        boolean r = false;
        colisionCheck:
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < quantity_columns; j++) {
                if(aliens[i][j].getIsAlive()) {
                    if (((aliens[i][j].getCoordinates().getY() - aliens[i][j].getSprite().getImageView().getImage().getHeight()) <= (coordinates_for_check.getY() - sprite_for_check.getImageView().getImage().getHeight()))
                            && (aliens[i][j].getCoordinates().getY() + aliens[i][j].getSprite().getImageView().getImage().getHeight()) >= (coordinates_for_check.getY() - sprite_for_check.getImageView().getImage().getHeight())) {
                        if (((aliens[i][j].getCoordinates().getX() - aliens[i][j].getSprite().getImageView().getImage().getWidth()) <= coordinates_for_check.getX())
                                && (aliens[i][j].getCoordinates().getX() + aliens[i][j].getSprite().getImageView().getImage().getWidth()) >= coordinates_for_check.getX()) {
                            aliens[i][j].setDead();
                            r = true;
                            break colisionCheck; 
                        }
                    }
                }
            }
        }
        return r;
    }
    
    /**
     * 
     * @param line 
     * @param x_coordinate
     */
       public void draw(AnchorPane main) {
           for (int i = 4; i >= 0; i--) {
                for (int j = 0; j < quantity_columns; j++) {
                    aliens[i][j].getCoordinates().setX(coordinates.getX() + (j * sprite_size.getX()) + (3 * j));
                    aliens[i][j].getCoordinates().setY(coordinates.getY() + ((4 - i) * sprite_size.getY()) + ((4 - i) * 3));
                    aliens[i][j].draw(main);
                }
            }
        }
       
       public void drawMove() {
            for (int i = 4; i >= 0; i--) {
               for (int j = 0; j < quantity_columns; j++) {
                    aliens[i][j].getCoordinates().setX(coordinates.getX() + (j * sprite_size.getX()) + (3 * j));
                    aliens[i][j].getCoordinates().setY(coordinates.getY() + ((4 - i) * sprite_size.getY()) + ((4 - i) * 3));
                    aliens[i][j].drawMove();
               }
           }
        }
       
       public void destructor(AnchorPane main) {
            for (int i = 4; i >= 0; i--) {
                for (int j = 0; j < quantity_columns; j++) {
                    main.getChildren().remove(aliens[i][j].getSprite().getImageView());
                }
            }
        }   
    
    /**
     * @param max_coodinates
     */
    public void move() {
         if(front_move == true) {
            if((((quantity_columns - 2) * 3) + coordinates.getX() + (quantity_columns * sprite_size.getX())) < (screen_size.getX())) {
                coordinates.setX(coordinates.getX() + speed);
            } else {
                if((coordinates.getY()) < (screen_size.getY() - ((quantity_row * sprite_size.getY()) + (quantity_row * 3)))) {
                    coordinates.setY(coordinates.getY() + sprite_size.getY()/3);
                }
                front_move = false;
            }
        } else if(coordinates.getX() >=  0.0) {
            if((coordinates.getX() -  speed == 0.0) || (coordinates.getX()== 0.0)) {
                if((coordinates.getY()) < (screen_size.getY() - ((quantity_row * sprite_size.getY()) + (quantity_row * 3))))  {
                    coordinates.setY(coordinates.getY() + sprite_size.getY()/3);
                }
                front_move = true;
            } else if(coordinates.getX() -  speed < 0.0) {
                coordinates.setX(0);
            } else {
                coordinates.setX(coordinates.getX() - speed);
            }
        }
        drawMove();
    }

    public int getcolumns() {
        return quantity_columns;
    }

    /**
     * 
     * @return
     */
    public int getRows() {
        return quantity_row;
    }

    /**
     * 
     */
    public void randomShot() {
    }
    

    /**
     * @param columm
     * @param row
     */
    public void removeAlien(int columm, int row) {
        if(verifyAlienExistense(columm, row)) {
            aliens[columm][row] = null;
        } 
    }

    /**
     * @param columm
     * @param row
     * @return
     */
    public boolean verifyAlienExistense(int columm, int row) {
        if(aliens[columm][row] != null) {
            return true;
        }
        return false;
    }

}