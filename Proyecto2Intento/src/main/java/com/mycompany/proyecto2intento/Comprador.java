/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2intento;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Comprador extends Persona{
   
    //Constructor
    public Comprador(String nombre, String apellido, String organizacion, String correo, String clave){
        super(nombre, apellido, organizacion, correo, clave);
    }
    
    //Metodos
    public static ArrayList<Comprador> datosComprador() throws FileNotFoundException{
        ArrayList<Comprador> compradores = new ArrayList<>();
        File file = new File("Compradores.txt");
        if (file.exists()) {
            ArrayList<String[]> datos = Utilitaria.readfile("Compradores.txt");
            for (String[] dato : datos) {
                compradores.add(new Comprador(dato[0], dato[1], dato[2], dato[3], dato[4]));
            }
            return compradores;
        } else
            return compradores;
    }
    
    public static Comprador obtenerComprador(String correo) throws FileNotFoundException{
        for(Comprador c:datosComprador()){
            Persona p = (Persona)c;
            if(p.getCorreo().equals(correo))
                return c;
        }
        return null;
    }
    
    public static void registrarComprador() throws NoSuchAlgorithmException {
        /////validar que el correo no se encuentre en el archivo
        ArrayList<String> datos = Persona.registrarDatos("Compradores.txt");
        Persona.filePersona("Compradores.txt", new Comprador(datos.get(0),datos.get(1),datos.get(2),datos.get(3),datos.get(4)));
        System.out.println("\n[Comprador registrado exitosamente!!]");
    }    
    
}
