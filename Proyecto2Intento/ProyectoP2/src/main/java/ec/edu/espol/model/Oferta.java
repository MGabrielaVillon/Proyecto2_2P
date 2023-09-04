package ec.edu.espol.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Oferta implements Serializable{
    
    //Atributos
    private Vehiculo vehiculo;
    private Usuario usuario;
    private double valor;
    private String correoContacto;

    //Constructor
    public Oferta(Vehiculo vehiculo, Usuario usuario, double valor, String correoContacto) {
        this.vehiculo = vehiculo;
        this.usuario = usuario;
        this.valor = valor;
        this.correoContacto = correoContacto;
    }
    
    public void guardarArchivoOfertas(String nomfile) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nomfile,true))){
            bw.write(this.vehiculo.getTipo() + " - " + this.vehiculo.getPlaca() + " - " + this.usuario.getCorreo() + " - " + this.vehiculo.getPlaca() + "\n");
            System.out.println(this.toString());
        }catch(IOException ie){  
        }
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }
    
    public String toString() {
        return "Oferta{" + "usuario=" + usuario.getCorreo() + "correo=" + correoContacto + ", valor=" + valor + ", vehiculo=" + vehiculo.getPlaca() + "}";
    }
    
    
}
