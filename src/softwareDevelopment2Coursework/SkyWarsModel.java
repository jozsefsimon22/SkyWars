// SkyWarsModel.java
package softwareDevelopment2Coursework;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import grid.Grid;
import ships.Ship;
import ships.ShipGenerator;
import ships.Square;

public class SkyWarsModel {
	private Grid gameGrid;
	private int prevRow = -1;
	private int prevCol = -1;
	private ShipGenerator enemyShip = new ShipGenerator();
	private Random random = new Random();

	public SkyWarsModel(Grid gameGrid) {
		this.gameGrid = gameGrid;
	}

	public Grid getGameGrid() {
		return gameGrid;
	}

	public void allocateMasterShipRandomly() {
		int row = random.nextInt(gameGrid.getCol());
		int col = random.nextInt(gameGrid.getRow());

		gameGrid.getSquare(row, col).setMasterShipAtSquare();
		prevRow = row;
		prevCol = col;
	}

	public void moveMasterShip(int newRow, int newCol) {
		grid.Square currentSquare = gameGrid.getSquare(prevRow, prevCol);
		if (currentSquare != null) {
			currentSquare.removeMasterShipAtSquare();
		}
		gameGrid.getSquare(newRow, newCol).setMasterShipAtSquare();
		prevRow = newRow;
		prevCol = newCol;
	}

	// Check if the user is trying move the Master-ship to a valid location
	public boolean isValidMove(int newRow, int newCol) {
		int rowDiff = Math.abs(newRow - prevRow);
		int colDiff = Math.abs(newCol - prevCol);

		return (rowDiff <= 1 && colDiff <= 1) && !(rowDiff == 0 && colDiff == 0);
	}

	public void notValidMove() {
		JOptionPane.showMessageDialog(null, "You can only move to neighbouring squares.", null,
				JOptionPane.WARNING_MESSAGE);
	}

	public int getPrevRow() {
		return prevRow;
	}

	public int getPrevCol() {
		return prevCol;
	}

	public void newGame() {
		Grid sky = new Grid();
		SkyWarsModel model = new SkyWarsModel(sky);
		SkyWarsMainGUI view = new SkyWarsMainGUI();
		SkyWarsController controller = new SkyWarsController(model, view);
		controller.initializeMasterShip();
		view.setVisible(true);
	}

	// Generate a new enemy ship with a 1 in a 3 chance
	public void newEnemyShip() {
		int newShip = random.nextInt(3);

		if (newShip == 0) {
			this.gameGrid.getSquare(0, 0).addEnemyShip(enemyShip.newShip());
		}
	}

	// Move enemy ships to a new location
	public void enemyShipMove() {
	    Grid newGrid = new Grid();
	    int tempRow;
	    int tempCol;
	    for (int row = 0; row < gameGrid.getRow(); row++) {
	        for (int col = 0; col < gameGrid.getCol(); col++) {
	            ArrayList<Ship> enemyShips = getGameGrid().getSquare(row, col).getEnemyShipsAtSquare();
	            if (!enemyShips.isEmpty()) {
	                // Define the bounds for the random movement of the ships
	                int minRow = Math.max(row - 1, 0); // Ensure the minimum row stays within the grid
	                int maxRow = Math.min(row + 1, gameGrid.getRow() - 1); // Ensure the maximum row stays within the grid
	                int minCol = Math.max(col - 1, 0); // Ensure the minimum column stays within the grid
	                int maxCol = Math.min(col + 1, gameGrid.getCol() - 1); // Ensure the maximum column stays within the grid

	                for (Ship ship : enemyShips) {
	                    tempRow = random.nextInt(maxRow - minRow + 1) + minRow; // Randomly generate a row between minRow and maxRow
	                    tempCol = random.nextInt(maxCol - minCol + 1) + minCol; // Randomly generate a column between minCol and maxCol
	                    newGrid.getSquare(tempRow, tempCol).addEnemyShip(ship);
	                }
	            }
	        }
	    }
	    this.gameGrid = newGrid;
	}

}// end class
