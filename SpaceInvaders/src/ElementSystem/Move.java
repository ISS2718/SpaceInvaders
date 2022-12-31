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
    
    protected Coordinates screen_size;
    protected Coordinates coordinates;
    protected double speed;
    
    Move(double x, double y, double speed, Coordinates screen_size) {
        coordinates = new Coordinates(x , y);
        this.speed = speed;
        this.screen_size = screen_size;
    }
    

    public void move(double x, double y) {
        coordinates.setX(coordinates.getY() + (x * speed));
        coordinates.setY(coordinates.getY() + (y * speed));
    }
    

    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    public Coordinates getScreenSize() {
        return screen_size;
    }
}