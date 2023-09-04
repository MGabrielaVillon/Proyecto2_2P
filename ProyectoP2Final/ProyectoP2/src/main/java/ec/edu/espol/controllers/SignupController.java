package ec.edu.espol.controllers;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyectop2.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignupController implements Initializable {

    @FXML
    private TextField apellido;
    @FXML
    private TextField nombre;
    @FXML
    private TextField correo;
    @FXML
    private TextField organizacion;
    @FXML
    private PasswordField contraseña;
    private ArrayList<Usuario> usuarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarios = Usuario.leerArchivoUsuarios("Usuarios.ser");
    }
    
    @FXML
    private void registrar(MouseEvent event) {
        if (!casillasVacias()) {
            String corr = correo.getText();
            if (Usuario.validarCorreo(usuarios, corr)) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Usuario inválido. Este correo ya fue usado");
                a.show(); correo.setText("");
            } else {
                Usuario u = new Usuario(nombre.getText(), apellido.getText(), organizacion.getText(),corr, contraseña.getText());
                Usuario.guardarArchivoUsuarios(u, "Usuarios.ser");
                Alert a = new Alert(Alert.AlertType.INFORMATION, "¡Usuario registrado exitosamente!");
                a.show();
                ingresar(event);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Necesita registrar todos los datos");
            a.show();
        }
    }
    
    @FXML
    private void ingresar(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("login");
            Scene sc = new Scene(loader.load());
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void limpiar(MouseEvent event) {
       nombre.setText("");
       apellido.setText("");
       organizacion.setText("");
       contraseña.setText("");
       correo.setText("");
    }
    
    private boolean casillasVacias(){
        return (nombre.getText().equals("") || apellido.getText().equals("") || organizacion.getText().equals("") || correo.getText().equals("") || contraseña.getText().equals(""));
    }
    
}
