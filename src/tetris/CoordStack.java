package tetris;

public class CoordStack {
	public int x, y;
	boolean isLast = false;

	public CoordStack(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setIsLast(boolean isLast) {
		this.isLast = isLast;
	}
}
