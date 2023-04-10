package ships;

import javax.swing.ImageIcon;

public class BattleShooter extends Ship{
	
	ImageIcon originalIcon = new ImageIcon("ship_two.png");
	
	public BattleShooter() {
		setType("Battle Shooter");
		setIcon(originalIcon);
	}

}
