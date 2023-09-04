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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author María José Villón
 */
public class CambioContrasenaController implements Initializable {

    private Usuario usuario;
    @FXML
    private PasswordField claveActual;
    @FXML
    private PasswordField nuevaClave;
    @FXML
    private PasswordField confirmaNuevaClave;

    
    ArrayList<Usuario> usuarios;
    ArrayList<Usuario> usuariosTodos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cambiarClave(MouseEvent event) {
        
        usuarios = Usuario.leerArchivoUsuarios("UsuarioIngresado.txt");
        //System.out.println(usuarios);
        String contrasenaActual = claveActual.getText();
        String contrasenaNueva = nuevaClave.getText();
        String confirmaContrasenaNueva = confirmaNuevaClave.getText();
        
        if(contrasenaActual.isEmpty() || contrasenaNueva.isEmpty() || confirmaContrasenaNueva.isEmpty()){
            mensajeAlerta("Aún hay casillas vacías.\nRevise de nuevo.", Alert.AlertType.ERROR);
        }else{
            for(Usuario usr: usuarios){
                System.out.println(usr);
                if(!contrasenaActual.equals(usr.getClave())){
                    mensajeAlerta("La contraseña actual es incorrecta.\nInténtelo de nuevo.", Alert.AlertType.ERROR);
                    
                }else if(contrasenaActual.equals(contrasenaNueva)){
                    mensajeAlerta("La nueva contraseña no debe ser la misma que la actual.\nInténtelo de nuevo.", Alert.AlertType.ERROR);
                }else if(!confirmaContrasenaNueva.equals(contrasenaNueva)){
                    mensajeAlerta("Su confirmación no coincide con la nueva contraseña.\nInténtelo de nuevo.", Alert.AlertType.ERROR);
                    
                }else{
                    usr.setClave(contrasenaNueva);
                    System.out.println(usr);
                    Usuario.eliminarArchivoTemporal("UsuarioIngresado.txt");
                    Usuario.guardarArchivoUsuarios(Usuario.obtenerUsuario(usuarios, usr.getCorreo()), "UsuarioIngresado.txt");
                    
                    usuariosTodos = Usuario.leerArchivoUsuarios("Usuarios.XML");
                    ArrayList<Usuario> l_usrfinal = new ArrayList<>(); 
                    for(Usuario ut: usuariosTodos){
                        if(ut.getCorreo().equals(usr.getCorreo())){
                            //System.out.println(ut);
                            ut.setClave(contrasenaNueva);
                            //Usuario.guardarArchivoUsuarios(ut, "Usuarios.XML");
                            l_usrfinal.add(ut);
                    
                        }else{
                            l_usrfinal.add(ut);
                        }
                        //System.out.println(ut);
                    }
                    //System.out.println(usuariosTodos);
                    
                    Usuario.eliminarArchivoTemporal("Usuarios.XML");
                    for(Usuario usrF :l_usrfinal){
                        Usuario.guardarArchivoUsuarios(usrF, "Usuarios.XML");
                    }
                            
                    
                    
                    mensajeAlerta("Su contraseña ha sido restablecida con éxito.", Alert.AlertType.INFORMATION);
                    claveActual.setText("");
                    nuevaClave.setText("");
                    confirmaNuevaClave.setText("");
                    
                }
                //usr_clave.setText(usr.getClave());

            }
            
        }
        
        
        
        
    }
    
    public void mensajeAlerta(String mensaje, Alert.AlertType tipoAlerta){
        Alert alert = new Alert(tipoAlerta);
        alert.setContentText(mensaje);
        alert.show();
    }
    
    public void setUsuario(Usuario u){
        usuario = u;
    }
    

    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("perfil");
            Scene sc = new Scene(loader.load());
            //MenuController mc = loader.getController();
            //mc.setUsuario(Usuario.obtenerUsuario(Usuario.leerArchivoUsuarios("Usuarios.XML"), usuario.getCorreo()));
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
