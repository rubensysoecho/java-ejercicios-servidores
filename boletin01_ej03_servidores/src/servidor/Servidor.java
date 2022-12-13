package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	final static int PUERTO = 5000;
	
	public static void main(String[] args) {
		
		ServerSocket skservidor = null;
		Socket skcliente = null;
		
		try {
			skservidor = new ServerSocket(PUERTO);
			System.out.println("SERVIDOR CONECTADO. ESCUCHANDO CONEXIONES...");
			
			while (true)	{
				skcliente = skservidor.accept();

				System.out.println("<<Cliente del edificio conectado. || IP: " + skcliente.getInetAddress());
				HiloSeguridad hiloSeguridad = new HiloSeguridad(skcliente);
				hiloSeguridad.start();
			}
			
		} catch (IOException e) { e.printStackTrace(); }
		finally	{
			System.out.println("SERVIDOR CERRADO.");
			try {
				skservidor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
