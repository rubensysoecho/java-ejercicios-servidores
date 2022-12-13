package cliente;

public class Edificios {

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			HiloEdificio hiloDatos = new HiloEdificio("Edificio "+i);
			hiloDatos.start();
		}
	}
}
