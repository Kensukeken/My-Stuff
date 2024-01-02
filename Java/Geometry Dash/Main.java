package src;
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

public class GeometryDash extends JPanel implements ActionListener {

    // Constants for defining the dimensions and behavior of the game
    public static final int WIDTH = 737;
    public static final int HEIGHT = 545;
    public static final int GRAVITY = 1;
    public static final int JUMP_HEIGHT = 16;
    public static final int TIME_TICK = 10;
    public static final int LOGO_SCREEN_DURATION = 5000; // 5 seconds

    // Variables to control game flow and track player performance
    private Timer timer;
    private int attempt; // Keep track of attempts
    private boolean dead; // Indicate if the player is alive or dead
    private int score; // Store the player's score

    // Objects representing game entities
    private Cube cube; // The player's cube
    private List<Actor> spikes; // List to store spike objects

    // Images for the game background, logo, and a scaled version of the logo
    private Image background;
    private ZRect floor;
    private Image logo;
    private boolean showLogo = true; // Control whether to display the logo

    // Timers to manage the logo screen and delay before starting the game
    private Timer logoTimer;
    private Timer startGameTimer;

    private Image scaledLogo; // Scaled version of the logo image

    // Constructor for the GeometryDash class
    public GeometryDash() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Set the preferred size of the game panel
        setFocusable(true); // Allow the panel to receive focus

        setBackground(Color.blue); // Set the background color to blue

        spikes = new ArrayList<>(); // Initialize the list to store spike objects

        background = new ImageIcon("images/background.png").getImage(); // Load the background image
        logo = new ImageIcon("images/logo-game.png").getImage(); // Load the game logo image
        scaleLogo(); // Scale the logo image

        spikes.add(new Actor("images/spike.png", 550, 310)); // Create and add spike objects to the list
        spikes.add(new Actor("images/spike.png", 700, 310));

        floor = new ZRect(0, 340, WIDTH, 3); // Create a rectangle object to represent the floor
        cube = new Cube(floor); // Create a cube object for the player

        timer = new Timer(TIME_TICK, this); // Create a timer for the game

        // Set up a timer for the logo screen
        logoTimer = new Timer(LOGO_SCREEN_DURATION, e -> {
            showLogo = false; // Hide the logo after the logo screen duration
            repaint();

            // Start a timer for the delay before starting the game
            startGameTimer = new Timer(1000, startGameActionListener);
            startGameTimer.setRepeats(false);
            startGameTimer.start();
        });
        logoTimer.setRepeats(false);
        logoTimer.start();

