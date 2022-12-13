package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	final static Scanner entrada = new Scanner(System.in);
	
	final static int PORT = 5000;
	final static String HOST = "localhost";
	
	public static void main(String[] args) {
		
		Socket socketcliente = null;
		DataInputStream flujoentrada;
		DataOutputStream flujosalida;
		
		try {
			socketcliente = new Socket(HOST, PORT);
			System.out.println("CLIENTE CONECTADO AL SERVIDOR");
			flujoentrada = new DataInputStream(socketcliente.getInputStream());
			flujosalida = new DataOutputStream(socketcliente.getOutputStream());
			
			System.out.print(">> ");
			String respuesta = entrada.nextLine();
			
			flujosalida.writeUTF(respuesta);
			
			if (respuesta.equals("PUNTUACIONES"))	{
				String puntuaciones = flujoentrada.readUTF();
				System.out.println(puntuaciones);
			}else	{
				int indexEspacio = respuesta.indexOf(" ");
				respuesta = respuesta.replace(" ", "");
				int puntosenviados = Integer.valueOf(respuesta.substring(0, indexEspacio));
				String nombreenviados = respuesta.substring(indexEspacio, respuesta.length());
				
				flujosalida.writeInt(puntosenviados);
				flujosalida.writeUTF(nombreenviados);
				
				respuesta = flujoentrada.readUTF();
				
				System.out.println(respuesta);
			}
			
		} catch (IOException e) { e.printStackTrace(); }
		
		finally	{
			try {
				socketcliente.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
	}

}
