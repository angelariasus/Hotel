package main;

public class HabitacionSimple extends Habitacion {
	private static int maxHuespedes = 2;
    private static float precioNoche = 100;
	public HabitacionSimple(int codigo) {
        super(codigo, maxHuespedes, precioNoche); 
    }

    @Override
    public String Detalles() {
        return "Habitación Simple: " + super.Detalles();
    }
}
