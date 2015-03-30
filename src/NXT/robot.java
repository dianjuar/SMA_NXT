package NXT;

import NXT.conexion.Bluetooth;

public class robot
{
	private Bluetooth conect_bl;
	
	public robot()
	{
		conect_bl = new Bluetooth() 
		{
			@Override
			public void analizadorDeSMS_BT(String sms)
			{
							
			}
		};
	}
	
	

}
