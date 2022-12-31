package ElementSystem;

import java.util.Random;
import javafx.scene.layout.AnchorPane;

/**
 * 
 */
public class Spaceship extends Move {
    /**
     * 
     */
    private boolean front_move;
    
    /**
     * 
     */
    private boolean isAlive;
    
    /**
    * 
    */
    private final Sprite sprite;
    
    /**
     * 
     * @param size
     */
    public Spaceship(Coordinates size) {
        super(0, 5, 0.5, size);
        isAlive = false;
        front_move = true;
        sprite = new Sprite("sprites/nave.png");
    }
   
    /**
     * 
     * @param coordinates_for_check
     * @param sprite_for_check
     * @return 
     */
    public int checkColision(Coordinates coordinates_for_check, Sprite sprite_for_check) {
        double lower_limit = (coordinates.getY() + sprite.getImageView().getImage().getHeight());
        double upper_limit = (coordinates.getY() - sprite.getImageView().getImage().getHeight());
        double left_limit = coordinates.getX();
        double rigth_limit = (coordinates.getX() + sprite.getImageView().getImage().getWidth());

        double lower_limit_for_check = (coordinates_for_check.getY() + sprite_for_check.getImageView().getImage().getHeight());
        double upper_limit_for_check = (coordinates_for_check.getY() - sprite_for_check.getImageView().getImage().getHeight());
        double left_limit_for_check = coordinates_for_check.getX();
        double rigth_limit_for_check = (coordinates_for_check.getX() + sprite_for_check.getImageView().getImage().getWidth());
        int r = 0;
        if(isAlive) {
            if (((upper_limit <= upper_limit_for_check) && (coordinates.getY() >= upper_limit_for_check))
                    || ((coordinates.getY() <= lower_limit_for_check) && (lower_limit >= lower_limit_for_check))) {

                if (((left_limit <= left_limit_for_check) && (rigth_limit >= left_limit_for_check))
                        || ((left_limit <= rigth_limit_for_check) && (rigth_limit >= rigth_limit_for_check))) {

                    setDead();
                    r = 500;
                }
            }
        }
        return r;
    }
    
    /**
     * 
     * 
     * @param x_coordinate
     */
   public void draw(AnchorPane main) {
       sprite.getImageView().setLayoutX(0);
        sprite.getImageView().setLayoutY(0);
        sprite.getImageView().setX(coordinates.getX());
        sprite.getImageView().setY(coordinates.getY());
        sprite.getImageView().setVisible(false);
        main.getChildren().add(sprite.getImageView());
    }
   
       /**
     * 
     * 
     * @param x_coordinate
     */
   public void drawMove() {
        sprite.getImageView().setX(coordinates.getX());
        sprite.getImageView().setY(coordinates.getY());
    }
   
    public void destructor(AnchorPane main) {
        main.getChildren().remove(sprite.getImageView());
    }   
    
    /**
     * 
     * @param max_coordinates 
     */
    public void move() {
        if(isAlive) {
            if (front_move == true) {
                if ((coordinates.getX() + speed) <= (screen_size.getX() - sprite.getImageView().getImage().getWidth())) {
                    coordinates.setX(coordinates.getX() + speed);
                } else {
                    coordinates.setX(screen_size.getX() - sprite.getImageView().getImage().getWidth());
                }
            } else if ((coordinates.getX() - speed) >= 0) {
                coordinates.setX(coordinates.getX() - speed);
            } else {
                coordinates.setX(0);
            }
            
            drawMove();
            
            if((coordinates.getX() == 0) || (coordinates.getX()) == (screen_size.getX() - sprite.getImageView().getImage().getWidth())) {
                setDead();
            }
        }
    }
    
    public void randomAppears() {
        if(isAlive == false) {
            Random rand = new Random();
            if(rand.nextInt() % 1000  == 9) {
                isAlive = true;
                setSideToMove(rand.nextBoolean());
            }
        }
    }
    
    /**
     * 
     * @param front_move 
     */
    public void setSideToMove(boolean front_move) {
        this.front_move = front_move; 
        if(front_move) {
               coordinates.setX(0.0);
        } else {
            coordinates.setX(screen_size.getX() - sprite.getImageView().getImage().getWidth());
        }
        sprite.getImageView().setVisible(true);
    }
    
    public void setDead() {
        isAlive = false;
        sprite.getImageView().setVisible(false);
    }
}