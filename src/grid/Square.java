package grid;

import java.io.Serializable;
import java.util.ArrayList;

import ships.Ship;

public class Square implements Serializable {

	// Serial version UID to ensure compatibility of serialised instances across
	// different versions of the class
	private static final long serialVersionUID = 1L;

	// ArrayList to store enemy ships on this square
	ArrayList<Ship> enemyShipsAtSquare = new ArrayList<Ship>();

	// Boolean flag to indicate presence of master ship on this square
	boolean masterShipAtSquare = false;

	/*
	 * This method adds an enemy ship to the ArrayList of enemyShips on this
	 * square.
	 */
	public void addEnemyShip(Ship enemyShip) {
		this.enemyShipsAtSquare.add(enemyShip);
	}

	/* This method returns an ArrayList of enemy ships on this square. */
	public ArrayList<Ship> getEnemyShipsAtSquare() {
		return enemyShipsAtSquare;
	}

	/*
	 * This method removes a specific ship from the ArrayList of enemy ships on this
	 * square.
	 */
	public void removeShip(Ship shipToRemove) {
		this.enemyShipsAtSquare.remove(shipToRemove);
	}

	/*
	 * This method removes all enemy ships from the ArrayList of enemy ships on this
	 * square.
	 */
	public void removeAllShip() {
		this.enemyShipsAtSquare.removeAll(enemyShipsAtSquare);
	}

	/*
	 * This method sets the boolean flag to indicate the presence of a master ship
	 * on this square.
	 */
	public void setMasterShipAtSquare() {
		this.masterShipAtSquare = true;
	}

	/*
	 * This method removes the boolean flag that indicates the presence of a master
	 * ship on this square.
	 */
	public void removeMasterShipAtSquare() {
		this.masterShipAtSquare = false;
	}

	/*
	 * This method returns the boolean flag that indicates the presence of a master
	 * ship on this square.
	 */
	public boolean isMasterShipAtSquare() {
		return masterShipAtSquare;
	}

}
