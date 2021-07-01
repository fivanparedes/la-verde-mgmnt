package modelo;

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

    private String cuit;
    private String apellidos;
    private String nombres;

    @OneToMany(mappedBy = "productor")
    private List<Lote> lotes = new ArrayList<>();

    public Productor() {
        this.setCuit("[CUIT IND.]");
        this.setApellidos("[APELLIDO IND.]");
        this.setNombres("[NOMBRE IND.]");
    }

    /* Todos los argumentos son requeridos para dar de alta un Productor */
    public Productor(String c, String a, String n) {
        this.setCuit(c);
        this.setApellidos(a);
        this.setNombres(n);
    }
    
    public String getCuit() {
        return cuit;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setCuit(String c) {
        cuit = c;
    }

    public void setApellidos(String a) {
        apellidos = a;
    }

    public void setNombres(String n) {
        nombres = n;
    }

    public void setLotes(List<Lote> lt) {
        lotes = lt;
    }
}
