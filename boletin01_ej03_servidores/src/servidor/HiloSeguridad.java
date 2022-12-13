package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloSeguridad extends Thread{

	Socket skCliente;
	DataOutputStream flujosalida;
	DataInputStream flujoentrada;

	final static int umbral_temp = 50;
	final static int umbral_CO2 = 1;

	public HiloSeguridad(Socket socketcliente)	{
		skCliente = socketcliente;
	}

	@Override
	public void run() {
		try {
			flujoentrada = new DataInputStream(skCliente.getInputStream());
			flujosalida = new DataOutputStream(skCliente.getOutputStream());

			while(true)	{
				int temperatura = flujoentrada.readInt();
				double co2 = flujoentrada.readDouble();
				
				if (temperatura <= umbral_temp && co2 <= umbral_CO2)	{
					flujosalida.writeUTF("Todo OK");
				}else	{
					flujosalida.writeUTF("ALARMA");
				}
			}

		} catch (IOException e) { e.printStackTrace(); }
		finally {
			try {
				skCliente.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
}
