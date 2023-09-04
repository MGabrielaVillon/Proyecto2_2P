
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

public class Usuario implements Serializable{
    
    //Atributos
    private static final long serialVersionUID = 8799656478674716638L;
    private String nombre;
    private String apellido;
    private String organizacion;
    private String correo;
    private String clave;
    private ArrayList<Vehiculo> vehiculos;

    //Constructor
    public Usuario(String nombre, String apellido, String organizacion, String correo, String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
        this.vehiculos = new ArrayList<>();
    }
    
    //Getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }
    
    //Metodos
    public static void guardarArchivoUsuarios(Usuario u, String nomfile) {
        File archivo = new File(nomfile);
        if (archivo.exists()) {
            try (myObjectOutputStream out = new myObjectOutputStream(new FileOutputStream(nomfile, true))) {
                out.writeObject(u);
            } catch (FileNotFoundException e) {
            } catch (IOException ie) {
                ie.printStackTrace(System.out);
            }

        } else {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile))) {
                out.writeObject(u);
            } catch (IOException ie) {
                ie.printStackTrace(System.out);
            }

        }
    }
    
    public static void eliminarArchivoTemporal(String nomfile){
        File archivo = new File(nomfile);
        if(archivo.exists()){
            archivo.delete();
        }
    }
    
    public static ArrayList<Usuario> leerArchivoUsuarios(String nomfile){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario u;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomfile))){
            while(true){
                u = (Usuario) in.readObject();
                System.out.println(u.toString());
                usuarios.add(u);
            }
        }catch(EOFException ie){
        } catch (ClassNotFoundException | IOException ex) {
        }
        return usuarios;
    }
    
    
    public static boolean validarCorreo(ArrayList<Usuario> usuarios, String correo) {
        for(Usuario u : usuarios){
            if (u.correo.equals(correo)) {
                    return true;
            }       
        }
        return false;
    }
    
    public static boolean validarClave(ArrayList<Usuario> usuarios, String correo, String clave) {
        for(Usuario u : usuarios){
            if (u.correo.equals(correo) && u.clave.equals(clave)) {
                    return true;
            }       
        }
        return false;
    }
    
    public static Usuario obtenerUsuario(ArrayList<Usuario> usuarios, String correo){
        for(Usuario u : usuarios){
            if (u.correo.equals(correo)) {
                    return u;
            }       
        }
        return null;
    }
    
    public void añadirVehiculo(Vehiculo v){
        vehiculos.add(v);
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellido=" + apellido + ", organizacion=" + organizacion + ", correo=" + correo + ", clave=" + clave + "}";
    }
    
}
