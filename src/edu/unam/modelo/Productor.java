package edu.unam.modelo;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Productor {

    /* Un ID es necesario en caso de que el CUIT/CUIL ingresado haya sido erroneo, el ID va a seguir permitiendo identificar
    de forma unica el registro. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProductor;

    private long cuit;
    private String apellidos;
    private String nombres;
  
    @OneToMany(targetEntity = Lote.class, mappedBy = "productor")
    private List<Lote> lotes = new ArrayList<>();

    public Productor() {
        this.setCuit(0);
        this.setApellidos("");
        this.setNombres("");
    }

    /* Todos los argumentos son requeridos para dar de alta un Productor */
<<<<<<< HEAD
    public Productor(String cuit, String apellidos, String nombres) {
        this.setCuit(cuit);
        this.setApellidos(apellidos);
        this.setNombres(nombres);
=======
    public Productor(long cuit, String apellidos, String n) {
        this.setCuit(cuit);
        this.setApellidos(apellidos);
        this.setNombres(n);
>>>>>>> origin/main
    }
    
    public long getCuit() {
        return this.cuit;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getNombres() {
        return this.nombres;
    }

    public List<Lote> getLotes() {
        return this.lotes;
    }

<<<<<<< HEAD
    public void setCuit(String c) {
        this.cuit = c;
    }

    public void setApellidos(String a) {
        this.apellidos = a;
    }

    public void setNombres(String n) {
        this.nombres = n;
    }
    public void setLotes(List<Lote> lt) {
        this.lotes = lt;
    }
=======
    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
>>>>>>> origin/main
}
