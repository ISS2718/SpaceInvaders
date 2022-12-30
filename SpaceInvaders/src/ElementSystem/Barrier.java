package ElementSystem;

import javafx.scene.layout.AnchorPane;

/**
 * 
 */
public class Barrier extends Static {
    /**
     * 
     */
    private final int height;
    
    /**
     * 
     */
    private final int width;
    
    /**
     * 
     */
    private int[ ][ ] area;
    
    private Brick[][] barrier;
    
    /**
     * Sprite size (width, height).
     */
    private Coordinates sprites_size;
    
    /**
     * Default constructor
     * 
     * @param initial
     */
    public Barrier(Coordinates initial, Coordinates sprites_size) {
        super(initial);
        height = 3;
        width = 3;

        this.sprites_size = sprites_size;
        
        barrier = new Brick[height][width];
        int type = 0;
        for (int j = 0; j< height; j++) {
            for (int i = 0; i < width; i++) {
                type++;
                barrier[j][i] = new Brick(type, new Coordinates((initial.getX() + (sprites_size.getX() * i)), (initial.getY() + (sprites_size.getY() * j))));
            }
        }
    }

    /**
     * 
     * 
     * @param main
     */
    public void draw(AnchorPane main) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                barrier[j][i].draw(main);
            }
        }
    }
    
    public void destructor(AnchorPane main) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                barrier[j][i].destructor(main);
            }
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
}