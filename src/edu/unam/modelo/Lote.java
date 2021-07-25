package modelo;

import java.util.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idLote;

    private double[] punto1;
    private double[] punto2;

    @ManyToOne
    private Productor productor;

    @OneToMany(mappedBy = "lote")
    private List<Cuadro> cuadros = new ArrayList<>();

    public Lote() {
        inicio();
    }

    public Lote(double[] punto1, double[] punto2) {
        inicio();
        this.setPunto1(punto1);
        this.setPunto2(punto2);
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

    public List<Cuadro> getCuadros() {
        return cuadros;
    }

    public void setPunto1(double[] punto1) {
        this.punto1 = punto1;
    }

    public void setPunto2(double[] punto2) {
        this.punto2 = punto2;
    }

    private void inicio() {
        this.punto1 = new double[2];
        this.punto2 = new double[2];
    }
}