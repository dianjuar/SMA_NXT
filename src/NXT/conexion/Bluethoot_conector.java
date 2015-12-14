package NXT.conexion;

import javax.microedition.io.Connection;

import NXT.robot;
import NXT.Sensors.Light;
import NXT.Sensors.Sonic;
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
	
	public boolean isConnected()
	{
		return isConnected;
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
			
			//es necesario detener todos los hilos para que el robot se apague al momento de recibit el mensaje de borrar
			robot.detenerHilos();
		}
	}
	
	public void enviar_MovTerminado()
	{	
		bt_env.enviar( Encabezado_MensajesNXT.MovimientoTERMINADO );
	}
	
	public void enviar_CorTerminado()
	{	
		bt_env.enviar( Encabezado_MensajesNXT.CorreccionTERMINADO );
	}
	
	public void enviar_faseCalibTerminada( boolean alto, boolean bajo)
	{
		bt_env.enviar( Encabezado_MensajesNXT.Calibrar_SensorOptico + Encabezado_MensajesNXT.Separador+
			  (alto?1:0) + (bajo?1:0));
	}
	
	public void enviar_Sensores(NXT.Sensors.Sonic s, NXT.Sensors.Light l)
	{
		String lum = String.valueOf( l.getLuminocidad() );
		
		//siempre se envía de primero el Sonico y luego el lumínico
		bt_env.enviar( Encabezado_MensajesNXT.Sensor + Encabezado_MensajesNXT.Separador + 
				s.getDistancia() + Encabezado_MensajesNXT.Separador2  + lum );
	}
	
	public abstract void analizadorDeSMS_BT(String sms);

	
}
