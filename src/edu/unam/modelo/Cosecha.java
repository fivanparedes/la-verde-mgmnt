package modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cosecha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCosecha;

    private LocalDate fecha;

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
}
