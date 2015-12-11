package NXT;

import NXT.Sensors.Sonic;
import lejos.nxt.Motor;
import lejos.nxt.NXT;
import lejos.robotics.navigation.DifferentialPilot;

public class Cinetica
{
	private DifferentialPilot pilot;
	
	public static float velocidad = 10;
	public static float velocidad_calib = 5;
	
	private int velocidadMov;
	private int velocidadGiro;
	
	public Cinetica()
	{	
		velocidadMov = 700;
		velocidadGiro = 500;
		
		pilot = new DifferentialPilot(5.5f, 11.35f, Motor.B, Motor.A);
		
		setVelocidad(velocidad);
	}
	
	public void set_velocidad_calib()
	{
		setVelocidad(velocidad_calib, true);
	}
	
	public void reestablecerVelocidad()
	{
		setVelocidad(velocidad);
	}

	public void colocarRobotEn_Distancia(int d)
	{
		if( Sonic.getDistancia() <= d )
			alejar(d);
		else
			acercar(d);
	}
	
	public void stop()
	{
		pilot.stop();
	}
	
	public void avanzar(float distancia)
	{
		pilot.travel(distancia);
	}
	
	public void acercar( int distancia)
	{
		pilot.forward();
		
		while( Sonic.getDistancia() >= distancia ){}
		
		stop();
	}
	
	public void alejar( int distancia)
	{
		pilot.backward();
		
		while( Sonic.getDistancia() <= distancia ){}
		
		stop();
	}
	
	public void girar(float grados)
	{
		pilot.rotate(grados); 
	}
	
	public void setVelocidad(float vel)
	{
		this.velocidad = vel;
		
		pilot.setTravelSpeed(vel);
		pilot.setRotateSpeed(vel*6);
	}
	
	public void setVelocidad(float vel, boolean temporal)
	{
		if(!temporal)
			this.velocidad = vel;
		
		pilot.setTravelSpeed(vel);
		pilot.setRotateSpeed(vel*6);
	}
	
	private void setVelMov()
	{
		Motor.A.setSpeed(velocidadMov);
		Motor.B.setSpeed(velocidadMov);
	}
	
	private void setVelGiro()
	{
		Motor.A.setSpeed(velocidadGiro);
		Motor.B.setSpeed(velocidadGiro);
	}
	
	public void avanzar()
	{
		setVelMov();
		Motor.A.forward();
		Motor.B.forward();
	}
	
	public void girarIzq()
	{
		setVelGiro();
		Motor.A.forward();
		Motor.B.backward();
		setVelMov();
	}
	
	public void girarDer()
	{
		setVelGiro();
		Motor.B.forward();
		Motor.A.backward();
		setVelMov();
	}
	
	public void atras()
	{
		setVelMov();
		Motor.A.backward();
		Motor.B.backward();
	}

}
