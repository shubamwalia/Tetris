package tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

class ShapeStruct {
	public int x, y;
	public char value;

	ShapeStruct(int x, int y, char value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

}

public class Shape {

	public ShapeStruct[] objectMove = new ShapeStruct[5];
	public static List<CoordStack> stack = new ArrayList<CoordStack>();
	public static Stack<CoordStack> undoStack = new Stack<CoordStack>();
	public static Stack<CoordStack> redoStack = new Stack<CoordStack>();
	public static ArrayList<CoordStack> stackList = new ArrayList<CoordStack>();
	public static ArrayList<CoordStack> redoStackList = new ArrayList<CoordStack>();

	public void coInitialize() {
		objectMove[0] = new ShapeStruct(0, 20, '#');
	}

	public static void randomGenerate() {
		Random rand = new Random();
		int gen = rand.nextInt(3);
		switch (gen) {
		case 0:
			Main.shape.iShape1();
			Main.type = 'i';
			Main.position = 1;
			break;
		case 1:
			Main.shape.jShape1();
			Main.type = 'j';
			Main.position = 1;
			break;
		case 2:
			Main.shape.lShape1();
			Main.type = 'l';
			Main.position = 1;
			break;
		}
	}

	public void stackFilling() {
		for (int i = 0; i < 4; i++) {
			undoStack.push(new CoordStack(objectMove[i].x, objectMove[i].y));
		}
	}

	public void boardIShapeFilling(ShapeStruct[] object) {
		for (int i = 0; i < 4; i++) {
			Board.array[object[i].x][object[i].y] = object[i].value;
		}
		objectMove = object;
		stackFilling();
	}

	public void redo() {
		ShapeStruct[] object = new ShapeStruct[5];
		boolean isLast = false;
		for (int i = 0; i < 4; i++) {
			if (redoStack.isEmpty()) {
				return;
			}
			CoordStack temp = redoStack.pop();
			if (temp.isLast) {
				isLast = true;
			}
			redoStackList.add(temp);
			object[i] = new ShapeStruct(temp.x, temp.y, '#');
		}
		boardIShapeFilling(object);
		if (isLast == true) {
			for (int i = 0; i < 4; i++) {
				stack.add(new CoordStack(object[i].x, object[i].y));
			}
		}
		Board.settingBoard();
		redoStackList.clear();
	}

	public void undo() {
		ShapeStruct[] object = new ShapeStruct[5];
		for (int i = 0; i < 4; i++) {
			if (undoStack.isEmpty()) {
				return;
			}
			CoordStack temp = undoStack.pop();
			int p1 = Board.find(temp.x, temp.y);
			if (p1 >= 0) {
				stack.remove(p1);
			}
		}
		for (int i = 0; i < 4; i++) {
			if (undoStack.isEmpty()) {
				return;
			}
			CoordStack temp = undoStack.pop();
			int p1 = Board.find(temp.x, temp.y);
			if (p1 >= 0) {
				stack.remove(p1);
				temp.setIsLast(true);
			}
			object[i] = new ShapeStruct(temp.x, temp.y, '#');
			stackList.add(temp);
			redoStack.push(temp);
		}
		boardIShapeFilling(object);
		Board.settingBoard();
		stackList.clear();
	}

	public void moveDown() {
		ShapeStruct[] tempMove = new ShapeStruct[5];
		for (int i = 0; i < 4; i++) {
			tempMove[i] = new ShapeStruct(objectMove[i].x, objectMove[i].y, objectMove[i].value);
		}
		boolean quit = false;
		for (int i = 0; i < 4; i++) {
			if (((tempMove[i].x + 1) == Board.row - 1) || (Board.find(tempMove[i].x + 1, tempMove[i].y) >= 0)) {
				quit = true;
				break;
			} else {
				tempMove[i].x = tempMove[i].x + 1;
			}
		}
		if (!quit) {
			objectMove = tempMove;
			Board.settingBoard();
			boardIShapeFilling(objectMove);
		} else {
			for (int i = 0; i < 4; i++) {
				stack.add(new CoordStack(objectMove[i].x, objectMove[i].y));
			}
			Main.shape.coInitialize();
			randomGenerate();
		}
	}

	public void moveLeft() {
		ShapeStruct[] tempMove = new ShapeStruct[5];
		for (int i = 0; i < 4; i++) {
			tempMove[i] = new ShapeStruct(objectMove[i].x, objectMove[i].y, objectMove[i].value);
		}
		boolean quit = false;
		for (int i = 0; i < 4; i++) {
			if (((tempMove[i].y - 1) == 0) || (Board.find(tempMove[i].x, tempMove[i].y - 1) >= 0)) {
				quit = true;
				break;
			} else {
				tempMove[i].y = tempMove[i].y - 1;
			}
		}
		if (!quit) {
			objectMove = tempMove;
			Board.settingBoard();
			boardIShapeFilling(objectMove);
		}
	}

