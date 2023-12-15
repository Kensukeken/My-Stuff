package src;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CubeGame extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final int CUBE_SIZE = 50;
    private static final int OBSTACLE_WIDTH = 20;
    private static final int OBSTACLE_HEIGHT = HEIGHT;

    private int cubeY = HEIGHT / 2 - CUBE_SIZE / 2;
    private int cubeVelocity = 0;
    private int rotationAngle = 0;

    private Timer timer;
    private boolean isJumping = false;
    private int obstacleX = WIDTH;

    private boolean isGameOver = false;

    public CubeGame() {
        setTitle("Stereo Madness");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isGameOver) {
                    restartGame();
                }
            }
        });

        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateGame();
            }
        });

        timer.start();
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        if (!isGameOver && evt.getKeyCode() == KeyEvent.VK_SPACE && !isJumping) {
            jump();
        } else if (isGameOver && evt.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    private void restartGame() {
        isGameOver = false;
        cubeY = HEIGHT / 2 - CUBE_SIZE / 2;
        cubeVelocity = 0;
        rotationAngle = 0;
        obstacleX = WIDTH;
    }

    private void jump() {
        isJumping = true;
        cubeVelocity = -10;
    }

    private void updateGame() {
        cubeY += cubeVelocity;
        cubeVelocity += 1;

        if (cubeY > HEIGHT - CUBE_SIZE) {
            cubeY = HEIGHT - CUBE_SIZE;
            isJumping = false;
            rotationAngle = 0; // Reset rotation when landing
        } else {
            rotationAngle += 5; // Increase rotation during jump
        }

        if (!isGameOver) {
            obstacleX -= 5; // Move the obstacle to the left

            if (obstacleX + OBSTACLE_WIDTH < 0) {
                obstacleX = WIDTH; // Reset obstacle position when it goes off-screen
            }

            // Check for collision with the obstacle
            if (cubeY + CUBE_SIZE > HEIGHT - OBSTACLE_HEIGHT && cubeY < HEIGHT) {
                if (obstacleX < WIDTH / 4 && obstacleX + OBSTACLE_WIDTH > WIDTH / 4) {
                    // Collision occurred
                    isGameOver = true;
                }
            }
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (!isGameOver) {
            // Draw rotated cube
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(Math.toRadians(rotationAngle), WIDTH / 4 + CUBE_SIZE / 2, cubeY + CUBE_SIZE / 2);
            g2d.setColor(Color.BLUE);
            g2d.fillRect(WIDTH / 4, cubeY, CUBE_SIZE, CUBE_SIZE);
            g2d.rotate(-Math.toRadians(rotationAngle), WIDTH / 4 + CUBE_SIZE / 2, cubeY + CUBE_SIZE / 2);

            // Draw opening obstacles
            int obstacleGap = 100; // Adjust this value to control the gap between obstacles
            int obstacleHeight = 150; // Adjust this value to control the obstacle height

            g.setColor(Color.RED);
            g.fillRect(obstacleX, 0, OBSTACLE_WIDTH, HEIGHT - obstacleGap - obstacleHeight);
            g.fillRect(obstacleX, HEIGHT - obstacleGap, OBSTACLE_WIDTH, obstacleHeight);
        } else {
            // Draw "Game Over" message
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            String gameOverMessage = "Game Over";
            g.drawString(gameOverMessage, WIDTH / 2 - g.getFontMetrics().stringWidth(gameOverMessage) / 2, HEIGHT / 2);

            // Draw "Try Again" message
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            String tryAgainMessage = "Press 'R' or click to try again";
            g.drawString(tryAgainMessage, WIDTH / 2 - g.getFontMetrics().stringWidth(tryAgainMessage) / 2, HEIGHT / 2 + 30);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CubeGame().setVisible(true);
            }
        });
    }
}
