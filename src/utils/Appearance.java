package utils;

import java.awt.Color;
import java.awt.Font;

public class Appearance {

	// fonts
	public static Font fontComponent = new Font("Monospaced", Font.PLAIN, 18);
	public static Font fontDialogTop = new Font("Monospaced", Font.BOLD, 16);
	public static Font fontDialogs = new Font("Monospaced", Font.BOLD, 14);
	public static Font fontRecords = new Font("Monospaced", Font.BOLD, 12);
	
	// colors
	public static Color color0 = new Color(255, 255, 255);	// for 0 fields and some labels
	public static Color color1 = Color.BLUE;
	public static Color color2 = new Color (60, 220, 60);	// fail flag after loosing
	public static Color color3 = Color.RED;					// flag, mine background
	public static Color color4 = new Color (0, 204, 255);
	public static Color color5 = Color.MAGENTA;
	public static Color color6 = Color.ORANGE;
	public static Color color7 = Color.PINK;
	public static Color color8 = Color.black;				// game background, dialogs background
	public static Color color9 = new Color(100, 100, 100);	// flag background
	public static Color color10 = new Color(235, 235, 235);	// labels, new game button, timer and 1-8 field background
	public static Color color11 = Color.GRAY;				// unclicked field, dialog buttons
	
	// default theme
	public static void setGrayColours() {
		color0 = Color.WHITE;				// white
		color1 = Color.BLUE;
		color2 = new Color (60, 220, 60);	// darker green
		color3 = Color.RED;
		color4 = new Color (0, 204, 255);	// dark cyan
		color5 = Color.MAGENTA;
		color6 = Color.ORANGE;
		color7 = Color.PINK;
		color8 = Color.BLACK;
		color9 = new Color(100, 100, 100);	// dark gray
		color10 = new Color(235, 235, 235);	// light gray
		color11 = Color.GRAY;
	}
	
	// reverse theme
	public static void setReverseGrayColours() {
		color0 = Color.GRAY;				
		color1 = new Color(51, 140, 255);				
		color2 = new Color(51, 255, 102);
		color3 = new Color(255, 51, 51);				
		color4 = new Color(18, 255, 255);
		color5 = Color.MAGENTA;				
		color6 = Color.ORANGE;				
		color7 = Color.PINK;
		color8 = new Color(190, 190, 190);
		color9 = new Color(100, 100, 100);
		color10 = new Color(77, 77, 77);
		color11 = Color.WHITE;
	}
	
}
