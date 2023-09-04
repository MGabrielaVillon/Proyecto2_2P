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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PerfilController implements Initializable {

    private Usuario usuario;
    @FXML
    private AnchorPane cambioClave;
    @FXML
    private TextField usr_nombre;
    @FXML
    private TextField usr_apellido;
    @FXML
    private TextField usr_org;
    @FXML
    private TextField usr_correo;
    @FXML
    private PasswordField usr_clave;
    
    
    
    private ArrayList<Usuario> usuarios;
    @FXML
    private CheckBox mostrarClave;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializa los campos de texto con los datos del usuario
        usuarios = Usuario.leerArchivoUsuarios("UsuarioIngresado.txt");
        //System.out.println(usuarios);
        
        for(Usuario usr: usuarios){
            usr_nombre.setText(usr.getNombre());
            usr_apellido.setText(usr.getApellido());
            usr_org.setText(usr.getOrganizacion());
            usr_correo.setText(usr.getCorreo());
            usr_clave.setText(usr.getClave());
        }
        
    }
    
    
    
    public void setUsuario(Usuario u){
        usuario = u;
    }

    @FXML
    private void cambioClave(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("CambioContrasena");
            Scene sc = new Scene(loader.load());
            //MenuController mc = loader.getController();
            //mc.setUsuario(Usuario.obtenerUsuario(Usuario.leerArchivoUsuarios("Usuarios.XML"), usuario.getCorreo()));
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("menu");
            Scene sc = new Scene(loader.load());
            MenuController mc = loader.getController();
            mc.setUsuario(Usuario.obtenerUsuario(Usuario.leerArchivoUsuarios("Usuarios.XML"), usuario.getCorreo()));
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void mostrarActivado(MouseEvent event) {
        if (mostrarClave.isSelected()) {
            // Mostrar la contraseña como texto
            usr_clave.setPromptText(usr_clave.getText());
            usr_clave.setText("");
            usr_clave.setDisable(true);
        } else {
            // Ocultar la contraseña nuevamente
            usr_clave.setText(usr_clave.getPromptText());
            usr_clave.setPromptText(null);
            usr_clave.setDisable(false);
        }
    }    
}
