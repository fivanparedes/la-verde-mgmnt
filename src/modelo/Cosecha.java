package modelo;

import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cosecha {
   

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCosecha;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    private LocalDate fecha;

    @OneToMany(mappedBy = "cosecha")
    private List<Lote> lotes = new ArrayList<>();

    private Empleado empleado;
    private Secadero secadero;

    public Secadero getSecadero() {
        return secadero;
    }

    public void setSecadero(Secadero secadero) {
        this.secadero = secadero;
    }

    public Cosecha() {
        fecha = LocalDate.now();
    }

    public Cosecha(LocalDate f) {
        fecha = f;
    }

    public int getIdCosecha() {
        return idCosecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setIdCosecha(int id) {
        idCosecha = id;
    }

    public void setFecha(LocalDate f) {
        fecha = f;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }
}
