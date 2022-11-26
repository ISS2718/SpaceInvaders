package Engine;

import ElementSystem.Coordinates;
import java.util.*;

/**
 * 
 */
public class Screen {

    private Coordinates size;

    /**
     * @param height 
     * @param width
     */
    public Screen(double width, double height) {
        size = new Coordinates(width-2, height-4);
    }

    /**
     * 
     * @return
     */
    public Coordinates getSize() {
        return size;
    }

    /**
     * 
     */
    private void drawBeginOfScreen() {
        System.out.print('|');
    }

    /**
     * 
     */
    private void drawEndOfScreen(int quantity_charactere_on_screen) {
        for(int i = quantity_charactere_on_screen; i < size.getX(); i++) {
            System.out.print(' ');
        }
        System.out.print('|');
    }

    private void drawLine() {
        for (int j = 0; j <= ((int)size.getX()) + 1; j++) {
            System.out.print('=');
        }
        System.out.println();
    }

    /**
     * 
     */
    private void drawTopOfScreen(Player p) {
        this.drawLine();
        System.out.print("Score: " + p.getScore());
        System.out.println("    Lifes: " + p.getLifes()); 
        this.drawLine();
    }

    /**
     *
     */
    public void drawScreen(Game game) {
        Coordinates actual_coordinates = new Coordinates(0, 0);
        this.drawTopOfScreen(game.getPlayer());
        for(int i = ((int)size.getY()); i >= 0; i--) {
            actual_coordinates.setY(i);
                for (int j = 0; j < ((int)size.getX()); j++) {
                    actual_coordinates.setX(j);
                    if(game.getAliensMatrix().getCoordinates().getX() == actual_coordinates.getX() && game.getAliensMatrix().getCoordinates().getY() == actual_coordinates.getY()) {
                        for(int a = game.getAliensMatrix().getRows() - 1; a >= 0 ; a--) {
                            this.drawBeginOfScreen();
                            game.getAliensMatrix().draw(a, j);
                            this.drawEndOfScreen(j + game.getAliensMatrix().getcolumns());
                            if((a - 1) >= 0) {
                                System.out.println();
                            }
                            i--;
                        }
                        break;
                    } else if(((int)game.getCannon().getCoordinates().getX()) == actual_coordinates.getX() && game.getCannon().getCoordinates().getY() == actual_coordinates.getY()) {
                        this.drawBeginOfScreen();
                        game.getCannon().draw(j);
                        this.drawEndOfScreen(j+1);
                        i--;
                        break;
                    } else if (game.getHaveSpaceShip()  && ((int)game.getSpaceship().getCoordinates().getX()) == actual_coordinates.getX() && game.getSpaceship().getCoordinates().getY() == actual_coordinates.getY()) {
                        this.drawBeginOfScreen();
                        game.getSpaceship().draw(j);
                        this.drawEndOfScreen(j+1);
                        i--;
                        break;
                    } else if(!((j+1) < ((int)size.getX()))){
                        this.drawBeginOfScreen();
                        this.drawEndOfScreen(0);
                    }
                }
                System.out.println();
        }
        this.drawLine();
    }
}