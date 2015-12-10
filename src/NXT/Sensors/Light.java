package NXT.Sensors;

import NXT.Ports;
import lejos.nxt.LightSensor;

public class Light
{
	public LightSensor lightSensor;
	
	int alto,bajo;
	private boolean calibrado_alto, calibrado_bajo;
	
	public Light()
	{
		lightSensor = new LightSensor( Ports.Sensor_LigthPort );	
		 calibrado_alto = calibrado_bajo = false ;
	}
	
	public boolean isCalibrated()
	{
		return (calibrado_alto==false || calibrado_bajo==false) ? false : true; 
	}
	
	public void calibrarBajo()
	{
		lightSensor.calibrateLow();
		bajo = lightSensor.getLow();
		calibrado_bajo = true;
	}
	
	public void calibrarAlto()
	{
		lightSensor.calibrateHigh();
		alto = lightSensor.getHigh();
		calibrado_alto = true;
	}
	
	public void set_calibradoALTO(boolean b)
	{
		calibrado_alto = b;
	}
	
	public void set_calibradoBAJO(boolean b)
	{
		calibrado_bajo = b;
	}
	
	public int readValue()
	{
		return lightSensor.readValue();
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
