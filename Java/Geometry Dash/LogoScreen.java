package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class LogoScreen extends JPanel implements ActionListener {

    private boolean animationStarted;
    private int logoSize;
    private Image logoImage;

    public LogoScreen() {
        setPreferredSize(new Dimension(GeometryDash.WIDTH, GeometryDash.HEIGHT));
        setFocusable(false);
        setOpaque(false);

        // Load logo image using getResource to ensure it works in different environments
        URL imageUrl = getClass().getResource("/images/logo-game.png");
        System.out.println("Image URL: " + imageUrl);

        if (imageUrl != null) {
            try {
                logoImage = ImageIO.read(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading the logo image: " + e.getMessage(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Could not find the logo image.", "Image Loading Error", JOptionPane.ERROR_MESSAGE);
        }

        animationStarted = false;
        logoSize = 1;

        Timer logoTimer = new Timer(50, this);
        logoTimer.start();
    }

    public void startAnimation() {
        if (!animationStarted) {
            animationStarted = true;
            logoSize = 1;
            setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logoSize += 5;
        if (logoSize >= 200) {
            ((Timer) e.getSource()).stop();
            setVisible(false);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Debugging: draw a background to ensure the panel is visible
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        int x = (getWidth() - logoImage.getWidth(null)) / 2;
        int y = (getHeight() - logoImage.getHeight(null)) / 2;
        g2d.drawImage(logoImage, x, y, this);
        g2d.dispose();
    }
}
