package ec.edu.espol.model;

public class Camioneta extends Vehiculo {

    private String traccion;

    public Camioneta(String traccion, Usuario usuario, TipoVehiculo tipo, String placa, String marca, String modelo, String tipoMotor, int anio, double recorrido, String color, String tipoCombustible, double precio) {
        super(usuario, tipo, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio);
        this.traccion = traccion;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

}
