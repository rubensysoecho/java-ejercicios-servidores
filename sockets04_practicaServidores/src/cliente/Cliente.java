package cliente;

import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

	final static String HOST = "localhost";
	final static int PUERTO = 5000;

	final static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		Socket skCliente = null;
		DataInputStream flujoentrada = null;
		DataOutputStream flujosalida = null;		

		try {
			skCliente = new Socket(HOST, PUERTO);
			System.out.println("Conexion establecida con el servidor.");
			flujoentrada = new DataInputStream(skCliente.getInputStream());
			flujosalida = new DataOutputStream(skCliente.getOutputStream());

			System.out.println("RESERVA DE ASIENTOS\n");
			
			boolean fin=false;
			while(!fin) {
				System.out.print("Fila del asiento: ");
				int fila = entrada.nextInt();

				System.out.print("Columna del asiento: ");
				int columna = entrada.nextInt();

				System.out.print("Estas seguro de querer reservar el asiento " + fila + " x " + columna + "? (s/n) ");
				String respuesta = entrada.next().toLowerCase();

				if (respuesta.equals("n"))	{ System.out.println("Cancelando operacion..."); }
				else if (!respuesta.equals("s"))	{ System.out.println("Opcion invalida. Cancelando operacion..."); }
				else	{
					flujosalida.writeInt(fila);
					flujosalida.writeInt(columna);
					String respuestaservidor = flujoentrada.readUTF();

					if (respuestaservidor.equals("VAGON COMPLETO"))	{ 
						System.out.println("El vagón está lleno. Cancelando operacion..."); 
						fin=true;
					}
					else if (respuestaservidor.equals("RESERVADO"))	{ 
						System.out.println("Compra realizada correctamente, asiento reservado.");
						fin=true;
					}
					else	{
						System.out.println("Asiento ocupado. Lista de asientos libres: ");
						System.out.println(respuestaservidor);
					}

				}
			}


		} catch (IOException e) { e.printStackTrace(); }

		finally	{
			try {
				entrada.close();
				skCliente.close();
			} catch (IOException e) { e.printStackTrace(); }

		}
	}
}
