package edu.unam;
import edu.unam.controladores.ControladorVistaCuadros;
import edu.unam.controladores.ControladorVistaEmpleados;
import edu.unam.controladores.ControladorVistaLotes;
import edu.unam.controladores.ControladorVistaProductores;
import edu.unam.controladores.ControladorVistaSecaderos;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioCuadro;
import edu.unam.servicios.ServicioEmpleado;
import edu.unam.servicios.ServicioLote;
import edu.unam.servicios.ServicioProductor;
import edu.unam.servicios.ServicioSecadero;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
//import javafx.scene.control.Label;
//import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import edu.unam.controladores.ControladorVistaMain;
//import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LaVerdeSA");
        Repositorio repo = new Repositorio(emf);
        ServicioEmpleado se = new ServicioEmpleado(repo);
        ServicioSecadero sc = new ServicioSecadero(repo);
        ServicioProductor sp = new ServicioProductor(repo);
        ServicioLote sl = new ServicioLote(repo);
        ServicioCuadro scu = new ServicioCuadro(repo);
        ControladorVistaEmpleados.enlazarServicio(se);
        ControladorVistaProductores.enlazarServicio(sp);
        ControladorVistaSecaderos.enlazarServicio(sc);
        ControladorVistaLotes.enlazarServicio(sl, sp);
        ControladorVistaCuadros.enlazarServicio(scu, sl);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./vistas/VistaMain.fxml"));     
        Parent root = (Parent)fxmlLoader.load();
        //ControladorVistaMain controlador = fxmlLoader.<ControladorVistaMain>getController();
        Scene scene = new Scene(root, 1360, 700);        
        stage.setScene(scene);
        stage.setTitle("La Verde S.A. Management - BIENVENIDA");
        //stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }
}
