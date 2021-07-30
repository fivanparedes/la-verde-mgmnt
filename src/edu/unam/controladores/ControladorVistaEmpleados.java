package edu.unam.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControladorVistaEmpleados implements Initializable {
    @FXML
    private Label title;

    @FXML
    private VBox contenedorEmp;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Successfully initialized");       
    }

    public VBox getContenedor() {
        return this.contenedorEmp;
    }
}
