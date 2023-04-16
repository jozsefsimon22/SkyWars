package ships;

import javax.swing.ImageIcon;

public class MasterShip extends Ship {
	
	private static final long serialVersionUID = 1L;
	ImageIcon originalIcon = new ImageIcon("master.png");
	
	public MasterShip() {
		setType("Master");
		setIcon(originalIcon);
	}
}
