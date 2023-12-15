package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CubeGame extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final int CUBE_SIZE = 50;
    private static final int OBSTACLE_WIDTH = 20;
    private static final int OBSTACLE_HEIGHT = 100; // Increased obstacle height

    private int cubeY = HEIGHT / 2 - CUBE_SIZE / 2;
    private int cubeVelocity = 0;
    private int rotationAngle = 0;

    private Timer timer;
    private boolean isJumping = false;
    private boolean isGameOver = false;
    private int obstacleX = WIDTH;

    private JButton tryAgainButton;
    private JButton jumpButton;

    public CubeGame() {
        setTitle("Stereo Madness");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jumpButton = new JButton("Jump");
        jumpButton.setBounds(WIDTH / 2 - 50, HEIGHT - 50, 100, 30);
        jumpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver && !isJumping) {
                    jump();
                }
            }
        });

        add(jumpButton);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isGameOver) {
                    restartGame();
                } else {
                    jump();
                }
            }
        });

        timer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateGame();
            }
        });

        tryAgainButton = new JButton("Try Again");
        tryAgainButton.setBounds(WIDTH / 2 - 50, HEIGHT / 2 + 50, 100, 30);
        tryAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        add(tryAgainButton);

        timer.start();
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        if (!isGameOver && evt.getKeyCode() == KeyEvent.VK_SPACE && !isJumping) {
            jump();
        }
    }

    private void restartGame() {
        isGameOver = false;
        cubeY = HEIGHT / 2 - CUBE_SIZE / 2;
        cubeVelocity = 0;
        rotationAngle = 0;
        obstacleX = WIDTH;
        tryAgainButton.setVisible(false);
    }

    private void jump() {
        isJumping = true;
        cubeVelocity = -20; // Decrease initial jump velocity
        rotationAngle = 30; // Rotate the cube by 30 degrees
    }

    private void updateGame() {
        if (!isGameOver) {
            cubeVelocity += 1; // Increase downward acceleration
            cubeY += cubeVelocity;

            if (cubeY > HEIGHT - CUBE_SIZE) {
                cubeY = HEIGHT - CUBE_SIZE;
                isJumping = false;
                rotationAngle = 0; // Reset rotation when landing
            } else {
                rotationAngle += 5; // Increase rotation during jump
            }

            updateObstacle();

            // Check for collision with the obstacle
            if (cubeY + CUBE_SIZE > HEIGHT - OBSTACLE_HEIGHT && cubeY < HEIGHT) {
                if (obstacleX < WIDTH / 4 && obstacleX + OBSTACLE_WIDTH > WIDTH / 4) {
                    // Collision occurred
                    isGameOver = true;
                    tryAgainButton.setVisible(true);
                }
            }
        }

        repaint();
    }

    private void updateObstacle() {
        obstacleX -= 10; // Increase the obstacle movement speed

        if (obstacleX + OBSTACLE_WIDTH < 0) {
            obstacleX = WIDTH; // Reset obstacle position when it goes off-screen
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw rotated cube
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(rotationAngle), WIDTH / 4 + CUBE_SIZE / 2, cubeY + CUBE_SIZE / 2);
        g2d.setColor(Color.BLUE);
        g2d.fillRect(WIDTH / 4, cubeY, CUBE_SIZE, CUBE_SIZE);
        g2d.rotate(-Math.toRadians(rotationAngle), WIDTH / 4 + CUBE_SIZE / 2, cubeY + CUBE_SIZE / 2);

        // Draw opening obstacles
        g.setColor(Color.RED);
        g.fillRect(obstacleX, 0, OBSTACLE_WIDTH, HEIGHT - 100); // Adjusted obstacle height
        g.fillRect(obstacleX, HEIGHT - 100 + OBSTACLE_HEIGHT, OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CubeGame().setVisible(true);
            }
        });
    }
}
