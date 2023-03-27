package ships;

import java.util.Random;

public class ShipGenerator {

	Random randomGenerator = new Random();
	int random;
	
	public Ship newShip() {
		Ship ship;
		
		random = randomGenerator.nextInt(3);
		
		if(random == 0) {
			ship = new BattleCruiser();
		}
		else if (random == 1) {
			ship = new BattleStar();
		}
		else {
			ship = new BattleShooter();
		}
		
		return ship;
	}
	
	
}
