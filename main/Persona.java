package main;

public class Persona {
    private String nombre;
    private String tipoDocumento;
    private int numeroDocumento;
    private String pais;

    public Persona(String nombre, String tipoDocumento, int numeroDocumento, String pais) {
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getPais() {
        return pais;
    }

    public String DetallesMinimos() {
        return "Nombre: " + nombre + " - " + tipoDocumento + ": " + numeroDocumento + "\n";
    }

    public String Detalles() {
        return "Nombre: " + nombre + " - " + tipoDocumento + " " + numeroDocumento + " - " + pais;
    }
}
