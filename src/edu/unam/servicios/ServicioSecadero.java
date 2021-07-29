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
        if (cuit == null || razonSocial.trim().length() == 0 || cosechas == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repo.iniciarTransaccion();
        Secadero secadero = new Secadero(cuit, razonSocial.toUpperCase().trim(), cosechas);
        this.repo.insertar(secadero);
        this.repo.confirmarTransaccion();
    }

    public void editarSecadero(int idSecadero, Long cuit, String razonSocial, List<Cosecha> cosechas) {
        if (cuit == null || razonSocial.trim().length() == 0 || cosechas == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repo.iniciarTransaccion();
        Secadero secadero = this.repo.buscar(Secadero.class, idSecadero);
        if (secadero != null) {
            secadero.setCuit(cuit);
            secadero.setRazonSocial(razonSocial);
            secadero.setCosechas(cosechas);
    
            this.repo.modificar(secadero);
            this.repo.confirmarTransaccion();
        } else {
            this.repo.descartarTransaccion();
        }
    }

    public int eliminarSecadero(int idSecadero) {
        this.repo.iniciarTransaccion();
        Secadero secadero = this.repo.buscar(Secadero.class, idSecadero);

        if(secadero != null){
            this.repo.eliminar(secadero);
            this.repo.confirmarTransaccion();
            return 0;
        } else {
            this.repo.descartarTransaccion();
            return 1;
        }
    }
}
