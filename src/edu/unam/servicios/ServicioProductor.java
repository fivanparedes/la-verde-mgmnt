package edu.unam.servicios;

import edu.unam.repositorio.*;

import java.util.List;

import edu.unam.modelo.Productor;

public class ServicioProductor {

    private Repositorio repo;

    public ServicioProductor(Repositorio r) {
        this.repo = r;
    }

    public List<Productor> listarProductores() {
        return this.repo.buscarTodos(Productor.class);
    }

    public Productor buscarProductor(int idProductor) {
        return this.repo.buscar(Productor.class, idProductor);
    }

    public void agregarProdutor(String cuit, String apellidos, String nombres) {
        if (cuit.trim().length() == 0 || apellidos.trim().length() == 0 || nombres.trim().length() == 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repo.iniciarTransaccion();
        Productor productor = new Productor(cuit.toUpperCase().trim(), apellidos.toUpperCase().trim(), nombres.toUpperCase().trim());
        this.repo.insertar(productor);
        this.repo.confirmarTransaccion();
    }

    public void editarProductor(int idProductor, String cuit, String apellidos, String nombres) {
        if (cuit.trim().length() == 0 || apellidos.trim().length() == 0 || nombres.trim().length() == 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repo.iniciarTransaccion();
        Productor productor = this.repo.buscar(Productor.class, idProductor);
        if (productor != null) {
            productor.setCuit(cuit);
            productor.setApellidos(apellidos);
            productor.setNombres(nombres);
    
            this.repo.modificar(productor);
            this.repo.confirmarTransaccion();
        } else {
            this.repo.descartarTransaccion();
        }
    }

    public int eliminarProductor(int idProductor) {
        this.repo.iniciarTransaccion();
        Productor productor = this.repo.buscar(Productor.class, idProductor);
        
        //En este caso si el productor tiene asignado lotes no se lo puede eliminar
        if (productor != null && productor.getLotes().isEmpty()){
            this.repo.eliminar(productor);
            this.repo.confirmarTransaccion();
            return 0;
        } else {
            this.repo.descartarTransaccion();
            return 1;
        }
    }
}