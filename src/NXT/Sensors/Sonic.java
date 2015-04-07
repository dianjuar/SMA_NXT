package NXT.Sensors;

import NXT.Ports;
import NXT.robot;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.UltrasonicSensor;

public class Sonic extends Thread {
	private UltrasonicSensor us;
	private int distancia;

	private boolean isActivo;

	public Sonic() {
		isActivo = true;

		us = new UltrasonicSensor(Ports.Sensor_SonicPort);
		us.continuous();

		start();
	}

	public void pararHilo() {
		isActivo = false;
	}

	public int getDistancia() {
		return distancia;
	}

	public void run() {
		
		
		while (Button.readButtons() != Button.ID_ESCAPE) {
			distancia = us.getDistance();
			
			
			Tools.LCD.drawString(""+distancia, 7);

			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
