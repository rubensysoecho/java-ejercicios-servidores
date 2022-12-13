package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
	
	public static void main(String[] args) {
		
		int PUERTO = 5500;
		ServerSocket skServidor = null;
		Socket skCliente = null;
		DataInputStream flujoEntrada = null;
		DataOutputStream flujoSalida = null;
		
		try {
			skServidor = new ServerSocket(PUERTO);
			System.out.println("SERVIDOR EN FUNCIONAMIENTO...");
			
			while(true)	{
				skCliente = skServidor.accept();
				System.out.println("<<<Cliente conectado // IP: " + skCliente.getInetAddress() + ">>>");
				
				flujoEntrada = new DataInputStream(skCliente.getInputStream());
				flujoSalida = new DataOutputStream(skCliente.getOutputStream());
				
				double num1 = flujoEntrada.readDouble();
				double num2 = flujoEntrada.readDouble();
				
				flujoSalida.writeDouble(num1 + num2);
				flujoSalida.writeDouble(num1 - num2);
				
				System.out.println("\tSuma de " + num1 + " y " + num2 + " enviada.");
				System.out.println("\tResta de " + num1 + " y " + num2 + " enviada.");
			}
			
		} catch (IOException e) { e.printStackTrace(); }
		finally	{
			try	{
				skServidor.close();
				skCliente.close();
				flujoEntrada.close();
				flujoSalida.close();
			}catch (Exception e)	{ e.printStackTrace(); }
		}
	}

}
