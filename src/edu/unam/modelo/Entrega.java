package edu.unam.modelo;

import java.util.*;
import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Entrega extends Cosecha {
    private float pesoEnt;
    private LocalDate tiempoEnt;

    public Entrega() {
        super();
        this.setPesoEnt(0.0f);
        this.setTiempoEnt(LocalDate.now());
    }

    public Entrega(float pesoEnt) {
        super();
        this.setPesoEnt(pesoEnt);
        this.setTiempoEnt(LocalDate.now());
    }

    public Entrega(float pesoEnt, LocalDate fecha) {
        super(fecha);
        this.setPesoEnt(pesoEnt);
        this.setTiempoEnt(LocalDate.now());
    }

    public Entrega(float pesoEnt, LocalDate fecha, LocalDate tiempoEnt) {
        super(fecha);
        this.setPesoEnt(pesoEnt);
        this.setTiempoEnt(tiempoEnt);
    }

    public Entrega(float pesoEnt, LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados, Secadero secadero) {
        super(fecha, cuadros, empleados, secadero);
        this.setPesoEnt(pesoEnt);
        this.setTiempoEnt(LocalDate.now());
    }

    public Entrega(float pesoEnt, LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados, Secadero secadero,
            LocalDate tiempoEnt) {
        super(fecha, cuadros, empleados, secadero);
        this.setPesoEnt(pesoEnt);
        this.setTiempoEnt(tiempoEnt);
    }

    public float getPesoEnt() {
        return this.pesoEnt;
    }

    public LocalDate getTiempoEnt() {
        return this.tiempoEnt;
    }

    public void setPesoEnt(float pesoEnt) {
        this.pesoEnt = pesoEnt;
    }

    public void setTiempoEnt(LocalDate tiempoEnt) {
        this.tiempoEnt = tiempoEnt;
    }
}
