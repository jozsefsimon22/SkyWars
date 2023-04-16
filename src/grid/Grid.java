package grid;

import java.io.Serializable;

//This class represents a grid consisting of a 2D array of Square objects
public class Grid implements Serializable {

	private static final long serialVersionUID = 1L;
	private final int defaultRow = 4; // Default number of rows
	private final int defaultCol = 4; // Default number of columns
	private Square[][] map; // 2D array of Square objects to store the grid

	// Constructor to initialise the grid with default values
	public Grid() {
		this.map = new Square[defaultRow][defaultCol];

		// Populate the grid with Square objects
		for (int row = 0; row < defaultCol; row++) {
			for (int column = 0; column < defaultRow; column++) {
				this.map[row][column] = new Square();
			}
		}
	}

	// Method to get the 2D array of Square objects representing the grid
	public Square[][] getMap() {
		return map;
	}

	// Method to get a specific Square object from the grid using row and column
	// indices
	public Square getSquare(int row, int column) {
		return this.map[row][column];
	}

	// Method to set a specific Square object in the grid using row and column
	// indices
	public void setSquare(int row, int column, Square square) {
		this.map[row][column] = square;
	}

	// Method to get the default number of rows in the grid
	public int getRow() {
		return defaultRow;
	}

	// Method to get the default number of columns in the grid
	public int getCol() {
		return defaultCol;
	}

}