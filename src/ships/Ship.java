package ships;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ship {
	private String type;
	private ImageIcon originalIcon;
	private Image shipImage;
	private Image scaledImage;
	private ImageIcon icon;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
	public void setIcon(ImageIcon origial) {
		this.originalIcon = origial;
		this.shipImage = this.originalIcon.getImage();
		this.scaledImage = shipImage.getScaledInstance(100, 100, shipImage.SCALE_SMOOTH);
		this.icon = new ImageIcon(scaledImage);
	}

	public ImageIcon getIcon() {
		return icon;
	}

}
