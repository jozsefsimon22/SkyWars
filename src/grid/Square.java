package grid;

import java.util.ArrayList;

import ships.Ship;

public class Square {
	
	ArrayList<Ship> enemyShipsAtSquare = new ArrayList<Ship>();
	
	public void addEnemyShip(Ship enemyShip) {
		this.enemyShipsAtSquare.add(enemyShip);
	}
	
	public ArrayList<Ship> getEnemyShipsAtSquare() {
		return enemyShipsAtSquare;
	}
	
	public void removeShip(Ship shipToRemove) {
		this.enemyShipsAtSquare.remove(shipToRemove);
	}
	
	public void removeAllShip() {
		this.enemyShipsAtSquare.removeAll(enemyShipsAtSquare);
	}
	
}
