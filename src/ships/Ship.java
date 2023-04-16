package ships;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Ship implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    // Marked as transient to exclude them from default serialisation, as ImageIcon and Image are not serialisable
    private transient ImageIcon originalIcon;
    private transient Image shipImage;
    private transient Image scaledImage;
    private transient ImageIcon icon;

    // Getters and setters for type and icon fields
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIcon(ImageIcon original) {
        this.originalIcon = original;
        this.shipImage = this.originalIcon.getImage();
        this.scaledImage = shipImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        this.icon = new ImageIcon(scaledImage);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    // Custom serialisation method to handle the serialisation of the ImageIcon field
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // Serialise all non-transient fields using default serialisation
        if (originalIcon != null) {
            Image image = originalIcon.getImage();
            // Convert the Image object to a BufferedImage, which can be serialised using ImageIO
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            ImageIO.write(bufferedImage, "png", oos); // Serialise the BufferedImage as a PNG format
        }
    }

    // Custom deserialisation method to handle the deserialisation of the ImageIcon field
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // Deserialise all non-transient fields using default deserialisation
        BufferedImage bufferedImage = ImageIO.read(ois); // Deserialise the BufferedImage from the input stream
        if (bufferedImage != null) {
            originalIcon = new ImageIcon(bufferedImage);
            setIcon(originalIcon); // Reconstruct the icon field from the deserialised BufferedImage
        }
    }
}
