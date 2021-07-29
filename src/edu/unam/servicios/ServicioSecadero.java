package edu.unam.servicios;

import edu.unam.repositorio.*;

import java.util.List;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Secadero;

public class ServicioSecadero {

    private Repositorio repo;

    public ServicioSecadero(Repositorio r) {
        this.repo = r;
    }

    public List<Secadero> listarSecaderos() {
        return this.repo.buscarTodos(Secadero.class);
    }

    public Secadero buscarSecadero(int idSecadero) {
        return this.repo.buscar(Secadero.class, idSecadero);
    }

    public void agregarSecadero(Long cuit, String razonSocial, List<Cosecha> cosechas) {
        if (cuit != null || razonSocial.trim().length() == 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repo.iniciarTransaccion();
        Productor productor = new Productor(cuit.toUpperCase().trim(), apellidos.toUpperCase().trim(), nombres.toUpperCase().trim());
        this.repo.insertar(productor);
        this.repo.confirmarTransaccion();
    }
}
