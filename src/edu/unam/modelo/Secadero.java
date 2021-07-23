package modelo;

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

    @OneToMany(mappedBy = "secadero")
    private List<Cosecha> cosechas = new ArrayList<>();

    public Secadero() {
        this.cuit = 119999911;
        this.razonSocial = "[RAZON SOCIAL IND.]";
    }

    public int getIdSecadero() {
        return idSecadero;
    }

    public void setIdSecadero(int idSecadero) {
        this.idSecadero = idSecadero;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public List<Cosecha> getCosechas() {
        return cosechas;
    }

    public void setCosechas(List<Cosecha> cosechas) {
        this.cosechas = cosechas;
    }

    public Secadero(long cuit, String razonSocial, List<Cosecha> cosechas) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.cosechas = cosechas;
    }

   
    
}
