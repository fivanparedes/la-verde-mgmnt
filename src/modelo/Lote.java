package modelo;

import java.util.*;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Lote {
    public Cosecha getCosecha() {
        return cosecha;
    }
    public void setCosecha(Cosecha cosecha) {
        this.cosecha = cosecha;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idLote;

    private int[] punto1;
    private int[] punto2;
    private int[] initpoint = {0, 0};
    private Productor productor;
    private Cosecha cosecha;

    @OneToMany(mappedBy = "lote")
    private List<Cuadro> cuadros = new ArrayList<>();
    
    public Productor getProductor() {
        return productor;
    }
    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public Lote(int[] punto1, int[] punto2) {
        this.punto1 = punto1;
        this.punto2 = punto2;
        this.productor = new Productor();
    }
    public Lote() {
        this.punto1 = initpoint;
        this.punto2 = initpoint;
        this.productor = new Productor();
    }

    public Lote(int[] punto1, int[] punto2, Productor productor) {
        this.punto1 = punto1;
        this.punto2 = punto2;
        this.productor = productor;
    }
    
    public int getIdLote() {
        return idLote;
    }
    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }
    public int[] getPunto1() {
        return punto1;
    }
    public void setPunto1(int[] punto1) {
        this.punto1 = punto1;
    }
    public int[] getPunto2() {
        return punto2;
    }
    public void setPunto2(int[] punto2) {
        this.punto2 = punto2;
    }
    
    public void setPunto1(int p1, int p2) {
        this.punto1[0] = p1;
        this.punto1[1] = p2;
    }

    public void setPunto2(int p1, int p2) {
        this.punto2[0] = p1;
        this.punto2[1] = p2;
    }
}