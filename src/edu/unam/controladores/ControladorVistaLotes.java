package edu.unam.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import edu.unam.modelo.Cuadro;
import edu.unam.modelo.Lote;
import edu.unam.modelo.Productor;
import edu.unam.servicios.ServicioLote;
import edu.unam.servicios.ServicioProductor;
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

public class ControladorVistaLotes implements Initializable {
    private Lote loteSeleccionado;
    private static ServicioLote servicio;
    private static ServicioProductor servicioP;
    @FXML
    private TableView<Lote> tabla;
    @FXML
    private TableColumn<Lote, Integer> columnaId;
    @FXML
    private TableColumn<Lote, String> columnaPuntos;
    @FXML
    private TableColumn<Lote, Productor> columnaProductor;
    @FXML
    private TableView<Cuadro> cuadros;
    @FXML
    private TableColumn<Lote, Integer> columnaIdCuadro;
    @FXML
    private TableColumn<Lote, String> columnaDescripcionCuadro;
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
    private TextField fieldX1;
    @FXML
    private TextField fieldY1;
    @FXML
    private TextField fieldX2;
    @FXML
    private TextField fieldY2;
    @FXML
    private ComboBox<Productor> comboProductores;
    @FXML
    private VBox contenedor;

    @FXML
    private Label label;

    public static void enlazarServicio(ServicioLote s1, ServicioProductor s2) {
        servicio = s1;
        servicioP = s2;
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Successfully initialized");
        tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idLote"));
        columnaPuntos.setCellValueFactory(new PropertyValueFactory<>("puntos"));
        columnaProductor.setCellValueFactory(new PropertyValueFactory<>("productor"));
        //TODO: hacer andar el servicio.
        tabla.getItems().addAll(servicio.listarLotes());
        comboProductores.getItems().addAll(servicioP.listarProductores());
        tabla.getSelectionModel().selectedItemProperty().addListener(e -> cargarDatos());
        columnaIdCuadro.setCellValueFactory(new PropertyValueFactory<>("idCuadro"));
        columnaDescripcionCuadro.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        btnCambiar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    @FXML
    void showEditWarning() {
        editWarningLabel.setText("Hay campos rellenados.");
    }

    @FXML
    private void clicNuevo() {
        tabla.getSelectionModel().clearSelection();
        try {
            double[] p1 = {Double.parseDouble(fieldX1.getText()), Double.parseDouble(fieldY1.getText())};
            double[] p2 = {Double.parseDouble(fieldX2.getText()), Double.parseDouble(fieldY2.getText())};
            servicio.agregarLote(p1, p2, comboProductores.getSelectionModel().getSelectedItem());
            limpiar();
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al guardar", "Revise los datos ingresados en los campos de arriba.");
        }
    }
    @FXML
    private void clicEliminar() {
        loteSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (loteSeleccionado != null) {
            servicio.eliminarLote(loteSeleccionado.getIdLote());
            limpiar();
        }
    }

    @FXML
    private void cambiarDatos() {
        loteSeleccionado = tabla.getSelectionModel().getSelectedItem();
        double[] p1 = {Double.parseDouble(fieldX1.getText()), Double.parseDouble(fieldY1.getText())};
        double[] p2 = {Double.parseDouble(fieldX2.getText()), Double.parseDouble(fieldY2.getText())};
        if (loteSeleccionado != null) {
            servicio.editarLote(loteSeleccionado.getIdLote(), p1, p2, comboProductores.getSelectionModel().getSelectedItem());
            limpiar();
        }    
    }

    @FXML
    private void mostrarAyuda() {
        VistaUtils.mostrarAlerta(AlertType.INFORMATION, "Ayuda - Lotes", "Mensaje de ayuda:", "Para agregar lotes, es necesario contar con la existencia de Productores en el sistema, no así para los cuadros que compongan al lote.");
    }

    /* Este procedimiento introduce los valores del campo seleccionado en los campos de texto respectivos, y tambien carga la lista de cosechas */
    @FXML
    private void cargarDatos() {
        loteSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (loteSeleccionado != null) {
            cuadros.getItems().clear();
            //etiquetaIdEmpleado.setText(String.valueOf(loteSeleccionado.getIdEmpleado()));
            fieldX1.setText(Double.toString(loteSeleccionado.getPunto1()[0]));
            fieldY1.setText(Double.toString(loteSeleccionado.getPunto1()[1]));
            fieldX2.setText(Double.toString(loteSeleccionado.getPunto2()[0]));
            fieldY2.setText(Double.toString(loteSeleccionado.getPunto2()[1]));
            comboProductores.getSelectionModel().select(loteSeleccionado.getProductor());
            cuadros.getItems().addAll(loteSeleccionado.getCuadros());
            btnCambiar.setDisable(false);
            btnEliminar.setDisable(false);
        }
    }
    private void limpiar() {
        // limpiamos
        cuadros.getItems().clear();
        btnCambiar.setDisable(true);
        btnEliminar.setDisable(true);
        editWarningLabel.setText("Actualizando...");
        fieldX1.clear();
        fieldX2.clear();
        fieldY1.clear();
        fieldY2.clear();
        cuadros.getItems().clear();
        tabla.getItems().clear();
        tabla.getItems().addAll(servicio.listarLotes());
        editWarningLabel.setText(" ");
    }

    public VBox getContenedor() {
        return this.contenedor;
    }
    
}
