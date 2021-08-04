package edu.unam.modelo;

import java.util.*;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Secadero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idSecadero;

    private long cuit;
    private String razonSocial;

    @OneToMany(targetEntity = Cosecha.class, mappedBy = "secadero")
    private List<Cosecha> cosechas;

    public Secadero() {
        this.setCuit(0);
        this.setRazonSocial("");;
        this.cosechas = new ArrayList<>();
    }

    public Secadero (long cuit, String razonSocial) {
        this.setCuit(cuit);
        this.setRazonSocial(razonSocial);
        this.cosechas = new ArrayList<>();
    }

    public Secadero(long cuit, String razonSocial, List<Cosecha> cosechas) {
        this.setCuit(cuit);
        this.setRazonSocial(razonSocial);
        this.setCosechas(cosechas);
    }

    public int getIdSecadero() {
        return this.idSecadero;
    }

    public long getCuit() {
        return this.cuit;
    }

    public String getRazonSocial() {
        return this.razonSocial;
    }

    public List<Cosecha> getCosechas() {
        return this.cosechas;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setCosechas(List<Cosecha> cosechas) {
        this.cosechas = cosechas;
    }
}
