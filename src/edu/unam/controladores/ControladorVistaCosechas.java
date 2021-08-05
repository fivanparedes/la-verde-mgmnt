package edu.unam.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Entrega;
import edu.unam.modelo.Registro;
import edu.unam.modelo.Cuadro;
import edu.unam.modelo.Empleado;
import edu.unam.modelo.Secadero;
import edu.unam.servicios.ServicioCosecha;
import edu.unam.servicios.ServicioEntrega;
import edu.unam.servicios.ServicioRegistro;
import edu.unam.vistas.VistaUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ControladorVistaCosechas implements Initializable {
    private Cosecha cosechaSeleccionada;
    private static ServicioCosecha servicioCosecha;
    private static ServicioEntrega servicioEntrega;
    private static ServicioRegistro servicioRegistro;
    //Por medio de @FXML se referencian los elementos con los que se van a interactuar en su vista. Aqui hay ejemplos.
    @FXML
    private Label title;
    @FXML
    private TableView<Cosecha> tabla;
    @FXML
    private TableColumn<Cosecha, Long> columnaId;
    @FXML
    private TableColumn<Empleado, Long> columnaSecadero;
    @FXML
    private TableColumn<Empleado, LocalDate> columnaFecha;
    @FXML
    private TableView<Cuadro> cuadros;
    @FXML
    private TableColumn<Cuadro, Integer> columnaIdCuadro;
    @FXML
    private TableView<Empleado> empleados;
    @FXML
    private TableColumn<Empleado, Integer> columnaIdEmpleado;
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
    private TextField fieldSecadero;
    @FXML
    private DatePicker fieldFecha;
    @FXML
    private TextField fieldPesoEnt;
    @FXML 
    private DatePicker fieldTiempoEnt;
    @FXML
    private TextField fieldPesoReg;
    @FXML 
    private DatePicker fieldTiempoReg;
    @FXML
    private TextField fieldCuadro;
    @FXML
    private TextField fieldEmpleado;
    //En este caso, este VBox es el contenedor que engloba todos los elementos de mi vista de Empleados. Gracias al metodo getChildren() puedo obtener
    //todos los elementos hijos (o contenidos dentro) de este VBox y utilizarlos en otros lugares. Para ello me voy a valer de un getter.
    @FXML
    private VBox contenedor;

    public static void enlazarServicio(ServicioCosecha c, ServicioEntrega e, ServicioRegistro r) {
        servicioCosecha = c;
        servicioEntrega = e;
        servicioRegistro = r;
    }

    //La interfaz Initializable te exige redefinir el metodo initialize(), hasta ahora no le di alguna funcionalidad significativa.
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Successfully initialized");
        editWarningLabel.setOpacity(0);
        tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idCosecha"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaSecadero.setCellValueFactory(new PropertyValueFactory<>("secadero"));
        //TODO: hacer andar el servicio.
        //tabla.getItems().addAll(servicio.listarEmpleados());
        tabla.getSelectionModel().selectedItemProperty().addListener(e -> cargarDatos());
        columnaIdCuadro.setCellValueFactory(new PropertyValueFactory<>("Cuadro"));
        columnaIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("Empleado"));
    }

    @FXML
    void showEditWarning() {
        editWarningLabel.setText("Hay campos rellenados.");
    }

    @FXML
    private void clicNuevo() {
        int idSecadero;
        Secadero aux;
        tabla.getSelectionModel().clearSelection();
        try {
            idSecadero = Integer.getInteger(fieldSecadero.getText()).intValue();
            aux = ControladorVistaSecaderos.getServicioSecadero().buscarSecadero(idSecadero);
            if (aux != null) {
                servicioCosecha.agregarCosecha(fieldFecha.getValue(), cuadros.getItems(), empleados.getItems(), aux);
            }        
            limpiar();
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al guardar", e.getMessage());
        }
    }
    @FXML
    private void clicEliminar() {
        cosechaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (cosechaSeleccionada != null) {
            servicioCosecha.eliminarCosecha(cosechaSeleccionada.getIdCosecha());
            limpiar();
        }
    }

    @FXML
    private void cambiarDatos() {
        int idSecadero;
        Secadero aux;
        cosechaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (cosechaSeleccionada != null) {
            idSecadero = Integer.getInteger(fieldSecadero.getText()).intValue();
            aux = ControladorVistaSecaderos.getServicioSecadero().buscarSecadero(idSecadero);
            if (aux != null) {
                servicioCosecha.editarCosecha(cosechaSeleccionada.getIdCosecha(), fieldFecha.getValue(), cuadros.getItems(), empleados.getItems(), aux);
            } 
            limpiar();
        }    
    }

    @FXML
    private void mostrarAyuda() {
        VistaUtils.mostrarAlerta(AlertType.INFORMATION, "Ayuda - Empleados", "Mensaje de ayuda:", "Solo se pueden agregar registros nuevos si los campos están llenos. En caso de seleccionar uno en la lista, presionar 'Cambiar' para modificar ese mismo registro con los datos de los campos. \n Los datos numericos como DNI y CUIL van sin puntos ni comas.\n ");
    }

    /* Este procedimiento introduce los valores del campo seleccionado en los campos de texto respectivos, y tambien carga la lista de cosechas */
    @FXML
    private void cargarDatos() {
        cosechaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (cosechaSeleccionada != null) {
            cuadros.getItems().clear();
            empleados.getItems().clear();
            //etiquetaIdEmpleado.setText(String.valueOf(cosechaSeleccionada.getIdEmpleado()));
            fieldSecadero.setText(String.valueOf(cosechaSeleccionada.getSecadero().getIdSecadero()));
            fieldFecha.setValue(cosechaSeleccionada.getFecha());
            fieldPesoEnt.setText("");
            fieldTiempoEnt.setValue(cosechaSeleccionada.getFecha());
            cuadros.getItems().addAll(cosechaSeleccionada.getCuadros());
            empleados.getItems().addAll(cosechaSeleccionada.getEmpleados());
        }
    }
    private void limpiar() {
        // limpiamos
        cuadros.getItems().clear();
        empleados.getItems().clear();
        editWarningLabel.setText("Actualizando...");
        fieldSecadero.clear();
        fieldFecha.setValue(LocalDate.now());
        fieldPesoEnt.clear();
        fieldTiempoEnt.setValue(LocalDate.now());
        fieldPesoReg.clear();
        fieldTiempoReg.setValue(LocalDate.now());
        cuadros.getItems().clear();
        empleados.getItems().clear();
        tabla.getItems().clear();
        tabla.getItems().addAll(servicioCosecha.listarCosechas());
        editWarningLabel.setText(" ");
    }
    /*  Este getter me permite utilizar esta vista en otros lugares fuera de este controlador.
        Por ejemplo:
        private Group cambiante;            //Un contenedor del tipo Group.
        cambiante.getChildren().clear();    //Limpio el contenedor
        ControladorVistaCosechas controlador = fxmlEmp.<ControladorVistaCosechas>getController(); //Esto me permite obtener este mismo controlador
        cambiante.getChildren().addAll(controlador.getContenedor().getChildren());  //Obtengo los hijos del contenedor, y agrego los hijos del VBox de este controlador
    */
    public VBox getContenedor() {
        return this.contenedor;
    }
}
