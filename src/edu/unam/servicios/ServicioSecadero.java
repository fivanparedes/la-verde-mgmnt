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

    //Removi el tercer argumento (lista de cosechas) ya que dicha lista se agregara en el servicio de cosechas.
    //Para hacer andar este servicio, agregue un tercer constructor en el modelo que acepte dos parametros.
    public void agregarSecadero(long cuit, String razonSocial) {
        if (razonSocial.trim().length() == 0 || cuit <= 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();

        Secadero secadero = new Secadero(cuit, razonSocial.toUpperCase().trim());
        List<Secadero> listaSecaderos = listarSecaderos();
        
        //Control de CUIT
        for (int i=0; i<listaSecaderos.size();i++){
            if (secadero.getCuit() == listaSecaderos.get(i).getCuit()){
                this.repositorio.descartarTransaccion();
                throw new IllegalArgumentException("Ya existe un secadero con ese CUIT");
            }
        }
        this.repositorio.insertar(secadero);
        this.repositorio.confirmarTransaccion();
    }

    public void editarSecadero(int idSecadero, long cuit, String razonSocial, List<Cosecha> cosechas) {
        if (razonSocial.trim().length() == 0 || cuit <= 0) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();

        List<Secadero> listaSecaderos = listarSecaderos();
        Secadero secadero = buscarSecadero(idSecadero);
        if (secadero != null) {
            secadero.setCuit(cuit);
            secadero.setRazonSocial(razonSocial);
            secadero.setCosechas(cosechas);
            this.repositorio.modificar(secadero);
            
            //Control de CUIT
            for (int i=0; i<listaSecaderos.size();i++){
                if (secadero.getCuit() == listaSecaderos.get(i).getCuit() && secadero.getIdSecadero() != listaSecaderos.get(i).getIdSecadero()){
                  this.repositorio.descartarTransaccion();
                  throw new IllegalArgumentException("Ya existe un secadero con ese CUIT");
                  }
              }
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }

    public int eliminarSecadero(int idSecadero) {
        this.repositorio.iniciarTransaccion();
        Secadero secadero = buscarSecadero(idSecadero);
        
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
