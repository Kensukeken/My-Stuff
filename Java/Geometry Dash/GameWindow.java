import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame {
    Avatar avatar;
    Obstacle obstacle;

    public GameWindow() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        avatar = new Avatar();
        obstacle = new Obstacle(400, 300);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    avatar.jump();
                }
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        avatar.draw(g);
        obstacle.draw(g);
    }

    public static void main(String[] args) {
        new GameWindow();
    }
}

class Avatar {
    int x, y;
    int vy;
    final int GRAVITY = 1;

    public Avatar() {
        x = 50;
        y = 300;
    }

    public void jump() {
        vy = -10;
    }

    public void update() {
        y += vy;
        vy += GRAVITY;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, 20, 20);
    }
}

class Obstacle {
    int x, y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, 20, 20);
    }
}
