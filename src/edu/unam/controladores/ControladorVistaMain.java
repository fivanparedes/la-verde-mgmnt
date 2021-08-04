package edu.unam.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
//import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorVistaMain implements Initializable {
    @FXML
    private Button btnEmpleados, btnProductores, btnSecaderos, btnCosechas, btnLotes, btnCuadros;

    @FXML 
    private Group cambiante;

    @FXML
    void mostrarEmpleados(ActionEvent event) throws IOException {
        Stage stageEvento = (Stage)btnEmpleados.getScene().getWindow();
        cambiante.getChildren().clear();
        FXMLLoader fxmlEmp = new FXMLLoader(getClass().getResource("../vistas/VistaEmpleados.fxml"));     
        fxmlEmp.load();      
        ControladorVistaEmpleados controlador = fxmlEmp.<ControladorVistaEmpleados>getController();
        stageEvento.setTitle("La Verde S.A. Management - EMPLEADOS");
        //btnEmpleados.setDisable(true);
        cambiante.getChildren().addAll(controlador.getContenedor().getChildren());
        controlador.getContenedor().setLayoutY(-200);
    }

    @FXML
    void mostrarProductores(ActionEvent event) throws IOException {
        Stage stageEvento = (Stage)btnProductores.getScene().getWindow();
        cambiante.getChildren().clear();
        FXMLLoader fxmlView = new FXMLLoader(getClass().getResource("../vistas/VistaProductores.fxml"));
        fxmlView.load();
        ControladorVistaProductores controlador = fxmlView.<ControladorVistaProductores>getController();
        //Scene escena = new Scene(root);
        stageEvento.setTitle("La Verde S.A. Management - PRODUCTORES");
        cambiante.getChildren().addAll(controlador.getContenedor().getChildren());
    }

    @FXML
    void mostrarSecaderos(ActionEvent event) throws IOException {
        Stage stageEvento = (Stage)btnSecaderos.getScene().getWindow();
        cambiante.getChildren().clear();
        FXMLLoader fxmlView = new FXMLLoader(getClass().getResource("../vistas/VistaSecaderos.fxml"));
        fxmlView.load();
        ControladorVistaSecaderos controlador = fxmlView.<ControladorVistaSecaderos>getController();
        //Scene escena = new Scene(root);
        stageEvento.setTitle("La Verde S.A. Management - SECADEROS");
        cambiante.getChildren().addAll(controlador.getContenedor().getChildren());
    }

    @FXML
    void mostrarCosechas(ActionEvent event) throws IOException {
        Stage stageEvento = (Stage)btnCosechas.getScene().getWindow();
        cambiante.getChildren().clear();
        FXMLLoader fxmlView = new FXMLLoader(getClass().getResource("../vistas/VistaCosechas.fxml"));
        fxmlView.load();
        ControladorVistaCosechas controlador = fxmlView.<ControladorVistaCosechas>getController();
        //Scene escena = new Scene(root);
        stageEvento.setTitle("La Verde S.A. Management - COSECHAS");
        cambiante.getChildren().addAll(controlador.getContenedor().getChildren());
    }

    @FXML
    void mostrarLotes(ActionEvent event) throws IOException {
        Stage stageEvento = (Stage)btnLotes.getScene().getWindow();
        cambiante.getChildren().clear();
        FXMLLoader fxmlView = new FXMLLoader(getClass().getResource("../vistas/VistaLotes.fxml"));
        fxmlView.load();
        ControladorVistaLotes controlador = fxmlView.<ControladorVistaLotes>getController();
        //Scene escena = new Scene(root);
        stageEvento.setTitle("La Verde S.A. Management - LOTES");
        cambiante.getChildren().addAll(controlador.getContenedor().getChildren());
    }

    @FXML
    void mostrarCuadros(ActionEvent event) throws IOException {
        Stage stageEvento = (Stage)btnCuadros.getScene().getWindow();
        cambiante.getChildren().clear();
        FXMLLoader fxmlView = new FXMLLoader(getClass().getResource("../vistas/VistaCuadros.fxml"));
        fxmlView.load();
        ControladorVistaCuadros controlador = fxmlView.<ControladorVistaCuadros>getController();
        //Scene escena = new Scene(root);
        stageEvento.setTitle("La Verde S.A. Management - CUADROS");
        cambiante.getChildren().addAll(controlador.getContenedor().getChildren());
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Successfully initialized");       
    }
}
