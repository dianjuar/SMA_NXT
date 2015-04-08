package NXT.conexion;

import javax.microedition.io.Connection;

import NXT.robot;
import lejos.nxt.LCD;
import lejos.nxt.comm.NXTConnection;

public abstract class Bluethoot_conector
{
	private static final String connected = "Connected";
	private static final String waiting = "Waiting...";
	private static final String closing = "Mother tell me close...";
	
	private NXTConnection connection;
	
	private Bluethoot_envia bt_env;
	private Bluethoot_recibe bt_rec;
	
	private boolean isConnected;
	
	public Bluethoot_conector() 
	{
		isConnected = false;
		
		LCD.drawString(waiting,0,0);
		connection = lejos.nxt.comm.Bluetooth.waitForConnection();
		isConnected = true;
	    LCD.clear();
	    LCD.drawString(connected,0, robot.LCD_posPrint++ );
	    
	    bt_env = new Bluethoot_envia( connection.openDataOutputStream() );
	    bt_rec = new Bluethoot_recibe( connection.openDataInputStream() )
	    {	
			@Override
			public void analizadorDeSMS(String sms)
			{				
				if(sms.compareTo( Encabezado_MensajesNXT.Cerrar ) == 0)
					cerrarConexion(false);
				else
					analizadorDeSMS_BT(sms);
			}
		};
	}
	
	public void cerrarConexion( boolean ImClosing )
	{
		
		if( isConnected )
		{
			if(ImClosing)
			bt_env.enviar( Encabezado_MensajesNXT.Cerrar );
			
			LCD.clear();
			LCD.drawString(closing,0,0);
			
			bt_env.close();
			bt_rec.close();	
			connection.close();
			
			isConnected = false;
		}
	}
	
	public void enviar_faseCalibTerminada(int RobotID, int faseCalib)
	{
		bt_env.enviar( Encabezado_MensajesNXT.Calibrar_SensorOptico+Encabezado_MensajesNXT.Separador+
				Encabezado_MensajesNXT.Separador2 + faseCalib);
	}
	
	public abstract void analizadorDeSMS_BT(String sms);

	
}
