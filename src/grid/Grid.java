package grid;

public class Grid {
	int length = 4;
	int width = 4;
	
	Square[][] map = new Square[length][width];
	
	public Grid() {
		for(int widthLoop = 0; widthLoop < width; widthLoop++) {
			for(int lengthLoop = 0; lengthLoop < length; lengthLoop++) {
			map[widthLoop][lengthLoop] = new Square();
			}
		}
	}
}