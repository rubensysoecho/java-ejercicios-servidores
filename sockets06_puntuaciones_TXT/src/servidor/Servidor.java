package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	final static int PORT = 5000;
	
	public static void main(String[] args) {
		
		Socket socketcliente = null;
		ServerSocket socketservidor = null;
		
		try {
			socketservidor = new ServerSocket(PORT);
			System.out.println("SERVIDOR CONECTADO Y ESCUCHANDO...");
			
			Puntuaciones puntuaciones = new Puntuaciones();
			while(true)	{
				socketcliente = socketservidor.accept();
				System.out.println("<Cliente con IP " + socketcliente.getInetAddress() + " conectado>");
				HiloPuntuaciones hilo = new HiloPuntuaciones(socketcliente, puntuaciones);
				hilo.start();
			}
			
		} catch (IOException e) { e.printStackTrace(); }
		
		finally	{
			try {
				socketservidor.close();
				socketcliente.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
}
