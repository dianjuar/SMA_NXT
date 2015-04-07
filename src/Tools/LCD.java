package Tools;

import NXT.robot;

public class LCD
{
	private static String vacio="                           ";
	
	public static void drawString(String s)
	{
		lejos.nxt.LCD.drawString(s,0, robot.LCD_posPrint++ );
	}
	public static void drawString(String s, int n)
	{
		lejos.nxt.LCD.drawString(vacio,0, n );
		lejos.nxt.LCD.drawString(s,0, n );
	}

}
