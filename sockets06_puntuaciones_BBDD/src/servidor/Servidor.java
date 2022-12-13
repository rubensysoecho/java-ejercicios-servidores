package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Servidor {

	final static int PORT = 5000;
	
	public static void generarBBDD(Connection conexion)	{
		try {
			Statement sentencia = conexion.createStatement();
			String direccion = "jdbc:mysql://localhost:3306/";
			String usuario = "root";
			String passwd = "root";
			
			ArrayList<String> sentencias = new ArrayList<String>();
			sentencias.add("CREATE DATABASE IF NOT EXISTS `puntuaciones` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;");
			sentencias.add("DROP TABLE IF EXISTS `puntuaciones`");
			sentencias.add("CREATE TABLE `puntuaciones` (\r\n"
					+ "  `PUNTOS` int(30) NOT NULL,\r\n"
					+ "  `NOMBRE` varchar(30) CHARACTER SET latin1 NOT NULL\r\n"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;");
			
			boolean creado = false;
			for (String sql : sentencias) {
				if (!sql.contains("CREATE DATABASE") && !creado)	{
					direccion += "puntuaciones";
					conexion = DriverManager.getConnection(direccion, usuario, passwd);
					creado = true;
				}else if (!sql.contains("CREATE DATABASE"))	{
					sentencia = conexion.createStatement();
					sentencia.executeUpdate(sql);
				}
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public static void main(String[] args) {
		
		Socket socketcliente = null;
		ServerSocket socketservidor = null;
		Connection conexion = null;
		
		try {
			socketservidor = new ServerSocket(PORT);
			System.out.println("SERVIDOR CONECTADO Y ESCUCHANDO...");
			
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
			generarBBDD(conexion);
			
			Puntuaciones puntuaciones = new Puntuaciones();
			while(true)	{
				socketcliente = socketservidor.accept();
				System.out.println("<Cliente con IP " + socketcliente.getInetAddress() + " conectado>");
				HiloPuntuaciones hilo = new HiloPuntuaciones(socketcliente, puntuaciones, conexion);
				hilo.start();
			}
			
		} catch (IOException | SQLException e) { e.printStackTrace(); }
		
		finally	{
			try {
				socketservidor.close();
				socketcliente.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
}
