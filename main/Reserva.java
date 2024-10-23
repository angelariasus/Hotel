package main;

import java.time.*;
import java.time.format.*;

public class Reserva {
    private int codigoHabitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int dias;
    private Huesped[] huespedes;
    private LocalDateTime fechaRegistro;
    
    public Reserva(int codigoHabitacion, LocalDate fechaInicio, LocalDate fechaFin, int dias, Huesped[] huespedes) {
        this.codigoHabitacion = codigoHabitacion;   
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.dias = dias;
        this.huespedes = huespedes;
        fechaRegistro = LocalDateTime.now();
    }

    public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public void Extender(int dias) {
        this.fechaFin = fechaFin.plusDays(dias);
    }

	public int getCodigoHabitacion() {
		return codigoHabitacion;
	}

	public void setCodigoHabitacion(int codigoHabitacion) {
		this.codigoHabitacion = codigoHabitacion;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Huesped[] getHuespedes() {
		return huespedes;
	}

	public void setHuespedes(Huesped[] huespedes) {
		this.huespedes = huespedes;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getInfoHuespedes() {
	    String info = "";
	    for (int i = 0; i < huespedes.length; i++) {
	        if (huespedes[i] != null) {
	            info += "- Nombre: " + huespedes[i].getNombre() + ", DNI: " + huespedes[i].getNumeroDocumento();
	        }
	    }
	    return info;
	}
	
	@Override
    public String toString() {
        return "Código Habitación: " + codigoHabitacion + " - Desde: " + fechaInicio + " Hasta: " + fechaFin;
    }
}