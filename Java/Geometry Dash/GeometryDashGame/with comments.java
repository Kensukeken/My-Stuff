// Hey Ed, let's take a chill look at the Geometry Dash game code together. It's like telling a story.

// So, imagine you're in a class named GeometryDashGame - it's like the whole game universe.
public class GeometryDashGame extends JPanel implements ActionListener {

    // We have some magic numbers here, WIDTH and HEIGHT, defining the size of our game window.
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int TIME_TICK = 10;

    // Now, our game needs a timer, a score tracker, and a flag to know if the game's over.
    private Timer timer;
    private int score;
    private boolean gameOver;
    
    // Meet the characters of our game: the player's cube, a moving obstacle, and a "play again" button.
    private Cube cube;
    private Obstacle obstacle;
    private JButton playAgainButton;

    // This part is like setting up the game environment.
    public GeometryDashGame() {
        // Get ready to listen to keyboard events.
        setFocusable(true);
        // When keys are pressed, we'll figure out what to do.
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // If the game is over, 'Space' or 'R' starts a new game.
                if (e.getKeyCode() == KeyEvent.VK_SPACE && gameOver) {
                    resetGame();
                } else if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
                    resetGame();
                } else if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
                    cube.jump();
                }
            }
        });

        // The game background is set to a calming blue.
        setBackground(Color.blue);

        // Our game needs a heartbeat, and that's what the timer is for.
        timer = new Timer(TIME_TICK, this);
        timer.start();
        // Our main character, the cube, and an unpredictable obstacle are introduced.
        cube = new Cube();
        obstacle = new Obstacle();

        // Let's add a button for a second chance - the "play again" button.
        playAgainButton = new JButton("Play again");
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        // At first, the button is hiding; it's shy.
        playAgainButton.setVisible(false);

        // The button is centered on the screen using some magical layout stuff.
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(playAgainButton, gbc);

        // The button gets a size adjustment.
        playAgainButton.setPreferredSize(new Dimension(200, 100));

        // We want the button to listen to keyboard events too.
        playAgainButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // If 'R' is pressed and the game's over, let's restart.
                if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
                    resetGame();
                }
            }
        });
        // Now, the button is ready to get key events.
        playAgainButton.setFocusable(true);
    }

    // Painting time! Let's draw everything on the screen.
    public void paint(Graphics g) {
        super.paint(g);
        // The cube, obstacle, and the score are painted here.
        cube.draw(g);
        obstacle.draw(g);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);

        // If the game's over, our shy button shows up.
        if (gameOver) {
            playAgainButton.setVisible(true);
        }
    }

    // When the timer ticks, this part decides what happens next in our game.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            updateGame();
            repaint();
        }
    }

    // The game logic gets updated here during each tick.
    private void updateGame() {
        if (!gameOver) {
            // Our characters move, and we check for collisions and scores.
            cube.move();
            obstacle.move();
            checkCollision();
            checkScore();
        }
    }

    // Checking if the cube meets an obstacle.
    private void checkCollision() {
        if (cube.intersects(obstacle)) {
            score = 0;
            cube.reset();
            gameOver = true;
        }
    }

    // Checking if the cube successfully passes an obstacle.
    private void checkScore() {
        if (cube.passes(obstacle)) {
            score++;
        }
    }

    // Time for a fresh start! Resetting the game state.
    private void resetGame() {
        cube.reset();
        obstacle.reset();
        score = 0;
        gameOver = false;
        // Our shy button goes back into hiding.
        playAgainButton.setVisible(false);
    }

    // Here's where our game adventure begins.
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        // Our game is now a part of a window named 'frame'.
        GeometryDashGame game = new GeometryDashGame();
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The magical 'R' key can restart the game if it's over.
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R && game.gameOver) {
                    game.resetGame();
                }
            }
        });
    }
}

// Now, let's meet the characters of our game.

// The player's cube has some cool moves and expressions.
class Cube {
    // Cube's details: position, size, and jumping features.
    private int x, y;
    private int width, height;
    private boolean isJumping;
    private int jumpSpeed;

    // Our cube's journey begins with a starting position and size.
    public Cube() {
        x = 50;
        y = 500;
        width = 50;
        height = 50;
        isJumping = false;
        jumpSpeed = 0;
    }

    // The cube's moves are like dance steps, including some impressive jumps.
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

    // The cube shows off a jump move, but only if it's not already jumping.
    public void jump() {
        if (!isJumping) {
            isJumping = true;
            jumpSpeed = 20;
        }
    }

    // Our cube can reset itself to its starting cool state.
    public void reset() {
        isJumping = false;
        jumpSpeed = 0;
        y = 500;
    }

    // Time to draw our cube with its vivid colors and expressions.
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);

        g.setColor(Color.CYAN);
        g.fillRect(x + 10, y + 10, 10, 10); // Left eye
        g.fillRect(x + 30, y + 10, 10, 10); // Right eye

        g.setColor(Color.CYAN);
        g.fillRect(x + 10, y + 30, 30, 10); // Mouth
    }

    // Let's check if our cube's path collides with an obstacle.
    public boolean intersects(Obstacle obstacle) {
        int obstacleTop = obstacle.getY();
        int obstacleBottom = obstacle.getY() + obstacle.getHeight();
        int obstacleLeft = obstacle.getX();
        int obstacleRight = obstacle.getX() + obstacle.getWidth();

        int cubeRight = x + width;
        int cubeBottom = y + height;

        return !(cubeRight <= obstacleLeft || x >= obstacleRight || cubeBottom <= obstacleTop || y >= obstacleBottom);
    }

    // And, does the cube successfully pass an obstacle? Let's find out.
    public boolean passes(Obstacle obstacle) {
        return x + width <= obstacle.getX();
    }
}

// The moving obstacle adds excitement to our game.
class Obstacle {
    // Obstacle's details: position, size, and speed.
    private int x, y;
    private int width, height;
    private int speed;

    // The obstacle appears randomly, creating suspense for the player.
    public Obstacle() {
        x = GeometryDashGame.WIDTH;
        y = (int) (Math.random() * (GeometryDashGame.HEIGHT - 200)) + 100;
        width = 50;
        height = 50 + (int) (Math.random() * 300);
        speed = -2;
    }

    // The obstacle moves across the screen, keeping things dynamic.
    public void move() {
        x += speed;
        if (x + width <= 0) {
            // Once it's off-screen, the obstacle reappears in a new mysterious location.
            x = GeometryDashGame.WIDTH;
            y = (int) (Math.random() * (GeometryDashGame.HEIGHT - 200)) + 100;
            height = 50 + (int) (Math.random() * 300);
        }
    }

    // The obstacle can also reset itself to create variety in the game.
    public void reset() {
        x = GeometryDashGame.WIDTH;
        y = (int) (Math.random() * (GeometryDashGame.HEIGHT - 200)) + 100;
        width = 50;
        height = 50 + (int) (Math.random() * 300);
    }

    // Drawing time! Let's showcase our mysterious obstacle.
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    // Some details about the obstacle's position and size are revealed.
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
