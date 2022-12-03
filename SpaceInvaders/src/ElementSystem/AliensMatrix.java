package ElementSystem;

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
     * Quantity of rows in the alies matrix;
     */
    private int quantity_row = 5;

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
    public AliensMatrix(int quantity_columns, Coordinates size) {
       super(0.0, size.getY(), 1.0);
       this.quantity_columns = quantity_columns;
       aliens = new Alien[5][this.quantity_columns];
       for (int i = 0; i < this.quantity_columns; i++) {
           aliens[0][i] = new Alien(1);
           aliens[1][i] = new Alien(1);
           aliens[2][i] = new Alien(2);
           aliens[3][i] = new Alien(2);
           aliens[4][i] = new Alien(3);
       }
    }

    /**
     * 
     * @param line 
     * @param x_coordinate
     */
    public void draw(int line, double x_coordinate) {
        for(int j = 0; j < x_coordinate; j++) {
            System.out.print(' ');
        }
        if(line < quantity_row) {
            for (int j = 0; j < quantity_columns; j++) {
                System.out.print(aliens[line][j].getSprite().getSprite());
            }
        }
    }
    
    /**
     * @param max_coodinates
     */
    public void move(Coordinates max_coordinates) {
         if(front_move == true) {
            if(coordinates.getX() + quantity_columns <(max_coordinates.getX())) {
                coordinates.setX(coordinates.getX() + speed);
            } else {
                if((coordinates.getY()) > (max_coordinates.getY() + quantity_row)) {
                    coordinates.setY(coordinates.getY() - speed);
                }
                front_move = false;
            }
        } else if(coordinates.getX() > 0.0) {
            if(coordinates.getX() -  1 == 0.0) {
                if((coordinates.getY()) > (max_coordinates.getY() + quantity_row)) {
                    coordinates.setY(coordinates.getY() - speed);
                }
                front_move = true;
            } 
            coordinates.setX(coordinates.getX() - speed);
        } 
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