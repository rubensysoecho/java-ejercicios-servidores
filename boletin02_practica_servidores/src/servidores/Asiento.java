package servidores;

public class Asiento {

	private int fila;
	private int columna;
	private boolean libre = true;
	
	public Asiento(int x, int y)	{
		fila = x;
		columna = y;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public boolean isLibre() {
		return libre;
	}

	public void setLibre(boolean libre) {
		this.libre = libre;
	}

	@Override
	public String toString() {
		return "Asiento [" + "FILA " + fila + 1 + " | " + "COLUMNA " + columna + 1 + "]";
	}
		
	
}
