
package com.mycompany.proyecto2intento;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilitaria {

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static void saveFile(String nomfile, String m) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))) {
            pw.println(m);
        } catch (Exception e) {
        }
    }

    public static ArrayList<String[]> readfile(String nomfile) throws FileNotFoundException {
        ArrayList<String[]> datos = new ArrayList<>();
        try (Scanner sn = new Scanner(new File(nomfile))) {
            while (sn.hasNextLine()) {
                String linea = sn.nextLine();
                String[] tokens = linea.split(" - ");
                datos.add(tokens);
            }
        }
        return datos;
    }
    
    public static boolean validarClave(String nomfile, String correo, String clv) throws FileNotFoundException, NoSuchAlgorithmException {
        String clave = toHexString(getSHA(clv));
        try (Scanner sn = new Scanner(new File(nomfile))) {
            while (sn.hasNextLine()) {
                String linea = sn.nextLine();
                String[] separacion = linea.split(" - ");
                if (correo.equals(separacion[3])) {
                    String hashcode = separacion[4];
                    return (hashcode.equals(clave));
                }
            }
        }
        return false;
    }
    
    public static boolean validarCorreo(String nomfile, String correo) {
         try(Scanner sn = new Scanner(new File(nomfile))){
             if(!sn.hasNextLine())
                 return false;
            while (sn.hasNextLine()) {
                 String linea = sn.nextLine();
                 String[] separacion = linea.split(" - ");
                 if (correo.equals(separacion[3])) {
                       return true;
                }
            }
        } catch (Exception e){
        }
        return false;
    }
    
    public static ArrayList<String> obtenerCorreoClave(String nomfile) throws FileNotFoundException, NoSuchAlgorithmException{
        Scanner sn = new Scanner(System.in);
        ArrayList<String> datos = new ArrayList<>();
        System.out.print("Ingrese el correo: ");
        String cor = sn.next();
        while (!Utilitaria.validarCorreo(nomfile, cor)) {
            System.out.println("[El correo no se encuentra registrado]");
            System.out.print("Ingrese el correo: ");
            cor = sn.next();
        }
        datos.add(cor);
        
        System.out.print("Ingrese la clave: ");
        String clv = sn.next();
        while (!Utilitaria.validarClave(nomfile, cor, clv)) {
            System.out.println("[Clave incorrecta]");
            System.out.print("Ingrese la clave: ");
            clv = sn.next();
        }
        datos.add(clv);
        return datos;
    }
    
    public static boolean validarPlaca(String pl) {
        try (Scanner sn = new Scanner(new File("Vehiculos.txt"))) {
            while (sn.hasNextLine()) {
                String linea = sn.nextLine();
                String[] separacion = linea.split(" - ");
                if (pl.equals(separacion[1])) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public static boolean validarRango(double valor, String max, String min) {
        if(max.isEmpty() && min.isEmpty())
            return true;
        else if(max.isEmpty())
            return (Double.parseDouble(min) <= valor);
        else if(min.isEmpty())
            return (Double.parseDouble(max) >= valor);
        else
            return (Double.parseDouble(min) <= valor && Double.parseDouble(max) >= valor);
    }
    
    
    public static void mos(String pl) {
        try (Scanner sn = new Scanner(new File("Vehiculos.txt"))) {
            while (sn.hasNextLine()) {
                String linea = sn.nextLine();
                String[] separacion = linea.split(" - ");
                if (pl.equals(separacion[1])) {///MOTOCICLETA - bbb - 1 - 7 - 4 - 554 - 5.0 - 1 - 7 - 2.0
                    System.out.println(separacion[2]+" "+separacion[3]+" Precio:"+separacion[9]);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
    }
   
}




