/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementSystem;

/**
 *
 * @author isaac
 */
public class Move {
    
    public Coordinates coordinates;
    public double speed;
    
    Move(double x, double y, double speed) {
        coordinates = new Coordinates(x , y);
        this.speed = speed;
    }
    
    /**
     * 
     * @param x
     * @param y 
     */
    public void move(double x, double y) {
        coordinates.setX(coordinates.getY() + (x * speed));
        coordinates.setY(coordinates.getY() + (y * speed));
    }
    
        /**
     * 
     * @return
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
}