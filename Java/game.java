import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ArkanoidGame extends JPanel implements KeyListener, ActionListener {
    private int ballX = 300, ballY = 400, ballSpeedX = 2, ballSpeedY = 2;
    private int paddleX = 250, paddleSpeed = 20;
    private boolean play = false;
    private int score = 0;

    private ArrayList<Rectangle> bricks = new ArrayList<Rectangle>();


    public ArkanoidGame() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer timer = new Timer(5, this);
        timer.start();
        initializeBricks();
    }

    private void initializeBricks() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                bricks.add(new Rectangle(i * 70 + 30, j * 50 + 50, 60, 30));
            }
        }
    }

    public void paint(Graphics g) {
        // Background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // Bricks
        for (Rectangle brick : bricks) {
            g.setColor(Color.white);
            g.fillRect(brick.x, brick.y, brick.width, brick.height);
        }

        // Paddle
        g.setColor(Color.green);
        g.fillRect(paddleX, 550, 100, 8);

        // Ball
        g.setColor(Color.yellow);
        g.fillOval(ballX, ballY, 20, 20);

        // Score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, 590, 30);

        if (bricks.isEmpty()) {
            play = false;
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won!", 260, 300);
        }

        if (ballY > 570) {
            play = false;
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over", 250, 300);
        }
        g.dispose();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddleX >= 600) {
                paddleX = 600;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddleX <= 10) {
                paddleX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ballX = 300;
                ballY = 400;
                ballSpeedX = 2;
                ballSpeedY = 2;
                paddleX = 250;
                score = 0;
                bricks.clear();
                initializeBricks();
                repaint();
            }
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void moveRight() {
        play = true;
        paddleX += paddleSpeed;
    }

    public void moveLeft() {
        play = true;
        paddleX -= paddleSpeed;
    }

    public void actionPerformed(ActionEvent e) {
        if (play) {
            if (new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(paddleX, 550, 100, 8))) {
                ballSpeedY = -ballSpeedY;
            }

            for (Rectangle brick : bricks) {
                if (new Rectangle(ballX, ballY, 20, 20).intersects(brick)) {
                    bricks.remove(brick);
                    score += 5;
                    if (ballX + 19 <= brick.x || ballX + 1 >= brick.x + brick.width) {
                        ballSpeedX = -ballSpeedX;
                    } else {
                        ballSpeedY = -ballSpeedY;
                    }
                    break;
                }
            }

            ballX += ballSpeedX;
            ballY += ballSpeedY;

            if (ballX < 0) {
                ballSpeedX = -ballSpeedX;
            }

            if (ballY < 0) {
                ballSpeedY = -ballSpeedY;
            }

            if (ballX > 670) {
                ballSpeedX = -ballSpeedX;
            }
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ArkanoidGame game = new ArkanoidGame();
        frame.setSize(700, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);
    }
}
