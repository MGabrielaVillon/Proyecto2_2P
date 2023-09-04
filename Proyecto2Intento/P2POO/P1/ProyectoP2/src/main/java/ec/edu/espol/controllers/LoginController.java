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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private PasswordField contraseña;
    @FXML
    private TextField correo;
    private ArrayList<Usuario> usuarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarios = Usuario.leerArchivoUsuarios("Usuarios.XML");
    }    

    @FXML
    private void ingresar(MouseEvent event) {
        if (!casillasVacias()) {
            String contr = contraseña.getText();
            String corr = correo.getText();
            if (Usuario.validarCorreo(usuarios, corr)) {
                if (Usuario.validarClave(usuarios, corr, contr)) {
                    try {
                        Usuario.eliminarArchivoTemporal("UsuarioIngresado.txt");
                        Usuario.guardarArchivoUsuarios(Usuario.obtenerUsuario(usuarios, corr), "UsuarioIngresado.txt");
                        FXMLLoader loader = App.loadFXML("menu");
                        Scene sc = new Scene(loader.load());
                        MenuController mc = loader.getController();
                        usuarios = Usuario.leerArchivoUsuarios("Usuarios.XML");
                        mc.setUsuario(Usuario.obtenerUsuario(usuarios, corr));
                        App.setScene(sc);
                    } catch (IOException ex) {
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Usuario inválido. Contraseña incorrecta");
                    a.show(); contraseña.setText("");
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Usuario inválido. Correo no registrado");
                a.show(); cleanTextClean();
            }
        }
    }

    @FXML
    private void registrar(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("signup");
            Scene sc = new Scene(loader.load());
            App.setScene(sc);
        } catch (IOException ex) {
        }
    }
    
    private void cleanTextClean(){
       contraseña.setText("");
       correo.setText("");
    }
    
    private boolean casillasVacias(){
        return (correo.getText().equals("") || contraseña.getText().equals(""));
    }

}
