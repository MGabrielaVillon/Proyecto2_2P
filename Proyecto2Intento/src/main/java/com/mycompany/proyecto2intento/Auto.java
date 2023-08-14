/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2intento;

/**
 *
 * @author naomi
 */
public class Auto extends Vehiculo{

    //Atributos
    private int vidrio;
    private int transmision;

    //Constructor
    public Auto(TipoVehiculo tipo, String placa, String marca, String modelo, String tipoMotor, String anio, double recorrido, String color, String tipoCombustible, double precio, int vidrio, int transmision){
        super(tipo, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio);
        this.vidrio = vidrio;
        this.transmision = transmision;
    }
  
    //Getters y setters
    public int getVidrio(){
        return vidrio;
    }
    public void setVidrio(int vidrio){
        this.vidrio = vidrio;
    }

    public int getTransmision(){
        return transmision;
    }
    public void setTransmision(int transmision){
        this.transmision = transmision;
    }
  
}