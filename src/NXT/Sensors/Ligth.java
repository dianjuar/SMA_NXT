package NXT.Sensors;

import NXT.Ports;
import lejos.nxt.LightSensor;

public class Ligth
{
	LightSensor light;
	
	int alto,bajo;
	boolean calibrado_alto, calibrado_bajo;
	
	public Ligth()
	{
		 light = new LightSensor( Ports.Sensor_LigthPort );	
		 calibrado_alto = calibrado_bajo = false ;
	}
	
	public void calibrarBajo()
	{
		light.calibrateLow();
		bajo = light.getLow();
		calibrado_bajo = true;
	}
	
	public void calibrarAlto()
	{
		light.calibrateHigh();
		alto = light.getHigh();
		calibrado_alto = true;
	}
	
	public int readValue()
	{
		return light.readValue();
	}
	
	public boolean isObstaculo()
	{
		return false;
	}
	
	public boolean isCalibrado_alto()
	{
		return calibrado_alto;
	}
	
	public boolean isCalibrado_bajo()
	{
		return calibrado_bajo;
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
