package grid;

import java.io.Serializable;
import java.util.ArrayList;

import ships.Ship;

public class Square implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
