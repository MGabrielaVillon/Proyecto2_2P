package ec.edu.espol.controllers;

import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.TipoVehiculo;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyectop2.App;
import java.io.File;
import java.io.FileFilter;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class DarOfertaController implements Initializable {

    @FXML
    private TextField placa;
    @FXML
    private VBox vbox;
    @FXML
    private TableView<Oferta> tableView;
    @FXML
    private TableColumn<Oferta, Double> cOferta;
    @FXML
    private TableColumn<Oferta, String> cCorreo;
    private Usuario usuario;
    private Vehiculo v;
    private ArrayList<Vehiculo> vehiculos;
    private ObservableList<Oferta> data;
    @FXML
    private Label precio;
    @FXML
    private TextField valor;
    @FXML
    private TextField correo;
    private ImageView ImageView;
    @FXML
    private AnchorPane imagen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");
        tableView.setVisible(false);
        vbox.setVisible(false);
        tableView.setPlaceholder(new Label("No hay ofertas hasta el momento")); 
        cOferta.setCellValueFactory(new PropertyValueFactory<Oferta, Double>("valor"));
        cCorreo.setCellValueFactory(new PropertyValueFactory<Oferta, String>("correoContacto"));
    }    

    @FXML
    private void buscar(ActionEvent event) {
        vbox.setVisible(false);
        if(!placa.getText().equals("")){
            String pla = placa.getText();
            if (Vehiculo.validarPlaca(vehiculos, pla)) {
                v = Vehiculo.obtenerVehiculo(vehiculos, pla);
                data = FXCollections.observableArrayList(v.getOfertas());
                tableView.setItems(data);
                tableView.setVisible(true);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Placa no registrada");
                a.show(); placa.setText("");
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Necesita registrar una placa");
            a.show();
        }   
    }

    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("buscarVehiculo");
            Scene sc = new Scene(loader.load());
            BuscarVehiculoController bc = loader.getController();
            bc.setUsuario(usuario);
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void ofertar(MouseEvent event) {
        if(tableView.isVisible()){
            limpiar();
            vbox.setVisible(true);
            precio.setText("");
            precio.setText(""+v.getPrecio());
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Necesita buscar una placa");
            a.show();
        }  
        
    }
    
    private void limpiar() {
        valor.clear();
        correo.clear();
        imagen.getChildren().clear();
    }
    
    public void setUsuario(Usuario u){
        usuario = u;
    }

    @FXML
    private void subirImagen(MouseEvent event) {
        
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));
        File imgFile = fc.showOpenDialog(App.getStage());
        if (imgFile != null) {
            imagen.getChildren().clear();
            Image i = new Image("File:"+imgFile.getPath());
            //ImageView.setImage(image);
            ImageView iv = new ImageView(i);
            iv.setFitHeight(97);
            iv.setFitWidth(181);
            imagen.getChildren().add(iv);
        }
    }

    @FXML
    private void guardar(MouseEvent event) {
        if(valor.getText().equals("") || correo.getText().equals("") || imagen.getChildren().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Necesita registrar todos los datos");
            a.show();
        } else {
            try {
                double vl = Double.parseDouble(valor.getText());
                Oferta o = new Oferta(v, usuario, vl, correo.getText());
                o.guardarArchivoOfertas("Ofertas.XML");
                ArrayList<Oferta> ofertas = v.getOfertas();
                ofertas.add(o);
                v.setOfertas(ofertas);
                v.modificarVehiculoArchivo("Vehiculos.XML");
                vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.XML");
                data = FXCollections.observableArrayList(v.getOfertas());
                tableView.setItems(data);
            } catch (NumberFormatException e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Revise de nuevo. Algunos parámetros deben ser llenados por números");
                a.show();
            }
        }
            
    }
}

    
    

