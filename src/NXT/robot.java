package NXT;

import NXT.conexion.Bluethoot_conector;

public class robot
{
	private Bluethoot_conector conect_bl;
	
	public robot()
	{
		conect_bl = new Bluethoot_conector() 
		{
			@Override
			public void analizadorDeSMS_BT(String sms)
			{
							
			}
		};
	}
	
	

}
