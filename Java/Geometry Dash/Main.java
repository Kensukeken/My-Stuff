import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class SpikeJumpGame extends JPanel implements ActionListener {

    public static final int WIDTH = 737;
    public static final int HEIGHT = 545;
    public static final int GRAVITY = 1;
    public static final int TIME_TICK = 16;
    private Timer timer;
    private boolean jump;
    private boolean dead;
    private int score;
    private Cube cube;
    private List<Actor> spikes;
    private Actor hangingSpike;
    private Actor jumpOrb;
    private Actor jumpPad;
    private Image background;
    private ZRect floor;

    public SpikeJumpGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        setBackground(Color.BLACK);

        spikes = new ArrayList<>();

        hangingSpike = new Actor("hanging_spike.png", 505, 90);
        jumpOrb = new Actor("jump_orb.png", 550, 250);
        jumpPad = new Actor("jump_pad.png", 550, 335);

        background = new ImageIcon("background1.png").getImage();

        spikes.add(new Actor("spike.png", 550, 310));
        spikes.add(new Actor("spike.png", 650, 310));

        floor = new ZRect(0, 340, WIDTH, 3);
        cube = new Cube(floor);

        timer = new Timer(TIME_TICK, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        // Add mouse listener for mouse clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick();
            }
        });
    }

    private void handleMouseClick() {
        if (!dead) {
            cube.jump();
        }
    }

    private void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !dead) {
            cube.jump();
        } else if (e.getKeyCode() == KeyEvent.VK_R && dead) {
            resetGame();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        Image bufferImage = createImage(WIDTH, HEIGHT);
        Graphics bufferGraphics = bufferImage.getGraphics();

        bufferGraphics.drawImage(background, 0, 0, this);

        hangingSpike.draw(bufferGraphics);

        if (!dead) {
            drawGame(bufferGraphics);
        } else {
            drawGameOver(bufferGraphics);
        }

        g.drawImage(bufferImage, 0, 0, this);
    }

    private void drawGame(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(floor.getX(), floor.getY(), floor.getWidth(), floor.getHeight());

        for (Actor spike : spikes) {
            spike.draw(g);
        }

        cube.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 40, 40);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 40));
        g.drawString("Game Over!", 250, 200);
        g.drawString("Your Score: " + score, 225, 300);
    }

    public void actionPerformed(ActionEvent e) {
        if (!dead) {
            updateGame();
            repaint();
        }
    }

    private void updateGame() {
        if (jump) {
            cube.jump();
            jump = false;
        }

        cube.update();
        for (Actor spike : spikes) {
            updateSpike(spike);

            if (spike.getX() < cube.getRight() && spike.getRight() > cube.getX()
                    && spike.getY() < cube.getBottom() && spike.getBottom() > cube.getY()) {
                cube.jump();
            }
        }

        updateHangingSpike();
        updateJumpOrb();
        updateJumpPad();

        score++;
    }

    private void updateSpike(Actor spike) {
        spike.update();
        if (spike.collidesWith(cube)) {
            dead = true;
        }
        if (spike.getX() + spike.getWidth() <= 0) {
            spike.setX(WIDTH + 5);
        }
    }

    private void updateHangingSpike() {
        hangingSpike.setX(hangingSpike.getX() - hangingSpike.getXSpeed());
        if (hangingSpike.collidesWith(cube)) {
            dead = true;
        }
        if (hangingSpike.getRight() <= 0) {
            resetHangingSpike();
        }
    }

    private void updateJumpOrb() {
        jumpOrb.setX(jumpOrb.getX() - jumpOrb.getXSpeed());
        if (jumpOrb.collidesWith(cube)) {
            dead = true;
        }
        if (jumpOrb.getRight() <= 0) {
            resetJumpOrb();
        }
    }

    private void updateJumpPad() {
        jumpPad.setX(jumpPad.getX() - jumpPad.getXSpeed());
        if (jumpPad.collidesWith(cube)) {
            dead = true;
        }
        if (jumpPad.getRight() <= 0) {
            resetJumpPad();
        }
    }

    private void resetHangingSpike() {
        hangingSpike.setX(WIDTH + 5);
        hangingSpike.setXSpeed(new java.util.Random().nextInt(5) + 3);
    }

    private void resetJumpOrb() {
        jumpOrb.setX(WIDTH + 5);
        jumpOrb.setXSpeed(new java.util.Random().nextInt(5) + 3);
    }

    private void resetJumpPad() {
        jumpPad.setX(WIDTH + 5);
        jumpPad.setXSpeed(new java.util.Random().nextInt(5) + 3);
    }

    private void resetGame() {
        cube.reset();
        for (Actor spike : spikes) {
            spike.setX(WIDTH + 5);
        }
        resetHangingSpike();
        resetJumpOrb();
        resetJumpPad();
        score = 0;
        dead = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Geometry Dash");
            SpikeJumpGame game = new SpikeJumpGame();
            frame.add(game);
            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    game.handleKeyPress(e);
                }
            });
        });
    }
}


