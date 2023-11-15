/*
 * Brick.java
 * This class represents the bricks in the game.
 */
import java.awt.*;

public class Brick {
    private int x, y; // These are the brick's position.
    private int width, height; // These are the dimensions of the brick.
    private boolean visible; // It tells us if the brick is visible or not.

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = true; // The brick is visible when created.
    }

    public int getX() {
        return x; // This gives the brick's horizontal position.
    }

    public int getY() {
        return y; // This gives the brick's vertical position.
    }

    public int getWidth() {
        return width; // This tells us how wide the brick is.
    }

    public int getHeight() {
        return height; // This tells us how tall the brick is.
    }

    public boolean isVisible() {
        return visible; // It tells us if the brick is still visible.
    }

    public void setInvisible() {
        visible = false; // When the ball hits a brick, it becomes invisible.
    }

    public void draw(Graphics g) {
        if (visible) {
            g.setColor(Color.GREEN); // You can change the brick's color here.
            g.fillRect(x, y, width, height); // We draw the brick on the screen if it's visible.
        }
    }
}
