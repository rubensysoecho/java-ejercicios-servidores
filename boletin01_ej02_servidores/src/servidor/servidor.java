package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {

	final static int PUERTO = 5000;
	
	public static void main(String[] args) {
		
		ServerSocket socketservidor = null;
		Socket socketcliente = null;
		
		try {
			socketservidor = new ServerSocket(PUERTO);
			System.out.println("SERVIDOR ESCUCHANDO EN EL PUERTO " + PUERTO);
			
			while(true)	{
				socketcliente = socketservidor.accept();
				System.out.println("<<Conexion con cliente recibida || IP: " + socketcliente.getInetAddress());
				manejador hiloEnvioDatos = new manejador(socketcliente);
				hiloEnvioDatos.start();
			}
		} catch (IOException e) { e.printStackTrace(); }
		finally	{
			try {
				socketservidor.close();
				socketcliente.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
