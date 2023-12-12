public class Avatar {
    double y; // Vertical position of the avatar
    double vy; // Vertical velocity of the avatar
    final double GRAVITY = 0.1;

    public void update() {
        y += vy; // Update position
        vy += GRAVITY; // Apply gravity
    }
}
