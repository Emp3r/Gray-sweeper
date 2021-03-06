package logic;

import java.util.Random;
import logic.Field;

public class Board {

	private int width;
	private int height;
	private int allFields;
	private int mineFields;
	private int clearFields;
	private Field[][] fields;

	public Board(int w, int h, int difficulty) {
		this.width = w;
		this.height = h;
		this.fields = new Field[w][h];
		this.allFields = w * h;
		this.mineFields = allFields / difficulty;	// difficulty - higher number for less mines on the board
		this.clearFields = allFields - mineFields;

		fillFields();
		setMines();
		setMinesAround();
	}

	public int getAllFields() {
		return allFields;
	}

	public int getAllMineFields() {
		return mineFields;
	}

	public int getAllClearFields() {
		return clearFields;
	}

	public Field[][] getFields() {
		return fields;
	}

	public int click(int x, int y) {
		return fields[x][y].click();
	}

	public boolean getFlag(int x, int y) {
		return fields[x][y].getFlag();
	}

	public void flag(int x, int y) {
		fields[x][y].setFlag();
	}

	// fields initialization
	private void fillFields() {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				fields[x][y] = new Field();
	}

	// mines initialization - random placement
	private void setMines() {
		Random rnd = new Random();

		for (int i = 0; i < mineFields; i++) {
			int x = rnd.nextInt(width);
			int y = rnd.nextInt(height);

			// if field on new random position is already a mine,
			// it generates new random position
			if (!fields[x][y].isMine())
				fields[x][y].setMine(true);
			else
				i--;
		}
	}

	// calculation of all mines around each field
	private void setMinesAround() {
		for (int x = 0; x < width; x++) {
			if (x == 0) {
				// left top corner
				fields[0][0].setMines(fields[x + 1][0].isMineInt()
						+ fields[x][1].isMineInt() + fields[x + 1][1].isMineInt());
				// left bottom corner
				fields[0][height - 1].setMines(fields[x + 1][height - 1].isMineInt()
						+ fields[x][height - 2].isMineInt() + fields[x + 1][height - 2].isMineInt());
				// all no-corner fields on left
				for (int y = 1; y < height - 1; y++)
					fields[x][y].setMines(xIsZero(x, y));
			} 
			else if (x == width - 1) {
				// right top corner
				fields[x][0].setMines(fields[x - 1][0].isMineInt()
						+ fields[x][1].isMineInt() + fields[x - 1][1].isMineInt());
				// right bottom corner
				fields[x][height - 1].setMines(fields[x - 1][height - 1].isMineInt()
						+ fields[x][height - 2].isMineInt() + fields[x - 1][height - 2].isMineInt());
				// all no-corner fields on right
				for (int y = 1; y < height - 1; y++)
					fields[x][y].setMines(xIsWidth(x, y));
			} 
			else {
				// top field
				fields[x][0].setMines(yIsZero(x, 0));
				// bottom field
				fields[x][height - 1].setMines(yIsHeight(x, height - 1));
				// all other fields (in the middle, they don't touch any edges)
				for (int y = 1; y < height - 1; y++)
					fields[x][y].setMines(CountAllAround(x, y));
			}
		}
	}

	// simple methods for easier calculation of mines around field
	private int xIsZero(int x, int y) {
		return fields[x + 1][y].isMineInt() + fields[x][y + 1].isMineInt()
				+ fields[x][y - 1].isMineInt() + fields[x + 1][y + 1].isMineInt()
				+ fields[x + 1][y - 1].isMineInt();
	}

	private int xIsWidth(int x, int y) {
		return fields[x - 1][y].isMineInt() + fields[x][y + 1].isMineInt()
				+ fields[x][y - 1].isMineInt() + fields[x - 1][y + 1].isMineInt()
				+ fields[x - 1][y - 1].isMineInt();
	}

	private int yIsZero(int x, int y) {
		return fields[x + 1][y].isMineInt() + fields[x - 1][y].isMineInt()
				+ fields[x][y + 1].isMineInt() + fields[x + 1][y + 1].isMineInt()
				+ fields[x - 1][y + 1].isMineInt();
	}

	private int yIsHeight(int x, int y) {
		return fields[x + 1][y].isMineInt() + fields[x - 1][y].isMineInt()
				+ fields[x][y - 1].isMineInt() + fields[x + 1][y - 1].isMineInt()
				+ fields[x - 1][y - 1].isMineInt();
	}

	// fields in the midlle
	private int CountAllAround(int x, int y) {
		return fields[x + 1][y].isMineInt() + fields[x - 1][y].isMineInt()
				+ fields[x][y + 1].isMineInt() + fields[x][y - 1].isMineInt()
				+ fields[x + 1][y + 1].isMineInt() + fields[x + 1][y - 1].isMineInt()
				+ fields[x - 1][y + 1].isMineInt() + fields[x - 1][y - 1].isMineInt();
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("  | ");

		for (int i = 0; i < width; i++) {
			char ch = (char) ('a' + i);
			result.append(ch + " ");
		}

		result.append("\n");
		for (int i = 0; i < width * 2 + 3; i++)
			result.append('-');

		result.append("\n");

		for (int y = 0; y < height; y++) {
			result.append((y + 1) + (y + 1 > 9 ? "| " : " | "));
			for (int x = 0; x < width; x++) {
				result.append(fields[x][y].toString());
				result.append(" ");
			}
			result.append("\n");
		}
		return result.toString();
	}
}
