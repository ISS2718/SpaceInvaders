package ElementSystem;

import java.util.*;

/**
 * 
 */
public class Barrier extends Static {
    /**
     * 
     */
    private int height;
    
    /**
     * 
     */
    private int width;
    
    /**
     * 
     */
    private char[ ][ ] area;
    
    Sprite sprite;

    /**
     * Default constructor
     * 
     * @param height 
     * @param width 
     * @param x 
     * @param y
     */
    public Barrier(int height, int width, int x, int y) {
        super(x, y);
        this.sprite = new Sprite("sprites/barrera.png");
        this.height = height;
        this.width = width;
    }

    /**
     * 
     * 
     * @param line
     * @param x_coordinate
     */
    public void draw(int line, double x_coordinate) {
        for(int j = 0; j < x_coordinate; j++) {
            System.out.print(' ');
        }
        for (int j = 0; j < height; j++) {
            System.out.print(area[line][j]);
        }
    }
    
    /**
     * 
     * @return 
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * 
     * @return 
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * 
     * @param x 
     * @param y
     */
    public void removePiece(int x, int y) {

    }
}