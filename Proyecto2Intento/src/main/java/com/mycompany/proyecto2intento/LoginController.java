

package com.mycompany.proyecto2intento;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController implements Initializable {

    @FXML
    private Button ingresar;
    @FXML
    private PasswordField usr_contrasenia;
    @FXML
    private TextField usr_correo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ingresar(ActionEvent event) {
        try{
            
            String correo = usr_correo.getText();
            String contr = usr_contrasenia.getText();
            Persona p1 = new Persona(correo, contr);
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Validado");
            a.show();
            cleanTextField();
        }catch(NumberFormatException e){
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Usuario incorrecto");
            a.show();
        }
        
    }
    private void cleanTextField(){
        usr_correo.setText("");
        usr_contrasenia.setText("");
    }
    
    
    
}
