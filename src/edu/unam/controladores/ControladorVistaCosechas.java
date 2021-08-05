package edu.unam.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

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
import javafx.collections.FXCollections;
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
    private Button agregarCuadro;
    @FXML
    private Button quitarCuadro;
    @FXML
    private Button cambiarCuadro;
    @FXML
    private Button agregarEmpleado;
    @FXML
    private Button quitarEmpleado;
    @FXML
    private Button cambiarEmpleado;
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
    //En este caso, este VBox es el contenedor que engloba todos los elementos de mi vista de Cosechas. Gracias al metodo getChildren() puedo obtener
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
        cuadros.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        empleados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idCosecha"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaSecadero.setCellValueFactory(new PropertyValueFactory<>("secadero"));
        //TODO: hacer andar el servicio.
        //tabla.getItems().addAll(servicio.listarCosechas());
        tabla.getSelectionModel().selectedItemProperty().addListener(e -> cargarDatos());
        columnaIdCuadro.setCellValueFactory(new PropertyValueFactory<>("idCuadro"));
        cuadros.getSelectionModel().selectedItemProperty().addListener(e -> cargarCuadro());
        columnaIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        empleados.getSelectionModel().selectedItemProperty().addListener(e -> cargarEmpleado());
    }

    @FXML
    void showEditWarning() {
        editWarningLabel.setText("Hay campos rellenados.");
    }

    @FXML
    private void clicNuevo() {
        int idSecadero;
        float peso;
        Secadero aux;
        Cosecha nuevo = new Cosecha();
        tabla.getSelectionModel().clearSelection();
        try {
            idSecadero = Integer.getInteger(fieldSecadero.getText()).intValue();
            aux = ControladorVistaSecaderos.getServicioSecadero().buscarSecadero(idSecadero);
            if (aux != null) {
                servicioCosecha.agregarCosecha(fieldFecha.getValue(), cuadros.getItems(), empleados.getItems(), aux);
                for (Cosecha comparado: servicioCosecha.listarCosechas()) {
                    if (tabla.getItems().contains(comparado)) {
                        continue;
                    } else {
                        nuevo = comparado;
                        break;
                    }
                }
                if (!(fieldPesoEnt.getText().equals(""))) {
                    peso = Float.valueOf(fieldPesoEnt.getText()).floatValue();
                    servicioEntrega.agregarEntrega(nuevo.getIdCosecha(), peso, fieldTiempoEnt.getValue());
                }
                if (!(fieldPesoReg.getText().equals(""))) {
                    peso = Float.valueOf(fieldPesoReg.getText()).floatValue();
                    servicioRegistro.agregarRegistro(nuevo.getIdCosecha(), peso, fieldTiempoReg.getValue());
                }
            }        
            limpiar();
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al guardar", e.getMessage());
        }
    }

    @FXML
    private void clicEliminar() {
        Entrega entElim;
        Registro regElim;
        cosechaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (cosechaSeleccionada != null) {
            entElim = servicioEntrega.buscarEntrega(cosechaSeleccionada.getIdCosecha());
            if (entElim != null) {
                servicioEntrega.eliminarEntrega(cosechaSeleccionada.getIdCosecha());
            }
            regElim = servicioRegistro.buscarRegistro(cosechaSeleccionada.getIdCosecha());
            if (regElim != null) {
                servicioRegistro.eliminarRegistro(cosechaSeleccionada.getIdCosecha());
            }
            servicioCosecha.eliminarCosecha(cosechaSeleccionada.getIdCosecha());
            limpiar();
        }
    }

    @FXML
    private void cambiarDatos() {
        int idSecadero;
        float peso;
        Secadero aux;
        Entrega ent;
        Registro reg;
        cosechaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (cosechaSeleccionada != null) {
            idSecadero = Integer.getInteger(fieldSecadero.getText()).intValue();
            aux = ControladorVistaSecaderos.getServicioSecadero().buscarSecadero(idSecadero);
            if (aux != null) {
                servicioCosecha.editarCosecha(cosechaSeleccionada.getIdCosecha(), fieldFecha.getValue(), cuadros.getItems(), empleados.getItems(), aux);
            }
            ent = servicioEntrega.buscarEntrega(cosechaSeleccionada.getIdCosecha());
            if (ent != null) {
                if (fieldPesoEnt.getText().equals("")) {
                    servicioEntrega.eliminarEntrega(cosechaSeleccionada.getIdCosecha());
                } else {
                    peso = Float.valueOf(fieldPesoEnt.getText()).floatValue();
                    servicioEntrega.editarEntrega(cosechaSeleccionada.getIdCosecha(), peso, fieldTiempoEnt.getValue());
                }
            } else {
                if (!(fieldPesoEnt.getText().equals(""))) {
                    peso = Float.valueOf(fieldPesoEnt.getText()).floatValue();
                    servicioEntrega.agregarEntrega(cosechaSeleccionada.getIdCosecha(), peso, fieldTiempoEnt.getValue());
                }
            }
            reg = servicioRegistro.buscarRegistro(cosechaSeleccionada.getIdCosecha());
            if (reg != null) {
                if (fieldPesoReg.getText().equals("")) {
                    servicioRegistro.eliminarRegistro(cosechaSeleccionada.getIdCosecha());
                } else {
                    peso = Float.valueOf(fieldPesoReg.getText()).floatValue();
                    servicioRegistro.editarRegistro(cosechaSeleccionada.getIdCosecha(), peso, fieldTiempoReg.getValue());
                }
            } else {
                if (!(fieldPesoReg.getText().equals(""))) {
                    peso = Float.valueOf(fieldPesoReg.getText()).floatValue();
                    servicioRegistro.agregarRegistro(cosechaSeleccionada.getIdCosecha(), peso, fieldTiempoReg.getValue());
                }
            }
            limpiar();
        }    
    }

    @FXML
    private void agregarEmpleado() {
        long idEmpleado;
        Empleado busqueda;
        List<Empleado> aux;
        aux = empleados.getItems();
        empleados.getSelectionModel().clearSelection();
        try {
            idEmpleado = Long.getLong(fieldEmpleado.getText()).longValue();
            busqueda = ControladorVistaEmpleados.getServicioEmpleado().buscarEmpleado(idEmpleado);
            if (busqueda != null) {
                aux.add(busqueda);
                empleados.setItems(FXCollections.observableList(aux));
            }        
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al cargar", e.getMessage());
        }
    }

    @FXML
    private void quitarEmpleado() {
        Empleado busqueda;
        List<Empleado> aux;
        aux = empleados.getItems();
        busqueda = empleados.getSelectionModel().getSelectedItem();
        aux.remove(busqueda);
        empleados.setItems(FXCollections.observableList(aux)); 
    }

    @FXML
    private void cambiarEmpleado() {
        quitarEmpleado();
        agregarEmpleado();  
    }

    @FXML
    private void agregarCuadro() {
        int idCuadro;
        Cuadro busqueda;
        List<Cuadro> aux;
        aux = cuadros.getItems();
        cuadros.getSelectionModel().clearSelection();
        try {
            idCuadro = Integer.getInteger(fieldCuadro.getText()).intValue();
            busqueda = ControladorVistaCuadros.getServicioCuadro().buscarCuadro(idCuadro);
            if (busqueda != null) {
                aux.add(busqueda);
                cuadros.setItems(FXCollections.observableList(aux));
            }        
        } catch (Exception e) {
            VistaUtils.mostrarAlerta(AlertType.ERROR, "Error", "Error al cargar", e.getMessage());
        }
    }

    @FXML
    private void quitarCuadro() {
        Cuadro busqueda;
        List<Cuadro> aux;
        aux = cuadros.getItems();
        busqueda = cuadros.getSelectionModel().getSelectedItem();
        aux.remove(busqueda);
        cuadros.setItems(FXCollections.observableList(aux)); 
    }

    @FXML
    private void cambiarCuadro() {
        quitarEmpleado();
        agregarEmpleado();  
    }

    @FXML
    private void mostrarAyuda() {
        VistaUtils.mostrarAlerta(AlertType.INFORMATION, "Ayuda - Empleados", "Mensaje de ayuda:", "Solo se pueden agregar registros nuevos si los campos están llenos. En caso de seleccionar uno en la lista, presionar 'Cambiar' para modificar ese mismo registro con los datos de los campos. \n Los datos numericos como DNI y CUIL van sin puntos ni comas.\n ");
    }

    /* Este procedimiento introduce los valores del campo seleccionado en los campos de texto respectivos, cargando tambien las listas de cuadros y empleados */
    @FXML
    private void cargarDatos() {
        Entrega e;
        Registro r;
        cosechaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (cosechaSeleccionada != null) {
            fieldCuadro.setText("");
            fieldEmpleado.setText("");
            cuadros.getItems().clear();
            empleados.getItems().clear();
            fieldSecadero.setText(String.valueOf(cosechaSeleccionada.getSecadero().getIdSecadero()));
            fieldFecha.setValue(cosechaSeleccionada.getFecha());
            cuadros.getItems().addAll(cosechaSeleccionada.getCuadros());
            empleados.getItems().addAll(cosechaSeleccionada.getEmpleados());
            e = servicioEntrega.buscarEntrega(cosechaSeleccionada.getIdCosecha());
            if (e != null) {
                fieldPesoEnt.setText(String.valueOf(e.getPesoEnt()));
                fieldTiempoEnt.setValue(e.getTiempoEnt());
            } else {
                fieldPesoEnt.setText("");
                fieldTiempoEnt.setValue(LocalDate.now());
            }
            r = servicioRegistro.buscarRegistro(cosechaSeleccionada.getIdCosecha());
            if (r != null) {
                fieldPesoReg.setText(String.valueOf(r.getPesoReg()));
                fieldTiempoReg.setValue(r.getTiempoReg());
            } else {
                fieldPesoReg.setText("");
                fieldTiempoReg.setValue(LocalDate.now());
            }
        }
    }

    @FXML
    private void cargarCuadro() {
        Cuadro cuadroSeleccionado = cuadros.getSelectionModel().getSelectedItem();
        if (cuadroSeleccionado != null) {
            fieldCuadro.setText(String.valueOf(cuadroSeleccionado.getIdCuadro()));
        }
    }

    @FXML
    private void cargarEmpleado() {
        Empleado empleadoSeleccionado = empleados.getSelectionModel().getSelectedItem();
        if (empleadoSeleccionado != null) {
            fieldEmpleado.setText(String.valueOf(empleadoSeleccionado.getIdEmpleado()));
        }
    }

    private void limpiar() {
        // limpiamos
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
