/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2intento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Oferta {

    
    //Atributos
    private Vehiculo vehiculo;
    private Comprador comprador;
    private double valor;
    private static ArrayList<Oferta> ofertas= new ArrayList<>();


    
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
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
    public Oferta(Vehiculo vehiculo, Comprador comprador, double valor) {
        this.vehiculo = vehiculo;
        this.comprador = comprador;
        this.valor = valor;
    }

    //Métodos
    public static void registrarOferta(Vehiculo v, String correo) throws FileNotFoundException {
        Scanner sn = new Scanner(System.in);
        double oferta;
        String modificar = "S";
        for (Oferta o : ofertas) {
            if (o.vehiculo.getPlaca().equals(v.getPlaca()) && o.comprador.getCorreo().equals(correo)) {
                System.out.println("[Ya ha realizado una oferta para este vehiculo]");
                System.out.print("Modificar (S/N): ");
                modificar = sn.next();
                while (!modificar.equals("S") && !modificar.equals("N")) {
                    System.out.println("[Opcion no válida]");
                    System.out.print("\nModificar (S/N): ");
                    modificar = sn.next();
                }
                break;
            }
        }
        if (modificar.equals("S")) {
            System.out.print("\nIngrese el precio de su oferta: ");
            oferta = sn.nextDouble();
            String m = (v.getTipo() + " - " + v.getPlaca() + " - " + correo + " - " + oferta);
            Utilitaria.saveFile("Ofertas.txt", m);
            ofertas.add(new Oferta(v, Comprador.obtenerComprador(correo), oferta));
            System.out.print("\n[Su oferta se ha registrado exitosamente!!]\n");
        }

    }
    
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






    //ACEPTAR OFERTA
    public static void aceptarOferta(String plb,String c,String l) throws FileNotFoundException, NoSuchAlgorithmException, IOException{
        Scanner sn = new Scanner(System.in);
        ArrayList<String> filtro = Oferta.filtradorOfertas(plb);
        ////System.out.println(filtro);
        int index = 0;
        if (filtro.isEmpty()) {
            System.out.println("\n[No se encontraron ofertas para este vehículo]");
            return;
        }
        
        System.out.println("Se han realizado " + filtro.size() + " ofertas");
        boolean revisarSiguiente = true;
        while (index < filtro.size() && revisarSiguiente) {
            System.out.println("Oferta N° " + (index + 1));
            //System.out.println(filtro.get(index));
            String[]dato=filtro.get(index).split("-");
            String correo=dato[2];
            System.out.println("Correo:"+dato[2]);
            System.out.println("Precio Ofertado:$"+dato[3]);
            
            System.out.println("\nOpciones -----------------");
            System.out.println("1 - Siguiente Vehiculo");
            System.out.println("2 - Anterior Vehiculo");
            System.out.println("3 - Aceptar Oferta");
            System.out.println("0 - Salir!");
            System.out.print("Ingrese una opción: ");
            String op = sn.next();
            switch (op) {
                case "1":
                    index++;
                    break;
                case "2":
                    if(index!=0){
                        index--;
                    }else{
                        System.out.println("Esta es la oferta 1. Pruebe otra opcion");
                    }
                    break;
                case "3":
                    eliminarVehiculo(plb);
                    enviarCorreo(correo,c,l);
                    //enviarCorreoConfirmacion(oferta.getComprador());
                        
                    System.out.println("\n[La oferta ha sido aceptada y se ha enviado un correo de confirmación]");
                case "0":
                    revisarSiguiente = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }
    

    public static  void eliminarVehiculo(String placa) {
        File archivoOriginal = new File("Vehiculos.txt");
        File archivoTemporal = new File("temp.txt");

        try (Scanner sn = new Scanner(archivoOriginal);
             BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal))) {

            while (sn.hasNextLine()) {
                String linea = sn.nextLine();
                String[] separacion = linea.split(" - ");
                if (!placa.equals(separacion[1])) {
                    writer.write(linea);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Eliminar archivo original
        archivoOriginal.delete();

        // Renombrar archivo temporal con el nombre del archivo original
        archivoTemporal.renameTo(archivoOriginal);
    }
  

    public static void enviarCorreo(String destinatario,String c,String l) {
        String remitente = c; // Reemplaza con tu dirección de correo
        String password = l; // Reemplaza con tu contraseña de correo

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Puedes cambiar el host si no utilizas Gmail
        props.put("mail.smtp.port", "587"); // Puerto SMTP

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject("Aceptacion de Oferta");
            mensaje.setText("La oferta enviada fue aceptada");

            Transport.send(mensaje);

            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

