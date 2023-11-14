import javax.swing.*;
import java.awt.*;

public class Game0 extends JFrame {
    public static final int APP_WIDTH = 400;
    public static final int APP_HEIGHT = 600;

    public static final int WIDTH = APP_WIDTH;
    public static final int HEIGHT = APP_HEIGHT;

    public static final int PADDLE_WIDTH = 60;
    public static final int PADDLE_HEIGHT = 10;

    public static final int PADDLE_Y_OFFSET = 30;

    public static final int BRICKS_PER_ROW = 10;

    public static final int BRICKS_ROWS = 10;
    public static final int BRICK_SEP = 4;

    public static final int BRICK_WIDTH = (WIDTH - (BRICKS_PER_ROW - 1) * BRICK_SEP) / BRICKS_PER_ROW;

    public static final int BRICK_HEIGHT = 8;
    public static final int BALL_RADIUS = 10;

    public static final int BRICK_Y_OFFSET = 70;

    public static final int TURNS = 3;

    public Game0() {
        super("Arkanoid Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel game = new GamePanel();
        add(game);
        pack();
        setLocationRelativeTo(null); // Center the JFrame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game0());
    }
}

class GamePanel extends JPanel {
    private Brick[][] bricks;

    public GamePanel() {
        bricks = new Brick[1][Game0.BRICKS_PER_ROW];
        for (int i = 0; i < bricks[0].length; i++) {
            int brickX = i * (Game0.BRICK_WIDTH + Game0.BRICK_SEP);
            int brickY = Game0.BRICK_Y_OFFSET;
            bricks[0][i] = new Brick(brickX, brickY, Game0.BRICK_WIDTH, Game0.BRICK_HEIGHT, Color.RED);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                brick.draw(g);
            }
        }
    }
}
