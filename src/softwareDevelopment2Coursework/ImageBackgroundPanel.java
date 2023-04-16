package softwareDevelopment2Coursework;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// This class represents a JPanel that has an image as its background.
public class ImageBackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    // The image to use as the background of this panel.
    private Image backgroundImage;

    // Constructs a new ImageBackgroundPanel with the specified image path.
    public ImageBackgroundPanel(String imagePath) {
        // Loads the image from the given path.
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    // Paints the image onto the panel.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draws the image to fill the entire panel.
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
