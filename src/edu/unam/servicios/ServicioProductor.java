package edu.unam.servicios;

import edu.unam.repositorio.*;

import java.util.List;

import edu.unam.modelo.Productor;

public class ServicioProductor extends Servicio {
    public ServicioProductor(Repositorio repositorio) {
        super(repositorio);
    }

    public List<Productor> listarProductores() {
        return this.repositorio.buscarTodos(Productor.class);
    }

    public Productor buscarProductor(int idProductor) {
        return this.repositorio.buscar(Productor.class, idProductor);
    }

    public void agregarProdutor(long cuit, String apellidos, String nombres) {
        if (apellidos.trim().length() == 0 || nombres.trim().length() == 0 || cuit < 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        
        Productor productor = new Productor(cuit, apellidos.toUpperCase().trim(), nombres.toUpperCase().trim());
        List<Productor> listaProductor = listarProductores();
        
        //Control de CUIT
        for (int i=0; i<listaProductor.size();i++){
            if (productor.getCuit() == listaProductor.get(i).getCuit()){
                this.repositorio.descartarTransaccion();
                throw new IllegalArgumentException("Ya existe un productor con ese CUIT");
            }
        }
        this.repositorio.insertar(productor);
        this.repositorio.confirmarTransaccion();
    }

    public void editarProductor(int idProductor, long cuit, String apellidos, String nombres) {
        if (apellidos.trim().length() == 0 || nombres.trim().length() == 0 || cuit < 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        List<Productor> listaProductor = listarProductores();
        Productor productor = buscarProductor(idProductor);
        
        if (productor != null) {
            productor.setCuit(cuit);
            productor.setApellidos(apellidos);
            productor.setNombres(nombres);
            this.repositorio.modificar(productor);
            
            //Control de CUIT
            for (int i=0; i<listaProductor.size();i++){
              if (productor.getCuit() == listaProductor.get(i).getCuit() && productor.getIdProductor() != listaProductor.get(i).getIdProductor()){
                this.repositorio.descartarTransaccion();
                throw new IllegalArgumentException("Ya existe un productor con ese CUIT");
                }
            }
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }

    public int eliminarProductor(int idProductor) {
        this.repositorio.iniciarTransaccion();
        Productor productor = buscarProductor(idProductor);

        // En este caso si el productor tiene asignado lotes no se lo puede eliminar
        if (productor != null && productor.getLotes().isEmpty()) {
            this.repositorio.eliminar(productor);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }
}
