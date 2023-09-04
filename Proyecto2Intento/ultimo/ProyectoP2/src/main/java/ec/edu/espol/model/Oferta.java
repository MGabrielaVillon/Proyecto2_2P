/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.PasswordAuthentication;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


//import javax.mail.*;
//import javax.mail.internet.*;
//import java.util.Properties;

public class Oferta implements Serializable{

    
    //Atributos
    private Vehiculo vehiculo;
    private Usuario comprador;
    private double valor;
    private static ArrayList<Oferta> ofertas= new ArrayList<>();


    
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public static ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public static void setOfertas(ArrayList<Oferta> ofertas) {
        Oferta.ofertas = ofertas;
    }

    
    //Constructor
    public Oferta(Vehiculo vehiculo, Usuario comprador, double valor) {
        this.vehiculo = vehiculo;
        this.comprador = comprador;
        this.valor = valor;
    }

   
    
    ////USADO
    public static ArrayList<String> filtradorOfertas(String placa) {
        ArrayList<String> filtro = new ArrayList<>();
        String rutaArchivo = "Ofertas.txt";
        File archivo = new File(rutaArchivo);
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separacion = linea.split(" - ");
                if (placa.equals(separacion[1])) {
                    filtro.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filtro;
    }


}

