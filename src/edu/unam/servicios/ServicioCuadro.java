package edu.unam.servicios;

import edu.unam.repositorio.Repositorio;

import java.util.List;

import edu.unam.modelo.Cuadro;
import edu.unam.modelo.Lote;

public class ServicioCuadro extends Servicio {
    public ServicioCuadro(Repositorio repositorio) {
        super(repositorio);
    }

    public List<Cuadro> listarCuadros() {
        return this.repositorio.buscarTodos(Cuadro.class);
    }

    public Cuadro buscarCuadro(int idCuadro) {
        return this.repositorio.buscar(Cuadro.class, idCuadro);
    }

    public void agregarCuadro(String descripcion, Lote lote) {
        if (!(this.repositorio.buscarTodos(Lote.class).contains(lote))) {
            throw new IllegalArgumentException("El lote no existe");
        }
        if (descripcion.trim().length() == 0) {
            throw new IllegalArgumentException("Hay un problema con los datos.");
        }
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(new Cuadro(descripcion.trim(), lote));
        this.repositorio.confirmarTransaccion();
    }

    public int editarCuadro(int idCuadro, String descripcion, Lote lote) {
        if (!(this.repositorio.buscarTodos(Lote.class).contains(lote))) {
            throw new IllegalArgumentException("El lote no existe");
        }
        this.repositorio.iniciarTransaccion();
        Cuadro cuadro = buscarCuadro(idCuadro);
        if (cuadro != null) {
            cuadro.setDescripcion(descripcion.trim());
            cuadro.setLote(lote);
            this.repositorio.modificar(cuadro);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    public int eliminarCuadro(int idCuadro) {
        this.repositorio.iniciarTransaccion();
        Cuadro cuadro = buscarCuadro(idCuadro);
        if (cuadro != null && cuadro.getCosechas().isEmpty()) {
            cuadro.getLote().getCuadros().remove(cuadro);
            this.repositorio.eliminar(cuadro);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }
}
