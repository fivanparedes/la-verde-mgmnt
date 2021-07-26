package edu.unam.servicios;

import edu.unam.repositorio.Repositorio;

import java.time.LocalDate;
import java.util.List;

import edu.unam.modelo.Empleado;

public class ServicioEmpleado {
    private Repositorio repo;

    public ServicioEmpleado(Repositorio r) {
        this.repo = r;
    }

    public List<Empleado> listarEmpleados() {
        return this.repo.buscarOrdenAsc(Empleado.class, idEmpleado);
    }

    public Empleado buscarEmpleado(Long idEmpleado) {
        return this.repo.buscar(Empleado.class, idEmpleado);
    }

    public void agregarEmpleado(String nombres, String apellidos, int dni, String legajo, LocalDate fechaIngreso, LocalDate nacimiento, long cuil) {
        if (nombres.trim().length() == 0 || apellidos.trim().length() == 0 || legajo.trim().length() == 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repo.iniciarTransaccion();
        Empleado empleado = new Empleado(nombres.toUpperCase().trim(), apellidos.toUpperCase().trim(), departamento);
        this.repo.insertar(empleado);
        this.repo.confirmarTransaccion();
    }

    // cambiar valor devuelto (por ejemplo: True ok, False problemas)
    public void editarEmpleado(Long idEmpleado, String nombres, String apellidos) {
        if (nombres.trim().length() == 0 || apellidos.trim().length() == 0 || 
            departamento == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repo.iniciarTransaccion();
        Empleado empleado = this.repo.buscar(Empleado.class, idEmpleado);
        if (empleado != null) {
            empleado.setApellidos(apellidos);
            empleado.setNombres(nombres);
            // implementar comparable o comparator
            // o si el id es unico pueden compararar por id
            /* if (! empleado.getDepartamento().equals(departamento)) {
                empleado.getDepartamento().getEmpleados().remove(empleado);
                empleado.setDepartamento(departamento);
                departamento.getEmpleados().add(empleado);
            } */
            this.repo.modificar(empleado);
            this.repo.confirmarTransaccion();
        } else {
            this.repo.descartarTransaccion();
        }
    }

    public int eliminarEmpleado(Long idEmpleado) {
        this.repo.iniciarTransaccion();
        Empleado empleado = this.repo.buscar(Empleado.class, idEmpleado);
        // como se soluciona??
        if (empleado != null && empleado.getProyectos().isEmpty() ) {
            this.repo.eliminar(empleado);
            this.repo.confirmarTransaccion();
            return 0;
        } else {
            this.repo.descartarTransaccion();
            return 1;
        }
    }
}
