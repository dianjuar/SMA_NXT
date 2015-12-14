package NXT.Sensors;

import NXT.Ports;
import Tools.SensorsControl;
import lejos.nxt.UltrasonicSensor;

public class Sonic extends Thread {
	
	public static int distanciaMinimaDeFrente = 6;
	
	private UltrasonicSensor us;
	private static int  distancia;
	private Tools.SensorsControl establicidad_lectura_sensor;

	private static boolean isActivo;

	public Sonic() 
	{
		isActivo = true;

		us = new UltrasonicSensor(Ports.Sensor_SonicPort);
		us.continuous();
		
		establicidad_lectura_sensor = new SensorsControl(5, 0);

		start();
	}
	
	public static int getDistanciaMinima()
	{
		//acá se debe preguntar la horientacion actual del robot. ya que si va de lado tiene una lectura diferente
		
		return distanciaMinimaDeFrente;
	}

	public static void pararHilo() {
		isActivo = false;
	}

	public static int getDistancia() {
		return distancia;
	}

	public void run() {
		
		
		while (isActivo) 
		{
			distancia = establicidad_lectura_sensor.add(us.getDistance());
			Tools.LCD.drawSonicValue(distancia);

			/*try 
			{
				Thread.sleep(250);
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

	}
}
