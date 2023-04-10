package ships;

import javax.swing.ImageIcon;

public class MasterShip extends Ship {
	
	ImageIcon originalIcon = new ImageIcon("master.png");
	
	public MasterShip() {
		setType("Master");
		setIcon(originalIcon);
	}
}
