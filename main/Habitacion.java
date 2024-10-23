package main;

public class Habitacion {
	private int codigo;
    private int maxHuespedes;
    private float precioNoche;
    private int huespedesActuales;
    private boolean disponible;
    private Huesped[] huespedes;
    private Reserva reserva;

    public Habitacion(int codigo, int maxHuespedes, float precioNoche) {
        this.codigo = codigo;
        this.maxHuespedes = maxHuespedes;
        this.precioNoche = precioNoche;
        disponible = true;
        huespedes = new Huesped[maxHuespedes];
    }
    
    public String Detalles() {
        String detalles = "Habitación " + codigo + " - ";
        if (!disponible) {
            detalles += "Ocupada";
        } else {
            detalles += "Disponible\nPrecio por noche: " + precioNoche + "\n";
        }
        return detalles;
    }

    private int BuscarHuesped(Huesped huesped) {
        for (int i = 0; i < huespedesActuales; i++) {
            if (huespedes[i].equals(huesped)) {
                return i;
            }
        }
        return -1;
    }

    public boolean AgregarHuesped(Huesped huesped) {
        if (huespedesActuales < maxHuespedes) {
            huespedes[huespedesActuales] = huesped;
            huespedesActuales++; // Aumentar el contador de huéspedes actuales
            return true;
        }
        return false; // No se pudo agregar el huésped
    }


    public boolean EliminarHuesped(Huesped huesped) {
        int pos = BuscarHuesped(huesped);
        if (pos != -1) {
            for (int i = pos; i < huespedesActuales - 1; i++) {
                huespedes[i] = huespedes[i + 1];
            }
            huespedes[huespedesActuales - 1] = null; 
            huespedesActuales--;
            return true;
        }
        return false;
    }

    public boolean Disponible() {
        return disponible;
    }

    public void Deshabilitar() {
        for (int i = 0; i < huespedesActuales; i++) {
            huespedes[i].setReserva(null);
            huespedes[i] = null;
        }
        huespedesActuales = 0;
        this.disponible = false;
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getMaxHuespedes() {
		return maxHuespedes;
	}

	public void setMaxHuespedes(int maxHuespedes) {
		this.maxHuespedes = maxHuespedes;
	}

	public float getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(float precioNoche) {
		this.precioNoche = precioNoche;
	}

	public int getHuespedesActuales() {
		return huespedesActuales;
	}

	public void setHuespedesActuales(int huespedesActuales) {
		this.huespedesActuales = huespedesActuales;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Huesped[] getHuespedes() {
		return huespedes;
	}

	public void setHuespedes(Huesped[] huespedes) {
		this.huespedes = huespedes;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
}