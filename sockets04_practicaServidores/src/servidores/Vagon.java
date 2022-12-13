package servidores;

import java.util.ArrayList;

public class Vagon {

	private ArrayList<Asiento> lista_asientos;
	private ArrayList<Asiento> asientos_libres;

	public Vagon()	{
		lista_asientos = new ArrayList<Asiento>();
		asientos_libres = new ArrayList<Asiento>();
		for(int i = 0; i < 10; i++)	{
			for (int j = 0; j < 4; j++) {
				lista_asientos.add(new Asiento(i, j));
			}
		}
		for (Asiento asiento : lista_asientos) {
			asientos_libres.add(asiento);
		}
		
	}

	public void comprobar_asientos_libres()	{
		asientos_libres.clear();
		for (Asiento asiento : lista_asientos) {
			if (asiento.isLibre()) asientos_libres.add(asiento);
		}
	}

	public ArrayList<Asiento> asientos_libresRecogidos()	{
		comprobar_asientos_libres();
		return asientos_libres;
	}

	public Asiento seleccionar_asiento(int x, int y)	{
		for (Asiento asiento : lista_asientos) {
			if (asiento.getFila() == x && asiento.getColumna() == y) return asiento;
		}
		return null;
	}

	public String reservar(int x, int y) {
		
		Asiento asiento_seleccionado = seleccionar_asiento(x, y);
		comprobar_asientos_libres();
		
		String respuesta = "";
		if (asientos_libres.size() == 0)	{ respuesta = "VAGON COMPLETO"; }
		else	{
			if (!asiento_seleccionado.isLibre())	{
				respuesta = "";
				ArrayList<Asiento> libres = asientos_libresRecogidos();
				for (Asiento asiento : libres) {
					respuesta = respuesta + asiento.toString() + "\n";
				}
			}
			else	{
				for (Asiento asiento : lista_asientos) {
					if (asiento.getFila() == asiento_seleccionado.getFila() && asiento.getColumna() == asiento_seleccionado.getColumna())	{ asiento.setLibre(false); }
				}
				respuesta = "RESERVADO";
			}
		}
		return respuesta;
		
	}
}
