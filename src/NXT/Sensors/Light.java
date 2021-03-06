package NXT.Sensors;

import NXT.Ports;
import Tools.SensorsControl;
import lejos.nxt.LightSensor;

public class Light extends Thread
{
	public LightSensor lightSensor;
	public static int lecturaLight;
	
	private static boolean isActivo;
	private Tools.SensorsControl establicidad_lectura_sensor;
	
	int alto,bajo;
	private boolean calibrado_alto, calibrado_bajo;
	
	public Light()
	{		
		isActivo = true;
		
		lightSensor = new LightSensor( Ports.Sensor_LigthPort );	
		calibrado_alto = calibrado_bajo = false;
		
		Tools.FileManager.READparameters_LigthSensor( this );
		establicidad_lectura_sensor = new SensorsControl(5, 0);
		
		start();
	}
	
	public void run()
	{
		while(isActivo)
		{			
			lecturaLight =  establicidad_lectura_sensor.add(lightSensor.readValue());
			Tools.LCD.drawLigthValue( lecturaLight );
						
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
	
	public static void pararHilo() {
		isActivo = false;
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

	public int getLuminocidad() 
	{
		return lecturaLight;
	}
	
	
}
