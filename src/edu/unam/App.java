package edu.unam;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./vistas/VistaMain.fxml"));     
        Parent root = (Parent)fxmlLoader.load();
        //ControladorVistaMain controlador = fxmlLoader.<ControladorVistaMain>getController();
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setTitle("La Verde S.A. Management - BIENVENIDA");
        stage.setResizable(false);
        stage.show();
    }
}
