package Engine;

import java.util.*;

/**
 * 
 */
public class Player {
    /**
     * 
     */
    private int lifes;

    /**
     * 
     */
    private int score;

    /**
     * @param lifes
     */
    public Player(int lifes) {
        score = 0;
        this.lifes = lifes;
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
    }

    /**
     * 
     */
    public void decreasesLifes() {
        lifes--;
    }

}