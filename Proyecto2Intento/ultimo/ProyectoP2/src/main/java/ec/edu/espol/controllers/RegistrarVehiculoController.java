package ec.edu.espol.controllers;

import ec.edu.espol.model.Auto;
import ec.edu.espol.model.Camioneta;
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
    private TextField vidrios;
    @FXML
    private TextField transmision;
    @FXML
    private TextField traccion;
    @FXML
    private ComboBox<TipoVehiculo> tipoVehiculo;
    private Usuario usuario;
    private ArrayList<Vehiculo> vehiculos;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");
        tipoVehiculo.getItems().addAll(TipoVehiculo.values());
        editarCasillas(false);
    }    
    
    @FXML
    private void seleccion(ActionEvent event) {
        ComboBox<TipoVehiculo> cb = (ComboBox) event.getSource();
        TipoVehiculo tipo = cb.getValue();
        if (tipo == TipoVehiculo.MOTOCICLETA) {
            editarCasillas(true);
            vidrios.setText("0");
            transmision.setText("---");
            traccion.setText("---");
            vidrios.setEditable(false);
            transmision.setEditable(false);
            traccion.setEditable(false);
        } else {
            if(!vidrios.isEditable())
                vidrios.setText("");
            if(!transmision.isEditable())
                transmision.setText("");
            editarCasillas(true);
        }
        if (tipo == TipoVehiculo.AUTO){
            traccion.setText("---");
            traccion.setEditable(false);
        } else if (tipo == TipoVehiculo.CAMIONETA){
            traccion.setText("");
        }
    }
    
    @FXML
    private void registrar(MouseEvent event) {
        if (!casillasVacias()) {
            String pla = placa.getText();
            if (Vehiculo.validarPlaca(vehiculos, pla)) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Vehiculo inválido. Esta placa ya fue usada");
                a.show();
                placa.setText("");
            } else {
                try {
                    int a = Integer.parseInt(anio.getText());
                    int vd = Integer.parseInt(vidrios.getText());
                    double rec = Double.parseDouble(recorrido.getText());
                    double pr = Double.parseDouble(precio.getText());
                    TipoVehiculo tipo = tipoVehiculo.getValue();
                    Vehiculo v;
                    if (tipo == TipoVehiculo.MOTOCICLETA) {
                        v = new Vehiculo(usuario, tipoVehiculo.getValue(), pla, marca.getText(), modelo.getText(), tipoMotor.getText(), a, rec, color.getText(), tipoCombustible.getText(), pr);
                    } else if (tipo == TipoVehiculo.AUTO) {
                        v = new Auto(vd , transmision.getText(), usuario, tipoVehiculo.getValue(), pla, marca.getText(), modelo.getText(), tipoMotor.getText(), a, rec, color.getText(), tipoCombustible.getText(), pr);
                    } else {
                        v = new Camioneta(traccion.getText(), usuario, tipoVehiculo.getValue(), pla, marca.getText(), modelo.getText(), tipoMotor.getText(), a, rec, color.getText(), tipoCombustible.getText(), pr);
                    }
                    usuario.añadirVehiculo(v);
                    Vehiculo.guardarArchivoVehiculos(v, "Vehiculos.XML");
                    vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");
                    Alert b = new Alert(Alert.AlertType.INFORMATION, "¡Vehiculo registrado con éxito!");
                    b.show(); limpiar(event);
                } catch (NumberFormatException e) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Revise de nuevo. Algunos parámetros deben ser llenados por números");
                    a.show();
                }
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Necesita registrar todos los datos");
            a.show();
        }
    }

    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("menu");
            Scene sc = new Scene(loader.load());
            MenuController mc = loader.getController();
            Usuario u = Usuario.obtenerUsuario(Usuario.leerArchivoUsuarios("Usuarios.XML"), usuario.getCorreo());
            mc.setUsuario(u); mc.setLabel(u.getNombre());
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void limpiar(MouseEvent event) {
        tipoVehiculo.getSelectionModel().clearSelection();
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
                || color.getText().equals("") || tipoCombustible.getText().equals("") || anio.getText().equals("")
                || vidrios.getText().equals("")|| transmision.getText().equals("")|| traccion.getText().equals(""));
    }
    
    private void editarCasillas(boolean b){
        placa.setEditable(b);
        marca.setEditable(b);
        modelo.setEditable(b);
        tipoMotor.setEditable(b);
        anio.setEditable(b);
        recorrido.setEditable(b);
        color.setEditable(b);
        tipoCombustible.setEditable(b);
        vidrios.setEditable(b);
        transmision.setEditable(b);
        traccion.setEditable(b);
        precio.setEditable(b);
    }

}
