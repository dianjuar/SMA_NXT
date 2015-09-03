package NXT.conexion;

public class Encabezado_MensajesNXT { //TODOS los mensajes de encabezado DEBEN ser de 3 letras
	public static final String Separador = "->";
            public static final String Separador2 = "-";
                
	public static final String Sensor = "SEN";
            public static final String Sen_Sonico = "SON";
            public static final String Sen_Optico = "OPT";

	public static final String RobotID = "IDR";

	public static final String Movimiento = "MOV";
	public static final String MovimientoSIMPLE = "MOS";
            public static final String Mov_norte = "0";
            public static final String Mov_noreste = "1";
            public static final String Mov_este = "2";
            public static final String Mov_sureste = "3";
            public static final String Mov_sur = "4";
            public static final String Mov_suroeste = "5";
            public static final String Mov_oeste = "6";
            public static final String Mov_noroeste = "7";
            public static final String Mov_PARAR = "8";

	public static final String Cerrar = "close";

	public static final String Calibrar_SensorOptico = "CAL";

}