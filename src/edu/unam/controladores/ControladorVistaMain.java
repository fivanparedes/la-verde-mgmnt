package edu.unam.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;

public class ControladorVistaMain implements Initializable {
    @FXML
    private Button btnEmpleados;

    @FXML 
    private Group cambiante;

    @FXML
    void mostrarEmpleados(ActionEvent event) throws IOException {
        cambiante.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./vistas/VistaEmpleados.fxml"));     
        Parent root = (Parent)fxmlLoader.load();
        cambiante.getChildren().addAll(root);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Successfully initialized");       
    }
}
