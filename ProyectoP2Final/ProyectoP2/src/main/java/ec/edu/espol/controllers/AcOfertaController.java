
package ec.edu.espol.controllers;

import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyectop2.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author naomi
 */
public class AcOfertaController implements Initializable {

    @FXML
    private Button anterior;
    @FXML
    private Button siguiente;
    @FXML
    private VBox vbox;
    @FXML
    private Text mensaje;
    @FXML
    private TextField pla;
    private Vehiculo v;
    private Usuario usuario;
    private ArrayList<Oferta> ofertas;
    
    private int index = 0;
    private int max = 1;
    
    private ArrayList<String> of;
    private ArrayList<String> orden;
    private ArrayList<Vehiculo> vehiculos;
    @FXML
    private Button valido;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculos = Vehiculo.leerArchivoVehiculos("Vehiculos.ser");
        valido.setVisible(false);
        siguiente.setVisible(false);
        anterior.setVisible(false);
    }    

    @FXML
    private void aceptar(MouseEvent event) {
        String[] c= of.get(index).split(" - ");
        String correo=c[2];//correo del ofertador
        mensaje.setText("Se envio la aceptacion de la oferta al correo "+correo+".");
        
    }

    @FXML
    private void buscarP(MouseEvent event) {
        vbox.getChildren().clear();
        if (pla.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Ingrese una placa");
            a.show();
            
        }else{
            
        of=Oferta.filtradorOfertas(pla.getText());
        for(Vehiculo v:vehiculos){
            if (v.getPlaca().equals(pla.getText())){
                valido.setVisible(true);
                if(of.size()>1){
                    siguiente.setVisible(true);
                }
                    
                Text t2= new Text(v.getMarca()+" "+v.getModelo());
                Text t3= new Text("Precio: $"+v.getPrecio());
                HBox hb = new HBox(10);
                hb.getChildren().addAll(t2,t3);
                
            
        vbox.getChildren().addAll(hb);
        }}
        if (of.isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "No hay ofertas con esta placa");
            a.show();
        
        }else{
        max = of.size();        
        mostrarVehiculo(of.get(index));
        }
        }
        
    }
    public static String presentarVehiculo(String of) {
        String[] linea= of.split(" - ");
        return (
                "\n-> Correo: " + linea[2] +
                "\n-> Precio: " + linea[3] 
                );
    }  
    private void mostrarVehiculo(String linea){
        Text t1 = new Text("<< VEHICULO " + (index + 1) + " >>");
        Text t2 = new Text(presentarVehiculo(linea));
        t1.setTextAlignment(TextAlignment.CENTER);
        vbox.getChildren().addAll(t1, t2);
        if (index == 0)
            anterior.setVisible(false);
        else
            anterior.setVisible(true);
        if (index + 1 == max)
            siguiente.setVisible(false);
        else
            siguiente.setVisible(true);
    }

  
    @FXML
    private void bef(MouseEvent event) {
        index --;
        vbox.getChildren().clear();
        mostrarVehiculo(of.get(index));
    }

    @FXML
    private void next(MouseEvent event) {
        index ++;
        vbox.getChildren().clear();
        mostrarVehiculo(of.get(index));
    }

    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("menu");
            Scene sc = new Scene(loader.load());
            MenuController mc = loader.getController();
            mc.setUsuario(usuario); mc.setLabel(""+usuario.getNombre());
            App.setScene(sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setUsuario(Usuario u){
        usuario = u;
    }
    }  

