package ships;

import java.io.Serializable;
import java.util.Random;

public class ShipGenerator implements Serializable{
	private static final long serialVersionUID = 1L;

	// Create a new Random object for generating random numbers
	Random randomGenerator = new Random();

	// Declare an integer to hold a randomly generated value
	int random;
	
	// Create a method to generate a new Ship object
	public Ship newShip() {
		// Declare a variable to hold the new Ship object
		Ship ship;
		
		// Generate a random integer between 0 and 2
		random = randomGenerator.nextInt(3);
		
		// Choose a new Ship object based on the random integer
		if(random == 0) {
			ship = new BattleCruiser(); // If the integer is 0, create a new BattleCruiser object
		}
		else if (random == 1) {
			ship = new BattleStar(); // If the integer is 1, create a new BattleStar object
		}
		else {
			ship = new BattleShooter(); // If the integer is 2, create a new BattleShooter object
		}
		
		// Return the new Ship object
		return ship;
	}
}
