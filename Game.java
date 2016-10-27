package Pong;

/**
 * Modified by enmargaret on 26/10/2016.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel {

    Ball ball = new Ball(this);
    Racquet racquet = new Racquet(this, 150, 340);
    Racquet racquet2 = new Racquet(this, 150, getHeight() + 10);
    int speed = 2;

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racquet.keyReleased(e);
                racquet2.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {//adds an event that makes the program listen to the key being pressed
                racquet.keyPressed(e);
                racquet2.keyPressed(e);
            }
        });
        setFocusable(true);
        Sound.BACK.loop();
    }

    private void move() {
        ball.move();
        racquet.move();
        racquet2.move();
    }

    @Override
    public void paint(Graphics g) { //paints the objects to the frame
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        racquet.paint(g2d);
        racquet2.paint(g2d);
        this.setBackground(Color.DARK_GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.setColor(Color.PINK);
        g2d.drawString(String.valueOf(ball.getScore1()), 0, getHeight()/2); //draws the string onto the frame
        g2d.setColor(java.awt.Color.orange);
        g2d.drawString(String.valueOf(ball.getScore2()), getWidth()-30, getHeight()/2);
    }

    public void gameOver() {
        String num = "";
        int choice = 0;
        Sound.BACK.stop();
        Sound.GAMEOVER.play();
        if(ball.getScore1() == 3) {
            num = "1";
        }
        else if(ball.getScore2() == 3){
            num = "2";
        }
        //displays a dialog box with yes and no choices
        choice = JOptionPane.showConfirmDialog(null, "PLAYER " + num + " WINS!\nPlay again?","Restart?", JOptionPane.YES_NO_OPTION);

        if(choice == JOptionPane.YES_OPTION){
            ball.setLocation(150,200);
            Sound.BACK.loop();
        }
        else if(choice == JOptionPane.NO_OPTION) {
            System.exit(ABORT);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Pong"); //Makes a frame
        Game game = new Game(); //Makes a Game
        frame.add(game); //Adds the game to the frame
        frame.setSize(300, 400);
        frame.setVisible(true); //sets the visibility of the frame to 'true' to make it visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}