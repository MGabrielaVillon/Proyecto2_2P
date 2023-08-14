/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2intento;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static com.mycompany.proyecto2intento.Utilitaria.saveFile;

public class Vehiculo {

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
    private ArrayList<Oferta> ofertas = new ArrayList<>();

    //Constructor
    public Vehiculo(TipoVehiculo tipo, String placa, String marca, String modelo, String tipoMotor, String anio, double recorrido, String color, String tipoCombustible, double precio) {
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

    //Métodos
    public static void fileVehiculo(String nomfile, Vehiculo v) {
        String mensaje = v.getTipo() + " - " + v.getPlaca() + " - " + v.getMarca() + " - " + v.getModelo() + " - " + v.getTipoMotor() + " - " + v.getAnio() + " - " + v.getRecorrido() + " - " + v.getColor() + " - " + v.getTipoCombustible() + " - " + v.getPrecio();
        if (v.getTipo() != TipoVehiculo.MOTOCICLETA){
            Auto a = (Auto) v;
            mensaje += " - " + a.getTransmision() + " - " + a.getVidrio();
            if (v.getTipo() == TipoVehiculo.CAMIONETA) {
                Camioneta c = (Camioneta) a;
                mensaje += " - " + c.getTraccion();
            }
        }
        saveFile(nomfile, mensaje);
    }
    
    public static ArrayList<Vehiculo> datosVehiculo() throws FileNotFoundException{
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        if (new File("Vehiculos.txt").exists()) {
            ArrayList<String[]> datos = Utilitaria.readfile("Vehiculos.txt");
            for (String[] dato : datos) {
                if (dato[0].equals("MOTOCICLETA"))
                    vehiculos.add(new Vehiculo(TipoVehiculo.MOTOCICLETA, dato[1], dato[2], dato[3], dato[4], dato[5], Double.parseDouble(dato[6]), dato[7], dato[8], Double.parseDouble(dato[9])));
                else if (dato[0].equals("AUTO"))
                    vehiculos.add(new Auto(TipoVehiculo.AUTO, dato[1], dato[2], dato[3], dato[4], dato[5], Double.parseDouble(dato[6]), dato[7], dato[8], Double.parseDouble(dato[9]), Integer.parseInt(dato[10]), Integer.parseInt(dato[11])));
                else if (dato[0].equals("CAMIONETA"))
                    vehiculos.add(new Camioneta(TipoVehiculo.CAMIONETA, dato[1], dato[2], dato[3], dato[4], dato[5], Double.parseDouble(dato[6]), dato[7], dato[8], Double.parseDouble(dato[9]), Integer.parseInt(dato[10]), Integer.parseInt(dato[11]), Integer.parseInt(dato[12])));
            }
            return vehiculos;
        } else
            return vehiculos;
    }
    
    public static String obtenerTipoVh(){
        Scanner sn = new Scanner(System.in);
        sn.useDelimiter("\n");
        TipoVehiculo[] tvehiculos = TipoVehiculo.values();
        for (int i = 0; i < tvehiculos.length; i++) {
            System.out.println((i + 1) + "." + tvehiculos[i]);
        }
        System.out.print("Ingrese el tipo de vehiculo 1-3: ");
        String num = sn.nextLine();
        boolean datoRegistrado = false;
        do{
            if(num.isEmpty()){
                return num;
            } else if (Integer.parseInt(num) > 3 || Integer.parseInt(num) < 1){
                System.out.println("[Opción fuera de rango. Ingrese otra opción]");
                System.out.print("Ingrese el tipo de vehiculo 1-3: ");
                num = sn.nextLine();
            } else
                datoRegistrado = true;   
        } while(!datoRegistrado);
        return num;
    }
    
    public static void registrarVehiculo() throws FileNotFoundException {
        Scanner sn = new Scanner(System.in);
        System.out.println("\n[A continuación ingrese los datos del vehiculo]\n");
        String num = obtenerTipoVh();
        while(num.isEmpty()){
            System.out.println("\n[Elija una opcion]\n");
            num = obtenerTipoVh();
        }
        int n = Integer.parseInt(num);
        System.out.print("Ingrese la placa: ");
        String pl = sn.next();
        if (!datosVehiculo().isEmpty()) {
            while (Utilitaria.validarPlaca(pl)) {
                System.out.println("[La placa ya se encuentra registrada]");
                System.out.print("Ingrese la placa: ");
                pl = sn.next();
            }
        }
        System.out.print("Ingrese la marca: ");
        String mar = sn.next();
        System.out.print("Ingrese el modelo: ");
        String mod = sn.next();
        System.out.print("Ingrese el tipo motor: ");
        String mot = sn.next();
        System.out.print("Ingrese el año: ");
        String an = sn.next();
        System.out.print("Ingrese el recorrido: ");
        double rec = sn.nextDouble();
        sn.nextLine();
        System.out.print("Ingrese el color: ");
        String col = sn.next();
        System.out.print("Ingrese el tipo de combustible: ");
        String tc = sn.next();
        System.out.print("Ingrese el precio: ");
        double pre = sn.nextDouble();
        Vehiculo v;
        if (n != 1) {
            System.out.print("Ingrese el numero de vidrios: ");
            int vid = sn.nextInt();
            System.out.print("Ingrese la transmision: ");
            int trs = sn.nextInt();
            if (n == 2) {
                v = new Auto(TipoVehiculo.AUTO, pl, mar, mod, mot, an, rec, col, tc, pre, vid, trs);
            } else {
                System.out.print("Ingrese la tracción: ");
                int tra = sn.nextInt();
                v = new Camioneta(TipoVehiculo.CAMIONETA, pl, mar, mod, mot, an, rec, col, tc, pre, vid, trs, tra);
            }
        } else {
            v = new Vehiculo(TipoVehiculo.MOTOCICLETA, pl, mar, mod, mot, an, rec, col, tc, pre);
        }
        fileVehiculo("Vehiculos.txt", v);
        System.out.println("\n[Vehiculo registrado exitosamente!!]");
    }

    public static ArrayList<Vehiculo> filtrarVehiculos() throws FileNotFoundException {
        Scanner sn = new Scanner(System.in);
        sn.useDelimiter("\n");
        ArrayList<Vehiculo> fvehiculos = new ArrayList<>();
        ArrayList<Vehiculo> vehiculos = datosVehiculo();
        ArrayList<String> valores = new ArrayList<>();
        System.out.println("\n[A continuación ingrese los parámetros para buscar un vehiculo]\n");
        String num = obtenerTipoVh();
        String[] parametros = new String[]{"recorrido", "año", "precio"};
        System.out.println("\n[Inicie la búsqueda colocando los criterios a buscar. Puede dejar espacios en blanco]\n");
        for (int i = 0; i < parametros.length; i++) {
            System.out.println("Indique un rango para filtrar por " + parametros[i]);
            System.out.print("1) Valor máximo: ");
            valores.add(sn.nextLine());
            System.out.print("2) Valor mínimo: ");
            valores.add(sn.nextLine());
        }
        
        for (Vehiculo v : vehiculos) {
            if(num.equals("1") && v.tipo.equals(TipoVehiculo.MOTOCICLETA))
                fvehiculos.add(v);
            else if (num.equals("2")  && v.tipo==TipoVehiculo.AUTO)
                fvehiculos.add(v);
            else if (num.equals("3")  && v.tipo==TipoVehiculo.CAMIONETA)
                fvehiculos.add(v);
            else if (num.equals(""))
                fvehiculos = vehiculos;
        }
        
        for(Vehiculo v : fvehiculos){
            for (int i = 0; i < parametros.length; i++) {
                switch (i) {
                    case 0: if (!Utilitaria.validarRango(v.recorrido, valores.get(i * 2), valores.get((i * 2) + 1))) {
                            fvehiculos.remove(v);
                        } break;
                    case 1: if (!Utilitaria.validarRango(Integer.parseInt(v.anio), valores.get(i * 2), valores.get((i * 2) + 1))) {
                            fvehiculos.remove(v);
                        } break;
                    case 2: if (!Utilitaria.validarRango(v.precio, valores.get(i * 2), valores.get((i * 2) + 1))) {
                            fvehiculos.remove(v);
                        } break;
                    default:
                        break;
                }
            }
        }
        return fvehiculos;
    }
    
    public static void presentarVehiculo(Vehiculo v) {
        System.out.println("- " + v.tipo);
        System.out.println("-> Precio: $" + v.precio);
        System.out.println("-> Placa: " + v.placa);
        System.out.println("-> Marca: " + v.marca);
        System.out.println("-> Modelo: " + v.modelo);
        System.out.println("-> Color: " + v.color);
        System.out.println("-> Año: " + v.anio);
        System.out.println("-> Tipo de motor: " + v.tipoMotor);
        System.out.println("-> Tipo de combustible: " + v.tipoCombustible);
        System.out.println("-> Recorrido: " + v.recorrido);
        if (!(v.tipo == TipoVehiculo.MOTOCICLETA)) {
            Auto a = (Auto)v;
            System.out.println("-> Transmision: " + a.getTransmision());
            System.out.println("-> Vidrio: " + a.getVidrio());
            if (v.tipo == TipoVehiculo.CAMIONETA) {
                Camioneta c = (Camioneta)v;
                System.out.println("-> Traccion: " + c.getTraccion());
            }
        }
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
    public void setRecorrido(Double recorrido) {
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
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }
    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

}