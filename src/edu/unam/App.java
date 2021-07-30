package edu.unam;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
Consideraciones:

En el archivo settings.json deben quitar las referencias a las 
bibliotecas de JavaFX porque esta configurado para mi entorno.

Una interfaz gráfica de usuario, o GUI, le permite al usuario interactuar con un 
programa usando componentes gráficos, como ventanas, botones, cuadros de texto, etc., 
en oposición a las interfaces basadas en texto como la línea de comando.

Los programas JavaFX incluyen una clase que extiende de Aplication, 
análoga a tener una sola clase con un método main() para los programas de consola.

Para escribir un programa JavaFX simple: se debe crear una subclase de Aplication
y poner el código de inicio de la GUI en el método start(javafx.stage.Stage)

*/
public class App extends Application {
  @Override  
  public void start(Stage stage) {
    Label mensaje = new Label("Hola, JavaFX!");
    mensaje.setFont(new Font(100));
    stage.setScene(new Scene(mensaje));
    stage.setTitle("Hola");
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
