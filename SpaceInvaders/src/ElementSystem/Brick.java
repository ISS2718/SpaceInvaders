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
                three_quarters_barrier = new Image("sprites/barrera1.png");
                half_barrier = new Image("sprites/barrera1.png");
                one_quarter_barrier = new Image("sprites/barrera1.png");
                break;
            case 2:
                full_barrier = new Image("sprites/barrera2.png");
                three_quarters_barrier = new Image("sprites/barrera2.png");
                half_barrier = new Image("sprites/barrera2.png");
                one_quarter_barrier = new Image("sprites/barrera2.png");
                break;
            case 3:
                full_barrier = new Image("sprites/barrera3.png");
                three_quarters_barrier = new Image("sprites/barrera3.png");
                half_barrier = new Image("sprites/barrera3.png");
                one_quarter_barrier = new Image("sprites/barrera3.png");
                break;
            case 4:
                full_barrier = new Image("sprites/barrera4_5_6.png");
                three_quarters_barrier = new Image("sprites/barrera4_5_6.png");
                half_barrier = new Image("sprites/barrera4_5_6.png");
                one_quarter_barrier = new Image("sprites/barrera4_5_6.png");
                break;
            case 5:
                full_barrier = new Image("sprites/barrera4_5_6.png");
                three_quarters_barrier = new Image("sprites/barrera4_5_6.png");
                half_barrier = new Image("sprites/barrera4_5_6.png");
                one_quarter_barrier = new Image("sprites/barrera4_5_6.png");
                break;
            case 6:
                full_barrier = new Image("sprites/barrera4_5_6.png");
                three_quarters_barrier = new Image("sprites/barrera4_5_6.png");
                half_barrier = new Image("sprites/barrera4_5_6.png");
                one_quarter_barrier = new Image("sprites/barrera4_5_6.png");
                break;
            case 7:
                full_barrier = new Image("sprites/barrera7.png");
                three_quarters_barrier = new Image("sprites/barrera7.png");
                half_barrier = new Image("sprites/barrera7.png");
                one_quarter_barrier = new Image("sprites/barrera7.png");
                break;
            case 8:
                full_barrier = new Image("sprites/barrera8.png");
                three_quarters_barrier = new Image("sprites/barrera8.png");
                half_barrier = new Image("sprites/barrera8.png");
                one_quarter_barrier = new Image("sprites/barrera8.png");
                break;
            case 9:
                full_barrier = new Image("sprites/barrera9.png");
                three_quarters_barrier = new Image("sprites/barrera9.png");
                half_barrier = new Image("sprites/barrera9.png");
                one_quarter_barrier = new Image("sprites/barrera9.png");
                break;
            default:
                full_barrier = new Image("sprites/barrera1.png");
                three_quarters_barrier = new Image("sprites/barrera1.png");
                half_barrier = new Image("sprites/barrera1.png");
                one_quarter_barrier = new Image("sprites/barrera1.png");
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
