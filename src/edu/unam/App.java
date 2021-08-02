package edu.unam;

import edu.unam.controladores.ControladorVistaEmpleados;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioEmpleado;
import jakarta.persistence.Persistence;
//import edu.unam.controladores.ControladorVistaMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Repositorio repo = new Repositorio(Persistence.createEntityManagerFactory("EmpresaPU"));
        ServicioEmpleado se = new ServicioEmpleado(repo);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./vistas/VistaMain.fxml"));     
        Parent root = (Parent)fxmlLoader.load();
        ControladorVistaEmpleados.enlazarServicio(se);
        //ControladorVistaMain controlador = fxmlLoader.<ControladorVistaMain>getController();
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setTitle("La Verde S.A. Management - BIENVENIDA");
        stage.show();
    }
}
