package edu.unam.servicios;

import edu.unam.repositorio.Repositorio;

import java.time.LocalDate;
import java.util.List;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Cuadro;
import edu.unam.modelo.Empleado;
import edu.unam.modelo.Secadero;

public class ServicioCosecha extends Servicio {
    private String aux;

    public ServicioCosecha(Repositorio repositorio) {
        super(repositorio);
    }

    public List<Cosecha> listarCosechas() {
        return this.repositorio.buscarTodos(Cosecha.class);
    }

    public Cosecha buscarCosecha(int idCosecha) {
        return this.repositorio.buscar(Cosecha.class, idCosecha);
    }

    public void agregarCosecha(LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados, Secadero secadero) {
        for (Cuadro cuadro : cuadros) {
            if (!(this.repositorio.buscarTodos(Cuadro.class).contains(cuadro))) {
                throw new IllegalArgumentException("Hay un cuadro que no existe");
            }
        }
        for (Empleado empleado : empleados) {
            if (!(this.repositorio.buscarTodos(Empleado.class).contains(empleado))) {
                aux = "El empleado " + empleado.getLegajo() + " no existe";
                throw new IllegalArgumentException(aux);
            }
        }
        if (!(this.repositorio.buscarTodos(Secadero.class).contains(secadero))) {
            throw new IllegalArgumentException("El secadero no existe");
        }
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(new Cosecha(fecha, cuadros, empleados, secadero));
        this.repositorio.confirmarTransaccion();
    }

    public int editarCosecha(int idCosecha, LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados,
            Secadero secadero) {
        for (Cuadro cuadro : cuadros) {
            if (!(this.repositorio.buscarTodos(Cuadro.class).contains(cuadro))) {
                throw new IllegalArgumentException("Hay un cuadro que no existe");
            }
        }
        for (Empleado empleado : empleados) {
            if (!(this.repositorio.buscarTodos(Empleado.class).contains(empleado))) {
                aux = "El empleado " + empleado.getLegajo() + " no existe";
                throw new IllegalArgumentException(aux);
            }
        }
        if (!(this.repositorio.buscarTodos(Secadero.class).contains(secadero))) {
            throw new IllegalArgumentException("El secadero no existe");
        }
        this.repositorio.iniciarTransaccion();
        Cosecha cosecha = buscarCosecha(idCosecha);
        if (cosecha != null) {
            cosecha.setFecha(fecha);
            cosecha.setCuadros(cuadros);
            cosecha.setEmpleados(empleados);
            cosecha.setSecadero(secadero);
            this.repositorio.modificar(cosecha);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    public int eliminarCosecha(int idCosecha) {
        this.repositorio.iniciarTransaccion();
        Cosecha cosecha = buscarCosecha(idCosecha);
        if (cosecha != null) {
            for (Cuadro cuadro : cosecha.getCuadros()) {
                cuadro.getCosechas().remove(cosecha);
            }
            for (Empleado empleado : cosecha.getEmpleados()) {
                empleado.getCosechas().remove(cosecha);
            }
            cosecha.getSecadero().getCosechas().remove(cosecha);
            this.repositorio.eliminar(cosecha);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }
}