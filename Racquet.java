package Pong;

/**
 * Modified by enmargaret on 26/10/2016.
 */
import javafx.scene.paint.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
    private int Y;
    private static final int WITH = 50;
    private static final int HEIGHT = 10;
    private int x = 0;
    private int xa = 0;
    private Game game;

    public Racquet(Game game, int x, int y) { //initializes the racquet with corresponding place
        this.game = game;
        this.x = x;
        this.Y = y;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WITH)
            x = x + xa;
    }

    public void paint(Graphics2D g) {
        if(Y > 200){
            g.setColor(java.awt.Color.pink);
        }
        else if(Y < 200){
            g.setColor(java.awt.Color.orange);
        }
        g.fillRect(x, Y, WITH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if(Y>300) {
            //moves the lower racquet
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                xa = -game.speed;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                xa = game.speed;
        }
        else { //moves the upper racquet
            if (e.getKeyCode() == KeyEvent.VK_D)
                xa = game.speed;

            if (e.getKeyCode() == KeyEvent.VK_A)
                xa = -game.speed;
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WITH, HEIGHT);
    }

    public int getTopY() { //gets the top of the racquet
        if(Y > 200) {
            return Y - HEIGHT;
        }
        else {
            return HEIGHT - Y;
        }

    }
}
