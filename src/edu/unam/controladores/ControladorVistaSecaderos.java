package edu.unam.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControladorVistaSecaderos implements Initializable{

    @FXML
    private VBox contenedor;

    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    public VBox getContenedor() {
        return this.contenedor;
    }   
}
