package edu.unam.modelo;

import java.util.*;
import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Registro extends Cosecha {
    private float pesoReg;
    private LocalDate tiempoReg;

    public Registro() {
        super();
        this.setPesoReg(0.0f);
        this.setTiempoReg(LocalDate.now());
    }

    public Registro(float pesoReg) {
        super();
        this.setPesoReg(pesoReg);
        this.setTiempoReg(LocalDate.now());
    }

    public Registro(float pesoReg, LocalDate fecha) {
        super(fecha);
        this.setPesoReg(pesoReg);
        this.setTiempoReg(LocalDate.now());
    }

    public Registro(float pesoReg, LocalDate fecha, LocalDate tiempoReg) {
        super(fecha);
        this.setPesoReg(pesoReg);
        this.setTiempoReg(tiempoReg);
    }

    public Registro(float pesoEnt, LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados, Secadero secadero) {
        super(fecha, cuadros, empleados, secadero);
        this.setPesoReg(pesoEnt);
        this.setTiempoReg(LocalDate.now());
    }

    public Registro(float pesoEnt, LocalDate fecha, List<Cuadro> cuadros, List<Empleado> empleados, Secadero secadero,
            LocalDate tiempoReg) {
        super(fecha, cuadros, empleados, secadero);
        this.setPesoReg(pesoEnt);
        this.setTiempoReg(tiempoReg);
    }

    public float getPesoReg() {
        return this.pesoReg;
    }

    public LocalDate getTiempoReg() {
        return this.tiempoReg;
    }

    public void setPesoReg(float pesoReg) {
        this.pesoReg = pesoReg;
    }

    public void setTiempoReg(LocalDate tiempoReg) {
        this.tiempoReg = tiempoReg;
    }
}
