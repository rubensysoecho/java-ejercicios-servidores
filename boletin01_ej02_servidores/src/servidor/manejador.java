package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class manejador extends Thread{

	DataInputStream flujoentrada;
	DataOutputStream flujosalida;
	Socket socketcliente;

	public manejador(Socket socketcliente)	{
		this.socketcliente = socketcliente;
	}

	public double suma(double num1, double num2)	{ return num1 + num2; }

	public double resta(double num1, double num2)	{ return num1 - num2; }

	@Override
	public void run() {	
		try {
			flujoentrada = new DataInputStream(socketcliente.getInputStream());
			flujosalida = new DataOutputStream(socketcliente.getOutputStream());
			double num1 = flujoentrada.readDouble();
			double num2 = flujoentrada.readDouble();

			flujosalida.writeDouble(suma(num1, num2));
			flujosalida.writeDouble(resta(num1, num2));
		} catch (IOException e) { e.printStackTrace(); }
	}
}
