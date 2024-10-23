package main;

public class HabitacionSuite extends Habitacion {
	private static int maxHuespedes = 2;
    private static float precioNoche = 100;
	private boolean jacuzzi;
    public HabitacionSuite(int codigo) {
        super(codigo, maxHuespedes, precioNoche); 
        jacuzzi = true;
    }

    public boolean tieneJacuzzi() {
        return jacuzzi;
    }

    @Override
    public String Detalles() {
        String detalles = "Habitación Suite: " + super.Detalles();
        detalles += jacuzzi ? " - Con jacuzzi" : " - Sin jacuzzi";
        return detalles;
    }
}
