package edu.unam.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import edu.unam.modelo.Lote;
import edu.unam.modelo.Productor;
import edu.unam.servicios.ServicioProductor;
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

public class ControladorVistaProductores implements Initializable {

    private Productor productorSeleccionado;
    private static ServicioProductor servicio;
    //Por medio de @FXML se referencian los elementos con los que se van a interactuar en su vista. Aqui hay ejemplos.
    @FXML
    private Label title;
    @FXML
    private TableView<Productor> tabla;
    @FXML
    private TableColumn<Productor, Integer> columnaId;
    @FXML
    private TableColumn<Productor, Long> columnaCuit;
    @FXML
    private TableColumn<Productor, String> columnaNombres;
    @FXML
    private TableColumn<Productor, String> columnaApellidos;
    @FXML
    private TableView<Lote> lotes;
    @FXML
    private TableColumn<Lote, Integer> columnaIdLote;
    @FXML
    private TableColumn<Lote, String> columnaPuntos;
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
    private TextField fieldCuit;
    @FXML
    private VBox contenedor;

    public static void enlazarServicio(ServicioProductor s) {
        servicio = s;
    }

    //La interfaz Initializable te exige redefinir el metodo initialize(), hasta ahora no le di alguna funcionalidad significativa.
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Successfully initialized");
        tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idProductor"));
        columnaNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnaCuit.setCellValueFactory(new PropertyValueFactory<>("cuit"));
        tabla.getItems().addAll(servicio.listarProductores());
        tabla.getSelectionModel().selectedItemProperty().addListener(e -> cargarDatos());
        columnaIdLote.setCellValueFactory(new PropertyValueFactory<>("idLote"));
        columnaPuntos.setCellValueFactory(new PropertyValueFactory<>("puntos"));
    }

    @FXML
    void showEditWarning() {
        editWarningLabel.setText("Hay campos rellenados.");
    }

    @FXML
    private void clicNuevo() {
        tabla.getSelectionModel().clearSelection();
        try {
            servicio.agregarProdutor(Long.parseLong(fieldCuit.getText()), fieldApellidos.getText(), fieldNombres.getText());
            limpiar();
        } catch (Exception e) {
            e.printStackTrace();
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al guardar", e.getMessage());
        }
    }
    @FXML
    private void clicEliminar() {
        productorSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (productorSeleccionado != null) {
            if (servicio.eliminarProductor(productorSeleccionado.getIdProductor()) == 1) {
                VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al borrar", "No se pudo borrar el elemento. Probablemente hayan lotes vinculados.");
            }
            limpiar();
        }
    }

    @FXML
    private void cambiarDatos() {
        productorSeleccionado = tabla.getSelectionModel().getSelectedItem();
        try {
            if (productorSeleccionado != null) {
                servicio.editarProductor(productorSeleccionado.getIdProductor(), Long.parseLong(fieldCuit.getText()), fieldApellidos.getText(), fieldNombres.getText());
                limpiar();
            }  
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al modificar los datos", e.getMessage());
            e.printStackTrace();
        }
          
    }

    @FXML
    private void mostrarAyuda() {
        VistaUtils.mostrarAlerta(AlertType.INFORMATION, "Ayuda - Empleados", "Mensaje de ayuda:", "Solo se pueden agregar registros nuevos si los campos están llenos. En caso de seleccionar uno en la lista, presionar 'Cambiar' para modificarlo. \n No podrá borrar un registro si existen lotes asociados.");
    }

    /* Este procedimiento introduce los valores del campo seleccionado en los campos de texto respectivos, y tambien carga la lista de cosechas */
    @FXML
    private void cargarDatos() {
        productorSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (productorSeleccionado != null) {
            lotes.getItems().clear();
            //etiquetaIdEmpleado.setText(String.valueOf(productorSeleccionado.getIdEmpleado()));
            fieldNombres.setText(productorSeleccionado.getNombres());
            fieldApellidos.setText(productorSeleccionado.getApellidos());
            fieldCuit.setText(Long.toString(productorSeleccionado.getCuit()));
            lotes.getItems().addAll(productorSeleccionado.getLotes());
        }
    }
    private void limpiar() {
        // limpiamos
        lotes.getItems().clear();
        editWarningLabel.setText("Actualizando...");
        fieldNombres.clear();
        fieldApellidos.clear();
        fieldCuit.clear();
        lotes.getItems().clear();
        tabla.getItems().clear();
        tabla.getItems().addAll(servicio.listarProductores());
        editWarningLabel.setText(" ");
    }

    public VBox getContenedor() {
        return this.contenedor;
    }
}
