package edu.unam.servicios;

import edu.unam.repositorio.Repositorio;

import java.time.LocalDateTime;
import java.util.List;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Entrega;

public class ServicioEntrega extends Servicio {
    public ServicioEntrega(Repositorio repositorio) {
        super(repositorio);
    }

    public List<Entrega> listarEntregas() {
        return this.repositorio.buscarTodos(Entrega.class);
    }

    public Entrega buscarEntrega(int idCosecha) {
        return this.repositorio.buscar(Entrega.class, idCosecha);
    }

    public void agregarEntrega(int idCosecha, float pesoEnt, LocalDateTime tiempoEnt) {
        this.repositorio.iniciarTransaccion();
        Cosecha cosecha = this.repositorio.buscar(Cosecha.class, idCosecha);
        if (cosecha == null) {
            this.repositorio.descartarTransaccion();
            throw new IllegalArgumentException("La cosecha no existe");
        }
        this.repositorio.insertar(new Entrega(pesoEnt, cosecha.getFecha(), cosecha.getCuadros(), cosecha.getEmpleados(), cosecha.getSecadero(), tiempoEnt));
        this.repositorio.confirmarTransaccion();
    }

    public int editarEntrega(int idCosecha, float pesoEnt, LocalDateTime tiempoEnt) {
        this.repositorio.iniciarTransaccion();
        Cosecha cosecha = this.repositorio.buscar(Cosecha.class, idCosecha);
        if (cosecha == null) {
            this.repositorio.descartarTransaccion();
            throw new IllegalArgumentException("La cosecha no existe");
        }
        Entrega entrega = buscarEntrega(idCosecha);
        if (entrega != null) {
            entrega.setPesoEnt(pesoEnt);
            entrega.setTiempoEnt(tiempoEnt);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    public int eliminarEntrega(int idCosecha) {
        this.repositorio.iniciarTransaccion();
        Cosecha cosecha = this.repositorio.buscar(Cosecha.class, idCosecha);
        if (cosecha == null) {
            this.repositorio.descartarTransaccion();
            throw new IllegalArgumentException("La cosecha no existe");
        }
        Entrega entrega = buscarEntrega(idCosecha);
        if (entrega != null) {
            this.repositorio.eliminar(entrega);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }
}
