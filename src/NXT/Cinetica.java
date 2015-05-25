package NXT;

import NXT.Sensors.Sonic;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.Settings;
import lejos.robotics.navigation.DifferentialPilot;

public class Cinetica
{
	private DifferentialPilot pilot;
	private int velocidad = 10;
	
	public Cinetica()
	{
		pilot = new DifferentialPilot(5.5f, 11.35f, Motor.B, Motor.A);
		
		pilot.setTravelSpeed(velocidad);
		pilot.setRotateSpeed(velocidad*5);
	}
	
	public void stop()
	{
		pilot.stop();
	}
	
	public void avanzar(float distancia)
	{
		pilot.travel(distancia);
	}
	
	public void acercar(Sonic sonic, int distancia)
	{
		pilot.forward();
		
		while( sonic.getDistancia() >= distancia ){}
		
		stop();
	}
	
	public void alejar(Sonic sonic, int distancia)
	{
		pilot.backward();
		
		while( sonic.getDistancia() <= distancia ){}
		
		stop();
	}
	
	public void girar(float grados)
	{
		pilot.rotate(grados); 
	}
	
	public void setVelocidad(int vel)
	{
		this.velocidad = vel;
		
		pilot.setTravelSpeed(velocidad);
		pilot.setRotateSpeed(velocidad*2);
	}

}
