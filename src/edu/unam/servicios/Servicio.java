package edu.unam.servicios;

import edu.unam.repositorio.Repositorio;

public class Servicio {
    protected Repositorio repositorio;

    public Servicio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }
}
