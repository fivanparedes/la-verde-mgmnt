package edu.unam.servicios;

import edu.unam.repositorio.Repositorio;

import java.time.LocalDate;
import java.util.List;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Empleado;

public class ServicioEmpleado {
    private Repositorio repo;

    public ServicioEmpleado(Repositorio r) {
        this.repo = r;
    }

    public List<Empleado> listarEmpleados() {
        return this.repo.buscarTodos(Empleado.class);
    }

    public Empleado buscarEmpleado(Long idEmpleado) {
        return this.repo.buscar(Empleado.class, idEmpleado);
    }

    // Esto recupera la lista de cosechas correspondientes al empleado segun su ID.
    // Esto es util ya que busca a un empleado existente en la BBDD para insertar la lista en una tabla visual.
    public List<Cosecha> extraerCosechas(int idEmpleado) {
        Empleado emp = this.repo.buscar(Empleado.class, idEmpleado);
        return emp.getCosechas();
    }

    //Hacer validaciones extra de ser necesario
    public void agregarEmpleado(String nombres, String apellidos, int dni, String legajo, LocalDate fechaIngreso, LocalDate nacimiento, long cuil) {
        if (nombres.trim().length() == 0 || apellidos.trim().length() == 0 || legajo.trim().length() == 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        if (dni <= 0 || fechaIngreso == null || nacimiento == null || cuil <= 0) {
            throw new IllegalArgumentException("Datos erróneos.");
        }
        this.repo.iniciarTransaccion();
        this.repo.insertar(new Empleado(legajo.toUpperCase().trim(), dni, apellidos.toUpperCase().trim(), nombres.toUpperCase().trim(), fechaIngreso, nacimiento, cuil));
        this.repo.confirmarTransaccion();
    }

    // Edita el empleado con los valores indicados, pienso muy seriamente en hacer sobrecarga de este metodo, pero por ahora no lo hare.
    // En caso de error, tira un codigo de error 1, de lo contrario, el cero indica transaccion exitosa.
    // El ID funciona como parametro de busqueda, el resto de argumentos son los nuevos datos a modificar.
    // Por concenso, la lista de cosechas no se modificara desde la pantalla de Empleados sino desde la pantalla de cosechas, por lo que esta funcion sera
    // exclusivamente datos personales del empleado (que se modificaran con menos frecuencia que las cosechas)
    public int editarEmpleado(Long idEmpleado, String nombres, String apellidos, int dni, String legajo, LocalDate fechaIngreso, LocalDate nacimiento, long cuil) {
        if (nombres.trim().length() == 0 || apellidos.trim().length() == 0 || legajo.trim().length() == 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repo.iniciarTransaccion();
        Empleado empleado = this.repo.buscar(Empleado.class, idEmpleado);
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
            this.repo.modificar(empleado);
            this.repo.confirmarTransaccion();
            return 0;
        } else {
            this.repo.descartarTransaccion();
            return 1;
        }
    }

    public int eliminarEmpleado(Long idEmpleado) {
        this.repo.iniciarTransaccion();
        Empleado empleado = this.repo.buscar(Empleado.class, idEmpleado);
        // hacer todos los chequeos necesarios antes de eliminar
        if (empleado != null && empleado.getCosechas().isEmpty()) {
            this.repo.eliminar(empleado);
            this.repo.confirmarTransaccion();
            return 0;
        } else {
            this.repo.descartarTransaccion();
            return 1;
        }
    }

    //Esto permite agregar una cosecha a la lista de cosechas que tiene que hacer el empleado.
    //Como parametros, se admite el id del empleado y la cosecha en si. (Podria cambiarse para id de cosecha de ser mas comodo)
    //La funcion chequea que la cosecha en cuestion exista y que no este contenida en la lista de cosechas de empleado.
    //Retorna cero en caso de exito, retorna uno en caso de error.
    public int agregarCosecha(Long idEmpleado, Cosecha cosecha) {
        this.repo.iniciarTransaccion();
        Empleado empleado = this.repo.buscar(Empleado.class, idEmpleado);
        List<Cosecha> listaCosechas = empleado.getCosechas();
        if (this.repo.buscar(Cosecha.class, cosecha.getIdCosecha()) == cosecha && !listaCosechas.contains(cosecha)) {
            listaCosechas.add(cosecha);
            empleado.setCosechas(listaCosechas);
            this.repo.modificar(empleado);
            this.repo.confirmarTransaccion();
            return 0;
        } else {
            this.repo.descartarTransaccion();
            return 1;
        }
    }

    //Esta funcion solamente elimina la cosecha de la lista para desvincularla del empleado, no elimina la cosecha en si, para ello
    //tenemos el servicio de cosechas. Usar ese servicio y esta funcion en conjunto cuando corresponda.
    //Solo se controla que la cosecha este en lista ya que la funcion de agregar hace validaciones previas.
    //Retorna cero en caso de exito, retorna uno en caso de error.
    public int eliminarCosecha(Long idEmpleado, Cosecha cosecha) {
        this.repo.iniciarTransaccion();
        Empleado empleado = this.repo.buscar(Empleado.class, idEmpleado);
        List<Cosecha> listaCosechas = empleado.getCosechas();
        if (listaCosechas.contains(cosecha)) {
            listaCosechas.remove(cosecha);
            empleado.setCosechas(listaCosechas);
            this.repo.modificar(empleado);
            this.repo.confirmarTransaccion();
            return 0;
        } else {
            this.repo.descartarTransaccion();
            return 1;
        }
    }
}
