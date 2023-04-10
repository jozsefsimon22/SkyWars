package ships;

import javax.swing.ImageIcon;

public class BattleStar extends Ship {
	
	ImageIcon originalIcon = new ImageIcon("ship_one.png");
	
	public BattleStar() {
		setType("Battle Star");
		setIcon(originalIcon);
	}
}
