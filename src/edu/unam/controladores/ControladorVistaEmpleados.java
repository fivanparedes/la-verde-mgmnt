package edu.unam.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Empleado;
import edu.unam.servicios.ServicioEmpleado;
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

/*  Esta clase es obligatoria ya que el Scene Builder lo exige para su vista. A la hora de referenciar este controlador desde Scene Builder, utilizar:
    edu.unam.controladores.ControladorVistaEmpleados (por ejemplo)
*/
public class ControladorVistaEmpleados implements Initializable {
    private Empleado empleadoSeleccionado;
    private static ServicioEmpleado servicio;
    //Por medio de @FXML se referencian los elementos con los que se van a interactuar en su vista. Aqui hay ejemplos.
    @FXML
    private Label title;
    @FXML
    private TableView<Empleado> tabla;
    @FXML
    private TableColumn<Empleado, Long> columnaId;
    @FXML
    private TableColumn<Empleado, String> columnaLegajo;
    @FXML
    private TableColumn<Empleado, String> columnaNombres;
    @FXML
    private TableColumn<Empleado, String> columnaApellidos;
    @FXML
    private TableColumn<Empleado, Long> columnaDni;
    @FXML
    private TableColumn<Empleado, LocalDate> columnaNacimiento;
    @FXML
    private TableColumn<Empleado, Long> columnaCuil;
    @FXML
    private TableColumn<Empleado, LocalDate> columnaIngreso;
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
    private TextField fieldApellidos;
    @FXML
    private TextField fieldNombres;
    @FXML
    private TextField fieldDni;
    @FXML
    private TextField fieldCuil;
    @FXML
    private TextField fieldLegajo;
    @FXML
    private DatePicker fieldNacimiento;
    @FXML 
    private DatePicker fieldIngreso;
    //En este caso, este VBox es el contenedor que engloba todos los elementos de mi vista de Empleados. Gracias al metodo getChildren() puedo obtener
    //todos los elementos hijos (o contenidos dentro) de este VBox y utilizarlos en otros lugares. Para ello me voy a valer de un getter.
    @FXML
    private VBox contenedor;

    public static void enlazarServicio(ServicioEmpleado s) {
        servicio = s;
    }

    //La interfaz Initializable te exige redefinir el metodo initialize(), hasta ahora no le di alguna funcionalidad significativa.
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Successfully initialized");
        editWarningLabel.setOpacity(0);
        tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        columnaLegajo.setCellValueFactory(new PropertyValueFactory<>("legajo"));
        columnaNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnaDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        columnaNacimiento.setCellValueFactory(new PropertyValueFactory<>("nacimiento"));
        columnaCuil.setCellValueFactory(new PropertyValueFactory<>("cuil"));
        columnaIngreso.setCellValueFactory(new PropertyValueFactory<>("ingreso"));
        //TODO: hacer andar el servicio.
        //tabla.getItems().addAll(this.servicio.listarEmpleados());
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
            servicio.agregarEmpleado(fieldNombres.getText().trim(), fieldApellidos.getText().trim(), Long.getLong(fieldDni.getText()), fieldLegajo.getText(), fieldIngreso.getValue(), fieldNacimiento.getValue(), Long.getLong(fieldCuil.getText()));
            limpiar();
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al guardar", e.getMessage());
        }
    }
    @FXML
    private void clicEliminar() {
        empleadoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (empleadoSeleccionado != null) {
            servicio.eliminarEmpleado(empleadoSeleccionado.getIdEmpleado());
            limpiar();
        }
    }

    @FXML
    private void cambiarDatos() {
        empleadoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (empleadoSeleccionado != null) {
            servicio.editarEmpleado(empleadoSeleccionado.getIdEmpleado(), fieldNombres.getText(), fieldApellidos.getText(), Long.getLong(fieldDni.getText()), fieldLegajo.getText(), fieldIngreso.getValue(), fieldNacimiento.getValue(), Long.getLong(fieldCuil.getText()));
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
        empleadoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (empleadoSeleccionado != null) {
            cosechas.getItems().clear();
            //etiquetaIdEmpleado.setText(String.valueOf(empleadoSeleccionado.getIdEmpleado()));
            fieldNombres.setText(empleadoSeleccionado.getNombres());
            fieldApellidos.setText(empleadoSeleccionado.getApellidos());
            fieldDni.setText(Long.toString(empleadoSeleccionado.getDni()));
            fieldLegajo.setText(empleadoSeleccionado.getLegajo());
            fieldCuil.setText(Long.toString(empleadoSeleccionado.getCuil()));
            fieldNacimiento.setValue(empleadoSeleccionado.getNacimiento());
            fieldIngreso.setValue(empleadoSeleccionado.getIngreso());
            cosechas.getItems().addAll(empleadoSeleccionado.getCosechas());
        }
    }
    private void limpiar() {
        // limpiamos
        cosechas.getItems().clear();
        editWarningLabel.setText("Actualizando...");
        fieldNombres.clear();
        fieldApellidos.clear();
        fieldCuil.clear();
        fieldDni.clear();
        fieldLegajo.clear();
        cosechas.getItems().clear();
        tabla.getItems().clear();
        tabla.getItems().addAll(servicio.listarEmpleados());
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

    public static ServicioEmpleado getServicioEmpleado() {
        return servicio;
    }
}
