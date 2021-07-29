package edu.unam.servicios;

import edu.unam.repositorio.*;

import java.util.List;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Secadero;

public class ServicioSecadero extends Servicio {
    public ServicioSecadero(Repositorio repositorio) {
        super(repositorio);
    }

    public List<Secadero> listarSecaderos() {
        return this.repositorio.buscarTodos(Secadero.class);
    }

    public Secadero buscarSecadero(int idSecadero) {
        return this.repositorio.buscar(Secadero.class, idSecadero);
    }

    public void agregarSecadero(Long cuit, String razonSocial, List<Cosecha> cosechas) {
        if (cuit == null || razonSocial.trim().length() == 0 || cosechas == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Secadero secadero = new Secadero(cuit, razonSocial.toUpperCase().trim(), cosechas);
        this.repositorio.insertar(secadero);
        this.repositorio.confirmarTransaccion();
    }

    public void editarSecadero(int idSecadero, Long cuit, String razonSocial, List<Cosecha> cosechas) {
        if (cuit == null || razonSocial.trim().length() == 0 || cosechas == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Secadero secadero = this.repositorio.buscar(Secadero.class, idSecadero);
        if (secadero != null) {
            secadero.setCuit(cuit);
            secadero.setRazonSocial(razonSocial);
            secadero.setCosechas(cosechas);
    
            this.repositorio.modificar(secadero);
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }

    public int eliminarSecadero(int idSecadero) {
        this.repositorio.iniciarTransaccion();
        Secadero secadero = this.repositorio.buscar(Secadero.class, idSecadero);
        
        //chequeos necesarios antes de eliminar
        if(secadero != null && secadero.getCosechas().isEmpty()){
            this.repositorio.eliminar(secadero);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }
}
