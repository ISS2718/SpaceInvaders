/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementSystem;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author isaac
 */
public class Brick extends Static {
    private Image full_barrier;
    private Image three_quarters_barrier;
    private Image half_barrier;
    private Image one_quarter_barrier;
    
    private int life;
    
    private Sprite sprite;
    
    private int type;
    
    public Brick(int type, Coordinates coordinates) {
        super(coordinates);
        this.type = type;
        setTypeSprite();
        life = 4;
        sprite = new Sprite(full_barrier);
    }
    
    public boolean checkColision(Coordinates coordinates_for_check, Sprite sprite_for_check) {
        double lower_limit = (coordinates.getY() + sprite.getImageView().getImage().getHeight());
        double upper_limit = (coordinates.getY() - sprite.getImageView().getImage().getHeight());
        double left_limit = coordinates.getX();
        double rigth_limit = (coordinates.getX() + sprite.getImageView().getImage().getWidth());
        
        double lower_limit_for_check = (coordinates_for_check.getY() + sprite_for_check.getImageView().getImage().getHeight());
        double upper_limit_for_check = (coordinates_for_check.getY() - sprite_for_check.getImageView().getImage().getHeight());
        double left_limit_for_check = coordinates_for_check.getX();
        double rigth_limit_for_check = (coordinates_for_check.getX() + sprite_for_check.getImageView().getImage().getWidth());


        boolean r = false;
        if (life > 0) {
            if (((upper_limit <= upper_limit_for_check) && (coordinates.getY() >= upper_limit_for_check)) 
                    || ((coordinates.getY() <= lower_limit_for_check) && (lower_limit >= lower_limit_for_check))) {

                if (((left_limit <= left_limit_for_check) && (rigth_limit >= left_limit_for_check)) 
                        || ((left_limit <= rigth_limit_for_check) && (rigth_limit >= rigth_limit_for_check))) {
                    
                    decreasesLife();
                    r = true;
                }
            }
        }
        return r;
    }
    
    /**
     *
     *
     * @param main
     */
    public void draw(AnchorPane main) {
        sprite.getImageView().setLayoutX(0);
        sprite.getImageView().setLayoutY(0);
        sprite.getImageView().setX(coordinates.getX());
        sprite.getImageView().setY(coordinates.getY());
        main.getChildren().add(sprite.getImageView());
    }
    
    public void decreasesLife() {
        if (life > 0) {
            life--;
            setImageSprite();
        }
    }
    
    public void destructor(AnchorPane main) {
        main.getChildren().remove(sprite.getImageView());
    }
    
    public int getLife() {
        return life;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public void resetLife() {
        life = 4;
        setImageSprite();
        sprite.getImageView().setVisible(true);
    }
    
    /**
     * Defines the alien sprite depending on the received type.
     */
    private void setTypeSprite() {
        switch (type) {
            case 1:
                full_barrier = new Image("sprites/barrera1.png");
                three_quarters_barrier = new Image("sprites/barrera1_Q2.png");
                half_barrier = new Image("sprites/barrera1_Q3.png");
                one_quarter_barrier = new Image("sprites/barrera1_Q4.png");
                break;
            case 2:
                full_barrier = new Image("sprites/barrera2.png");
                three_quarters_barrier = new Image("sprites/barrera2_Q2.png");
                half_barrier = new Image("sprites/barrera2_Q3.png");
                one_quarter_barrier = new Image("sprites/barrera2_Q4.png");
                break;
            case 3:
                full_barrier = new Image("sprites/barrera3.png");
                three_quarters_barrier = new Image("sprites/barrera3_Q2.png");
                half_barrier = new Image("sprites/barrera3_Q3.png");
                one_quarter_barrier = new Image("sprites/barrera3_Q4.png");
                break;
            case 4:
                full_barrier = new Image("sprites/barrera4_5_6.png");
                three_quarters_barrier = new Image("sprites/barrera4_5_6_Q2.png");
                half_barrier = new Image("sprites/barrera4_5_6_Q3.png");
                one_quarter_barrier = new Image("sprites/barrera4_5_6_Q4.png");
                break;
            case 5:
                full_barrier = new Image("sprites/barrera4_5_6.png");
                three_quarters_barrier = new Image("sprites/barrera4_5_6_Q2.png");
                half_barrier = new Image("sprites/barrera4_5_6_Q3.png");
                one_quarter_barrier = new Image("sprites/barrera4_5_6_Q4.png");
                break;
            case 6:
                full_barrier = new Image("sprites/barrera4_5_6.png");
                three_quarters_barrier = new Image("sprites/barrera4_5_6_Q2.png");
                half_barrier = new Image("sprites/barrera4_5_6_Q3.png");
                one_quarter_barrier = new Image("sprites/barrera4_5_6_Q4.png");
                break;
            case 7:
                full_barrier = new Image("sprites/barrera7.png");
                three_quarters_barrier = new Image("sprites/barrera7_Q2.png");
                half_barrier = new Image("sprites/barrera7_Q3.png");
                one_quarter_barrier = new Image("sprites/barrera7_Q4.png");
                break;
            case 8:
                full_barrier = new Image("sprites/barrera8.png");
                three_quarters_barrier = new Image("sprites/barrera8_Q2.png");
                half_barrier = new Image("sprites/barrera8_Q3.png");
                one_quarter_barrier = new Image("sprites/barrera8_Q4.png");
                break;
            case 9:
                full_barrier = new Image("sprites/barrera9.png");
                three_quarters_barrier = new Image("sprites/barrera9_Q2.png");
                half_barrier = new Image("sprites/barrera9_Q3.png");
                one_quarter_barrier = new Image("sprites/barrera9_Q4.png");
                break;
            default:
                full_barrier = new Image("sprites/barrera1.png");
                three_quarters_barrier = new Image("sprites/barrera1_Q2.png");
                half_barrier = new Image("sprites/barrera1_Q3.png");
                one_quarter_barrier = new Image("sprites/barrera1_Q4.png");
                break;
        }
    }
    
    private void setImageSprite() {
        switch (life) {
            case 0:
                sprite.getImageView().setVisible(false);
                break;
            case 1:
                sprite.getImageView().setImage(one_quarter_barrier);
                break;
            case 2:
                sprite.getImageView().setImage(half_barrier);
                break;
            case 3:
                sprite.getImageView().setImage(three_quarters_barrier);
                break;
            case 4:
                sprite.getImageView().setImage(full_barrier);
                break;
            default:
                sprite.getImageView().setImage(full_barrier);
                break;
        }
    }
}
