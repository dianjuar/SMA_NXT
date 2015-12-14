package Tools;

public class SensorsControl {	
	/**
	 * @file promedio.h
	 * @brief Clase de ayuda para mantener un promedio de las N lecturas de tipo V
	 * @author Kevin Hernández, Ángel Gil
	 */
	
	private int muestra[];
	private int sumatoria;
	private int posPrimer;
	private int N;
	
	public SensorsControl(int N, int valorInicial)
	{
		this.N = N;
		muestra = new int[N];
		
		for(int i=0; i< N; i++)
			muestra[i] = valorInicial;
		
		sumatoria = valorInicial*N;
		
		posPrimer = 0;
	}
	
	public int add(int nueva_muestra)
	{
		sumatoria = sumatoria - muestra[posPrimer] + nueva_muestra;
		muestra[posPrimer++] = nueva_muestra;
		
		if (posPrimer >= N)
			posPrimer = 0;
		
		return sumatoria / N;
	}
	
	public int get()
	{	
		return sumatoria / N;
	}
}
