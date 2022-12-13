package servidores;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	final static int PUERTO = 5000;

	public static void main(String[] args) { 
		ServerSocket skServidor = null;
		Socket skCliente = null;

		try {
			skServidor = new ServerSocket(PUERTO);
			System.out.println("Servidor encendido y escuchando conexiones...");
			Vagon vagon = new Vagon();
			while(true) {
				skCliente = skServidor.accept();
				System.out.println("<" + skCliente.getInetAddress() + " conectado>");
				HiloManejador hilo = new HiloManejador(skCliente, vagon);
				hilo.start();
			}

		} catch (IOException e) { e.printStackTrace(); }
	}

}
