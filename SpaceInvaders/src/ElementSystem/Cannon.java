package ElementSystem;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author isaac
 */
public class Cannon extends Move {

    private Bullet bullet;
    
   private Image cannon;
   
   private boolean invencible;
   
   private Image invincible_cannon;
    

    private Sprite sprite;

    private Coordinates sprite_size;
    

    public Cannon(double speed, double bullet_spped, Coordinates size) {
        super(size.getX()/2, size.getY() - 3, speed, size);
        this.bullet = new Bullet(bullet_spped, size, true);
        cannon = new Image("sprites/canhao.png");
        invincible_cannon = new Image("sprites/canhao_in.gif");
        invencible = false;
        sprite = new Sprite(cannon);
        sprite_size = new Coordinates(sprite.getImageView().getImage().getWidth(), sprite.getImageView().getImage().getHeight());
    }
    
    public boolean checkColision(Coordinates coordinates_for_check, Sprite sprite_for_check) {
        double lower_limit = (coordinates.getY() + sprite_size.getY());
        double upper_limit = (coordinates.getY() - sprite_size.getY());
        double left_limit = coordinates.getX();
        double rigth_limit = (coordinates.getX() + sprite_size.getX());
        
        double lower_limit_for_check = (coordinates_for_check.getY() + sprite_for_check.getImageView().getImage().getHeight());
        double upper_limit_for_check = (coordinates_for_check.getY() - sprite_for_check.getImageView().getImage().getHeight());
        double left_limit_for_check = coordinates_for_check.getX();
        double rigth_limit_for_check = (coordinates_for_check.getX() + sprite_for_check.getImageView().getImage().getWidth());


        boolean r = false;

        if (((upper_limit <= upper_limit_for_check) && (coordinates.getY() >= upper_limit_for_check)) 
                    || ((coordinates.getY() <= lower_limit_for_check) && (lower_limit >= lower_limit_for_check))) {

                if (((left_limit <= left_limit_for_check) && (rigth_limit >= left_limit_for_check)) 
                        || ((left_limit <= rigth_limit_for_check) && (rigth_limit >= rigth_limit_for_check))) {
                    
                    r = true;
                }

        }
        return r;
    }

    public void destructor(AnchorPane main) {
        bullet.destructor(main);
        main.getChildren().remove(sprite.getImageView());
    }
    
    public void draw(AnchorPane main) {
        sprite.getImageView().setLayoutX(0);
        sprite.getImageView().setLayoutY(0);
        sprite.getImageView().setX(coordinates.getX());
        sprite.getImageView().setY(coordinates.getY() - sprite.getImageView().getImage().getHeight());
        main.getChildren().add(sprite.getImageView());
        this.bullet.draw(main);
    }
    
    public void drawMove() {
        sprite.getImageView().setX(coordinates.getX());
    }

    public Bullet getBullet() {
        return bullet;
    }
    
    public boolean getIsInvencible() {
        return invencible;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public void moveLeft() {
        if(coordinates.getX()  !=  0) {
            if((coordinates.getX() - speed)  <  0) {
                coordinates.setX(0);
            } else if (coordinates.getX()  >  0) {
                coordinates.setX(coordinates.getX() - speed);
            }
           drawMove();
        }
    }
    
    public void moveRight() {
        if((coordinates.getX() +  sprite.getImageView().getImage().getWidth())  !=  screen_size.getX()) {
            if (((coordinates.getX() +  sprite.getImageView().getImage().getWidth()) + speed)  >  screen_size.getX()) {
                coordinates.setX(screen_size.getX() - sprite.getImageView().getImage().getWidth());
            } else {
                 coordinates.setX(coordinates.getX() + speed);
             }
            drawMove();
        }
    }
    public void setInvencible() {
         invencible = true;
         sprite.getImageView().setImage(invincible_cannon);
    }
    
    public void setVencible() {
        invencible = false;
        sprite.getImageView().setImage(cannon);
    }
}