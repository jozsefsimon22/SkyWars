// SkyWarsModel.java
package softwareDevelopment2Coursework;

import java.util.Random;

import javax.swing.JOptionPane;

import grid.Grid;
import ships.Square;


public class SkyWarsModel {
	private Grid gameGrid;
	private int prevRow = -1;
	private int prevCol = -1;
	
	
    public SkyWarsModel(Grid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public Grid getGameGrid() {
        return gameGrid;
    }
    
    public void allocateMasterShipRandomly() {
    	Random random = new Random();
    	int row = random.nextInt(gameGrid.getWidth());
    	int col = random.nextInt(gameGrid.getLength());
    	
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
    	JOptionPane.showMessageDialog(null, "You can only move to neighbouring squares", null, JOptionPane.WARNING_MESSAGE);
    }

	public int getPrevRow() {
		return prevRow;
	}

	public int getPrevCol() {
		return prevCol;
	}

    
}// end class
