package src;
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


        setBackground(Color.BLUE);
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
        setLayout(new BorderLayout());
        add(playAgainButton, BorderLayout.SOUTH);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        cube.draw(g);
        obstacle.draw(g);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);

        if (gameOver) {
            playAgainButton.setVisible(true);
            g.setColor(Color.RED);
            g.drawString("Game Over! Press 'R' to Play again.", WIDTH / 4, HEIGHT / 2);
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

// Cube
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
    }

    public boolean intersects(Obstacle obstacle) {
        int obstacleTop = obstacle.getY();
        int obstacleBottom = obstacle.getY() + obstacle.getHeight();
        int obstacleLeft = obstacle.getX();
        int obstacleRight = obstacle.getX() + obstacle.getWidth();

        int cubeRight = x + width;
        int cubeBottom = y + height;

        if (!(cubeRight <= obstacleLeft || x >= obstacleRight || cubeBottom <= obstacleTop || y >= obstacleBottom)) {
            return true;
        }

        return false;
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
        g.setColor(Color.RED);
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

    public int getHeight() {
        return height;
    }
}
