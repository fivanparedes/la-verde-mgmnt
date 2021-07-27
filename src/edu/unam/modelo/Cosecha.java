package edu.unam.modelo;

import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cosecha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCosecha;

    private LocalDate fecha;

    @ManyToMany(targetEntity = modelo.Cuadro.class)
    private List<Cuadro> cuadros = new ArrayList<>();

    @ManyToMany(targetEntity = modelo.Empleado.class)
    private List<Empleado> empleados;

    @ManyToOne(targetEntity = modelo.Secadero.class)
    private Secadero secadero;

    public Cosecha() {
        this.setFecha(LocalDate.now());
    }

    public Cosecha(LocalDate fecha) {
        this.setFecha(fecha);
    }

    public Cosecha(LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados, Secadero secadero) {
        this.setFecha(fecha);
        this.setCuadros(cuadros);
        this.setEmpleados(empleados);
        this.setSecadero(secadero);
    }

    public int getIdCosecha() {
        return this.idCosecha;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public List<Cuadro> getCuadros() {
        return this.cuadros;
    }

    public List<Empleado> getEmpleados() {
        return this.empleados;
    }

    public Secadero getSecadero() {
        return this.secadero;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setCuadros(List<Cuadro> cuadros) {
        this.cuadros = cuadros;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void setSecadero(Secadero secadero) {
        this.secadero = secadero;
    }

    /*
     * @Override public String toString() { return "Cosecha [empleado=" + empleado +
     * ", fecha=" + fecha + ", idCosecha=" + idCosecha + ", cuadros=" + cuadros +
     * ", secadero=" + secadero + "]"; }
     */
}
