package ElementSystem;

import java.util.*;

/**
 * 
 */
public class Static {

    /**
     * 
     */
    private final Coordinates coordinates;

    /**
     * Default constructor.
     * 
     * @param x 
     * @param y
     */
    public Static(int x, int y) {
        coordinates = new Coordinates(x, y);
    }
    
    public Coordinates getCoordinates() {
        return coordinates;
    }
}