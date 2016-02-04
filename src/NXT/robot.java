package NXT;

import lejos.nxt.LightSensor;
import lejos.nxt.ListenerCaller;
import lejos.nxt.Sound;
import lejos.nxt.comm.BTConnection;
import NXT.Sensors.Light;
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
	//private static Sonic sonic;
	//private static Light ligth;
	private static envioInformacion envInf;

	public robot() 
	{
		mirada = 0;
		LCD_posPrint = 0;
		robotID = -1;

		sensorL_calibrate_f = 0;

		conect_bl = new Bluethoot_conector() {
			@Override
			//Todos los grados y distancias tendrán el siguiente formato +###.##
			public void analizadorDeSMS_BT(String sms) {
				String encabezado = sms.substring(0, 3);
				String cuerpo = sms.substring(3);
				
				Tools.LCD.drawString(encabezado,6);
				
				if (encabezado.
						equalsIgnoreCase(Encabezado_MensajesNXT.RobotID)) 
				{
					robotID = Integer.valueOf(cuerpo);
					Tools.LCD.drawString("ID : " + robotID);
				} 
				/*else if (encabezado
						.equalsIgnoreCase(Encabezado_MensajesNXT.Calibrar_SensorOptico))
				{
					cin.set_velocidad_calib();//pone el robot en una velocidad ideal para calibrar
					calibrarSensorL();
					cin.reestablecerVelocidad();//reestablecer la velocidad que llevaba.
					
					//guardar calibracion
					Tools.FileManager.WRITEparameters_LigthSensor( ligth.lightSensor );
				}*/
				else if(encabezado.
						equalsIgnoreCase(Encabezado_MensajesNXT.Movimiento) )
				{
					float grados = Float.valueOf( cuerpo.substring(0,7) );
					float distancia = Float.valueOf( cuerpo.substring(7) );
					
					cin.girar(grados);
					cin.avanzar(distancia);
					
					conect_bl.enviar_MovTerminado();
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
				else if( encabezado
						.equalsIgnoreCase( Encabezado_MensajesNXT.CorreccionDeTrayectoria ) )
				{					
					//if(ligth.isCalibrated())
					//{
						float teta = Float.valueOf( cuerpo.substring(0,7) );
						float distanciaDesface = Float.valueOf( cuerpo.substring(7,14) );
						float tetaDesface = Float.valueOf( cuerpo.substring(14) );
						
						cin.girar(teta);
						cin.avanzar(distanciaDesface);
						cin.girar(tetaDesface);
						
						conect_bl.enviar_CorTerminado();
					//}
					/*else
					{
						for (int i = 0; i < 5; i++)
							Sound.beepSequence();
						
						conect_bl.enviar_CorTerminado();
					}*/
				}
				else if(encabezado.equalsIgnoreCase( Encabezado_MensajesNXT.SetVelocidad ))
				{
					cin.setVelocidad( Float.valueOf(cuerpo) );
				}
				else if(encabezado.equalsIgnoreCase( Encabezado_MensajesNXT.SetVelocidad_izq_der ))
				{
					float VL = Float.valueOf( cuerpo.substring(0,7) );
					float VR = Float.valueOf( cuerpo.substring(7) );
					
					cin.setVelocidad(VL, VR);
				}
			}
		};
		
		//sonic = new Sonic();
		//ligth = new Light( );
		cin = new Cinetica();
		//envInf = new envioInformacion(conect_bl, ligth, sonic);
		
		Tools.LCD.drawString("|", Tools.LCD.posScreen_Separator);
	}
	
	/*private void calibrarSensorL()
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
	}*/
	
	/*private void calibrarSensorL_F2()
	{	System.out.println("ESTOY EN LA ULTIMA FASE");
		
		cin.colocarRobotEn_Distancia(distancia_girarSinColisionar);
		
		ligth.calibrarBajo();
		Tools.LCD.drawString("Bajo calibrado");
		conect_bl.enviar_faseCalibTerminada( true, true );
	}*/
	
	/*private void calibrarSensorL_F1()
	{
		switch (robotID) {
		case 1:
		case 2:
			break;
		case 3:
				cin.girar(90);
				cin.colocarRobotEn_Distancia( distancia_girarSinColisionar);
				
				ligth.calibrarAlto();
				Tools.LCD.drawString("Alto calibrado");
				cin.girar(-90);
			
				conect_bl.enviar_faseCalibTerminada( true, false );				
			break;
		}
	}

	private void calibrarSensorL_F0() {

		switch (robotID) {
		case 1:
		case 2:
			cin.colocarRobotEn_Distancia(distancia_girarSinColisionar);
			
			ligth.calibrarAlto();
			Tools.LCD.drawString("Alto calibrado");
			
			cin.girar(90);
			
			conect_bl.enviar_faseCalibTerminada( true, false );
			
			break;
		case 3:

			break;
		}
	}
*/
	public void cerrarConexionBluethoot() 
	{
		conect_bl.cerrarConexion(true);
	}
	
	public static void detenerHilos()
	{
		/*sonic.pararHilo();
		ligth.pararHilo();
		envInf.pararHilo();*/
	}

}

class envioInformacion extends Thread
{
	private Bluethoot_conector conexion_bt;
	private NXT.Sensors.Light l;
	private NXT.Sensors.Sonic s;
	
	private boolean isActivo;
	
	public envioInformacion( Bluethoot_conector conexion_bt, NXT.Sensors.Light l, NXT.Sensors.Sonic s )
	{
		isActivo = true;
		
		this.conexion_bt = conexion_bt;
		this.l = l;
		this.s = s;
		
		start();
	}
	
	public void pararHilo()
	{
		isActivo = false;
	}
	
	public void run()
	{
		while(isActivo)
		{
			if( conexion_bt.isConnected() )
				//siempre se envía de primero el Sonico y luego el lumínico
				conexion_bt.enviar_Sensores(s, l);
			
			try 
			{
				Thread.sleep(250);
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
