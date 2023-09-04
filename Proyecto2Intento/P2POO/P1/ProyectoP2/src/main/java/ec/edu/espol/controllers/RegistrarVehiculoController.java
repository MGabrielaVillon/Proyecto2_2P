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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegistrarVehiculoController implements Initializable {

    @FXML
    private TextField placa;
    @FXML
    private TextField marca;
    @FXML
    private TextField modelo;
    @FXML
    private TextField tipoMotor;
    @FXML
    private TextField recorrido;
    @FXML
    private TextField color;
    @FXML
    private TextField tipoCombustible;
    @FXML
    private TextField precio;
    @FXML
    private TextField anio;
    @FXML
    private ComboBox<TipoVehiculo> tipoVehiculo;
    private Usuario usuario;
    private ArrayList<Vehiculo> vehiculos;
    @FXML
    private TextField vidrios;
    @FXML
    private TextField transmision;
    @FXML
    private TextField traccion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");
        tipoVehiculo.getItems().addAll(TipoVehiculo.values());
    }    
    
    @FXML
    private void registrar(MouseEvent event) {
        
        if (!casillasVacias()) {
            String pla = placa.getText();
            if (Vehiculo.validarPlaca(vehiculos, pla)) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Vehiculo inválido. Esta placa ya fue usada");
                a.show(); placa.setText("");
            } else {
                try{
                    double rec = Double.parseDouble(recorrido.getText());
                    double pr = Double.parseDouble(precio.getText());
                    int vids = Integer.parseInt(vidrios.getText());
                    
                    if (tipoVehiculo.getValue() == TipoVehiculo.MOTOCICLETA) {
                        Vehiculo ve= new Vehiculo(usuario, tipoVehiculo.getValue(), pla, marca.getText(), modelo.getText(), tipoMotor.getText(), anio.getText(), rec, color.getText(), tipoCombustible.getText(), pr);
                        usuario.añadirVehiculo(ve);
                        Vehiculo.guardarArchivoVehiculos(ve, "Vehiculos.XML");
                        vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");  


                    } else if (tipoVehiculo.getValue() == TipoVehiculo.AUTO){
                        Vehiculo auto = new Vehiculo(usuario, tipoVehiculo.getValue(), pla, marca.getText(), modelo.getText(), tipoMotor.getText(), anio.getText(), rec, color.getText(), tipoCombustible.getText(), vids, transmision.getText(), pr);
                        usuario.añadirVehiculo(auto);
                        Vehiculo.guardarArchivoVehiculos(auto, "Vehiculos.XML");
                        vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");  
                        
                    }else if (tipoVehiculo.getValue() == TipoVehiculo.CAMIONETA){
                        Vehiculo camioneta = new Vehiculo(usuario, tipoVehiculo.getValue(), pla, marca.getText(), modelo.getText(), tipoMotor.getText(), anio.getText(), rec, color.getText(), tipoCombustible.getText(), vids, transmision.getText(), traccion.getText(), pr);
                        usuario.añadirVehiculo(camioneta);
                        Vehiculo.guardarArchivoVehiculos(camioneta, "Vehiculos.XML");
                        vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");    
                    }
                    
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "¡Vehiculo registrado con éxito!");
                    a.show();
                }catch(NumberFormatException e){
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Revise de nuevo.\nAlgunos parametros deben ser llenados por números");
                    a.show();
                }
                
            }
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
    private void limpiar(MouseEvent event) {
        placa.setText("");
        marca.setText("");
        modelo.setText("");
        tipoMotor.setText("");
        recorrido.setText("");
        precio.setText("");
        color.setText("");
        tipoCombustible.setText("");
        anio.setText("");
        vidrios.setText("");
        transmision.setText("");
        traccion.setText("");
        
    }
    
    public void setUsuario(Usuario u){
        usuario = u;
    }
    
    private boolean casillasVacias(){
        return (tipoVehiculo.getValue() == null || placa.getText().equals("") || marca.getText().equals("") || modelo.getText().equals("")
                || tipoMotor.getText().equals("") || recorrido.getText().equals("") || precio.getText().equals("")
                || color.getText().equals("") || tipoCombustible.getText().equals("") || anio.getText().equals("") );
    }

    @FXML
    private void fijarTipo(ActionEvent event) {
        TipoVehiculo tipoSelec = tipoVehiculo.getValue();
        
            
        if (tipoSelec == TipoVehiculo.MOTOCICLETA) {
            vidrios.setText("0");
            transmision.setText("---");
            traccion.setText("---");
        }
        else if (tipoSelec == TipoVehiculo.AUTO){
            vidrios.setEditable(true);
            vidrios.setText("");
            transmision.setEditable(true);
            transmision.setText("");
            traccion.setEditable(false);
            traccion.setText("---");
            
        }else if (tipoSelec == TipoVehiculo.CAMIONETA){
            vidrios.setEditable(true);
            vidrios.setText("");
            transmision.setEditable(true);
            transmision.setText("");
            traccion.setEditable(true);
            traccion.setText("");
        }
        else{
            //si el comboBox no tiene ninguno de los parámetros, se "reinicia" y los TextField se bloquearían
        }
    }

}
