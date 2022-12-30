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
   /**
 *
 */
private ImageView sprite;
   
   /**
    * Default constructor.
    * 
     * @param path_image
    */
   public Sprite(String path_image) {
       try {
                this.sprite = new ImageView(path_image);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("AAAAAAAAAAAAAAAAAAA:" + e.getMessage());
        }
   }
   
      /**
    * Default constructor.
    * 
     * @param image
    */
   public Sprite(Image image) {
       try {
                this.sprite = new ImageView(image);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("AAAAAAAAAAAAAAAAAAA:" + e.getMessage());
        }
   }
   
   public ImageView getImageView() {
       return sprite;
   }
}
