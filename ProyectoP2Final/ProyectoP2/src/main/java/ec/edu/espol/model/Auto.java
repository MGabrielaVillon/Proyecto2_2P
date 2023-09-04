package ec.edu.espol.model;

public class Auto extends Vehiculo {

    private int vidrio;
    private String transmision;

    public Auto(int vidrio, String transmision, Usuario usuario, TipoVehiculo tipo, String placa, String marca, String modelo, String tipoMotor, int anio, double recorrido, String color, String tipoCombustible, double precio) {
        super(usuario, tipo, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio);
        this.vidrio = vidrio;
        this.transmision = transmision;
        
        
    }

    public int getVidrio() {
        return vidrio;
    }

    public void setVidrio(int vidrio) {
        this.vidrio = vidrio;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

}
