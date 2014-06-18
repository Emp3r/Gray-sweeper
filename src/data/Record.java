package data;

public class Record {

	public int score;
	public String player;
	public String date;

	public Record(int s, String p, String d) {
		this.score = s;
		this.player = p;
		this.date = d;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		int minutes = score / 100;
		int seconds = score % 100;
		
		if (minutes < 10)
			result.append('0');
		result.append(minutes);
		result.append(":");
		if (seconds < 10)
			result.append('0');
		result.append(seconds);
		
		result.append(String.format(" %12s", player));
		result.append("     ");
		result.append(date);
		
		return result.toString();
	}
}
