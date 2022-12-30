package Engine;

import ElementSystem.Coordinates;

/**
 * 
 */
public class Screen {

    private Coordinates size;

    /**
     * @param height 
     * @param width
     */
    public Screen(double width, double height) {
        size = new Coordinates(width, height);
    }

    /**
     * 
     * @return
     */
    public Coordinates getSize() {
        return size;
    }
}