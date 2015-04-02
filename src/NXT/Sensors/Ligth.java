package NXT.Sensors;

import NXT.Ports;
import lejos.nxt.LightSensor;

public class Ligth
{
	LightSensor light;
	
	int alto,bajo;
	
	public Ligth()
	{
		 light = new LightSensor( Ports.Sensor_LigthPort );	
	}
	
	public void calibrarBajo()
	{
		light.calibrateLow();
		
		bajo = light.getLow();
	}
	
	public void calibrarAlto()
	{
		light.calibrateHigh();
		
		alto = light.getHigh();
	}
	
	public int readValue()
	{
		return light.readValue();
	}
	
	public boolean isObstaculo()
	{
		return false;
	}
	
	public boolean isAgenteFrontal()
	{
		return false;
	}
	
	public boolean isAgenteLateral()
	{
		return false;
	}
	
	
}
