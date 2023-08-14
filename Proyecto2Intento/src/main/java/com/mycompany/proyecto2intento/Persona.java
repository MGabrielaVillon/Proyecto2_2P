/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2intento;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import static com.mycompany.proyecto2intento.Utilitaria.getSHA;
import static com.mycompany.proyecto2intento.Utilitaria.saveFile;
import static com.mycompany.proyecto2intento.Utilitaria.toHexString;

public class Persona {
    
    //Atributos
    private String nombre;
    private String apellido;
    private String organizacion;
    private String correo;
    private String clave;
    private static ArrayList<Persona> personas = new ArrayList<>();

    //Constructor
    public Persona(String nombre, String apellido, String organizacion, String correo, String clave){
        this.nombre = nombre;
        this.apellido = apellido;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
    }
    
    //Metodos
    public static void filePersona(String nomfile, Persona p) throws NoSuchAlgorithmException {
        String hashcode = toHexString(getSHA(p.getClave()));
        String mensaje = p.getNombre() + " - " + p.getApellido() + " - " + p.getOrganizacion() + " - " + p.getCorreo() + " - " + hashcode;
        saveFile(nomfile, mensaje);
    }
    
    public static ArrayList<String> registrarDatos(String nomfile) throws NoSuchAlgorithmException {
        Scanner sn = new Scanner(System.in);
        ArrayList<String> datos = new ArrayList<>();
        System.out.print("Ingrese el nombre: ");
        String nom = sn.next();
        datos.add(nom);
        System.out.print("Ingrese el apellido: ");
        String ap = sn.next();
        datos.add(ap);
        System.out.print("Ingrese la organizacion: ");
        String org = sn.next();
        datos.add(org);
        System.out.print("Ingrese el correo: ");
        String cor = sn.next();
        while (Utilitaria.validarCorreo(nomfile, cor)) {
            System.out.println("[El correo ya se encuentra registrado]");
            System.out.print("Ingrese el correo: ");
            cor = sn.next();
        }
        datos.add(cor);
        System.out.print("Ingrese la clave: ");
        String clv = sn.next();
        datos.add(clv);
        return datos;
    }

    //Getters y setters
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return apellido;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getOrganizacion(){
        return organizacion;
    }
    public void setOrganizacion(String organizacion){
        this.organizacion = organizacion;
    }

    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getClave(){
        return clave;
    }
    public void setClave(String clave){
        this.clave = clave;
    }
    
}
