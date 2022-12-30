package ElementSystem;

/**
 * 
 */
public class Static {

    /**
     * 
     */
    protected final Coordinates coordinates;

    /**
     * Default constructor.
     * 
     * @param x 
     * @param y
     */
    public Static(int x, int y) {
        coordinates = new Coordinates(x, y);
    }
    
        /**
     * Default constructor.
     * 
     * @param coordinates
     */
    public Static(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    
    public Coordinates getCoordinates() {
        return coordinates;
    }
}