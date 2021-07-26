package edu.unam.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Registro extends Cosecha {
    private float pesoReg;

    public Registro() {
        super();
        this.setPesoReg(0.0f);
    }

    public Registro(float pesoReg, LocalDate fecha) {
        super(fecha);
        this.setPesoReg(pesoReg);
    }

    public float getPesoReg() {
        return this.pesoReg;
    }

    public void setPesoReg(float pesoReg) {
        this.pesoReg = pesoReg;
    }
}
