/*
 * ArkanoidGame.java
 * This is a Java program for an Arkanoid game.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ArkanoidGame extends JPanel implements KeyListener, ActionListener {
    Timer timer;
    Ball ball;
    Paddle paddle;
    Brick[] bricks;
    boolean gameOver;

    public static final int WIDTH = 800; // We define the WIDTH of the game here

    public ArkanoidGame() {
        ball = new Ball(400, 300, 5, -5);
        paddle = new Paddle(350, 550);

        // We prepare the bricks and other game-related stuff.
        bricks = new Brick[40]; // Assuming you have 40 bricks

        int brickX = 50;
        int brickY = 50;
        int brickWidth = 70;
        int brickHeight = 20;

        for (int i = 0; i < bricks.length; i++) {
            bricks[i] = new Brick(brickX, brickY, brickWidth, brickHeight);
            brickX += brickWidth + 10; // We leave some space between bricks
            if (i % 10 == 9) { // When we reach 10 bricks in a row, we start a new row
                brickX = 50;
                brickY += brickHeight + 10; // Creating a new row below
            }
        }

        setFocusable(true); // Make sure the game window can get user's attention
        requestFocus(); // Ask the game window to listen to user's input
        addKeyListener(this); // Let's respond to user's keyboard actions
        timer = new Timer(10, this); // We set a timer to move things every 10 milliseconds
        timer.start();
    }

    public void move() {
        if (!gameOver) {
            ball.move(); // The ball keeps moving
            paddle.move(); // The paddle moves too

            // We check for ball hitting bricks and update the game
            for (Brick brick : bricks) {
                if (brick.isVisible() && ball.intersects(brick)) {
                    brick.setInvisible(); // If the ball hits a brick, it disappears
                    ball.reverseYDirection(); // The ball bounces back
                }
            }

            if (ball.intersects(paddle)) {
                ball.reverseYDirection(); // The ball bounces back from the paddle
            }

            if (ball.getX() <= 0 || ball.getX() >= getWidth() - ball.getDiameter()) {
                ball.reverseXDirection(); // The ball bounces back from the walls
            }

            if (ball.getY() <= 0) {
                ball.reverseYDirection(); // The ball bounces back from the top
            }

            if (ball.getY() >= getHeight()) {
                gameOver = true; // If the ball falls down, it's game over
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            paddle.setLeftPressed(true); // Move the paddle left when the 'A' or left arrow key is pressed
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            paddle.setRightPressed(true); // Move the paddle right when the 'D' or right arrow key is pressed
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            paddle.setLeftPressed(false); // Stop moving left when the 'A' or left arrow key is released
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            paddle.setRightPressed(false); // Stop moving right when the 'D' or right arrow key is released
        }
    }

    // We don't need these, but they are required by Java
    public void keyTyped(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        move(); // This is where the game objects move
        repaint(); // We ask Java to repaint the screen
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameOver) {
            paddle.draw(g); // We draw the paddle
            ball.draw(g); // We draw the ball
            for (Brick brick : bricks) {
                brick.draw(g); // We draw the bricks
            }
        } else {
            g.setColor(Color.RED); // We use a red color for the text
            g.setFont(new Font("Arial", Font.BOLD, 36)); // A big and bold font
            g.drawString("Game Over", 320, 300); // We show "Game Over" on the screen
            // You can add the player's score or other end-game info here.
        }
    }

    public static void main(String[] arguments) {
        JFrame frame = new JFrame("Arkanoid Game"); // We create a game window
        ArkanoidGame game = new ArkanoidGame(); // We create the game itself
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The game closes when the window is closed
        frame.setSize(WIDTH, 600); // We set the window's size using the WIDTH constant
        frame.add(game); // We put the game inside the window
        frame.setVisible(true); // We make the window visible
    }
}
