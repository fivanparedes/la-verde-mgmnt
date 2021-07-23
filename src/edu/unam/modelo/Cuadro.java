package modelo;

import java.util.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Cuadro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCuadro;

    /* Estructura del array puntos:
    [0] = (a, b)
    [1] = (c, d)
    ... = ...
    [N] = (yn, zn)
    
    public int[][] getPuntos(): devuelve la tabla completa.
    Para obtener un par ordenado en particular, se puede recorrer linealmente ese array o, mas bien, utilizar:
    public int[] getUnPunto(int index, int[][] p): devuelve un punto (par ordenado -array de dos elementos-).
    index = indice de 0 a N de donde extraer el par ordenado.
    p = array en cuestion.
    Una vez obtenido el punto, es tan simple como operar con los valores de punto[0] y punto[1] como latitud y longitud. */
    private int[][] puntos;
    private Lote lote;
    @OneToMany(mappedBy = "cuadro")
    private List<Cosecha> cosechas = new ArrayList<>();

    public Cuadro(int[][] puntos, Lote lote, List<Cosecha> cosechas) {
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
    public int[] getUnPunto(int index, int[][] p) {
        return puntos[index];
    }
    public int[][] getPuntos() {
        return puntos;
    }
    public void setPuntos(int[][] puntos) {
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
    
}
