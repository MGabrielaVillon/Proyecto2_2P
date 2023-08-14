/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2intento;
import java.util.ArrayList;
import java.security.NoSuchAlgorithmException;

public class Vendedor extends Persona{

    //Constructor
    public Vendedor(String nombre, String apellido, String organizacion, String correo, String clave){        
        super(nombre, apellido, organizacion, correo, clave);
    }

    //Metodos
    public static void registrarVendedor() throws NoSuchAlgorithmException {
        ArrayList<String> datos = Persona.registrarDatos("Vendedores.txt");
        Persona.filePersona("Vendedores.txt", new Vendedor(datos.get(0),datos.get(1),datos.get(2),datos.get(3),datos.get(4)));
        System.out.println("\n[Vendedor registrado exitosamente!!]");
    }

}
