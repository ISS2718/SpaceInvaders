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
        this.sprite = new Sprite('+');
        this.height = height;
        this.width = width;
        area = new char[width][height];
        int quant = 0;
        for(int i = 0; i < width; i++) {
            quant++;
            for(int j = 0; j < quant; j++) {
                area[i][j] = sprite.getSprite();
            }
            if(height > quant) {
                for(int j = height - quant; j < height; j++) {
                    area[i][j] = sprite.getSprite();
                }
            }
        }
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
        if(x < width && y < height) {
            area[x][y] = ' ';
        }
    }
}