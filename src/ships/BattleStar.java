package ships;

import javax.swing.ImageIcon;

public class BattleStar extends Ship {
	
	private static final long serialVersionUID = 1L;
	ImageIcon originalIcon = new ImageIcon("ship_one.png");
	
	public BattleStar() {
		setType("Battle Star");
		setIcon(originalIcon);
	}
}
