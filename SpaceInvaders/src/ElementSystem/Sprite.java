/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementSystem;

import javafx.scene.image.*;

/**
 *
 * @author isaac
 */
public class Sprite {

   private ImageView sprite;
   

   public Sprite(String path_image) {
        this.sprite = new ImageView(path_image);
   }

   public Sprite(Image image) {
       this.sprite = new ImageView(image);
   }

   public ImageView getImageView() {
       return sprite;
   }
}
