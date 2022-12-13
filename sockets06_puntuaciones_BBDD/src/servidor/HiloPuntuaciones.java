package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HiloPuntuaciones extends Thread{

	Socket socketcliente;
	Puntuaciones listaPuntos;
	Connection conexion;
	
	public HiloPuntuaciones(Socket socketcliente, Puntuaciones listaPuntos, Connection conexion)	{
		this.socketcliente = socketcliente;
		this.listaPuntos = listaPuntos;
		this.conexion = conexion;
	}
	
	private void insertarPuntuacionBBDD(Connection conexion, int puntuacion, String nombre) {
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/puntuaciones", "root", "root");
			String sql = "INSERT INTO `puntuaciones`(`PUNTOS`, `NOMBRE`) VALUES (?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, puntuacion);
			sentencia.setString(2, nombre);
			sentencia.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
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
				insertarPuntuacionBBDD(conexion, puntosrecibidos, nombrerecibido);
				flujosalida.writeUTF("OK");
			}
			
		} catch (IOException e) { e.printStackTrace(); }
		
	}
}
