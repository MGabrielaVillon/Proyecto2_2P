package ec.edu.espol.controllers;

import ec.edu.espol.model.Auto;
import ec.edu.espol.model.TipoVehiculo;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyectop2.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class BuscarVehiculoController implements Initializable {

    @FXML
    private ComboBox<String> tipoVehiculo;
    private Usuario usuario;
    private ArrayList<Vehiculo> vehiculos;
    private Vehiculo v;
    @FXML
    private TableView<Vehiculo> tableView;
    @FXML
    private TableColumn<Vehiculo, TipoVehiculo> cTipo;
    @FXML
    private TableColumn<Vehiculo, String> cPlaca;
    @FXML
    private TableColumn<Vehiculo, Double> cPrecio;
    @FXML
    private TableColumn<Vehiculo, String> cMarca;
    @FXML
    private TableColumn<Vehiculo, String> cModelo;
    @FXML
    private TableColumn<Vehiculo, String> cColor;
    @FXML
    private TableColumn<Vehiculo, Integer> cAnio;
    @FXML
    private TableColumn<Vehiculo, String> cMotor;
    @FXML
    private TableColumn<Vehiculo, String> cCombustible;
    @FXML
    private TableColumn<Vehiculo, Double> cRecorrido;
    @FXML
    private TableColumn<Vehiculo, Integer> cVidrios;
    @FXML
    private TableColumn<Vehiculo, String> cTransmision;
    @FXML
    private TableColumn<Vehiculo, String> cTraccion;
    private ObservableList<Vehiculo> data;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");
        data = FXCollections.observableArrayList(vehiculos);
        if (vehiculos.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "No hay vehiculos registrados actualmente");
            a.show();
        }
        tipoVehiculo.getItems().addAll(Vehiculo.tipoVehiculos());
        tipoVehiculo.getItems().add("TODOS");
        
        cTipo.setCellValueFactory(new PropertyValueFactory<Vehiculo, TipoVehiculo>("tipo"));
        cPlaca.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("placa"));
        cMarca.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("marca"));
        cModelo.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("modelo"));
        cMotor.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("tipoMotor"));
        cAnio.setCellValueFactory(new PropertyValueFactory<Vehiculo, Integer>("anio"));
        cRecorrido.setCellValueFactory(new PropertyValueFactory<Vehiculo, Double>("recorrido"));
        cColor.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("color"));
        cCombustible.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("tipoCombustible"));
        cPrecio.setCellValueFactory(new PropertyValueFactory<Vehiculo, Double>("precio"));
        cVidrios.setCellValueFactory(new PropertyValueFactory<Vehiculo, Integer>("vidrio"));
        cTransmision.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("transmision"));
        cTraccion.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("traccion"));
        
        tableView.setVisible(true);
        tableView.setItems(data);
    }
    
    @FXML
    private void filtrar(ActionEvent event) {
        cVidrios.setVisible(false);
        cTransmision.setVisible(false);
        cTraccion.setVisible(false);
        ComboBox<String> cb = (ComboBox)event.getSource();
        String tipo = cb.getValue();
        if (tipo.equals("TODOS")) {
            data = FXCollections.observableArrayList(vehiculos);
            tableView.setItems(data);
            if(vehiculos.isEmpty()){
                Alert a = new Alert(Alert.AlertType.INFORMATION,"No hay vehiculos registrados actualmente");
                a.show();
            }
        } else {
            ArrayList<Vehiculo> filtrados = Vehiculo.filtrarTipoVehiculo(vehiculos, tipo);
            data = FXCollections.observableArrayList(filtrados);
            tableView.setItems(data);
            if(filtrados.isEmpty()){
                Alert a = new Alert(Alert.AlertType.INFORMATION,"No hay vehiculos registrados en esta categoría");
                a.show();
            } else if (tipo.equals("AUTO")){
                cVidrios.setVisible(true);
                cTransmision.setVisible(true);
            } else if (tipo.equals("CAMIONETA")) {
                cTraccion.setVisible(true);
            }
        }
    }

    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("menu");
            Scene sc = new Scene(loader.load());
            MenuController mc = loader.getController();
            //Usuario u = Usuario.obtenerUsuario(Usuario.leerArchivoUsuarios("Usuarios.XML"), usuario.getCorreo());
            mc.setUsuario(usuario); mc.setLabel(usuario.getNombre());
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setUsuario(Usuario u){
        usuario = u;
    }

    @FXML
    private void verOferta(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("darOferta");
            Scene sc = new Scene(loader.load());
            DarOfertaController dc = loader.getController();
            dc.setUsuario(usuario);
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