class Cube {
    private int x, y;
    private int width, height;
    private int ySpeed;
    private int angle;
    private Image cubeImage;
    private ZRect floor;
    private boolean isJumping;

    public Cube(ZRect floor) {
        this.floor = floor;
        width = 50;
        height = 50;
        x = 50;
        y = 340 - height;
        ySpeed = 0;
        angle = 0;
        isJumping = true;

        cubeImage = new ImageIcon("square.jpg").getImage();
    }

    public void jump() {
        if (!isJumping) {
            if (y + height >= 340) {
                ySpeed = -15;
                isJumping = true;
            }
        }
    }

    public void reset() {
        x = 50;
        y = 340 - height;
        ySpeed = 0;
        angle = 0;
        isJumping = false;
    }

    public void update() {
        if (y + height < floor.getY()) {
            ySpeed += SpikeJumpGame.GRAVITY;
            y += ySpeed;
        } else {
            y = -height + floor.getY();
            ySpeed = 0;
            isJumping = false;
        }

        if (ySpeed > 0) {
            angle += 2;
        }

        if (angle <= 90 && angle > 0) {
            angle = 0;
        } else if (angle > 90 && angle <= 180) {
            angle = 90;
        } else if (angle > 180 && angle <= 270) {
            angle = 180;
        } else if (angle > 270 && angle <= 360) {
            angle = 270;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldTransform = g2d.getTransform();

        g2d.translate(x + width / 2, y + height / 2);
        g2d.rotate(Math.toRadians(angle));
        g2d.drawImage(cubeImage, -width / 2, -height / 2, width, height, null);

        g2d.setTransform(oldTransform);
    }

    public boolean collidesWith(Actor actor) {
        return x < actor.getRight() &&
                x + width > actor.getX() &&
                y < actor.getBottom() &&
                y + height > actor.getY();
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

    public int getRight() {
        return x + width;
    }

    public int getBottom() {
        return y + height;
    }
}

class Actor {
    private int x, y;
    private int width, height;
    private int xSpeed;
    private Image image; // New field for the image

    public Actor(String imageName, int x, int y) {
        this.x = x;
        this.y = y;
        this.xSpeed = 3;

        // Load the image using ImageIcon
        image = new ImageIcon(imageName).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void update() {
        x -= xSpeed;
    }

    public void draw(Graphics g) {
        // Draw the actor using the loaded image
        g.drawImage(image, x, y, width, height, null);
    }

    public boolean collidesWith(Cube cube) {
        return x < cube.getRight() &&
                x + width > cube.getX() &&
                y < cube.getBottom() &&
                y + height > cube.getY();
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

    public int getRight() {
        return x + width;
    }

    public int getBottom() {
        return y + height;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
}

class ZRect {
    private int x, y;
    private int width, height;

    public ZRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
