package NXT.conexion;




import java.io.DataOutputStream;
import java.io.IOException;

public class Bluethoot_envia {

    private  DataOutputStream dos;

    public Bluethoot_envia(DataOutputStream dos) {
        this.dos = dos;
    }

    public void enviar(int sms)
    {
        try 
        {
            dos.write(sms);
            dos.flush();
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
            dos.flush();
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
