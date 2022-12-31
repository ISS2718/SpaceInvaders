package ElementSystem;

/**
 * 
 */
public class Static {

    protected final Coordinates coordinates;

    public Static(int x, int y) {
        coordinates = new Coordinates(x, y);
    }
    
    public Static(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    
    public Coordinates getCoordinates() {
        return coordinates;
    }
}