package ElementSystem;

import java.util.Random;

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
    
    private double aliens_spacing;

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
     * The number of possible shots of the Alien Matrix.
     */
    private int quantity_shots;
    
    private boolean shoted;

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
    public AliensMatrix(int quantity_columns, double aliens_spacing, double speed, Coordinates size) {
       super(0,  50, speed, size);
       this.shoted = false;
       this.quantity_columns = quantity_columns;
       this.quantity_shots = 4;
       aliens = new Alien[5][this.quantity_columns];
       for (int i = 0; i < this.quantity_columns; i++) {
           aliens[0][i] = new Alien(1, speed,  size);
           aliens[1][i] = new Alien(1, speed,  size);
           aliens[2][i] = new Alien(2, speed,  size);
           aliens[3][i] = new Alien(2, speed,  size);
           aliens[4][i] = new Alien(3, speed,  size);
       }
       sprite_size = new Coordinates(aliens[0][0].getSprite().getImageView().getImage().getWidth(), aliens[0][0].getSprite().getImageView().getImage().getHeight());
       this.aliens_spacing = aliens_spacing;
    }

    /**
     * Check collision of the aliens with the barriers.
     * 
     * @param barriers To check for collision.
     */
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
    
    public int checkColisionWithBullet(Coordinates coordinates_for_check, Sprite sprite_for_check)  {
        int r = 0;
        colisionCheck:
        for (int i = (quantity_row - 1); i >= 0; i--) {
            for (int j = 0; j < quantity_columns; j++) {
                if(aliens[i][j].getIsAlive()) {
                    r = aliens[i][j].checkColision(coordinates_for_check, sprite_for_check);
                    if (r != 0) {
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
                    aliens[i][j].getCoordinates().setX(coordinates.getX() + (j * sprite_size.getX()) + (aliens_spacing * j));
                    aliens[i][j].getCoordinates().setY(coordinates.getY() + (((quantity_row - 1) - i) * sprite_size.getY()) + (((quantity_row - 1) - i) * aliens_spacing));
                    aliens[i][j].draw(main);
                }
            }
        }
       
       public void drawMove() {
            for (int i = (quantity_row - 1); i >= 0; i--) {
               for (int j = 0; j < quantity_columns; j++) {
                   aliens[i][j].getCoordinates().setX(coordinates.getX() + (j * sprite_size.getX()) + (aliens_spacing * j));
                   aliens[i][j].getCoordinates().setY(coordinates.getY() + (((quantity_row - 1) - i) * sprite_size.getY()) + (((quantity_row - 1) - i) * aliens_spacing));
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
    public boolean move() {
        boolean r = false;

        int firstLiveColumnAlienMatrix = firstLiveColumn();
        int lastLiveColumnAlienMatrix = lastLiveColumn();
        int firstLiveRowAlienMatrix = firstLiveRow();
        int lastLiveRowAlienMatrix = lastLiveRow();
        
        double right_speed = speed;
        double leftt_speed = speed;
        
        
        double min_height_AliensMatrix = (screen_size.getY() - ((quantity_row  * sprite_size.getY()) + ((quantity_row - 1) * aliens_spacing)) + firstLiveRowAlienMatrix* sprite_size.getY());
        double origin_point_AliensMatrix =  (-1 * ((firstLiveColumnAlienMatrix * sprite_size.getX()) + ((firstLiveColumnAlienMatrix) * aliens_spacing))) + (2 * (firstLiveColumnAlienMatrix + 1));
        double target_point_AliensMatrix = (screen_size.getX() - ((lastLiveColumnAlienMatrix) * aliens_spacing)) - ((lastLiveColumnAlienMatrix + 1.1) * sprite_size.getX());
        
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
                if((coordinates.getY()) < min_height_AliensMatrix)  {
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

        if (quantityAliensAlived() != 0) {
            if(coordinates.getY() >= min_height_AliensMatrix) {
                r = true;
            }
        }
        return r;
    }

    /**
     * Move shots and check collision with the barriers and the cannon.
     * 
     * @param cannon To check for collision.
     * @param barriers To check for collision.
     * 
     * @return Returns true if the cannon is hit.
     */
    public boolean moveShotsCheckColision(Cannon cannon, Barriers barriers) {
        boolean r = false;
        shoted =  false;
        for (int i = 0; i < quantity_row; i++) {
            for (int j = 0; j < quantity_columns; j++) {
                if (aliens[i][j].getBullet().getFlagShot()) {
                    aliens[i][j].getBullet().move();

                    if(cannon.checkColision(aliens[i][j].getBullet().getCoordinates(), aliens[i][j].getBullet().getSprite())) {
                        aliens[i][j].getBullet().setShoted();
                        r = true;
                    } else if(barriers.checkColision(aliens[i][j].getBullet().getCoordinates(), aliens[i][j].getBullet().getSprite())) {
                        aliens[i][j].getBullet().setShoted();
                    } else {
                        shoted = true;
                    }
                }
            }
        }
        return r;
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
        Random rand = new Random();
        int quantity = rand.nextInt(quantity_shots + 1);
        if ((shoted == false) && (quantity != 0)) {
            for (int i = 0; i < quantity; i++) {
                int collumn = rand.nextInt(quantity_columns);
                int row = rand.nextInt(quantity_row);
                aliens[row][collumn].getBullet().shot(aliens[row][collumn].getCoordinates(), aliens[row][collumn].getSprite());
            }
            shoted =  true;
        }
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

    public int quantityAliensAlived() {
        int r = 0;
        for (int i = 0; i < quantity_row; i++) {
            for (int j = 0; j < quantity_columns; j++) {
                if (aliens[i][j].getIsAlive()) {
                    r++;
                }
            }
        }
        return r;
    }
}