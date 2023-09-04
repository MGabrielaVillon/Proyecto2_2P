
package ec.edu.espol.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Vehiculo implements Serializable{
    
    //Atributos
    private TipoVehiculo tipo;
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private String anio;
    private double recorrido;
    private String color;
    private String tipoCombustible;
    private double precio;
    private int vidrio;
    private String transmision;
    private String traccion;
    
    private Usuario usuario;
    
    private static final long serialVersionUID = 8799656478674716638L;
    //private ArrayList<Oferta> ofertas = new ArrayList<>();
    
    //Constructor
    public Vehiculo(Usuario usuario, TipoVehiculo tipo, String placa, String marca, String modelo, String tipoMotor, String anio, double recorrido, String color, String tipoCombustible, double precio) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
    }

    public Vehiculo(Usuario usuario, TipoVehiculo tipo, String placa, String marca, String modelo, String tipoMotor, String anio, double recorrido, String color, String tipoCombustible, int vidrio, String transmision, double precio) {
        this.tipo = tipo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.vidrio = vidrio;
        this.transmision = transmision;
        this.usuario = usuario;
    }
    
    public Vehiculo(Usuario usuario, TipoVehiculo tipo, String placa, String marca, String modelo, String tipoMotor, String anio, double recorrido, String color, String tipoCombustible, int vidrio, String transmision, String traccion, double precio) {
        this.tipo = tipo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.vidrio = vidrio;
        this.transmision = transmision;
        this.traccion = traccion;
        this.usuario = usuario;
    }
    
    //Getters y setters
    public TipoVehiculo getTipo() {
        return tipo;
    }
    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }
    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public String getAnio() {
        return anio;
    }
    public void setAnio(String anio) {
        this.anio = anio;
    }

    public double getRecorrido() {
        return recorrido;
    }
    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }
    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    //Metodos
    public static void guardarArchivoVehiculos(Vehiculo v, String nomfile) {
        File archivo = new File(nomfile);
        if (archivo.exists()) {
            try (myObjectOutputStream out = new myObjectOutputStream(new FileOutputStream(nomfile, true))) {
                out.writeObject(v);
            } catch (FileNotFoundException e) {
            } catch (IOException ie) {
                ie.printStackTrace(System.out);
            }

        } else {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile))) {
                out.writeObject(v);
            } catch (IOException ie) {
                ie.printStackTrace(System.out);
            }

        }
    }
    
    public static ArrayList<Vehiculo> leerArchivoVehiculos(String nomfile){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        Vehiculo v;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomfile))){
            while(true){
                v = (Vehiculo)in.readObject();
                System.out.println(v.toString());
                vehiculos.add(v);
            }
        }catch(EOFException ie){
        } catch (ClassNotFoundException | IOException ex) {
        }
        return vehiculos;
    }
    
    public static ArrayList<String> tipoVehiculos(){
        ArrayList<String> tipos = new ArrayList<>();
        for (TipoVehiculo tvh : TipoVehiculo.values()){
            tipos.add(tvh.toString());
        }
        return tipos;
    }
    
    public static ArrayList<Vehiculo> filtrarTipoVehiculo(ArrayList<Vehiculo> vehiculos, String tvh){
        ArrayList<Vehiculo> retorno = new ArrayList<>();
        for (Vehiculo v : vehiculos){
            if(v.tipo.toString().equals(tvh))
                retorno.add(v);
        }
        return retorno;
    }
    
    public static boolean validarPlaca(ArrayList<Vehiculo> vehiculos, String pl) {
        for(Vehiculo v : vehiculos){
            if (v.placa.equals(pl)) {
                    return true;
            }       
        }
        return false;
    }
    
    public static String presentarVehiculo(Vehiculo vh) {
        return (vh.tipo +
                "\n\n-> Precio: $" + vh.precio +
                "\n-> Placa: " + vh.placa +
                "\n-> Marca: " + vh.marca +
                "\n-> Modelo: " + vh.modelo +
                "\n-> Color: " + vh.color +
                "\n-> Año: " + vh.anio + 
                "\n-> Tipo de motor: " + vh.tipoMotor +
                "\n-> Tipo de combustible: " + vh.tipoCombustible +
                "\n-> Recorrido: " + vh.recorrido);
    }    
    
    @Override
    public String toString() {
        return "Vehiculo{" + "tipoVehiculo=" + tipo + ", placa=" + placa + "}";
    }
}
