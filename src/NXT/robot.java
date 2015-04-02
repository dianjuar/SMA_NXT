package NXT;

import lejos.nxt.LightSensor;
import NXT.Sensors.Ligth;
import NXT.Sensors.Sonic;
import NXT.conexion.Bluethoot_conector;
import NXT.conexion.Encabezado_MensajesNXT;
import Tools.LCD;

public class robot {
	private Bluethoot_conector conect_bl;
	private int robotID;

	private int sensorL_calibrate_f;

	public static int LCD_posPrint;
	public static final int distancia_girarSinColisionar = 10;
	
	private Cinetica cin;
	private Sonic sonic;
	private Ligth ligth;

	public robot() {
		
		cin = new Cinetica();
		sonic = new Sonic();
		ligth = new Ligth();
		
		LCD_posPrint = 0;
		robotID = -1;

		sensorL_calibrate_f = 0;

		conect_bl = new Bluethoot_conector() {
			@Override
			public void analizadorDeSMS_BT(String sms) {
				String encabezado = sms.substring(0, 3);
				String cuerpo = sms.substring(3);

				
				if (encabezado.equalsIgnoreCase(Encabezado_MensajesNXT.RobotID)) {
					robotID = Integer.valueOf(cuerpo);
					Tools.LCD.drawString("ID : " + robotID);
				} else if (encabezado
						.equalsIgnoreCase(Encabezado_MensajesNXT.Calibrar_SensorOptico)) {
					calibrarSensorL();
				}
			}
		};
	}

	private void calibrarSensorL() {
		switch (sensorL_calibrate_f) {
		case 0:
			calibrarSensorL_F0();
			break;
		case 1:

			break;
		case 2:

			break;
		}

		sensorL_calibrate_f++;
	}

	private void calibrarSensorL_F0() {

		switch (robotID) {
		case 1:
		case 2:
			cin.foward();
			
			
			Tools.LCD.drawString( ""+sonic.getDistancia() );
			while( sonic.getDistancia() <= distancia_girarSinColisionar )
			{
				Tools.LCD.drawString( ""+sonic.getDistancia() );
			}
			
			cin.stop();
			
			ligth.calibrarBajo();
			
			
			Tools.LCD.drawString("bajo calibrado");
			
			break;
		case 3:

			break;
		}
	}

	public void cerrarConexionBluethoot() 
	{
		conect_bl.cerrarConexion(true);
		sonic.pararHilo();
	}

}
