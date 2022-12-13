package cliente;

import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class cliente {

	final static int PUERTO = 5000;
	final static String HOST = "localhost";
	public static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Socket socketcliente = null;
		DataInputStream flujoentrada;
		DataOutputStream flujosalida;
		
		try {
			socketcliente = new Socket(HOST, PUERTO);
			System.out.println("CONECTADO AL SERVIDOR");
			
			flujosalida = new DataOutputStream(socketcliente.getOutputStream());
			flujoentrada = new DataInputStream(socketcliente.getInputStream());
			
			System.out.print("Numero 1: ");
			double num1 = entrada.nextDouble();
			System.out.print("Numero 2: ");
			double num2 = entrada.nextDouble();
			
			flujosalida.writeDouble(num1);
			flujosalida.writeDouble(num2);
			
			double suma = flujoentrada.readDouble();
			double resta = flujoentrada.readDouble();
			
			System.out.println("Suma de " + num1 + " y " + num2 + " = " + suma);
			System.out.println("Resta de " + num1 + " y " + num2 + " = " + resta);
			
		} catch (IOException e) { e.printStackTrace(); }
		
		finally	{
			try {
				socketcliente.close();
				entrada.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
	}

}
