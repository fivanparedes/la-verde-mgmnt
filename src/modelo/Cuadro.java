package modelo;

import java.util.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Cuadro {
    public Cuadro(int[] puntos, Lote lote, List<Cosecha> cosechas) {
        this.puntos = puntos;
        this.lote = lote;
        this.cosechas = cosechas;
    }
    public int getIdCuadro() {
        return idCuadro;
    }
    public void setIdCuadro(int idCuadro) {
        this.idCuadro = idCuadro;
    }
    public int[] getPuntos() {
        return puntos;
    }
    public void setPuntos(int[] puntos) {
        this.puntos = puntos;
    }
    public Lote getLote() {
        return lote;
    }
    public void setLote(Lote lote) {
        this.lote = lote;
    }
    public List<Cosecha> getCosechas() {
        return cosechas;
    }
    public void setCosechas(List<Cosecha> cosechas) {
        this.cosechas = cosechas;
    }
    public Cuadro() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCuadro;

    private int[] puntos;
    private Lote lote;
    @OneToMany(mappedBy = "cuadro")
    private List<Cosecha> cosechas = new ArrayList<>();
}
