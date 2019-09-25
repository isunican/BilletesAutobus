package es.unican.is2.billetesAutobus;

public class DatoIncorrecto extends Exception {
	private String mensaje;

	public DatoIncorrecto(String string) {
		mensaje = string;
	}

}
