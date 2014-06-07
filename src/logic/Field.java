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

	// pro snadnější výpočet min kolem pole
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
		// pokud je pole označené vlajkou, nemůže se na něj kliknout
		if (!flag) {
			clicked = true;
			// pokud se stouplo na minu, prohra se signalizuje číslem -1, jinak vrací počet min kolem pole
			if (mine)
				return -1;
			else
				return minesAround;
		} else {
			return -2;
		}
	}

	// nastaví vlajku - pokud není pole odkliknuté, neguje se ovlajkování
	public void setFlag() {
		if (!clicked)
			flag = !flag;
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
