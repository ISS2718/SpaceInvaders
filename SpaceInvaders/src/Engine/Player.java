package Engine;

import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * 
 */
public class Player {
    /**
     * 
     */
    private ImageView[] hearts;
    
    /**
     * 
     */
    private double hearts_space;
    
    /**
     * 
     */
    private Label label_life;
    
    /**
     * 
     */
    private Label label_score;

    /**
     * 
     */
    private int lifes;

    /**
     * 
     */
    private int score;
    
    /**
     *
     */
    private Label text_score;

    /**
     * @param lifes
     */
    public Player(int lifes, double hearts_space, Label label_life, Label label_score, Label text_score) {
        this.hearts_space = hearts_space;
        this.lifes = lifes;
        this.label_life = label_life;
        this.label_score = label_score;
        this.text_score = text_score;
        this.score = 0;
        setHearts();
    }

    /**
     *
     */
    public void decreasesLifes() {
        if (lifes != 0) {
            lifes--;
            hearts[lifes].setVisible(false);
        }
    }
    
    public void destructor(AnchorPane main) {
        label_life.setVisible(false);
        label_score.setVisible(false);
        text_score.setText(String.format("%08d", 0));
        text_score.setVisible(false);
        for (int i = 0; i < lifes; i++) {
            main.getChildren().remove(hearts[i]);
        }   
    }
    
    public void draw(AnchorPane main) {
        label_life.setVisible(true);
        label_score.setVisible(true);
        text_score.setVisible(true);
        text_score.setText(String.format("%08d", score));
        for (int i = 0; i < lifes; i++) {
            main.getChildren().add(hearts[i]);
            hearts[i].setVisible(true);
        }   
    }
    
    /**
     * @return
     */
    public int getLifes() {
        return lifes;
    }

    /**
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * @param add 
     */
    public void increasesScore(int add) {
        score += add;
        text_score.setText(String.format("%08d", score));
    }
    
    private void setHearts() {
        hearts = new ImageView[lifes];
        for(int i = 0; i < lifes; i++) {
            hearts[i] = new ImageView("sprites/Vida.png");
            hearts[i].setLayoutX(0);
            hearts[i].setX(label_life.getPrefWidth() + label_life.getLayoutX() + (hearts[i].getImage().getWidth() * i) + (hearts_space * i));
            hearts[i].setLayoutY(0);
            hearts[i].setY(2 * label_life.getLayoutY());
        }
    }
}