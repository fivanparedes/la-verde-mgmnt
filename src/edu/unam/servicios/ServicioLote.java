package edu.unam.servicios;

import edu.unam.repositorio.Repositorio;

import java.util.List;

import edu.unam.modelo.Lote;
import edu.unam.modelo.Productor;

public class ServicioLote extends Servicio {
    public ServicioLote(Repositorio repositorio) {
        super(repositorio);
    }

    public List<Lote> listarLotes() {
        return this.repositorio.buscarTodos(Lote.class);
    }

    public Lote buscarLote(int idLote) {
        return this.repositorio.buscar(Lote.class, idLote);
    }

    public void agregarLote(double[] punto1, double[] punto2, Productor productor) {
        if (!(this.repositorio.buscarTodos(Productor.class).contains(productor))) {
            throw new IllegalArgumentException("El productor no existe");
        }
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(new Lote(punto1, punto2, productor));
        this.repositorio.confirmarTransaccion();
    }

    public int editarLote(int idLote, double[] punto1, double[] punto2, Productor productor) {
        if (!(this.repositorio.buscarTodos(Productor.class).contains(productor))) {
            throw new IllegalArgumentException("El productor no existe");
        }
        this.repositorio.iniciarTransaccion();
        Lote lote = buscarLote(idLote);
        if (lote != null) {
            lote.setPunto1(punto1);
            lote.setPunto2(punto2);
            lote.setProductor(productor);
            this.repositorio.modificar(lote);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    public int eliminarLote(int idLote) {
        this.repositorio.iniciarTransaccion();
        Lote lote = buscarLote(idLote);
        if (lote != null && lote.getCuadros().isEmpty()) {
            lote.getProductor().getLotes().remove(lote);
            this.repositorio.eliminar(lote);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }
}
