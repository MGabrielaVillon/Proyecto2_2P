module com.mycompany.proyecto2intento {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;

    opens com.mycompany.proyecto2intento to javafx.fxml;
    exports com.mycompany.proyecto2intento;
}
