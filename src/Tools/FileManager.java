package Tools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.microedition.sensor.LightSensorInfo;

import lejos.nxt.LightSensor;

public class FileManager 
{
	public static String ligthSensorFileName = "ligthSensor.bin";
	
	public FileManager()
	{
		
	}
	
	public static boolean WRITEparameters_LigthSensor( LightSensor l )
	{
		try
		{
			File data = new File(ligthSensorFileName);
			
			data.createNewFile();
			
			FileOutputStream outF = new FileOutputStream(data);
			DataOutputStream dataOut = new DataOutputStream(outF);
			  
	        dataOut.writeInt( l.getLow() );
	        dataOut.writeInt( l.getHigh() );
	
	        outF.close(); // flush the buffer and write the file
	        
	        return true;
		}
		catch (IOException e)
		{
			Tools.LCD.drawString("ERROR trying WRITE");
		}
		
		return false;
		
	}
	
	public static boolean READparameters_LigthSensor( NXT.Sensors.Light l )
	{
		try
		{
			File data = new File(ligthSensorFileName);
			
			FileInputStream inF = new FileInputStream(data);
			DataInputStream dataIn = new DataInputStream(inF);
			
			l.lightSensor.setLow( dataIn.readInt() );	l.set_calibradoALTO(true);
			l.lightSensor.setHigh( dataIn.readInt() );	l.set_calibradoBAJO(true);
			   
	        inF.close(); // flush the buffer and write the file
	        
	        return true;
		}
		catch (IOException e)
		{
			Tools.LCD.drawString("ERROR trying READ");
		}
		
		return false;
	}

}
