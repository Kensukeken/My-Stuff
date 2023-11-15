/* Paddle.java
* A class representing the game paddle.
*/
import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {
    private int x, y; // These are the paddle's coordinates.
    private int width, height; // These represent the size of the paddle.
    private int dx; // This variable is used for moving the paddle smoothly.
    private int speed; // This is how fast the paddle moves.
    private boolean leftPressed, rightPressed; // These tell us if the left or right keys are pressed.

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 100; // Adjust the width as needed (how wide the paddle is).
        this.height = 10; // Adjust the height as needed (how tall the paddle is).
        this.dx = 5;
        this.speed = 5; // Set the speed (how fast the paddle moves).
        this.leftPressed = false;
        this.rightPressed = false;
    }

    public int getX() {
        return x; // This gives the paddle's horizontal position.
    }

    public int getY() {
        return y; // This gives the paddle's vertical position.
    }

    public int getWidth() {
        return width; // This tells us how wide the paddle is.
    }

    public int getHeight() {
        return height; // This tells us how tall the paddle is.
    }

    public void move() {
        if (leftPressed && x > 0) {
            x -= speed; // Move left when the left key is pressed (if not at the edge).
        }
        if (rightPressed && x + width < ArkanoidGame.WIDTH) {
            x += speed; // Move right when the right key is pressed (if not at the edge).
        }
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed; // Set leftPressed to true when left key is pressed.
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed; // Set rightPressed to true when right key is pressed.
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true; // When left key is pressed, set leftPressed to true.
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true; // When right key is pressed, set rightPressed to true.
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false; // When left key is released, set leftPressed to false.
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false; // When right key is released, set rightPressed to false.
        }
    }

    public void keyTyped(KeyEvent e) {
        // We don't do anything when a key is typed.
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY); // You can change the paddle's color here.
        g.fillRect(x, y, width, height); // Draw the paddle on the screen.
    }
}
