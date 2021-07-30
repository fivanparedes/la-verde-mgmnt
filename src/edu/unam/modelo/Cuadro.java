package edu.unam.modelo;

import java.util.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class Cuadro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCuadro;

    private String descripcion;

    @ManyToOne
    private Lote lote;

    @ManyToMany(targetEntity = Cosecha.class, mappedBy = "cuadros")
    private List<Cosecha> cosechas = new ArrayList<>();

    public Cuadro() {
        this.setDescripcion("");
    }

    public Cuadro(String descripcion, Lote lote) {
        this.setDescripcion(descripcion);
        this.setLote(lote);
    }

    public int getIdCuadro() {
        return this.idCuadro;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Lote getLote() {
        return this.lote;
    }

    public List<Cosecha> getCosechas() {
        return this.cosechas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }
}
