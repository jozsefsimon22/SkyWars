package softwareDevelopment2Coursework;

import ships.ShipGenerator;

public class SkyWars {

	public static void main(String[] args) {
		
		ShipGenerator ship = new ShipGenerator();
		
		System.out.println(ship.newShip().getType());

	}
}