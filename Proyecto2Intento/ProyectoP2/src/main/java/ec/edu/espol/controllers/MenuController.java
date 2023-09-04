package ec.edu.espol.controllers;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyectop2.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuController implements Initializable {
    
    private Usuario usuario;
    @FXML
    private Label lb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private void buscarVehiculo(MouseEvent event) {
        System.out.println(usuario.toString());
        try {
            FXMLLoader loader = App.loadFXML("buscarVehiculo");
            Scene sc = new Scene(loader.load());
            BuscarVehiculoController bvc = loader.getController();
            bvc.setUsuario(usuario);
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void registrarVehiculo(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("registrarVehiculo");
            Scene sc = new Scene(loader.load());
            RegistrarVehiculoController rvc = loader.getController();
            rvc.setUsuario(usuario);
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @FXML
    private void salir(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("login");
            Scene sc = new Scene(loader.load());
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setUsuario(Usuario u){
        usuario = u;
    }
    
    public void setLabel(String str){
        lb.setText(str);
    }

    @FXML
    private void verPerfil(MouseEvent event) {
    }

}
