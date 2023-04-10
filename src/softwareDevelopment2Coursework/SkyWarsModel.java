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
	private int score;

	public SkyWarsModel(Grid gameGrid) {
		this.gameGrid = gameGrid;
	}

	public Grid getGameGrid() {
		return gameGrid;
	}
	
	public void setGameGrid(Grid gameGrid) {
		this.gameGrid = gameGrid;
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

	public void resetGame() {
		Grid sky = new Grid();
		setGameGrid(sky);
		score = 0;
	}

	// Generate a new enemy ship with a 1 in a 3 chance
	public void newEnemyShip() {
		int newShip = random.nextInt(3);

		if (newShip == 0) {
			this.gameGrid.getSquare(0, 0).addEnemyShip(enemyShip.newShip());
		}
	}

// Checks for a conflict at a specified square and removes all enemy ships at that square if the number of ships is less than the value defined for the mode,
// or ends the game if the number of enemy ships is greater than or equal to the corresponding
// defensive/offensive value in the Mode enum.
	public boolean checkConflict(int row, int col, Mode mode) {
	    boolean isGameOver = false;
	    grid.Square tempSquare = gameGrid.getSquare(row, col);
	    if (tempSquare.getEnemyShipsAtSquare().size() > 0) {
	        if (mode == Mode.DEFENSIVE) {
	            if (tempSquare.getEnemyShipsAtSquare().size() >= Mode.DEFENSIVE.getValue()) {
	                isGameOver = true;
	            } else {
	                tempSquare.removeAllShip();
	                score++;
	            }
	        } else if (mode == Mode.OFFENSIVE) {
	            if (tempSquare.getEnemyShipsAtSquare().size() >= Mode.OFFENSIVE.getValue()) {
	                isGameOver = true;
	            } else {
	                tempSquare.removeAllShip();
	                score++;
	            }
	        }
	    }
	    return isGameOver;
	}

	
	public int gameOver() {
	    String output;

	    output = "Game Over\n";
	    output += "Score: " + score;

	    Object[] options = {"Return to Main Menu", "Exit"};
	    int choice = JOptionPane.showOptionDialog(null, output, "Game Over",
	            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
	            null, options, options[0]);

	    return choice;
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
					int maxRow = Math.min(row + 1, gameGrid.getRow() - 1); // Ensure the maximum row stays within the
																			// grid
					int minCol = Math.max(col - 1, 0); // Ensure the minimum column stays within the grid
					int maxCol = Math.min(col + 1, gameGrid.getCol() - 1); // Ensure the maximum column stays within the
																			// grid

					for (Ship ship : enemyShips) {
						tempRow = random.nextInt(minRow, maxRow + 1); // Randomly generate a row between minRow and
																		// maxRow
						tempCol = random.nextInt(minCol, maxCol + 1); // Randomly generate a column between minCol and
																		// maxCol
						newGrid.getSquare(tempRow, tempCol).addEnemyShip(ship);
					}
				}
			}
		}
		this.gameGrid = newGrid;
	}

	public Mode modeSwitcher(Mode currentMode) {
		if (currentMode == Mode.OFFENSIVE) {
			return Mode.DEFENSIVE;
		} else {
			return Mode.OFFENSIVE;
		}
	}

	public int getEnemyShipsInSky() {
		int enemyShips = 0;
		for (int row = 0; row < gameGrid.getRow(); row++) {
			for (int col = 0; col < gameGrid.getCol(); col++) {
				enemyShips += gameGrid.getSquare(row, col).getEnemyShipsAtSquare().size();
			}
		}
		return enemyShips;
	}

	public int getScore() {
		return score;
	}
	
	public int noOfEnemyShipsAtSquare(int row, int col) {
		int output;
		
		output = getGameGrid().getSquare(row, col).getEnemyShipsAtSquare().size();
		
		return output;
	}

}// end class
