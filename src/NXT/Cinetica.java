package NXT;

import lejos.nxt.Motor;
import lejos.nxt.Settings;

public class Cinetica
{
	public Cinetica()
	{
		Motor.A.setSpeed(25);
		Motor.B.setSpeed(25);
	}
	
	public static void foward()
	{
		Motor.A.forward();
		Motor.B.forward();
	}
	
	public static void stop()
	{
		Motor.A.stop();
		Motor.B.stop();
	}

}
