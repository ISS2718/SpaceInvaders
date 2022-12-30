package ElementSystem;


/**
 * 
 */
public class Coordinates {
    /**
     *  Coordinate of the abscissa axis.
     */
    private double x;

    /**
     *  Coordinate of the ordinate axis.
     */
    private double y;
    
    
    /**
     * Default constructor for Coordinates objects.
     *
     * @param x abscissa axis value.
     * @param y ordinate axis value.
     */
    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the coordinate of the abscissa axis.
     * 
     * @return abscissa axis value.
     */
    public double getX() {
        return x;
    }

    /**
     *  Get coordinate of the ordinate axis.
     * 
     * @return ordinate axis value.
     */
    public double getY() {
        return y;
    }

    /**
     * Set coordinate of the ordinate axis.
     * 
     * @param x value for update of ordinate axis value.
     */
    public void setX(double x) {
       this.x = x;
    }

    /**
     * Set coordinate of the abscissa axis.
     * 
     * @param y value for update of abcis axis value.
     */
    public void setY(double y) {
       this.y = y;
    }

    /**
     * 
     * @param coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }
}