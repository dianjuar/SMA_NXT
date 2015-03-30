package NXT.conexion;

import javax.microedition.io.Connection;

import lejos.nxt.LCD;
import lejos.nxt.comm.NXTConnection;

public abstract class Bluetooth
{
	private static final String connected = "Connected";
	private static final String waiting = "Waiting...";
	private static final String closing = "Mother tell me close...";
	
	private NXTConnection connection;
	
	private BT_Envia bt_env;
	private BT_Recibe bt_rec;
	
	public Bluetooth() 
	{
		LCD.drawString(waiting,0,0);
		connection = lejos.nxt.comm.Bluetooth.waitForConnection();
	    LCD.clear();
	    LCD.drawString(connected,0,0);
	    
	    bt_env = new BT_Envia( connection.openDataOutputStream() );
	    bt_rec = new BT_Recibe( connection.openDataInputStream() )
	    {	
			@Override
			public void analizadorDeSMS(String sms)
			{
				if(sms.compareTo( EncabezadosMensajesNXT.Cerrar ) == 0)
					cerrarConexion();
				else
					analizadorDeSMS_BT(sms);
			}
		};
	}
	
	public void cerrarConexion()
	{
		LCD.clear();
		LCD.drawString(closing,0,0);
		 
		bt_env.close();
		bt_rec.close();		 
	}
	
	public abstract void analizadorDeSMS_BT(String sms);

	
}
