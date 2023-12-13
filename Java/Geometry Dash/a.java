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
        private Cube cube;
        private Obstacle obstacle;

        public GeometryDashGame() {
            setFocusable(true);
            addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        cube.jump();
                    }
                }
            });

            setBackground(Color.BLACK);
            timer = new Timer(TIME_TICK, this);
            timer.start();
            cube = new Cube();
            obstacle = new Obstacle();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            cube.draw(g);
            obstacle.draw(g);
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 20);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == timer) {
                updateGame();
                repaint();
            }
        }

        private void updateGame() {
            cube.move();
            obstacle.move();
            checkCollision();
            checkScore();
        }

        private void checkCollision() {
            if (cube.intersects(obstacle)) {
                score = 0;
                cube.reset();
            }
        }

        private void checkScore() {
            if (cube.passes(obstacle)) {
                score++;
            }
        }

        public static void main(String[] args) {
            JFrame frame = new JFrame();
            frame.add(new GeometryDashGame());
            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                jumpSpeed -= 1;
                y += jumpSpeed;
                if (y <= 500) {
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
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }

        public boolean intersects(Obstacle obstacle) {
            return y <= 0 || y + height >= obstacle.getY() || x >= obstacle.getX() + obstacle.getWidth() || x + width <= obstacle.getX();
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
            width = 100;
            height = 500;
            speed = -2;
        }

        public void move() {
            x += speed;
            if (x + width <= 0) {
                x = GeometryDashGame.WIDTH;
                y = (int) (Math.random() * (GeometryDashGame.HEIGHT - 200)) + 100;
            }
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
    }
}
