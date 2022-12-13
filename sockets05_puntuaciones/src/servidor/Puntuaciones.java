package servidor;

import java.util.ArrayList;

public class Puntuaciones {

	private ArrayList<Integer> listaPuntos;
	private ArrayList<String> listaNombres;
	
	public Puntuaciones()	{
		listaPuntos = new ArrayList<Integer>();
		listaNombres = new ArrayList<String>();
	}

	public ArrayList<Integer> getListaPuntos() {
		return listaPuntos;
	}

	public ArrayList<String> getListaNombres() {
		return listaNombres;
	}
	
	public synchronized void anhadir_puntuacion(int puntos, String nombre)	{
		listaPuntos.add(puntos);
		listaNombres.add(nombre);
	}
	
	public String ver_puntuaciones()	{
		System.out.println(listaPuntos.size());
		System.out.println(listaNombres.size());
		String respuesta = "";
		for (int i = 0; i < listaPuntos.size(); i++) {
			respuesta = respuesta + listaPuntos.get(i) + " " + listaNombres.get(i) + "\n";
		}
		return respuesta;
		
	}
}
