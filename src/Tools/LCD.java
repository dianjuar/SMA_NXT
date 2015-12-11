package Tools;

import java.awt.Point;

import NXT.robot;

public class LCD
{
	private static String vacio="                           ";
	private static String eraserWord = "     "; //length of 5because lengthWordSensorOnScreen = 5
	
	public static int LigthPos = 7;
	public static int SonicPos = 7;
	
	public static Point posScreen_Sonic = new Point(0 , SonicPos);
	public static Point posScreen_Ligth = new Point(6 , LigthPos);
	public static Point posScreen_Separator = new Point(4, SonicPos);
	
	public static void drawString(String s)
	{
		lejos.nxt.LCD.drawString(s,0, robot.LCD_posPrint++ );
	}
	
	public static void drawString(String s, int n)
	{
		lejos.nxt.LCD.drawString(vacio, 0, n);
		lejos.nxt.LCD.drawString(s, 0, n);
	}
	
	public static void drawString(String s, Point p)
	{
		lejos.nxt.LCD.drawString(eraserWord, p.x, p.y);
		lejos.nxt.LCD.drawString(s, p.x, p.y);
	}
	
	public static void drawSonicValue(int v)
	{
		drawString("D:"+ (v==Integer.MAX_VALUE ? "---":v), posScreen_Sonic);
	}
	
	public static void drawLigthValue(int v)
	{
		drawString("L:"+ (v==Integer.MAX_VALUE ? "---":v), posScreen_Ligth);
	}

}
