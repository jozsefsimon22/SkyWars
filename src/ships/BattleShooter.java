package ships;

import javax.swing.ImageIcon;

public class BattleShooter extends Ship{
	
	private static final long serialVersionUID = 1L;
	ImageIcon originalIcon = new ImageIcon("ship_two.png");
	
	public BattleShooter() {
		setType("Battle Shooter");
		setIcon(originalIcon);
	}

}
