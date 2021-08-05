package edu.unam.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Cuadro;
import edu.unam.modelo.Lote;
import edu.unam.servicios.ServicioCuadro;
import edu.unam.servicios.ServicioLote;
import edu.unam.vistas.VistaUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

//TODO: buscar errores
public class ControladorVistaCuadros implements Initializable {
    
    private Cuadro cuadroSeleccionado;
    private static ServicioLote servicioL;
    private static ServicioCuadro servicio;
    @FXML
    private TableView<Cuadro> tabla;
    @FXML
    private TableColumn<Cuadro, Integer> columnaId;
    @FXML
    private TableColumn<Cuadro, String> columnaDescripcion;
    @FXML
    private TableColumn<Cuadro, Lote> columnaLote;
    @FXML
    private TableView<Cosecha> cosechas;
    @FXML
    private TableColumn<Cosecha, Integer> columnaIdCosecha;
    @FXML
    private TableColumn<Lote, String> columnaFechaCosecha;
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
    private TextField fieldDescripcion;
    @FXML
    private ComboBox<Lote> comboLotes;
    @FXML
    private VBox contenedor;

    @FXML
    private Label label;

    public static void enlazarServicio(ServicioCuadro s2, ServicioLote s1) {
        servicio = s2;
        servicioL = s1;
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idCuadro"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnaLote.setCellValueFactory(new PropertyValueFactory<>("lote"));
        limpiar();
        tabla.getSelectionModel().selectedItemProperty().addListener(e -> cargarDatos());
        columnaIdCosecha.setCellValueFactory(new PropertyValueFactory<>("idCosecha"));
        columnaFechaCosecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        btnCambiar.setDisable(true);
        btnEliminar.setDisable(true);
        System.out.println("Successfully initialized");
    }

    @FXML
    void showEditWarning() {
        editWarningLabel.setText("Hay campos rellenados.");
    }

    @FXML
    private void clicNuevo() {
        tabla.getSelectionModel().clearSelection();
        try {
            servicio.agregarCuadro(fieldDescripcion.getText(), comboLotes.getSelectionModel().getSelectedItem());
            limpiar();
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al guardar", e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void clicEliminar() {
        cuadroSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (cuadroSeleccionado != null) {
            if (servicio.eliminarCuadro(cuadroSeleccionado.getIdCuadro()) == 1) {
                VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al borrar", "No se pudo borrar el elemento. Probablemente hayan cosechas vinculadas.");
            }
            limpiar();
        }
    }

    @FXML
    private void cambiarDatos() {
        cuadroSeleccionado = tabla.getSelectionModel().getSelectedItem();
        try {
            if (cuadroSeleccionado != null) {
                servicio.editarCuadro(cuadroSeleccionado.getIdCuadro(), fieldDescripcion.getText(), comboLotes.getSelectionModel().getSelectedItem());
                limpiar();
            }  
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al modificar los datos", e.getMessage());
            e.printStackTrace();
        }     
    }

    @FXML
    private void mostrarAyuda() {
        VistaUtils.mostrarAlerta(AlertType.INFORMATION, "Ayuda - Lotes", "Mensaje de ayuda:", "Para agregar cuadros, tiene que haber un lote definido. La descripcion puede ser 'Cuadro 1', 'Cuadro 2', 'Cuadro N', y se asociará al Lote dado por su ID.");
    }

    /* Este procedimiento introduce los valores del campo seleccionado en los campos de texto respectivos, y tambien carga la lista de cosechas */
    @FXML
    private void cargarDatos() {
        cuadroSeleccionado = tabla.getSelectionModel().getSelectedItem();
        cosechas.getItems().clear();
        if (cuadroSeleccionado != null) {
            fieldDescripcion.setText(cuadroSeleccionado.getDescripcion());
            comboLotes.getSelectionModel().select(cuadroSeleccionado.getLote());
            cosechas.getItems().addAll(cuadroSeleccionado.getCosechas());
            btnCambiar.setDisable(false);
            btnEliminar.setDisable(false);
        }
    }
    private void limpiar() {
        // limpiamos
        btnCambiar.setDisable(true);
        btnEliminar.setDisable(true);
        editWarningLabel.setText("Actualizando...");
        fieldDescripcion.clear();
        cosechas.getItems().clear();
        tabla.getItems().clear();
        comboLotes.getItems().clear();
        tabla.getItems().addAll(servicio.listarCuadros());
        comboLotes.getItems().addAll(servicioL.listarLotes());
        editWarningLabel.setText(" ");
    }

    public VBox getContenedor() {
        return this.contenedor;
    }

    public static ServicioCuadro getServicioCuadro() {
        return servicio;
    }
}
