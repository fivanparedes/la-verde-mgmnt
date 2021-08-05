package edu.unam.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Secadero;
import edu.unam.servicios.ServicioSecadero;
import edu.unam.vistas.VistaUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ControladorVistaSecaderos implements Initializable{

    private Secadero secaderoSeleccionado;
    private static ServicioSecadero servicio;
    //Por medio de @FXML se referencian los elementos con los que se van a interactuar en su vista. Aqui hay ejemplos.
    @FXML
    private Label title;
    @FXML
    private TableView<Secadero> tabla;
    @FXML
    private TableColumn<Secadero, Integer> columnaId;
    @FXML
    private TableColumn<Secadero, String> columnaRazonSocial;
    @FXML
    private TableColumn<Secadero, Long> columnaCuit;
    @FXML
    private TableView<Cosecha> cosechas;
    @FXML
    private TableColumn<Cosecha, Integer> columnaIdCosecha;
    @FXML
    private TableColumn<Cosecha, LocalDate> columnaFechaCosecha;
    @FXML
    private Label editWarningLabel;
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCambiar;
    @FXML
    private TextField fieldCuit;
    @FXML
    private TextField fieldRazonSocial;
    //En este caso, este VBox es el contenedor que engloba todos los elementos de mi vista de Empleados. Gracias al metodo getChildren() puedo obtener
    //todos los elementos hijos (o contenidos dentro) de este VBox y utilizarlos en otros lugares. Para ello me voy a valer de un getter.
    @FXML
    private VBox contenedor;

    public static void enlazarServicio(ServicioSecadero s) {
        servicio = s;
    }

    //La interfaz Initializable te exige redefinir el metodo initialize(), hasta ahora no le di alguna funcionalidad significativa.
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Successfully initialized");
        editWarningLabel.setOpacity(0);
        tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idSecadero"));
        columnaCuit.setCellValueFactory(new PropertyValueFactory<>("cuit"));
        columnaRazonSocial.setCellValueFactory(new PropertyValueFactory<>("razonSocial"));
        tabla.getItems().addAll(servicio.listarSecaderos());
        tabla.getSelectionModel().selectedItemProperty().addListener(e -> cargarDatos());
        columnaIdCosecha.setCellValueFactory(new PropertyValueFactory<>("idCosecha"));
        columnaFechaCosecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    @FXML
    void showEditWarning() {
        editWarningLabel.setText("Hay campos rellenados.");
    }

    @FXML
    private void clicNuevo() {
        tabla.getSelectionModel().clearSelection();
        try {
            servicio.agregarSecadero(Long.parseLong(fieldCuit.getText().trim()), fieldRazonSocial.getText().trim());
            limpiar();
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al guardar", e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void clicEliminar() {
        secaderoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (secaderoSeleccionado != null) {
            if (servicio.eliminarSecadero(secaderoSeleccionado.getIdSecadero()) == 1) {
                VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al borrar", "No se pudo borrar el elemento. Probablemente hayan cosechas vinculadas.");
            }
            limpiar(); 
        }
    }

    @FXML
    private void cambiarDatos() {
        secaderoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        try {
            if (secaderoSeleccionado != null) {
                servicio.editarSecadero(secaderoSeleccionado.getIdSecadero(), Long.parseLong(fieldCuit.getText()), fieldRazonSocial.getText(), secaderoSeleccionado.getCosechas());
                limpiar();
            }    
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al modificar los datos", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarAyuda() {
        VistaUtils.mostrarAlerta(AlertType.INFORMATION, "Ayuda - Secaderos", "Mensaje de ayuda:", "Solo se pueden agregar registros nuevos si los campos están llenos. \n El CUIT van sin puntos ni comas.\n Las cosechas se agregan en su respectiva pantalla.");
    }

    /* Este procedimiento introduce los valores del campo seleccionado en los campos de texto respectivos, y tambien carga la lista de cosechas */
    @FXML
    private void cargarDatos() {
        secaderoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (secaderoSeleccionado != null) {
            cosechas.getItems().clear();
            fieldCuit.setText(Long.toString(secaderoSeleccionado.getCuit()));
            fieldRazonSocial.setText(secaderoSeleccionado.getRazonSocial());
            cosechas.getItems().addAll(secaderoSeleccionado.getCosechas());
        }
    }
    private void limpiar() {
        // limpiamos
        editWarningLabel.setText("Actualizando...");
        fieldCuit.clear();
        fieldRazonSocial.clear();
        cosechas.getItems().clear();
        tabla.getItems().clear();
        tabla.getItems().addAll(servicio.listarSecaderos());
        editWarningLabel.setText(" ");
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
    
    public static ServicioSecadero getServicioSecadero() {
        return servicio;
    }
}
