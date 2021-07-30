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
import javafx.stage.Stage;

public class ControladorVistaMain implements Initializable {
    @FXML
    private Button btnEmpleados;

    @FXML 
    private Group cambiante;

    @FXML
    void mostrarEmpleados(ActionEvent event) throws IOException {
        Stage stageEvento = (Stage)btnEmpleados.getScene().getWindow();
        cambiante.getChildren().clear();
        FXMLLoader fxmlEmp = new FXMLLoader(getClass().getResource("../vistas/VistaEmpleados.fxml"));     
        Parent root = (Parent)fxmlEmp.load();
        ControladorVistaEmpleados controlador = fxmlEmp.<ControladorVistaEmpleados>getController();
        //Scene escena = new Scene(root);
        stageEvento.setTitle("La Verde S.A. Management - EMPLEADOS");
        cambiante.getChildren().addAll(controlador.getContenedor().getChildren());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Successfully initialized");       
    }
}
