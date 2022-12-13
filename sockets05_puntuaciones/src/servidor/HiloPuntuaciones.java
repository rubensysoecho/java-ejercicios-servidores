package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloPuntuaciones extends Thread{

	Socket socketcliente;
	Puntuaciones listaPuntos;
	
	public HiloPuntuaciones(Socket socketcliente, Puntuaciones listaPuntos)	{
		this.socketcliente = socketcliente;
		this.listaPuntos = listaPuntos;
	}
	
	@Override
	public void run() {
		
		DataInputStream flujoentrada;
		DataOutputStream flujosalida;
		
		try {
			flujoentrada = new DataInputStream(socketcliente.getInputStream());
			flujosalida = new DataOutputStream(socketcliente.getOutputStream());
			
			String respuesta = flujoentrada.readUTF();
			
			if (respuesta.equals("PUNTUACIONES"))	{
				System.out.println(listaPuntos.ver_puntuaciones());
				flujosalida.writeUTF(listaPuntos.ver_puntuaciones()); 
			}else	{
				int puntosrecibidos = flujoentrada.readInt();
				String nombrerecibido = flujoentrada.readUTF();
				listaPuntos.anhadir_puntuacion(puntosrecibidos, nombrerecibido);
				flujosalida.writeUTF("OK");
			}
			
		} catch (IOException e) { e.printStackTrace(); }
	}
}
