/* Ball.java
    */
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {
    private double x, y; // Ball position
    private double dx, dy; // Ball velocity
    private int radius;

    public Ball(double x, double y, double dx, double dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = 10; // Adjust the size as needed
    }

    public void move() {
        x += dx;
        y += dy;
        // Implement collision detection with the game boundaries here.
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED); // Adjust the color as needed
        g2d.fill(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDiameter() {
        return 2 * radius;
    }

    public boolean intersects(Brick brick) {
        // Implement collision detection logic between the ball and a brick here.
        Rectangle ballRect = new Rectangle((int) x - radius, (int) y - radius, 2 * radius, 2 * radius);
        Rectangle brickRect = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        return ballRect.intersects(brickRect);
    }

    public boolean intersects(Paddle paddle) {
        // Implement collision detection logic between the ball and the paddle here.
        Rectangle ballRect = new Rectangle((int) x - radius, (int) y - radius, 2 * radius, 2 * radius);
        Rectangle paddleRect = new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
        return ballRect.intersects(paddleRect);
    }

    public void reverseYDirection() {
        dy = -dy; // Reverse the vertical direction of the ball.
    }

    public void reverseXDirection() {
        dx = -dx; // Reverse the horizontal direction of the ball.
    }
}
