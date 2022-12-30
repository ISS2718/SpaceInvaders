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
     * If right_move its true the aliens matrix move to the right, but 
     * if right_move its false the aliens matrix move to the left.
     */
    private boolean right_move = true;

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
       super(0, 290, speed, size);
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
    
    public void checkColisionWithBarrier(Barriers barriers) {
        for (int i = (quantity_row - 1); i >= 0; i--) {
            for (int j = 0; j < quantity_columns; j++) {
                if (aliens[i][j].getIsAlive()) {
                     if (barriers.checkColision(aliens[i][j].getCoordinates(), aliens[i][j].getSprite())) {
                         aliens[i][j].setDead();
                     }
                }
            }
        }
    }
    
    public boolean checkColisionWithBullet(Coordinates coordinates_for_check, Sprite sprite_for_check)  {
        boolean r = false;
        colisionCheck:
        for (int i = (quantity_row - 1); i >= 0; i--) {
            for (int j = 0; j < quantity_columns; j++) {
                if(aliens[i][j].getIsAlive()) {
                    r = aliens[i][j].checkColision(coordinates_for_check, sprite_for_check);
                    if (r == true) {
                        break colisionCheck;
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
           for (int i = (quantity_row - 1); i >= 0; i--) {
                for (int j = 0; j < quantity_columns; j++) {
                    aliens[i][j].getCoordinates().setX(coordinates.getX() + (j * sprite_size.getX()) + (3 * j));
                    aliens[i][j].getCoordinates().setY(coordinates.getY() + (((quantity_row - 1) - i) * sprite_size.getY()) + (((quantity_row - 1) - i) * 3));
                    aliens[i][j].draw(main);
                }
            }
        }
       
       public void drawMove() {
            for (int i = (quantity_row - 1); i >= 0; i--) {
               for (int j = 0; j < quantity_columns; j++) {
                   aliens[i][j].getCoordinates().setX(coordinates.getX() + (j * sprite_size.getX()) + (3 * j));
                   aliens[i][j].getCoordinates().setY(coordinates.getY() + (((quantity_row - 1) - i) * sprite_size.getY()) + (((quantity_row - 1) - i) * 3));
                   aliens[i][j].drawMove();
               }
           }
        }
       
       public void destructor(AnchorPane main) {
            for (int i = (quantity_row - 1); i >= 0; i--) {
                for (int j = 0; j < quantity_columns; j++) {
                    aliens[i][j].destructor(main);
                }
            }
        }   
    
    /**
     * 
     */
    public void move() {
        int firstLiveColumnAlienMatrix = firstLiveColumn();
        int lastLiveColumnAlienMatrix = lastLiveColumn();
        int firstLiveRowAlienMatrix = firstLiveRow();
        int lastLiveRowAlienMatrix = lastLiveRow();
        
        double right_speed = speed;
        double leftt_speed = speed;
        
        
        double min_height_AliensMatrix = (screen_size.getY() - ((quantity_row * sprite_size.getY()) + (quantity_row * 3)) + ((firstLiveRowAlienMatrix* sprite_size.getY()) + (firstLiveRowAlienMatrix * 3)));
        double origin_point_AliensMatrix =  (-1 * ((firstLiveColumnAlienMatrix * 32) + ((firstLiveColumnAlienMatrix) * 3))) + (2 * (firstLiveColumnAlienMatrix + 1));
        double target_point_AliensMatrix = (screen_size.getX() - ((lastLiveColumnAlienMatrix - 2) * 3)) - ((lastLiveColumnAlienMatrix + 1) * sprite_size.getX());
        
        if(right_move == true) {
            if(coordinates.getX() < target_point_AliensMatrix) {
                coordinates.setX(coordinates.getX() + right_speed);
            } else {
                if((coordinates.getY()) < min_height_AliensMatrix) {
                    coordinates.setY(coordinates.getY() + sprite_size.getY()/3);
                }
                right_move = false;
            }
        } else if(coordinates.getX() >= origin_point_AliensMatrix) {
            if(((coordinates.getX()) -  leftt_speed == origin_point_AliensMatrix) || (coordinates.getX() == origin_point_AliensMatrix)) {
                if((coordinates.getY()) <min_height_AliensMatrix)  {
                    coordinates.setY(coordinates.getY() + sprite_size.getY()/3);
                }
                right_move = true;
            } else if(coordinates.getX() -  leftt_speed < origin_point_AliensMatrix) {
                coordinates.setX(origin_point_AliensMatrix);
            } else {
                coordinates.setX(coordinates.getX() - leftt_speed);
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
    

    public int firstLiveColumn() {
        int r = -1;
        firstLiveColumn:
        for (int j = 0; j < quantity_columns; j++) {
            for (int i = 0; i < quantity_row ; i++) {
                if(aliens[i][j].getIsAlive()) {
                    r = j;
                    break firstLiveColumn;
                }
            }
         }
        return r;
    }
    
    public int firstLiveRow() {
        int r = -1;
        firstLiveRow:
        for (int i = 0; i < quantity_row; i++) {
            for (int j = 0; j < quantity_columns; j++) {
                if (aliens[i][j].getIsAlive()) {
                    r = i;
                    break firstLiveRow;
                }
            }
        }
        return r;
    }
    
    
    public int lastLiveColumn() {
        int r = -1;
        lastLiveColumn:
        for (int j = (quantity_columns - 1); j >= 0; j--) {
            for (int i = 0; i < quantity_row; i++) {
                if (aliens[i][j].getIsAlive()) {
                    r = j;
                    break lastLiveColumn;
                }
            }
        }
        return r;
    }
    
        public int lastLiveRow() {
        int r = -1;
        lastLiveRow:
        for (int i = (quantity_row - 1); i >= 0; i--) {
            for (int j = 0; j < quantity_columns; j++) {
                if (aliens[i][j].getIsAlive()) {
                    r = i;
                    break lastLiveRow;
                }
            }
        }
        return r;
    }
}