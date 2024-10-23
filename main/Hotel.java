package main;

import java.time.LocalDate;
import java.util.Scanner;

public class Hotel {
    private int pisos;
    private int numeros;
    private Habitacion[][] habitaciones;
    private Reserva[] reservas;
    
    Scanner entrada = new Scanner(System.in);
    
    public Hotel(int pisos, int numeros) {
        this.pisos = pisos;
        this.numeros = numeros;
        this.habitaciones = new Habitacion[pisos][numeros];
        this.reservas = new Reserva[100]; 
    }

    public boolean CrearHabitacion(int piso, int numero) {
        if (piso >= pisos || numero >= numeros || piso < 0 || numero < 0) {
            return false;
        }
        if (habitaciones[piso][numero] == null) {
            
            System.out.println("Seleccione el tipo de habitación:");
            System.out.println("1. Habitación Simple");
            System.out.println("2. Habitación Suite");
            int tipoHabitacion = entrada.nextInt();

            int codigo = piso * 100 + numero;
            switch (tipoHabitacion) {
                case 1:
                    habitaciones[piso][numero] = new HabitacionSimple(codigo);
                    break;
                case 2:
                    habitaciones[piso][numero] = new HabitacionSuite(codigo);
                    break;
                default:
                    System.out.println("Tipo de habitación no válido.");
                    return false;
            }
            return true;
        }
        return false;
    }

    public void VerHabitacionesDisponibles() {
        System.out.print("-------------------------------\n");
        System.out.println("Habitaciones Disponibles:");
        System.out.print("-------------------------------\n");
        for (int i = 0; i < pisos; i++) {
            int c = 0;
            for (int j = 0; j < numeros; j++) {
                if (habitaciones[i][j] != null) {
                    c++;
                    System.out.println(habitaciones[i][j].Detalles());
                }
            }
            if (c == 0) {
                System.out.println("No hay habitaciones en el piso " + i);
            }
        }
    }

    public boolean CrearReserva(Huesped[] huespedes, int piso, int numero, LocalDate fechaInicio, int dias) {
        // Verificación de límites
        if (piso >= pisos || numero >= numeros || piso < 0 || numero < 0) {
            return false; 
        }
        
        // Obtención de la habitación
        Habitacion habitacion = habitaciones[piso][numero];
        
        // Verificación de disponibilidad
        if (habitacion != null && habitacion.Disponible()) {
            int codigo = habitacion.getCodigo(); // Obtén el código de la habitación
            LocalDate fechaFin = fechaInicio.plusDays(dias);
            
            // Creación de la reserva
            Reserva reserva = new Reserva(codigo, fechaInicio, fechaFin, dias, huespedes);
            
            // Asignar reserva a cada huésped y añadirlo a la habitación
            for (Huesped huesped : huespedes) {
                huesped.setReserva(reserva); // Asegúrate de que el método setReserva esté en la clase Huesped
                habitacion.AgregarHuesped(huesped); // Asegúrate de que este método esté implementado en la clase Habitacion
            }
            
            // Establecer la reserva en la habitación
            habitacion.setReserva(reserva); // Aquí se asigna la reserva a la habitación

            // Cambia el estado de la habitación
            habitacion.Deshabilitar(); 
            return true;
        }
        
        return false;
    }

    public boolean modificarHabitacion(int piso, int numero, int nuevoTipo) {
        // Verificar que la habitación a modificar existe
        if (piso >= pisos || numero >= numeros || piso < 0 || numero < 0) {
            return false; // Fuera de límites
        }

        Habitacion habitacionActual = habitaciones[piso][numero];
        if (habitacionActual == null || habitacionActual.Disponible()) {
            return false; // No hay habitación o está disponible
        }

        // Crear una nueva habitación del tipo especificado
        int codigo = habitacionActual.getCodigo(); // Mantener el mismo código
        Habitacion nuevaHabitacion = null;

        switch (nuevoTipo) {
            case 1: // Habitación Simple
                nuevaHabitacion = new HabitacionSimple(codigo);
                break;
            case 2: // Habitación Suite
                nuevaHabitacion = new HabitacionSuite(codigo);
                break;
            default:
                System.out.println("Tipo de habitación no válido.");
                return false;
        }

        // Copiar los huéspedes a la nueva habitación (si es necesario)
        for (int i = 0; i < habitacionActual.getHuespedes().length; i++) {
            if (habitacionActual.getHuespedes()[i] != null) {
                nuevaHabitacion.AgregarHuesped(habitacionActual.getHuespedes()[i]);
            }
        }

        // Reemplazar la habitación actual con la nueva
        habitaciones[piso][numero] = nuevaHabitacion;

        // Deshabilitar la habitación actual
        habitacionActual.Deshabilitar();

        return true;
    }
    
	public int getPisos() {
		return pisos;
	}

	public void setPisos(int pisos) {
		this.pisos = pisos;
	}

	public int getNumeros() {
		return numeros;
	}

	public void setNumeros(int numeros) {
		this.numeros = numeros;
	}

	public Habitacion[][] getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Habitacion[][] habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Reserva[] getReservas() {
		return reservas;
	}

	public void setReservas(Reserva[] reservas) {
		this.reservas = reservas;
	}

	public Scanner getEntrada() {
		return entrada;
	}

    
    
}