package servidores;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloManejador extends Thread{

	private Socket socketcliente;
	private Vagon vagon;



	public HiloManejador(Socket socketcliente, Vagon vagon) {
		super();
		this.socketcliente = socketcliente;
		this.vagon = vagon;
	}



	@Override
	public void run() {

		DataInputStream flujoentrada = null;
		DataOutputStream flujosalida = null;

		try {

			flujoentrada = new DataInputStream(socketcliente.getInputStream());
			flujosalida = new DataOutputStream(socketcliente.getOutputStream());

			boolean fin=false;
			while(!fin) {
				int fila = flujoentrada.readInt();
				int columna = flujoentrada.readInt();
				String reservar=vagon.reservar(fila, columna);
				flujosalida.writeUTF(reservar);
				if (reservar.equals("VAGON COMPLETO") || reservar.equals("RESERVADO")) {
					fin=true;
				}
			}

		} catch (IOException e) { e.printStackTrace(); }
		finally {
			try {
				socketcliente.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
	}

}
