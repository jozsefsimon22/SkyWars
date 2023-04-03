package softwareDevelopment2Coursework;

import ships.*;
import grid.*;
import grid.Square;

public class SkyWars {

	public static void main(String[] args) {
		
		ShipGenerator ship = new ShipGenerator();
		
		Ship s1 = ship.newShip();
		Ship s2 = ship.newShip();
		Ship s3 = ship.newShip();
		
		Grid map = new Grid();
		
		SkyWarsMainGUI gameWindow = new SkyWarsMainGUI(map);
		gameWindow.setVisible(true);
		
		
		Square target = map.getMap()[0][0];
		
		

		target.addEnemyShip(s1);
		target.addEnemyShip(s2);
		target.addEnemyShip(s3);
		
		System.out.println(target.getEnemyShipsAtSquare().get(0).getType());
		System.out.println(target.getEnemyShipsAtSquare().get(1).getType());
		System.out.println(target.getEnemyShipsAtSquare().get(2).getType());
		System.out.println(target.getEnemyShipsAtSquare().remove(s1));
		System.out.println(target.getEnemyShipsAtSquare().get(0).getType());
		System.out.println(target.getEnemyShipsAtSquare().get(1).getType());
//		System.out.println(target.getEnemyShipsAtSquare().get(2).getType());
		
	}
}