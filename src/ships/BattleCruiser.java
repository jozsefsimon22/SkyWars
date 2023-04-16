package ships;

import javax.swing.ImageIcon;

public class BattleCruiser extends Ship{
	private static final long serialVersionUID = 1L;

	public BattleCruiser() {
		
		ImageIcon originalIcon = new ImageIcon("ship_three.png");
		
		setType("Battle Cruiser");
		setIcon(originalIcon);
	}
}
