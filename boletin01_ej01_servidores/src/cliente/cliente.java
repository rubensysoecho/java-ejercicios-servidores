package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class cliente {

	public static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		int PUERTO = 5500;
		String host = "localhost";
		Socket skCliente = null;
		DataInputStream flujoEntrada = null;
		DataOutputStream flujoSalida = null;

		try {
			skCliente = new Socket(host, PUERTO);

			flujoEntrada = new DataInputStream(skCliente.getInputStream());
			flujoSalida = new DataOutputStream(skCliente.getOutputStream());

			System.out.println("1er numero a enviar(-1 para salir): ");
			double num1 = entrada.nextDouble();	

			flujoSalida.writeDouble(num1);

			System.out.println("2º numero a enviar: ");
			double num2 = entrada.nextDouble();

			flujoSalida.writeDouble(num2);

			double suma = flujoEntrada.readDouble();
			double resta = flujoEntrada.readDouble();

			System.out.println("Suma -> " + suma);
			System.out.println("Resta -> " + resta);
		} catch (IOException e) { e.printStackTrace(); }
		
		finally	{
			System.out.println("Cliente desconectado.");
			try {
				skCliente.close();
				flujoEntrada.close();
				flujoSalida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
