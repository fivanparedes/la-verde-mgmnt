package edu.unam.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/*  Esta clase es obligatoria ya que el Scene Builder lo exige para su vista. A la hora de referenciar este controlador desde Scene Builder, utilizar:
    edu.unam.controladores.ControladorVistaEmpleados (por ejemplo)
*/
public class ControladorVistaEmpleados implements Initializable {

    //Por medio de @FXML se referencian los elementos con los que se van a interactuar en su vista. Aqui hay ejemplos.
    @FXML
    private Label title;

    //En este caso, este VBox es el contenedor que engloba todos los elementos de mi vista de Empleados. Gracias al metodo getChildren() puedo obtener
    //todos los elementos hijos (o contenidos dentro) de este VBox y utilizarlos en otros lugares. Para ello me voy a valer de un getter.
    @FXML
    private VBox contenedor;

    //La interfaz Initializable te exige redefinir el metodo initialize(), hasta ahora no le di alguna funcionalidad significativa.
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Successfully initialized");       
    }

    /*  Este getter me permite utilizar esta vista en otros lugares fuera de este controlador.
        Por ejemplo:
        private Group cambiante;            //Un contenedor del tipo Group.
        cambiante.getChildren().clear();    //Limpio el contenedor
        ControladorVistaEmpleados controlador = fxmlEmp.<ControladorVistaEmpleados>getController(); //Esto me permite obtener este mismo controlador
        cambiante.getChildren().addAll(controlador.getContenedor().getChildren());  //Obtengo los hijos del contenedor, y agrego los hijos del VBox de este controlador
    */
    public VBox getContenedor() {
        return this.contenedor;
    }
}
