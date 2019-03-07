package tetris;

public class Board {
	protected static final int row = 20, col = 40;
	protected static char[][] array = new char[row][col];

	public char[][] getArray() {
		return array;
	}

	public static void printBoard() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
	}

	public static int find(int x, int y) {
		for (int i = 0; i < Shape.stack.size(); i++) {
			if ((Shape.stack.get(i).x == x) && (Shape.stack.get(i).y == y)) {
				return i;
			}
		}
		return -1;
	}

	public static int findUndoList(int x, int y) {
		for (int i = 0; i < Shape.stackList.size(); i++) {
			if ((Shape.stackList.get(i).x == x) && (Shape.stackList.get(i).y == y)) {
				return i;
			}
		}
		return -1;
	}

	public static int findRedoList(int x, int y) {
		for (int i = 0; i < Shape.redoStackList.size(); i++) {
			if ((Shape.redoStackList.get(i).x == x) && (Shape.redoStackList.get(i).y == y)) {
				return i;
			}
		}
		return -1;

	}

	public static void deleteRow(int num) {
		for (int i = num; i > 0; i--) {
			for (int j = 0; j < col; j++) {
				array[i][j] = array[i - 1][j];
			}
		}
		for (int i = 0; i < Shape.stack.size(); i++) {
			if (Shape.stack.get(i).x == num) {
				Shape.stack.remove(i);
				i--;
			} else if (Shape.stack.get(i).x < num) {
				Shape.stack.get(i).x = Shape.stack.get(i).x + 1;
			}
		}
	}

	public static void settingBoard() {
		int count;
		for (int i = 0; i < row; i++) {
			count = 0;
			for (int j = 0; j < col; j++) {
				if (i == (row - 1)) {
					array[i][j] = '-';
				} else if ((j == 0) || (j == (col - 1))) {
					array[i][j] = '|';
				} else {
					if ((find(i, j) == -1) && (findUndoList(i, j) == -1 && (findRedoList(i, j) == -1))) {
						array[i][j] = ' ';
					} else {
						count++;
					}
				}
			}
			if (count == col - 2) {
				deleteRow(i);
			}
		}
	}
}
