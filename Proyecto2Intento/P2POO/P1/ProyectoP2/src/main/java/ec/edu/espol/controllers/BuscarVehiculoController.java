package ec.edu.espol.controllers;

import ec.edu.espol.model.TipoVehiculo;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyectop2.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class BuscarVehiculoController implements Initializable {

    @FXML
    private ComboBox<String> tipoVehiculo;
    @FXML
    private VBox vehiculo;
    @FXML
    private Button anteriorBoton;
    @FXML
    private Button siguienteBoton;
    private Usuario usuario;
    private ArrayList<Vehiculo> vehiculos;
    private int index = 0;
    private int max = 1;
    private Vehiculo v;
    @FXML
    private ImageView im;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");
        if (vehiculos.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "No hay vehiculos registrados");
            a.show();
        }
        tipoVehiculo.getItems().addAll(Vehiculo.tipoVehiculos());
        tipoVehiculo.getItems().add("TODOS");
        anteriorBoton.setVisible(false);
        siguienteBoton.setVisible(false);
        // = vehiculos.get(index); max = vehiculos.size();
        //mostrarVehiculo(v);

        /*
        if(tipoVehiculo.getValue() != null){
            v = vehiculos.get(index); max = vehiculos.size();
            Text t1 = new Text("<< VEHICULO " + (index + 1) + " >>");
            Text t2 = new Text(v.presentarVehiculo());
            //t2.setWrappingWidth(300); //Marca el límite en el que se desborda el texto
            t1.setTextAlignment(TextAlignment.CENTER);
            vehiculo.getChildren().addAll(t1, t2);
            /*
            tipoVehiculo.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                vehiculo.getChildren().clear();
                Text t1 = new Text("<< VEHICULO " + (index + 1) + " >>");
                Text t2 = new Text(v.presentarVehiculo());
                //t2.setWrappingWidth(300); //Marca el límite en el que se desborda el texto
                t1.setTextAlignment(TextAlignment.CENTER);
                vehiculo.getChildren().addAll(t1,t2);
            });*/
        //}
        
    } 
    
    @FXML
    private void filtrar(ActionEvent event) {
        ComboBox<String> cb = (ComboBox)event.getSource();
        String tipo = cb.getValue();
        vehiculo.getChildren().clear();
        index = 0;
        if (tipo.equals("TODOS")) {
            vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");
            if(vehiculos.isEmpty()){
                Alert a = new Alert(Alert.AlertType.INFORMATION,"No hay vehiculos registrados");
                a.show();
            } else {
               max = vehiculos.size();
               mostrarVehiculo(vehiculos.get(index));
            }
        } else {
            ArrayList<Vehiculo> filtrados = Vehiculo.filtrarTipoVehiculo(vehiculos, tipo);
            if(filtrados.isEmpty()){
                Alert a = new Alert(Alert.AlertType.INFORMATION,"No hay vehiculos registrados en esta categoría");
                a.show();
            } else {
                max = filtrados.size();
                mostrarVehiculo(filtrados.get(index));
            }
        }
    }
    
    private void mostrarVehiculo(Vehiculo vh){
        Text t1 = new Text("<< VEHICULO " + (index + 1) + " >>");
        Text t2 = new Text(Vehiculo.presentarVehiculo(vh));
        t1.setTextAlignment(TextAlignment.CENTER);
        vehiculo.getChildren().addAll(t1, t2);
        if (index == 0)
            anteriorBoton.setVisible(false);
        else
            anteriorBoton.setVisible(true);
        if (index + 1 == max)
            siguienteBoton.setVisible(false);
        else
            siguienteBoton.setVisible(true);
    }

    @FXML
    private void anterior(MouseEvent event) {
        index --;
        vehiculo.getChildren().clear();
        mostrarVehiculo(vehiculos.get(index));
    }

    @FXML
    private void siguiente(MouseEvent event) {
        index ++;
        vehiculo.getChildren().clear();
        mostrarVehiculo(vehiculos.get(index));
    }
    
    public void setUsuario(Usuario u){
        usuario = u;
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
    
}
