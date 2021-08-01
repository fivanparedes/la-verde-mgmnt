package edu.unam.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControladorVistaCuadros implements Initializable {
    
    @FXML
    private VBox contenedor;

    @FXML
    private Label label;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Successfully initialized, ControladorVistaCuadros");  
        
    }

    public VBox getContenedor() {
        return this.contenedor;
    }
}
