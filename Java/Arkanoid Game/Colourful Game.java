import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArkanoidGame extends JFrame implements ActionListener, KeyListener, MouseListener {

    public static final int WIDTH = 800; // Increased window size for better visibility
    public static final int HEIGHT = 600;

    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks;
    private boolean[] keys = new boolean[256];
    private boolean gameOver = false;
    private String screen = "intro";

    private static final int TIMER_DELAY = 10; // Adjusted timer delay for smoother animations

    public ArkanoidGame() {
        initializeGameWindow();
        initializeGameElements();
        setupGameTimer();
        addInputListeners();
    }

    private void initializeGameWindow() {
        setTitle("Arkanoid Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Prevent resizing for consistent gameplay
    }

    private void initializeGameElements() {
        ball = new Ball(WIDTH / 2, HEIGHT / 2, 3, -3); // Increased initial ball speed
        paddle = new Paddle(WIDTH / 2 - 50, HEIGHT - 20);
        bricks = createBricks();
    }

    private List<Brick> createBricks() {
        List<Brick> brickList = new ArrayList<Brick>();
        Random random = new Random();

        int brickWidth = 60; // Increased brick size for better visibility
        int brickHeight = 30;
        int space = 5;
        int rows = 4;
        int cols = WIDTH / (brickWidth + space);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int brickX = j * (brickWidth + space);
                int brickY = i * (brickHeight + space) + 50;
                Color randomColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
                brickList.add(new Brick(brickX, brickY, brickWidth, brickHeight, randomColor));
            }
        }
        return brickList;
    }

    private void setupGameTimer() {
        Timer timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    private void addInputListeners() {
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            checkCollisions();
            repaint();
        }
    }

    private void move() {
        ball.move();

        if ((keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]) && paddle.getX() > 0) {
            paddle.moveLeft();
        }
        if ((keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]) && paddle.getX() + paddle.getWidth() < WIDTH) {
            paddle.moveRight();
        }
    }

    private void checkCollisions() {
        if (ball.getY() - ball.getDiameter() / 2 <= 0) {
            ball.RYD();
        }

        if (ball.getX() - ball.getDiameter() / 2 <= 0 || ball.getX() + ball.getDiameter() / 2 >= WIDTH) {
            ball.RXD();
        }

        if (ball.getY() + ball.getDiameter() / 2 >= HEIGHT) {
            gameOver = true;
            screen = "intro";
        }

        if (gameOver) {
            return;
        }

        if (ball.hitsPaddle(paddle)) {
            ball.RYD();
        }

        List<Brick> bricksToRemove = new ArrayList<Brick>();
        for (Brick brick : bricks) {
            if (ball.hitsBrick(brick) && brick.isVisible()) {
                brick.setInvisible();
                bricksToRemove.add(brick);
                ball.RYD();
            }
        }
        bricks.removeAll(bricksToRemove);

        if (bricks.isEmpty()) {
            gameOver = true;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGameComponents(g);

        if (gameOver) {
            displayGameOverMessage(g);
        }
    }

    private void drawGameComponents(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        ball.draw(g);
        paddle.draw(g);
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    private void displayGameOverMessage(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Game Over! Press 'R' to restart", WIDTH / 2 - 150, HEIGHT / 2);
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        keys[code] = true;

        if (gameOver && (e.getKeyChar() == 'r' || e.getKeyChar() == 'R')) {
            resetGame();
        }
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        keys[code] = false;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (screen.equals("intro")) {
            screen = "main";
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    private void resetGame() {
        initializeGameElements();
        gameOver = false;
        repaint();
    }

   public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ArkanoidGame game = new ArkanoidGame();
                game.setVisible(true);
            }
        });
    }
}
