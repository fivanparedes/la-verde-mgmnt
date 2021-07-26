package edu.unam.modelo;

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
@Inheritance(strategy = InheritanceType.JOINED)
public class Cosecha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCosecha;

    private LocalDate fecha;

    @OneToMany(mappedBy = "cosecha")
    private List<Cuadro> cuadros = new ArrayList<>();

    private Empleado empleado;
    private Secadero secadero;

    public void setSecadero(Secadero secadero) {
        this.secadero = secadero;
    }

    public Cosecha() {
        fecha = LocalDate.now();
    }

    public Cosecha(LocalDate f) {
        fecha = f;
    }

    public Cosecha(LocalDate fecha, List<Cuadro> cuadros, Empleado empleado, Secadero secadero) {
        this.fecha = fecha;
        this.cuadros = cuadros;
        this.empleado = empleado;
        this.secadero = secadero;
    }

    public Secadero getSecadero() {
        return secadero;
    }

    public int getIdCosecha() {
        return idCosecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<Cuadro> getCuadros() {
        return cuadros;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setIdCosecha(int id) {
        idCosecha = id;
    }

    public void setFecha(LocalDate f) {
        fecha = f;
    }

    public void setCuadros(List<Cuadro> cuadros) {
        this.cuadros = cuadros;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Cosecha [empleado=" + empleado + ", fecha=" + fecha + ", idCosecha=" + idCosecha + ", cuadros=" + cuadros
                + ", secadero=" + secadero + "]";
    }
}
