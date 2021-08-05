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

    private Long cuit;
    private String apellidos;
    private String nombres;
  
    @OneToMany(targetEntity = Lote.class, mappedBy = "productor")
    private List<Lote> lotes = new ArrayList<>();

    public Productor() {
        this.setCuit((long) 0);
        this.setApellidos("");
        this.setNombres("");
    }

    /* Todos los argumentos son requeridos para dar de alta un Productor */
    public Productor(Long cuit, String apellidos, String nombres) {
        this.setCuit(cuit);
        this.setApellidos(apellidos);
        this.setNombres(nombres);
    }
    
    public int getIdProductor() {
        return idProductor;
    }

    public void setIdProductor(int idProductor) {
        this.idProductor = idProductor;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    public Long getCuit() {
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

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public final String toString() {
        return Integer.toString(this.getIdProductor()) + ". " + Long.toString(this.getCuit()) + " " + this.getApellidos() + ", " + this.getNombres();
    }
}
