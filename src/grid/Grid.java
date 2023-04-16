package grid;

import java.io.Serializable;

public class Grid implements Serializable{
	private static final long serialVersionUID = 1L;
	private final int defaultRow = 4;
	private final int defaultCol = 4;
	private Square[][] map;
	
	public Grid() {
		this.map = new Square[defaultRow][defaultCol];
		for(int row = 0; row < defaultCol; row++) {
			for(int column = 0; column < defaultRow; column++) {
				this.map[row][column] = new Square();
			}
		}
	}
	
	
	public Square[][] getMap() {
		return map;
	}
	
	public Square getSquare(int row, int column) {
	    return this.map[row][column];
	}

	public void setSquare(int row, int column, Square square) {
	    this.map[row][column] = square;
	}

	public int getRow() {
		return defaultRow;
	}
	
	public int getCol() {
		return defaultCol;
	}	
	
	
	
}