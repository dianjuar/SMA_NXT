package NXT;

import lejos.nxt.Button;
import lejos.nxt.Sound;
import NXT.Sensors.Ligth;
import NXT.Sensors.Sonic;
import NXT.conexion.Bluethoot_conector;
import NXT.conexion.Encabezado_MensajesNXT;

public class robot 
{
    public static final int norte=0;
    public static final int noreste=1;
    public static final int este=2;
    public static final int sureste=3;   
    public static final int sur=4;
    public static final int suroeste=5;
    public static final int oeste=6;
    public static final int noroeste=7;
	
	private Bluethoot_conector conect_bl;
	private int robotID;
	private int mirada;

	private int sensorL_calibrate_f;

	public static int LCD_posPrint;
	public static final int distancia_girarSinColisionar = 10;
	
	private Cinetica cin;
	private Sonic sonic;
	private Ligth ligth;

	public robot() 
	{
		mirada = 0;
		LCD_posPrint = 0;
		robotID = -1;

		sensorL_calibrate_f = 0;

		conect_bl = new Bluethoot_conector() {
			@Override
			public void analizadorDeSMS_BT(String sms) {
				String encabezado = sms.substring(0, 3);
				String cuerpo = sms.substring(3);
				
				if (encabezado.
						equalsIgnoreCase(Encabezado_MensajesNXT.RobotID)) 
				{
					robotID = Integer.valueOf(cuerpo);
					Tools.LCD.drawString("ID : " + robotID);
				} 
				else if (encabezado
						.equalsIgnoreCase(Encabezado_MensajesNXT.Calibrar_SensorOptico))
				{
					calibrarSensorL();
				}
				else if(encabezado.
						equalsIgnoreCase(Encabezado_MensajesNXT.Movimiento) )
				{
					int grados = Integer.valueOf( cuerpo.substring(0,3) );
					float distancia = Integer.valueOf( cuerpo.substring(3) );
					
					cin.girar(grados);
					cin.avanzar(distancia);
				}
				else if(encabezado
						.equalsIgnoreCase(Encabezado_MensajesNXT.MovimientoSIMPLE))
				{
					if(cuerpo.equalsIgnoreCase( Encabezado_MensajesNXT.Mov_norte) )
						cin.avanzar();
					else if(cuerpo.equalsIgnoreCase( Encabezado_MensajesNXT.Mov_sur))
						cin.atras();
					else if (cuerpo.equalsIgnoreCase( Encabezado_MensajesNXT.Mov_este))
						cin.girarDer();
					else if (cuerpo.equalsIgnoreCase( Encabezado_MensajesNXT.Mov_oeste))
						cin.girarIzq();
					else if (cuerpo.equalsIgnoreCase( Encabezado_MensajesNXT.Mov_PARAR))
						cin.stop();
				}
			}
		};
		
		sonic = new Sonic();
		ligth = new Ligth();
		cin = new Cinetica();
	}
	
	private void calibrarSensorL()
	{
		switch (sensorL_calibrate_f) {
		case 0:
			calibrarSensorL_F0();
			break;
		case 1:
			calibrarSensorL_F1();
			break;
		case 2:
			calibrarSensorL_F2();
			break;
		}

		sensorL_calibrate_f++;
		for (int i = 0; i < sensorL_calibrate_f; i++)
		{
			Sound.beep();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Tools.LCD.drawString("Fase Numero "+sensorL_calibrate_f);
	}
	
	private void calibrarSensorL_F2()
	{	System.out.println("ESTOY EN LA ULTIMA FASE");
		
		cin.acercar(sonic, distancia_girarSinColisionar);
		
		ligth.calibrarBajo();
		Tools.LCD.drawString("Bajo calibrado");
		conect_bl.enviar_faseCalibTerminada(ligth.isCalibrado_alto(), ligth.isCalibrado_bajo() );
	}
	
	private void calibrarSensorL_F1()
	{
		switch (robotID) {
		case 1:
		case 2:
			break;
		case 3:
				cin.girar(90);
				
				if( sonic.getDistancia() <= distancia_girarSinColisionar )
				{
					cin.alejar(sonic, distancia_girarSinColisionar);
				}
				else
				{
					cin.acercar(sonic, distancia_girarSinColisionar);
				}
				
				ligth.calibrarAlto();
				Tools.LCD.drawString("Alto calibrado");
				cin.girar(-90);
			
				conect_bl.enviar_faseCalibTerminada(ligth.isCalibrado_alto(), ligth.isCalibrado_bajo() );				
			break;
		}
	}

	private void calibrarSensorL_F0() {

		switch (robotID) {
		case 1:
		case 2:
			cin.acercar(sonic, distancia_girarSinColisionar);
			
			ligth.calibrarAlto();
			Tools.LCD.drawString("Alto calibrado");
			
			cin.girar(90);
			
			conect_bl.enviar_faseCalibTerminada( ligth.isCalibrado_alto(), ligth.isCalibrado_bajo() );
			
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