        // Add a key listener to handle key presses
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                KeyPressed(e); // Call the method to handle key presses
            }
        });

        // Add a mouse listener to handle mouse clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MouseClick(); // Call the method to handle mouse clicks
            }
        });
    }

    // Scale the logo image to the desired dimensions
    private void scaleLogo() {
        int scaledWidth = 700;  // Set the desired width
        int scaledHeight = 300; // Set the desired height
        scaledLogo = logo.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
    }

    // Handle mouse clicks
    private void MouseClick() {
        cube.jump(); // Call the jump method when a mouse click is detected
    }

    // Handle key presses
    private void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !dead) {
            cube.jump(); // Call the jump method when the spacebar is pressed, and the player is alive
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            resetGame(); // Call the method to reset the game when the 'R' key is pressed
        }
    }

    // Override the paint method to paint the game components
    public void paint(Graphics g) {
        super.paint(g);

        if (showLogo) {
            drawLogoScreen(g); // Draw the logo screen if showLogo is true
        } else {
            Image bufferImage = createImage(WIDTH, HEIGHT);
            Graphics bufferGraphics = bufferImage.getGraphics();

            bufferGraphics.drawImage(background, 0, 0, this);

            if (!dead) {
                drawGame(bufferGraphics); // Draw the game components if the player is alive
            } else {
                drawGameOver(bufferGraphics); // Draw the game over screen if the player is dead
            }
            g.drawImage(bufferImage, 0, 0, this);
        }
    }

    // Draw the logo screen
    private void drawLogoScreen(Graphics g) {
        int x = (getWidth() - scaledLogo.getWidth(this)) / 2; // Calculate the x-coordinate to center the logo horizontally
        int y = (getHeight() - scaledLogo.getHeight(this)) / 2; // Calculate the y-coordinate to center the logo vertically
        g.drawImage(scaledLogo, x, y, this); // Draw the scaled logo at the calculated coordinates
    }

    // Draw the game components
    private void drawGame(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(floor.getX(), floor.getY(), floor.getWidth(), floor.getHeight());

        for (Actor spike : spikes) {
            spike.draw(g);
        }

        cube.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 40, 40);
        g.drawString("Attempt: " + attempt, 600, 40);
    }

    // Draw the game over screen
    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 40));
        g.drawString("Game Over!", 250, 200);
        g.drawString("Your Score: " + score, 225, 300);
    }

    // Handle actions performed during the game
    public void actionPerformed(ActionEvent e) {
        if (!dead) {
            updateGame();
            repaint();
        }
    }

    // Update the game state
    private void updateGame() {
        cube.update();
        for (Actor spike : spikes) {
            Spike(spike);

            if (spike.getX() < cube.getRight() && spike.getRight() > cube.getX()
                    && spike.getY() < cube.getBottom() && spike.getBottom() > cube.getY()) {
                // Check for collisions between the cube and spikes
            }
        }
        score++; // Increment the score with each game tick
    }

    // Update the state of a spike object
    private void Spike(Actor spike) {
        spike.update();
        if (spike.collidesWith(cube)) {
            dead = true; // If a collision occurs, set the player as dead
        }
        if (spike.getX() + spike.getWidth() <= 0) {
            spike.setX(WIDTH + 5); // Reset the spike position when it goes off-screen
        }
    }

    // Reset the game state for a new attempt
    private void resetGame() {
        for (Actor spike : spikes) {
            spike.setX(WIDTH + new java.util.Random().nextInt(200)); // Randomize spike positions for a new attempt
        }
        score = 0; // Reset the score
        attempt++; // Increment the attempt count
        dead = false; // Revive the player
    }

    // ActionListener for starting the game after the logo screen
    private ActionListener startGameActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            startGame(); // Start the game after the delay
        }
    };

    // Start the main game timer
    private void startGame() {
        timer.start();
    }

    // Main method to launch the game
    public static void main(String[] args) {
        JFrame frame = new JFrame("Geometry Dash");
        GeometryDash game = new GeometryDash();
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// Class representing the player's cube
class Cube {
    private static final int ROTATION_LIMIT = 90;

    private int x, y;
    private int width, height;
    private int ySpeed;
    private int jumpSpeed = GeometryDash.JUMP_HEIGHT;

    private int angle;
    private Image cubeImage;
    private ZRect floor;

    private boolean isRotating;

    public Cube(ZRect floor) {
        this.floor = floor;
        width = 50;
        height = 50;
        x = 50;
        y = 340 - height;
        ySpeed = 0;
        angle = 0;

        cubeImage = new ImageIcon("images/square.jpg").getImage();
        isRotating = false;
    }

    // Handle jumping action
    public void jump() {
        if (y + height >= floor.getY()) {
            ySpeed = -16;
            isRotating = true; // Start rotating when jumping
        }
        y += ySpeed;
    }

    // Update the cube's position and rotation
    public void update() {
        if (y + height < floor.getY()) {
            ySpeed += GeometryDash.GRAVITY;
            y += ySpeed;
        } else {
            y = -height + floor.getY();
            ySpeed = 0;
            if (isRotating) {
                angle += 90; // Rotate while on the way up after jumping
                if (angle >= ROTATION_LIMIT) {
                    isRotating = false; // Stop rotating when reaching the limit
                }
            }
        }
    }

    // Draw the cube on the screen
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldTransform = g2d.getTransform();

        g2d.translate(x + width / 2, y + height / 2);
        g2d.rotate(Math.toRadians(angle));
        g2d.drawImage(cubeImage, -width / 2, -height / 2, width, height, null);

        g2d.setTransform(oldTransform);
    }

    // Getter methods for cube dimensions and position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRight() {
        return x + width;
    }

    public int getBottom() {
        return y + height;
    }

    // Reset rotation to its initial state
    public void resetRotation() {
        isRotating = false;
        angle = 0;
    }
}

// Class representing game elements such as spikes
class Actor {
    private int x, y;
    private int width, height;
    private int xSpeed;
    private Image image;

    // Constructor to initialize Actor properties
    public Actor(String imageName, int x, int y) {
        this.x = x;
        this.y = y;
        this.xSpeed = 3;

        image = new ImageIcon(imageName).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    // Update the Actor's position
    public void update() {
        x -= xSpeed;
    }

    // Draw the Actor on the screen
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    // Check for collision with the cube
    public boolean collidesWith(Cube cube) {
        return x < cube.getRight() &&
                x + width > cube.getX() &&
                y < cube.getBottom() &&
                y + height > cube.getY();
    }

    // Getter methods for Actor dimensions and position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getRight() {
        return x + width;
    }

    public int getBottom() {
        return y + height;
    }

    // Setter method to update the Actor's position
    public void setX(int x) {
        this.x = x;
    }
}

// Class representing a rectangle for game elements
class ZRect {
    private int x, y;
    private int width, height;

    // Constructor to initialize ZRect properties
    public ZRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getter methods for ZRect dimensions and position
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
