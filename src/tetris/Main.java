package tetris;

import java.util.Scanner;

public class Main {
	public static int position = 1;
	public static char type = 'i';
	public static Shape shape = new Shape();

	public static void rotate() {
		Board.settingBoard();
		if (type == 'i') {
			if (position == 1) {
				shape.iShape2();
				position = 2;
			} else if (position == 2) {
				shape.iShape1();
				position = 1;
			}
		} else if (type == 'j') {
			if (position == 1) {
				shape.jShape2();
				position = 2;
			} else if (position == 2) {
				shape.jShape3();
				position = 3;
			} else if (position == 3) {
				shape.jShape4();
				position = 4;
			} else if (position == 4) {
				shape.jShape1();
				position = 1;
			}
		} else if (type == 'l') {
			if (position == 1) {
				shape.lShape2();
				position = 2;
			} else if (position == 2) {
				shape.lShape3();
				position = 3;
			} else if (position == 3) {
				shape.lShape4();
				position = 4;
			} else if (position == 4) {
				shape.lShape1();
				position = 1;
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean quit = false;
		Board.settingBoard();
		shape.coInitialize();
		shape.iShape1();
		Board.printBoard();
		while (!quit) {
			String choice = scanner.next();
			switch (choice) {
			case "s":
				shape.moveDown();
				break;
			case "a":
				shape.moveLeft();
				break;
			case "d":
				shape.moveRight();
				break;
			case "r":
				rotate();
				break;
			case "u":
				shape.undo();
				break;
			case "y":
				shape.redo();
				break;
			case "q":
				quit = true;
				break;
			}
			Board.printBoard();

		}

	}
}
