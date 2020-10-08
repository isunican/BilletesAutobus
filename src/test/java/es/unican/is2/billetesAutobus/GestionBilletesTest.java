package es.unican.is2.billetesAutobus;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class GestionBilletesTest {
	
	
	@Test
	public void calculaPrecioBilleteTest() {
		
		GestionBilletes.cargaDatos();
		
		Double valor;
		// Casos v�lidos
		try {
			valor = GestionBilletes.calculaPrecioBillete(Localidad.STN, Localidad.BCN, 0, LocalDate.now());
			assertTrue(valor.toString(), valor == 20.0);  // Es 25
		} catch (DatoIncorrecto e) {
			fail("Caso 1: No deber�a lanzar la excepci�n");
		}
		
		try {
			valor = GestionBilletes.calculaPrecioBillete(Localidad.MAD, Localidad.BCN, 14, LocalDate.now().plusDays(3));
			assertTrue(valor.toString(), valor == 15.0);
		} catch (DatoIncorrecto e) {
			fail("Caso 2: No deber�a lanzar la excepci�n");
		}
		
		try {
			valor = GestionBilletes.calculaPrecioBillete(Localidad.BCN, Localidad.MAD, 24, LocalDate.now().plusDays(6));
			assertTrue(valor.toString(), valor == 15.0);
		} catch (DatoIncorrecto e) {
			fail("Caso 3: No deber�a lanzar la excepci�n");
		}
		try {
			valor = GestionBilletes.calculaPrecioBillete(Localidad.STN, Localidad.MAD, 25, LocalDate.now().plusDays(7));
			assertTrue(valor.toString(), valor == 22.5);
		} catch (DatoIncorrecto e) {
			fail("Caso 4: No deber�a lanzar la excepci�n");
		}
		try {
			valor = GestionBilletes.calculaPrecioBillete(Localidad.BCN, Localidad.MAD, 37, LocalDate.now().plusDays(15));
			assertTrue(valor.toString(), valor == 27.0);
		} catch (DatoIncorrecto e) {
			fail("Caso 5: No deber�a lanzar la excepci�n");
		}
		
		try {
			valor = GestionBilletes.calculaPrecioBillete(Localidad.MAD, Localidad.STN, 64, LocalDate.now().plusDays(29));
			assertTrue(valor.toString(), valor == 22.5);
		} catch (DatoIncorrecto e) {
			fail("Caso 6: No deber�a lanzar la excepci�n");
		}
		try {
			valor = GestionBilletes.calculaPrecioBillete(Localidad.MAD, Localidad.BCN, 65, LocalDate.now().plusDays(30));
			assertTrue(valor.toString(), valor == 15.0);
		} catch (DatoIncorrecto e) {
			fail("Caso 7: No deber�a lanzar la excepci�n");
		}
		try {
			valor = GestionBilletes.calculaPrecioBillete(Localidad.MAD, Localidad.BCN, 80, LocalDate.now().plusDays(60));
			assertTrue(valor.toString(), valor == 15.0);
		} catch (DatoIncorrecto e) {
			fail("Caso 8: No deber�a lanzar la excepci�n");
		}
		try {
			valor = GestionBilletes.calculaPrecioBillete(Localidad.BCN, Localidad.MAD, 999, LocalDate.now());
			assertTrue(valor.toString(), valor ==  15.0);
		} catch (DatoIncorrecto e) {
			fail("Caso 9: No deber�a lanzar la excepci�n");
		}
		
		
		// Casos no v�lidos
		try {
			assertTrue(GestionBilletes.calculaPrecioBillete(Localidad.STN, Localidad.STN, 14, LocalDate.now().plusDays(3)) == 27.0);
			fail("Deber�a lanzar la excepci�n por ser iguales origen y destino");
		} catch (DatoIncorrecto e) {
			
		}
		
		try {
			assertTrue(GestionBilletes.calculaPrecioBillete(Localidad.STN, Localidad.MAD, 25, LocalDate.now().minusDays(1)) == 27.0);
			fail("Deber�a lanzar la excepci�n por fecha err�nea");
		} catch (DatoIncorrecto e) {
			
		}
		
		try {
			assertTrue(GestionBilletes.calculaPrecioBillete(Localidad.BCN, Localidad.MAD, -15, LocalDate.now().plusDays(30)) == 27.0);
			fail("Deber�a lanzar la excepci�n por edad");
		} catch (DatoIncorrecto e) {
			
		}
		
		try {
			assertTrue(GestionBilletes.calculaPrecioBillete(Localidad.MAD, Localidad.STN, 1234, LocalDate.now().plusDays(15)) == 27.0);
			fail("Deber�a lanzar la excepci�n por edad");
		} catch (DatoIncorrecto e) {
			
		}
		
	}

}
