import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GeometryDashGame extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int TIME_TICK = 10;

    private Timer timer;
    private int score;
    private boolean gameOver;
    private Cube cube;
    private Obstacle obstacle;
    private JButton playAgainButton;

    public GeometryDashGame() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && gameOver) {
                    resetGame();
                } else if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
                    resetGame();
                } else if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
                    cube.jump();
                }
            }
        });

        setBackground(Color.blue);

        timer = new Timer(TIME_TICK, this);
        timer.start();
        cube = new Cube();
        obstacle = new Obstacle();

        playAgainButton = new JButton("Play again");
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        playAgainButton.setVisible(false);

        // Use GridBagLayout to center the button both horizontally and vertically
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(playAgainButton, gbc);

        // Adjust the button size as needed
        playAgainButton.setPreferredSize(new Dimension(200, 100));

        // Add key listener to the button
        playAgainButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
                    resetGame();
                }
            }
        });
        // Set focusable to true for the button to receive key events
        playAgainButton.setFocusable(true);

    }

    public void paint(Graphics g) {
        super.paint(g);
        cube.draw(g);
        obstacle.draw(g);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);

        if (gameOver) {
            playAgainButton.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            updateGame();
            repaint();
        }
    }

    private void updateGame() {
        if (!gameOver) {
            cube.move();
            obstacle.move();
            checkCollision();
            checkScore();
        }
    }

    private void checkCollision() {
        if (cube.intersects(obstacle)) {
            score = 0;
            cube.reset();
            gameOver = true;
        }
    }

    private void checkScore() {
        if (cube.passes(obstacle)) {
            score++;
        }
    }

    private void resetGame() {
        cube.reset();
        obstacle.reset();
        score = 0;
        gameOver = false;
        playAgainButton.setVisible(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GeometryDashGame game = new GeometryDashGame();
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Allow the "R" key to restart the game
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R && game.gameOver) {
                    game.resetGame();
                }
            }
        });
    }
}





class Cube {
    private int x, y;
    private int width, height;
    private boolean isJumping;
    private int jumpSpeed;

    public Cube() {
        x = 50;
        y = 500;
        width = 50;
        height = 50;
        isJumping = false;
        jumpSpeed = 0;
    }

    public void move() {
        if (isJumping) {
            y -= jumpSpeed;
            jumpSpeed -= 1;
            if (y >= 500) {
                isJumping = false;
                y = 500;
            }
        }
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            jumpSpeed = 20;
        }
    }

    public void reset() {
        isJumping = false;
        jumpSpeed = 0;
        y = 500;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);

        g.setColor(Color.CYAN);
        g.fillRect(x + 10, y + 10, 10, 10); // Left eye
        g.fillRect(x + 30, y + 10, 10, 10); // Right eye

        g.setColor(Color.CYAN);
        g.fillRect(x + 10, y + 30, 30, 10); // Mouth
    }

    public boolean intersects(Obstacle obstacle) {
        int obstacleTop = obstacle.getY();
        int obstacleBottom = obstacle.getY() + obstacle.getHeight();
        int obstacleLeft = obstacle.getX();
        int obstacleRight = obstacle.getX() + obstacle.getWidth();

        int cubeRight = x + width;
        int cubeBottom = y + height;

        return !(cubeRight <= obstacleLeft || x >= obstacleRight || cubeBottom <= obstacleTop || y >= obstacleBottom);
    }

    public boolean passes(Obstacle obstacle) {
        return x + width <= obstacle.getX();
    }
}

class Obstacle {
    private int x, y;
    private int width, height;
    private int speed;

    public Obstacle() {
        x = GeometryDashGame.WIDTH;
        y = (int) (Math.random() * (GeometryDashGame.HEIGHT - 200)) + 100;
        width = 50;
        height = 50 + (int) (Math.random() * 300);
        speed = -2;
    }

    public void move() {
        x += speed;
        if (x + width <= 0) {
            x = GeometryDashGame.WIDTH;
            y = (int) (Math.random() * (GeometryDashGame.HEIGHT - 200)) + 100;
            height = 50 + (int) (Math.random() * 300);
        }
    }

    public void reset() {
        x = GeometryDashGame.WIDTH;
        y = (int) (Math.random() * (GeometryDashGame.HEIGHT - 200)) + 100;
        width = 50;
        height = 50 + (int) (Math.random() * 300);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
