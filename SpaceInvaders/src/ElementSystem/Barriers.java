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
            
    Coordinates barrier_size;
    
    final private int quantity_barriers; 
    
    Coordinates spacing_barries;
    
    Coordinates sprites_size; 
    
    public Barriers(int quantity_barriers, Coordinates size) {
        super(size);
        
        Brick brick =  new Brick(1, new Coordinates(0, 0));
        sprites_size = brick.getSpriteSize();
        
        barrier_size = new Coordinates((sprites_size.getX() * 3), (sprites_size.getY() * 3));
        
        spacing_barries = new Coordinates(((size.getX() - (quantity_barriers * barrier_size.getX())) / (quantity_barriers + 1)), (size.getY() - 2 * (barrier_size.getY())));
        
        this.quantity_barriers = quantity_barriers;
        barriers = new Barrier[quantity_barriers];
        for (int i = (quantity_barriers - 1); i >= 0; i--) {
            barriers[i] = new Barrier(new Coordinates(((barrier_size.getX()) * ((quantity_barriers - 1) - i)) + ((spacing_barries.getX()) * (quantity_barriers - i)), spacing_barries.getY()), sprites_size);
        }
    }
    
    public boolean checkColision(Coordinates coordinates_for_check, Sprite sprite_for_check) {
        boolean r = false;
        for (int i = 0; i < quantity_barriers; i++) {
            r = barriers[i].checkColision(coordinates_for_check, sprite_for_check);
            if (r == true) {
                break;
            }
        }
        return r;
    }
    

    public void draw(AnchorPane main) {
        for (int i = 0; i < quantity_barriers; i++) {
            barriers[i].draw(main);
        }
    }
    

    public void destructor(AnchorPane main) {
        for (int i = 0; i < quantity_barriers; i++) {
            barriers[i].destructor(main);
        }
    }
    
    public int getQuantityBarriers() {
        return quantity_barriers;
    }
    
}
