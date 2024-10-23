package main;

public class Huesped extends Persona {
    private Reserva reserva;
   
    public Huesped(String nombre, String tipoDocumento, int numeroDocumento, String pais) {
        super(nombre, tipoDocumento, numeroDocumento, pais); 
    }

    public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String Detalles() {
        String detalles = super.Detalles();  
        if (reserva != null) {
            detalles += " - Reserva: " + reserva;
        }
        return detalles;
    }
}