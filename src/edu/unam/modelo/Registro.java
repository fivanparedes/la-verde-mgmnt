package edu.unam.modelo;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Registro extends Cosecha {
    private float pesoReg;
    private LocalDateTime tiempoReg;

    public Registro() {
        super();
        this.setPesoReg(0.0f);
        this.setTiempoReg(LocalDateTime.now());
    }

    public Registro(float pesoReg) {
        super();
        this.setPesoReg(pesoReg);
        this.setTiempoReg(LocalDateTime.now());
    }

    public Registro(float pesoReg, LocalDate fecha) {
        super(fecha);
        this.setPesoReg(pesoReg);
        this.setTiempoReg(LocalDateTime.now());
    }

    public Registro(float pesoReg, LocalDate fecha, LocalDateTime tiempoReg) {
        super(fecha);
        this.setPesoReg(pesoReg);
        this.setTiempoReg(tiempoReg);
    }

    public Registro(float pesoEnt, LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados, Secadero secadero) {
        super(fecha, cuadros, empleados, secadero);
        this.setPesoReg(pesoEnt);
        this.setTiempoReg(LocalDateTime.now());
    }

    public Registro(float pesoEnt, LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados, Secadero secadero,
            LocalDateTime tiempoReg) {
        super(fecha, cuadros, empleados, secadero);
        this.setPesoReg(pesoEnt);
        this.setTiempoReg(tiempoReg);
    }

    public float getPesoReg() {
        return this.pesoReg;
    }

    public LocalDateTime getTiempoReg() {
        return this.tiempoReg;
    }

    public void setPesoReg(float pesoReg) {
        this.pesoReg = pesoReg;
    }

    public void setTiempoReg(LocalDateTime tiempoReg) {
        this.tiempoReg = tiempoReg;
    }
}
