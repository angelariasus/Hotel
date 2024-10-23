package main;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
	
    private Hotel hotel;
    private String adminUsuario = "admin";  
    private String adminPassword = "1234"; 
    
    Scanner scanner = new Scanner(System.in);
    
    public Main(int pisos, int numeros) {
        hotel = new Hotel(pisos, numeros);
    }

    public void menuPrincipal() {
        int opcion;
        do {
        	System.out.println("====================================");
            System.out.println("            ¡BIENVENIDO!");
            System.out.println("====================================");
            System.out.println("1. Ver habitaciones disponibles");
            System.out.println("2. Reservar habitación");
            System.out.println("3. Registrar salida");
            System.out.println("4. Generar boleta de pago");
            System.out.println("5. Menú para administradores");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:
                    hotel.VerHabitacionesDisponibles();
                    break;
                case 2:
                    realizarReserva();
                    break;
                case 3:
                    registrarSalida();
                    break;
                case 4:
                    generarBoletaPago();
                    break;
                case 5:
                	if (validarAdministrador()) {
                        menuAdministradores();
                    }
                    break;
                case 0:
                    System.out.println("¡Gracias por utilizar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void realizarReserva() {
        System.out.print("Ingrese el número de huéspedes: ");
        int numHuespedes = scanner.nextInt();
        scanner.nextLine(); 

        Huesped[] huespedes = new Huesped[numHuespedes];
        for (int i = 0; i < numHuespedes; i++) {
            System.out.print("Ingrese el nombre del huésped " + (i + 1) + ": ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el tipo de documento (ej. DNI): ");
            String tipoDocumento = scanner.nextLine();
            System.out.print("Ingrese el número de documento: ");
            int numeroDocumento = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese el país: ");
            String pais = scanner.nextLine();

            huespedes[i] = new Huesped(nombre, tipoDocumento, numeroDocumento, pais);
        }

        System.out.print("Ingrese el piso de la habitación: ");
        int piso = scanner.nextInt();
        System.out.print("Ingrese el número de la habitación: ");
        int numero = scanner.nextInt();
        System.out.print("Ingrese la fecha de inicio de la reserva dd/mm/aa: ");
        int dia = scanner.nextInt();
        int mes = scanner.nextInt();
        int anio = scanner.nextInt();
        scanner.nextLine();
        LocalDate fechaInicio = LocalDate.of(anio, mes, dia);
        System.out.println("Ingrese la cantidad de días: ");
        int dias = scanner.nextInt();
        
        if (hotel.CrearReserva(huespedes, piso, numero, fechaInicio, dias)) {
            System.out.println("Reserva realizada con éxito.");
        } else {
            System.out.println("No se pudo realizar la reserva. Verifique la disponibilidad.");
        }
    }

    private void registrarSalida() {
        System.out.print("Ingrese el piso de la habitación: ");
        int piso = scanner.nextInt();
        System.out.print("Ingrese el número de la habitación: ");
        int numero = scanner.nextInt();

        Habitacion habitacion = hotel.getHabitaciones()[piso][numero];
        
        if (habitacion != null && !habitacion.Disponible()) {
            habitacion.Deshabilitar(); 
            System.out.println("Salida registrada con éxito.");
        } else {
            System.out.println("No se pudo registrar la salida. La habitación no está ocupada.");
        }
    }

    
    
    private boolean validarAdministrador() {
        System.out.print("Ingrese el usuario de administrador: ");
        String usuarioIngresado = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String passwordIngresado = scanner.nextLine();

        if (usuarioIngresado.equals(adminUsuario) && passwordIngresado.equals(adminPassword)) {
            System.out.println("Acceso concedido.");
            return true;
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
            return false;
        }
    }
    
    private void menuAdministradores() {
        int opcionAdmin;
        do {
        	System.out.println("====================================");
            System.out.println("       Menú de administración");
            System.out.println("====================================");
            System.out.println("1. Añadir habitación");
            System.out.println("2. Modificar habitación");
            System.out.println("3. Colocar habitación fuera de servicio");
            System.out.println("4. Editar usuarios y contraseñas");
            System.out.println("5. Mostrar huéspedes registrados");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            opcionAdmin = scanner.nextInt();
            scanner.nextLine();  

            switch (opcionAdmin) {
                case 1:
                    añadirHabitacion();
                    break;
                case 2:
                	menuModificarHabitacion();
                    break;
                case 3:
                    colocarHabitacionFueraDeServicio();
                    break;
                case 4:
                    editarUsuarioContraseña();
                    break;
                case 5:
                	mostrarHuespedesRegistrados();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de administración.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionAdmin != 0);
    }
    
    private void menuModificarHabitacion() {
        System.out.println("Ingrese el piso de la habitación:");
        int piso = scanner.nextInt();
        System.out.println("Ingrese el número de la habitación:");
        int numero = scanner.nextInt();
        System.out.println("Seleccione el nuevo tipo de habitación:");
        System.out.println("1. Habitación Simple");
        System.out.println("2. Habitación Suite");
        int nuevoTipo = scanner.nextInt();

        if (hotel.modificarHabitacion(piso, numero, nuevoTipo)) {
            System.out.println("Habitación modificada con éxito.");
        } else {
            System.out.println("Error al modificar la habitación.");
        }
    }

    
    private void mostrarHuespedesRegistrados() {
        System.out.println("Huéspedes Registrados:");
        for (int i = 0; i < hotel.getPisos(); i++) {
            for (int j = 0; j < hotel.getNumeros(); j++) {
                Habitacion habitacion = hotel.getHabitaciones()[i][j];
                if (habitacion != null && !habitacion.Disponible()) {
                    String tipoHabitacion = (habitacion instanceof HabitacionSuite) ? "Suite" : "Simple";
                    System.out.println("Habitación Código: " + habitacion.getCodigo() + 
                                       " - Tipo: " + tipoHabitacion + 
                                       " - Precio por Noche: " + habitacion.getPrecioNoche());
                    Reserva reserva = habitacion.getReserva();
                    if (reserva != null) {
                        String infoHuespedes = reserva.getInfoHuespedes();
                        System.out.println("Huéspedes: ");
                        System.out.println(infoHuespedes);
                        System.out.println("====================================");
                    }
                    System.out.println("-------------------------------------");
                }
            }
        }
    }
    
    private void añadirHabitacion() {
        System.out.print("Ingrese el piso de la nueva habitación: ");
        int piso = scanner.nextInt();
        System.out.print("Ingrese el número de la nueva habitación: ");
        int numero = scanner.nextInt();
        
        if (hotel.CrearHabitacion(piso, numero)) {
            System.out.println("Habitación añadida con éxito.");
        } else {
            System.out.println("No se pudo añadir la habitación.");
        }
    }
    
    private void colocarHabitacionFueraDeServicio() {
        System.out.print("Ingrese el piso de la habitación: ");
        int piso = scanner.nextInt();
        System.out.print("Ingrese el número de la habitación: ");
        int numero = scanner.nextInt();

        Habitacion habitacion = hotel.getHabitaciones()[piso][numero];
        if (habitacion != null) {
            habitacion.Deshabilitar();
            System.out.println("Habitación colocada fuera de servicio.");
        } else {
            System.out.println("No se encontró la habitación.");
        }
    }
    
    private void modificarHabitacion() {
        System.out.print("Ingrese el piso de la habitación a modificar: ");
        int piso = scanner.nextInt();
        System.out.print("Ingrese el número de la habitación a modificar: ");
        int numero = scanner.nextInt();

        Habitacion habitacion = hotel.getHabitaciones()[piso][numero];
        if (habitacion != null) {
            System.out.println("Estado actual de la habitación:");
            System.out.println(habitacion.Detalles());
            
            System.out.print("¿Desea cambiar la disponibilidad de la habitación? (1 = Sí, 0 = No): ");
            int cambiarDisponibilidad = scanner.nextInt();
            if (cambiarDisponibilidad == 1) {
                if (habitacion.Disponible()) {
                    habitacion.Deshabilitar();
                    System.out.println("La habitación ahora está fuera de servicio.");
                } else {
                    System.out.println("Habitación fuera de servicio. Habilitándola de nuevo...");
                }
            }
        } else {
            System.out.println("La habitación no existe.");
        }
    }
    
    private void generarBoletaPago() {
        System.out.print("Ingrese el piso de la habitación: ");
        int piso = scanner.nextInt();
        System.out.print("Ingrese el número de la habitación: ");
        int numero = scanner.nextInt();

        // Retrieve the room from the hotel
        Habitacion habitacion = hotel.getHabitaciones()[piso][numero];
        
        // Check if the room is occupied
        if (habitacion != null && !habitacion.Disponible()) {
            // Assuming the reservation is accessible through the room
            Reserva reserva = habitacion.getReserva(); // Ensure this method exists in the Habitacion class

            if (reserva != null) {
                float monto = habitacion.getPrecioNoche() * reserva.getDias();
                System.out.println("====================================");
                System.out.println("           BOLETA DE PAGO");
                System.out.println("====================================");
                System.out.println("Habitación: " + habitacion.getCodigo());
                System.out.println("Fecha de inicio: " + reserva.getFechaInicio());
                System.out.println("Fecha de fin: " + reserva.getFechaFin());
                System.out.println("Días de estadía: " + reserva.getDias());
                System.out.println("Total a pagar: " + monto);
                String infoHuespedes = reserva.getInfoHuespedes();
                System.out.println("Huéspedes: ");
                System.out.println(infoHuespedes);
                System.out.println("====================================");
            } else {
                System.out.println("No hay reserva asociada a esta habitación.");
            }
        } else {
            System.out.println("No se puede generar la boleta. La habitación no está ocupada o no existe.");
        }
    }
    
    private void editarUsuarioContraseña() {
        System.out.print("Ingrese el nuevo nombre de usuario: ");
        adminUsuario = scanner.nextLine();
        System.out.print("Ingrese la nueva contraseña: ");
        adminPassword = scanner.nextLine();
        System.out.println("Usuario y contraseña actualizados.");
    }
   
    public static void main(String[] args) {
        Main sistema = new Main(5, 4); 
        sistema.menuPrincipal();
        
    }
}
