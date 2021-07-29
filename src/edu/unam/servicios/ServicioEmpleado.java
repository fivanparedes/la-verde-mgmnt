package edu.unam.servicios;

import edu.unam.repositorio.Repositorio;

import java.time.LocalDate;
import java.util.List;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Empleado;

public class ServicioEmpleado extends Servicio {
    public ServicioEmpleado(Repositorio repositorio) {
        super(repositorio);
    }

    public List<Empleado> listarEmpleados() {
        return this.repositorio.buscarTodos(Empleado.class);
    }

    public Empleado buscarEmpleado(long idEmpleado) {
        return this.repositorio.buscar(Empleado.class, idEmpleado);
    }
  
    //Hacer validaciones extra de ser necesario
    public void agregarEmpleado(String nombres, String apellidos, int dni, String legajo, LocalDate fechaIngreso, LocalDate nacimiento, long cuil) {
        if (nombres.trim().length() == 0 || apellidos.trim().length() == 0 || legajo.trim().length() == 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        if (dni <= 0 || fechaIngreso == null || nacimiento == null || cuil <= 0) {
            throw new IllegalArgumentException("Datos erróneos.");
        }
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(new Empleado(legajo.toUpperCase().trim(), dni, apellidos.toUpperCase().trim(), nombres.toUpperCase().trim(), fechaIngreso, nacimiento, cuil));
        this.repositorio.confirmarTransaccion();
    }

    // Edita el empleado con los valores indicados, pienso muy seriamente en hacer sobrecarga de este metodo, pero por ahora no lo hare.
    // En caso de error, tira un codigo de error 1, de lo contrario, el cero indica transaccion exitosa.
    // El ID funciona como parametro de busqueda, el resto de argumentos son los nuevos datos a modificar.
    // Por concenso, la lista de cosechas no se modificara desde la pantalla de Empleados sino desde la pantalla de cosechas, por lo que esta funcion sera
    // exclusivamente datos personales del empleado (que se modificaran con menos frecuencia que las cosechas)
    public int editarEmpleado(long idEmpleado, String nombres, String apellidos, int dni, String legajo, LocalDate fechaIngreso, LocalDate nacimiento, long cuil) {
        if (nombres.trim().length() == 0 || apellidos.trim().length() == 0 || legajo.trim().length() == 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Empleado empleado = buscarEmpleado(idEmpleado);
        if (empleado != null) {
            if (empleado.getApellidos() != apellidos) {
                empleado.setApellidos(apellidos);
            }
            if (empleado.getNombres() != nombres) {
                empleado.setNombres(nombres);
            }
            if (empleado.getLegajo() != legajo) {
                empleado.setLegajo(legajo);
            }
            if (empleado.getDni() != dni) {
                empleado.setDni(dni);
            }
            if (empleado.getCuil() != cuil) {
                empleado.setCuil(cuil);
            }
            if (empleado.getIngreso() != fechaIngreso) {
                empleado.setIngreso(fechaIngreso);
            }
            if (empleado.getNacimiento() != nacimiento) {
                empleado.setNacimiento(nacimiento);
            }
            this.repositorio.modificar(empleado);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    public int eliminarEmpleado(long idEmpleado) {
        this.repositorio.iniciarTransaccion();
        Empleado empleado = buscarEmpleado(idEmpleado);
        // hacer todos los chequeos necesarios antes de eliminar
        if (empleado != null && empleado.getCosechas().isEmpty()) {
            this.repositorio.eliminar(empleado);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }
}
