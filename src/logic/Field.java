package logic;

public class Field {

	private boolean mine;
	private int minesAround;
	private boolean clicked;
	private boolean flag;

	public Field() {
		this.mine = false;
		this.minesAround = 0;
		this.clicked = false;
		this.flag = false;
	}

	public boolean isMine() {
		return mine;
	}

	// for easier calculation of mines around field
	public int isMineInt() {	
		if (mine)
			return 1;
		else
			return 0;
	}

	public void setMine(boolean m) {
		this.mine = m;
	}

	public int getMines() {
		return minesAround;
	}

	public void setMines(int b) {
		this.minesAround = b;
	}
	
	public boolean getFlag() {
		return flag;
	}

	public boolean getClicked() {
		return clicked;
	}

	public int click() {
		// flagged field cannot be clicked
		if (!flag) {
			clicked = true;

			// if mine was clicked, loose return value is -1, otherwise it returns mines around field
			if (mine)
				return -1;
			else
				return minesAround;
		} else {
			return -2;
		}
	}

	// sets flag, if field is  unclicked, it negates previous value
	public void setFlag() {
		if (!clicked)
			flag = !flag;
	}

	public char toChar() {
		if (flag)
			return 'F';
		if (clicked) {
			if (mine)
				return 'X';
			else if (minesAround == 0)
				return ' ';
			else
				return (char)('0' + minesAround);
		} 
		else
			return '-';
	}
	
	@Override
	public String toString() {
		if (flag)
			return "F";
		if (clicked) {
			if (mine)
				return "X";
			else if (minesAround == 0)
				return " ";
			else
				return String.valueOf(minesAround);
		} 
		else
			return "-";
	}
}