	public void moveRight() {
		ShapeStruct[] tempMove = new ShapeStruct[5];
		for (int i = 0; i < 4; i++) {
			tempMove[i] = new ShapeStruct(objectMove[i].x, objectMove[i].y, objectMove[i].value);
		}
		boolean quit = false;
		for (int i = 0; i < 4; i++) {
			if (((tempMove[i].y + 1) == Board.col - 1) || (Board.find(tempMove[i].x, tempMove[i].y + 1) >= 0)) {
				quit = true;
				break;
			} else {
				tempMove[i].y = tempMove[i].y + 1;
			}
		}
		if (!quit) {
			objectMove = tempMove;
			Board.settingBoard();
			boardIShapeFilling(objectMove);
		}
	}

	public void iShape1() {
		ShapeStruct[] object = new ShapeStruct[5];
		int pointx = objectMove[0].x, pointy = objectMove[0].y;
		for (int i = 0; i < 4; i++) {
			object[i] = new ShapeStruct(pointx, pointy, '#');
			pointy++;
		}

		boardIShapeFilling(object);
	}

	public void iShape2() {
		ShapeStruct[] object = new ShapeStruct[5];
		int pointx = objectMove[0].x, pointy = objectMove[0].y;
		for (int i = 0; i < 4; i++) {
			object[i] = new ShapeStruct(pointx, pointy, '#');
			pointx++;
		}

		boardIShapeFilling(object);
	}

	public void jShape1() {
		ShapeStruct[] object = new ShapeStruct[5];
		int pointx = objectMove[0].x, pointy = objectMove[0].y;
		int i;
		for (i = 0; i < 3; i++) {
			object[i] = new ShapeStruct(pointx, pointy, '#');
			pointy++;
		}
		pointx++;
		pointy--;
		object[i] = new ShapeStruct(pointx, pointy, '#');
		boardIShapeFilling(object);
	}

	public void jShape2() {
		ShapeStruct[] object = new ShapeStruct[5];
		int pointx = objectMove[0].x, pointy = objectMove[0].y;
		int i;
		for (i = 0; i < 3; i++) {
			object[i] = new ShapeStruct(pointx, pointy, '#');
			pointx++;
		}
		pointx--;
		pointy--;
		object[i] = new ShapeStruct(pointx, pointy, '#');
		boardIShapeFilling(object);
	}

	public void jShape3() {
		ShapeStruct[] object = new ShapeStruct[5];
		int pointx = objectMove[0].x, pointy = objectMove[0].y;
		int i;
		for (i = 0; i < 2; i++) {
			object[i] = new ShapeStruct(pointx, pointy, '#');
			pointx++;
		}
		pointx--;
		for (i = 2; i < 4; i++) {
			pointy++;
			object[i] = new ShapeStruct(pointx, pointy, '#');
		}
		boardIShapeFilling(object);
	}

	public void jShape4() {
		ShapeStruct[] object = new ShapeStruct[5];
		int pointx = objectMove[0].x, pointy = objectMove[0].y;
		int i;
		pointy++;
		object[0] = new ShapeStruct(pointx, pointy, '#');
		pointy--;
		for (i = 1; i < 4; i++) {
			object[i] = new ShapeStruct(pointx, pointy, '#');
			pointx++;
		}
		boardIShapeFilling(object);
	}

	public void lShape1() {
		ShapeStruct[] object = new ShapeStruct[5];
		int pointx = objectMove[0].x, pointy = objectMove[0].y;
		int i;
		pointx++;
		object[0] = new ShapeStruct(pointx, pointy, '#');
		pointx--;
		for (i = 1; i < 4; i++) {
			object[i] = new ShapeStruct(pointx, pointy, '#');
			pointy++;
		}
		boardIShapeFilling(object);
	}

	public void lShape2() {
		ShapeStruct[] object = new ShapeStruct[5];
		int pointx = objectMove[0].x, pointy = objectMove[0].y;
		int i;
		for (i = 0; i < 2; i++) {
			object[i] = new ShapeStruct(pointx, pointy, '#');
			pointy++;
		}
		pointy--;
		for (i = 2; i < 4; i++) {
			pointx++;
			object[i] = new ShapeStruct(pointx, pointy, '#');
		}
		boardIShapeFilling(object);
	}

	public void lShape3() {
		ShapeStruct[] object = new ShapeStruct[5];
		int pointx = objectMove[0].x, pointy = objectMove[0].y;
		int i;
		for (i = 0; i < 2; i++) {
			object[i] = new ShapeStruct(pointx, pointy, '#');
			pointx++;
		}
		pointx--;
		for (i = 2; i < 4; i++) {
			pointy--;
			object[i] = new ShapeStruct(pointx, pointy, '#');
		}
		boardIShapeFilling(object);
	}

	public void lShape4() {
		ShapeStruct[] object = new ShapeStruct[5];
		int pointx = objectMove[0].x, pointy = objectMove[0].y;
		int i;
		for (i = 0; i < 3; i++) {
			object[i] = new ShapeStruct(pointx, pointy, '#');
			pointx++;
		}
		pointx--;
		pointy++;
		object[i] = new ShapeStruct(pointx, pointy, '#');
		boardIShapeFilling(object);
	}

}
