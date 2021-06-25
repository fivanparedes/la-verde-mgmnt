package modelo;

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

    public Productor() {
        this.setCuit("99-99999999-9");
        this.setApellidos("[APELLIDO]");
        this.setNombres("[NOMBRE]");
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

    public void setCuit(String c) {
        cuit = c;
    }

    public void setApellidos(String a) {
        apellidos = a;
    }

    public void setNombres(String n) {
        nombres = n;
    }
}
