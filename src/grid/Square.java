package grid;

import java.util.ArrayList;

import ships.Ship;

public class Square {
	
	ArrayList<Ship> enemyShipsAtSquare = new ArrayList<Ship>();
	boolean masterShipAtSquare = false;
	
	
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
	
	public void setMasterShipAtSquare() {
		this.masterShipAtSquare = true;
	}
	
	public void removeMasterShipAtSquare() {
		this.masterShipAtSquare = false;
	}

	public boolean isMasterShipAtSquare() {
		return masterShipAtSquare;
	}
	
	
}
