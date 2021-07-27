package edu.unam.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Entrega extends Cosecha {
    private float pesoEnt;
    private LocalDateTime tiempoEnt;

    public Entrega() {
        super();
        this.setPesoEnt(0.0f);
        this.setTiempoEnt(LocalDateTime.now());
    }

    public Entrega(float pesoEnt) {
        super();
        this.setPesoEnt(pesoEnt);
        this.setTiempoEnt(LocalDateTime.now());
    }

    public Entrega(float pesoEnt, LocalDate fecha) {
        super(fecha);
        this.setPesoEnt(pesoEnt);
        this.setTiempoEnt(LocalDateTime.now());
    }

    public Entrega(float pesoEnt, LocalDate fecha, LocalDateTime tiempoEnt) {
        super(fecha);
        this.setPesoEnt(pesoEnt);
        this.setTiempoEnt(tiempoEnt);
    }

    public Entrega(float pesoEnt, LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados, Secadero secadero) {
        super(fecha, cuadros, empleados, secadero);
        this.setPesoEnt(pesoEnt);
        this.setTiempoEnt(LocalDateTime.now());
    }

    public Entrega(float pesoEnt, LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados, Secadero secadero,
            LocalDateTime tiempoEnt) {
        super(fecha, cuadros, empleados, secadero);
        this.setPesoEnt(pesoEnt);
        this.setTiempoEnt(tiempoEnt);
    }

    public float getPesoEnt() {
        return this.pesoEnt;
    }

    public LocalDateTime getTiempoEnt() {
        return this.tiempoEnt;
    }

    public void setPesoEnt(float pesoEnt) {
        this.pesoEnt = pesoEnt;
    }

    public void setTiempoEnt(LocalDateTime tiempoEnt) {
        this.tiempoEnt = tiempoEnt;
    }
}
