package es.unican.is2.billetesAutobus;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

public class GestionBilletes {
	
	private static Map<Localidad, Map<Localidad,Double>> mapaTrayectos;
	private static final int DESCUENTO_EDAD_MOD = 50;
	private static final int DESCUENTO_ANTELACION_SEMANA = 10;
	private static final int DESCUENTO_ANTELACION_MES = 25;
	
	public static void cargaDatosMod() {
		//Inicializo valores
		Map<Localidad, Double> mapaSTN = new EnumMap<Localidad, Double>(Localidad.class);
		mapaSTN.put(Localidad.BCN, 50.0);
		mapaSTN.put(Localidad.MAD, 25.0);
		
		Map<Localidad, Double> mapaBCN = new EnumMap<Localidad, Double>(Localidad.class);
		mapaBCN.put(Localidad.MAD, 30.0);
		mapaBCN.put(Localidad.STN, 50.0);
		Map<Localidad, Double> mapaMAD = new EnumMap<Localidad, Double>(Localidad.class);
		mapaMAD.put(Localidad.BCN, 30.0);
		mapaMAD.put(Localidad.STN, 25.0);
		
		mapaTrayectos = new EnumMap<Localidad, Map<Localidad,Double>>(Localidad.class);
		mapaTrayectos.put(Localidad.STN, mapaSTN);
		mapaTrayectos.put(Localidad.BCN, mapaBCN);
		mapaTrayectos.put(Localidad.MAD, mapaMAD);
	
	}
	
	public static double calculaPrecioBillete (Localidad origen, Localidad destino, int edad, LocalDate fecha) throws DatoIncorrecto {
		if (edad < 0 || edad > 999) {
			throw new DatoIncorrecto("La edad debe estar entre 0 y 999");
		}
		if (fecha.isBefore(LocalDate.now()))
			throw new DatoIncorrecto("La fecha no puede ser anterior a hoy");
		
		if (origen == destino)
			throw new DatoIncorrecto("El origen no puede ser igual al destino");
		
		double tarifaBase = GestionBilletes.mapaTrayectos.get(origen).get(destino);
		int descuentoEdad =0;
		int descuentoAntelacion=0;
		
		if (edad <25 || edad>64){
			descuentoEdad=DESCUENTO_EDAD;
		}
		if (antelacionDias(fecha, 29)) {
			descuentoAntelacion = DESCUENTO_ANTELACION_MES;
		} else if (antelacionDias(fecha, 6)) {
			descuentoAntelacion = DESCUENTO_ANTELACION_SEMANA;
		}
		
		if (descuentoEdad > descuentoAntelacion)
			return tarifaBase - tarifaBase*descuentoEdad/100;
		else
			return tarifaBase - tarifaBase*descuentoAntelacion/100;
		
		
	}

	
	private static boolean antelacionDias(LocalDate fecha, int dias) {
		LocalDate fechaLimite = LocalDate.now().plusDays(dias);
		if (fecha.isAfter(fechaLimite))
			return true;
		return false;
	}
}
