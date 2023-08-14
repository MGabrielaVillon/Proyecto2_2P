/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2intento;

public class Camioneta extends Auto {

    //Atributo
    private int traccion;

    //Constructor
    public Camioneta(TipoVehiculo tipo, String placa, String marca, String modelo, String tipoMotor, String anio, double recorrido, String color, String tipoCombustible, double precio, int vidrio, int transmision, int traccion) {
        super(tipo, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio, vidrio, transmision);
        this.traccion = traccion;
    }

    //Getters y setters
    public int getTraccion() {
        return traccion;
    }
    public void setTraccion(int traccion) {
        this.traccion = traccion;
    }

}
