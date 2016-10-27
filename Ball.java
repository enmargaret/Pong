package Pong;

/**
 * Modified by enmargaret on 26/10/2016.
 */
import java.awt.*;

public class Ball {
    private static final int DIAMETER = 20;
    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;
    private int score1 = 0;
    private int score2 = 0;
    private Game game;
    public Ball(Game game) {
        this.game = game;
    }

    void move() {
        boolean changeDirection = true;
        if (x + xa < 0)
            xa = game.speed;
        else if (x + xa > game.getWidth() - DIAMETER)
            xa = -game.speed;
        else if (y + ya < 0){ //if the ball goes beyond the racquet, score is added and the ball is repainted to the middle
            ya = game.speed;
            score1++;
            x = game.getWidth()/2;
            y = game.getHeight()/2;
        }
        else if (y + ya > game.getHeight() - DIAMETER) {
            score2++;
            x = game.getWidth()/2;
            y = game.getHeight()/2;

         ya = -game.speed;
        }
        else if (collision()){
            ya = -game.speed;
            if(y>200) {
                y = game.racquet.getTopY() - DIAMETER;
            }
            else{
                y = DIAMETER - game.racquet2.getTopY() ;
                ya = game.speed;
            }
        } else {
            changeDirection = false;
        }

        if(score1 == 3 || score2 == 3){
            game.gameOver();
            score1 = 0; score2 = 0;
        }

        if (changeDirection)
            Sound.BALL.play();
        x = x + xa;
        y = y + ya;
    }

    public int getScore1(){
        return score1;
    }

    public int getScore2(){
        return score2;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    private boolean collision() {
        if(y > 200) {
            return game.racquet.getBounds().intersects(getBounds()); //returns the part where the ball and racquet intersects
        }
        else{
            return game.racquet2.getBounds().intersects(getBounds());
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    } //paints the ball

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
