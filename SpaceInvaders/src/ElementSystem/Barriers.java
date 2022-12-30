/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementSystem;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author isaac
 */
public class Barriers extends Static {
    
    private Barrier[] barriers;
    
    final private int quantity_barriers;
    
    Coordinates sprites_size;
    
    public Barriers(int quantity_barriers, Coordinates initial) {
        super(initial);
    
        Brick brick =  new Brick(1, new Coordinates(0, 0));
        sprites_size = new Coordinates(brick.getSprite().getImageView().getImage().getWidth(), brick.getSprite().getImageView().getImage().getHeight());
        
        this.quantity_barriers = quantity_barriers;
        barriers = new Barrier[quantity_barriers];
        for (int i = (quantity_barriers - 1); i >= 0; i--) {
            barriers[i] = new Barrier(new Coordinates(initial.getX() * (quantity_barriers - i) + ((3 * sprites_size.getX()) * (quantity_barriers - i)), initial.getY()), sprites_size);
        }
    }
    
    
    /**
     *
     *
     * @param main
     */
    public void draw(AnchorPane main) {
        for (int i = 0; i < quantity_barriers; i++) {
            barriers[i].draw(main);
        }
    }
    
    /**
     *
     *
     * @param main
     */
    public void destructor(AnchorPane main) {
        for (int i = 0; i < quantity_barriers; i++) {
            barriers[i].destructor(main);
        }
    }
    
}
