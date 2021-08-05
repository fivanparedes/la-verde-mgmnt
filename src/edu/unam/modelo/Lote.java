package edu.unam.modelo;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idLote;

    private double[] punto1;
    private double[] punto2;
    private String puntos;

    @ManyToOne
    private Productor productor;

    @OneToMany(targetEntity = Cuadro.class, mappedBy = "lote")
    private List<Cuadro> cuadros = new ArrayList<>();

    public Lote() {
        inicio();
    }

    public Lote(double[] punto1, double[] punto2, Productor productor) {
        inicio();
        this.setPunto1(punto1);
        this.setPunto2(punto2);
        this.setProductor(productor);
    }

    public int getIdLote() {
        return this.idLote;
    }

    public double[] getPunto1() {
        return this.punto1;
    }

    public double[] getPunto2() {
        return this.punto2;
    }

    public Productor getProductor() {
        return productor;
    }

    public String getPuntos() {
        this.puntos = this.toString();
        return this.puntos;
    }
    public List<Cuadro> getCuadros() {
        return cuadros;
    }

    public void setPunto1(double[] punto1) {
        this.punto1 = punto1;
    }

    public void setPunto2(double[] punto2) {
        this.punto2 = punto2;
    }

    public void setPuntos(String s) {
        this.puntos = s;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    private void inicio() {
        this.punto1 = new double[2];
        this.punto2 = new double[2];
        this.puntos = this.toString();
    }
    @Override
    public final String toString() {
        return Integer.toString(this.getIdLote()) + ". (" + Double.toString(this.punto1[0]) + "," + Double.toString(this.punto1[1]) + ") ; (" + Double.toString(this.punto2[0]) + "," + Double.toString(this.punto2[1]) + ")";
    }
}