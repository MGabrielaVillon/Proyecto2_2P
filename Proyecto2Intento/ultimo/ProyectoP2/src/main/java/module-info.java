module ec.edu.espol.proyectop2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyectop2 to javafx.fxml;
    exports ec.edu.espol.proyectop2;
    opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
    opens ec.edu.espol.controllers to javafx.fxml;
    exports ec.edu.espol.controllers;
}
