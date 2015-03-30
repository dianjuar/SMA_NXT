package NXT.conexion;

import java.io.DataOutputStream;
import java.io.IOException;

public class BT_Envia {

	private  DataOutputStream dos;
	
	public BT_Envia(DataOutputStream dos) {
		this.dos = dos;
	}
	
	public void enviar(int sms)
	{
		try 
		{
			dos.write(sms);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void enviar(String sms)
	{
		try 
		{			
			dos.writeUTF(sms);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try 
		{
			dos.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Error al cerrar la conexion de lectura");
			e.printStackTrace();
		}
	}
}
