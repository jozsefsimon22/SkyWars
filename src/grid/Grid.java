package grid;
import ships.Ship;

public class Grid {
	private final int length = 4;
	private final int width = 4;
	private Square[][] map;
	
	public Grid() {
		this.map = new Square[length][width];
		for(int row = 0; row < width; row++) {
			for(int column = 0; column < length; column++) {
				this.map[row][column] = new Square();
			}
		}
	}
	
	
	
	public Square[][] getMap() {
		return map;
	}
	
	
	
	
}