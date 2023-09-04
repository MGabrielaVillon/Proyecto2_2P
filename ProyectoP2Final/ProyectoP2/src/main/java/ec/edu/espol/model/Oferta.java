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
    private Usuario usuario;
    private Usuario comprador;
    private double valor;
    private String correoContacto;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }
    
    

    //Constructor
    public Oferta(Vehiculo vehiculo, Usuario usuario, double valor, String correoContacto) {
        this.vehiculo = vehiculo;
        this.usuario = usuario;
        this.valor = valor;
        this.correoContacto = correoContacto;
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
    
    public void guardarArchivoOfertas(String nomfile) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nomfile,true))){
            bw.write(this.vehiculo.getTipo() + " - " + this.vehiculo.getPlaca() + " - " + this.getCorreoContacto() + " - " + this.valor + "\n");
            System.out.println(this.toString());
        }catch(IOException ie){  
        }
    }
    
    public String toString() {
        return "Oferta{" + "usuario=" + usuario.getCorreo() + "correo=" + correoContacto + ", valor=" + valor + ", vehiculo=" + vehiculo.getPlaca() + "}";
    }

    public static  void eliminarVehiculo(String placa) { ///NO HE USADO
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
  

//    public static void enviarCorreo(String destinatario,String c,String l) {
//        String remitente = c; // Reemplaza con tu dirección de correo
//        String password = l; // Reemplaza con tu contraseña de correo
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com"); // Puedes cambiar el host si no utilizas Gmail
//        props.put("mail.smtp.port", "587"); // Puerto SMTP
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(remitente, password);
//            }
//        });
//
//        try {
//            Message mensaje = new MimeMessage(session);
//            mensaje.setFrom(new InternetAddress(remitente));
//            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
//            mensaje.setSubject("Aceptacion de Oferta");
//            mensaje.setText("La oferta enviada fue aceptada");
//
//            Transport.send(mensaje);
//
//            System.out.println("Correo enviado exitosamente.");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
}

