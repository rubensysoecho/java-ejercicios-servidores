package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.DecimalFormat;

public class HiloEdificio extends Thread{
	
	private String nombre;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	public HiloEdificio(String nombre)	{
		this.nombre = nombre;
	}
	
	public int rand_temperatura()	{ return (int) (70 * Math.random()); }
	public double rand_CO2()	{ return 1.5 * Math.random(); }
	
	@Override
	public void run() {
		DataOutputStream flujosalida;
		DataInputStream flujoentrada;
		int PUERTO = 5000;
		String HOST = "localhost";
		Socket skCliente = null;
		
		try {
			skCliente = new Socket(HOST, PUERTO);
			System.out.println("CONEXIÓN CON EL SERVIDOR SATISFACTORIA.");
			flujosalida = new DataOutputStream(skCliente.getOutputStream());
			flujoentrada = new DataInputStream(skCliente.getInputStream());
			
			while(true)	{
				int t = rand_temperatura();
				double co2 = rand_CO2();
				
				flujosalida.writeInt(t);
				flujosalida.writeDouble(co2);
				
				String respuesta = flujoentrada.readUTF();
				System.out.println(nombre + " Temp: " + t + " CO2: " + df.format(co2) + " Estado: " + respuesta);
				
				if (respuesta.equals("ALARMA"))	{ Thread.sleep(20000); }
				else {
					Thread.sleep(5000);	
				}
			}
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
}
