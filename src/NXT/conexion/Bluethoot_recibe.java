package NXT.conexion;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class Bluethoot_recibe extends Thread{
	
	private DataInputStream dis;
	private boolean activo;

	public Bluethoot_recibe(DataInputStream dis)
	{
        activo = true;
        this.dis = dis;
        
        start();
	}
	
	public void pararHilo()
	{
        activo = false;
	}
	
	public void run()
	{
        while ( activo )
        {
            String sms = leer();
            analizadorDeSMS(sms);
        }			
	}	
	
	public void close()
	{
        pararHilo();

        try
        {
            dis.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public abstract void analizadorDeSMS(String sms);
	
	private String leer()
	{
        String sms = "";

        try 
        {
            sms = dis.readUTF();
        } 
        catch (IOException e) 
        {
            System.out.println("Error al leer");
            System.out.println("Seguramente se cerró el Stream");
            //e.printStackTrace();
        }

        return sms;	
	}
	
}
