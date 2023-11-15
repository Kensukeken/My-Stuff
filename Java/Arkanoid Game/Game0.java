/* Game0.java
   A Java program for an Arkanoid game.
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Game0 extends JFrame {
    ArkanoidGamePanel game = new ArkanoidGamePanel();

    public Game0() {
        super("Arkanoid Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Set the window to close when the 'X' button is clicked
        setSize(800, 600);  // Set the size of the game window
        add(game);  // Add the game panel to the window
        setVisible(true);  // Make the game window visible
    }

    public static void main(String[] arguments) {
        Game0 frame = new Game0();  // Create an instance of the game frame
    }
}

class ArkanoidGamePanel extends JPanel implements KeyListener, ActionListener {
    Timer timer;
    Ball ball;
    Paddle paddle;
    ArrayList<Brick> bricks;
    int score;
    boolean gameOver;

    public ArkanoidGamePanel() {
        ball = new Ball(400, 300, 5, -5);  // Initialize the ball at a specific position and speed
        paddle = new Paddle(350, 550);  // Initialize the paddle at a specific position
        bricks = new ArrayList<Brick>();  // Create a list to store bricks
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                bricks.add(new Brick(j * 100, i * 20 + 50, 90, 15));  // Add bricks to the game
            }
        }
        setFocusable(true);  // Make the game panel focusable
        requestFocus();  // Request keyboard input focus
        addKeyListener(this);  // Add a key listener for user input
        timer = new Timer(10, this);  // Set up a timer for game updates every 10 milliseconds
        timer.start();  // Start the game timer
        score = 0;  // Initialize the player's score
        gameOver = false;  // Flag to track the game state

        // Add a key listener for the 'R' key to play again
        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
                    resetGame();  // Start a new game
                }
            }
        });
    }

    public void resetGame() {
        ball = new Ball(400, 300, 5, -5);  // Reset the ball's position and speed
        paddle = new Paddle(350, 550);  // Reset the paddle's position
        bricks.clear();  // Clear the list of bricks
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                bricks.add(new Brick(j * 100, i * 20 + 50, 90, 15));  // Add bricks to the game
            }
        }
        score = 0;  // Reset the player's score
        gameOver = false;  // Reset the game state
        repaint();  // Repaint to update the game screen
    }

    public void move() {
        if (!gameOver) {
            paddle.move();  // Move the paddle
            ball.move();  // Move the ball
            checkCollisions();  // Check for collisions
        }
    }

    public void checkCollisions() {
        // Implement collision detection between the ball, paddle, and bricks.
        for (int i = bricks.size() - 1; i >= 0; i--) {
            Brick brick = bricks.get(i);
            if (brick.isVisible() && ball.intersects(brick)) {
                brick.setInvisible();  // Hide the brick
                score += 10;  // Increase the player's score
                if (score == bricks.size() * 10) {
                    gameOver = true;  // Game over when all bricks are destroyed
                }
                ball.reverseYDirection();  // Change the ball's vertical direction
            }
        }

        if (ball.intersects(paddle)) {
            ball.reverseYDirection();  // Change the ball's vertical direction
        }

        if (ball.getX() <= 0 || ball.getX() >= getWidth() - ball.getDiameter()) {
            ball.reverseXDirection();  // Change the ball's horizontal direction
        }

        if (ball.getY() <= 0) {
            ball.reverseYDirection();  // Change the ball's vertical direction
        }

        if (ball.getY() >= getHeight()) {
            gameOver = true;  // Game over if the ball goes out of bounds
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            paddle.setLeftPressed(true);  // Handle left movement input
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            paddle.setRightPressed(true);  // Handle right movement input
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            paddle.setLeftPressed(false);  // Release left movement input
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            paddle.setRightPressed(false);  // Release right movement input
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();  // Execute game logic
        repaint();  // Repaint the game panel
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Set the background to black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (!gameOver) {
            paddle.draw(g);  // Draw the paddle
            ball.draw(g);  // Draw the ball
            for (Brick brick : bricks) {
                brick.draw(g);  // Draw the bricks
            }
        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over", 320, 300);  // Display "Game Over" message
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Your Score: " + score, 340, 350);  // Display the player's score
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Press 'R' to play again", 340, 380);  // Prompt to play again
        }
    }
}
